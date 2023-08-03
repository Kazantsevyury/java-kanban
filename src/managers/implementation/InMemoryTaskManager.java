package managers.implementation;

import data.Task;
import data.SubTask;
import data.Epic;
import enums.Status;
import managers.HistoryManager;
import managers.Managers;
import managers.TaskManager;
import utilities.FieldModifier;
import utilities.IdGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeSet;

public class InMemoryTaskManager implements TaskManager {
    private final IdGenerator idGenerator;
    private final HistoryManager historyManager;
    private final FieldModifier fieldModifier;
    private final HashMap<Integer, Task> tasks;
    private final HashMap<Integer, SubTask> subTasks;
    private final HashMap<Integer, Epic> epics;
    private final TreeSet<Task> prioritizedTasks;

    public InMemoryTaskManager() {
        idGenerator = new IdGenerator();
        historyManager = Managers.getDefaultHistory();
        tasks = new HashMap<>();
        subTasks = new HashMap<>();
        epics = new HashMap<>();
        prioritizedTasks = new TreeSet<>(Comparator.comparing(Task::getStartTime, Comparator.nullsLast(Comparator.naturalOrder())));
        this.fieldModifier = new FieldModifier(this);

    }
    public TreeSet<Task> getPrioritizedTasks() {
        return new TreeSet<>(prioritizedTasks);
    }

    private void addAllTasksToPrioritizedSet() {
        if (tasks != null) {
            prioritizedTasks.addAll(tasks.values());
        }
        if (subTasks != null) {
            prioritizedTasks.addAll(subTasks.values());
        }
        if (epics != null) {
            prioritizedTasks.addAll(epics.values());
        }
    }
    @Override
    public HistoryManager getHistoryManager() {
        return historyManager;
    }
    @Override
    public Task getAnyTaskById(int taskId) {
        Task task = tasks.get(taskId);
        if (task != null) {
            historyManager.add(task);
            return task;
        }

        task = subTasks.get(taskId);
        if (task != null) {
            historyManager.add(task);
            return task;
        }

        task = epics.get(taskId);
        if (task != null) {
            historyManager.add(task);
            return task;
        }

        System.out.println("Задачи с идентификатором " + taskId + " не существует.");
        return null;
    }

    @Override
    public void addTask (Task task) {
        if (checkForTimeIntersections(task)) {
            System.out.println("Внимание: Задача пересекается с существующими задачами.");
        }

        tasks.put(task.getTaskId(), task);
        prioritizedTasks.add(task);
    }
    @Override
    public void addSubTask(SubTask subTask) {
        if (checkForTimeIntersections(subTask)) {
            System.out.println("Внимание: Подзадача пересекается с существующими задачами.");
        }

        subTasks.put(subTask.getTaskId(), subTask);

        int parentEpicID = subTask.getParentEpicId();
        Epic parentEpic = epics.get(parentEpicID);
        updateEpicDurationAndStartTimeAndEndTime();

        if (parentEpic != null) {
            ArrayList<Integer> parentEpicArrayList = parentEpic.getSubTasks();
            if (!parentEpicArrayList.contains(subTask.getTaskId())) {
                parentEpicArrayList.add(subTask.getTaskId());
                prioritizedTasks.add(subTask);
            }
        }
        updateEpicStatus();
    }

    @Override
    public void addEpic(Epic epic) {
        if (checkForTimeIntersections(epic)) {
            System.out.println("Внимание: Эпик пересекается с существующими задачами.");
        }
        epics.put(epic.getTaskId(), epic);
        updateEpicDurationAndStartTimeAndEndTime();
        updateEpicStatus();
        prioritizedTasks.add(epic);
    }

    @Override
    public FieldModifier getFieldModifier() {
        return fieldModifier;
    }

    @Override
    public Task getTask(int taskId) {
        Task task = tasks.get(taskId);
        if (task != null) {
            historyManager.add(task);
        }
        return task;
    }

    @Override
    public SubTask getSubTask(int subTaskId) {
        SubTask subTask = subTasks.get(subTaskId);
        if (subTask != null) {
            historyManager.add(subTask);
        }
        return subTask;
    }

    @Override
    public Epic getEpic(int epicId) {
        Epic epic = epics.get(epicId);
        if (epic != null) {
            historyManager.add(epic);
        }
        return epic;
    }

    @Override
    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> allTasks = new ArrayList<>();
        allTasks.addAll(tasks.values());
        return allTasks;
    }
    @Override
    public ArrayList<Epic> getAllEpics() {
        ArrayList<Epic> allEpics = new ArrayList<>();
        allEpics.addAll(epics.values());
        return allEpics;
    }
    @Override
    public ArrayList<SubTask> getAllSubTasks() {
        ArrayList<SubTask> allSubTasks = new ArrayList<>();
        allSubTasks.addAll(subTasks.values());
        return allSubTasks;
    }

    @Override
    public void clearAllTasks() {
        tasks.clear();
        updateEpicStatus();
    }
    @Override
    public void clearAllEpics() {
        epics.clear();
        subTasks.clear();
    }
    @Override
    public void clearAllSubTasks() {
        subTasks.clear();
        updateEpicStatus();
    }

    @Override
    public void removeTask(int taskId) {
        Task taskToRemove = tasks.get(taskId);
        if (taskToRemove != null) {
            tasks.remove(taskId);
            historyManager.remove(taskId);
        }
    }
    @Override
    public void removeEpic(int epicId) {
        ArrayList<Integer> subTaskIdsToRemove = new ArrayList<>();

        for (SubTask subTask : subTasks.values()) {
            if (subTask.getParentEpicId() == epicId) {
                subTaskIdsToRemove.add(subTask.getTaskId());
            }
        }
        for (int subTaskId : subTaskIdsToRemove) {
            subTasks.remove(subTaskId);
        }
        for (int subTaskId : subTaskIdsToRemove) {
            subTasks.remove(subTaskId);
            historyManager.remove(subTaskId);
        }
        epics.remove(epicId);

        historyManager.remove(epicId);

        updateEpicStatus();
    }
    @Override
    public void removeSubTask(int subTaskId) {
        subTasks.remove(subTaskId);
        historyManager.remove(subTaskId);
        updateEpicStatus();
    }

    @Override
    public void updateEpicStatus() {
        for (Epic epic : epics.values()) {
            ArrayList<Integer> subTaskIds = epic.getSubTasks();
            int newSubTaskCount = 0;
            int doneSubTaskCount = 0;

            for (int subTaskId : subTaskIds) {
                SubTask subTask = subTasks.get(subTaskId);
                if (subTask != null) {
                    if (subTask.getStatus() == Status.NEW) {
                        newSubTaskCount++;
                    } else if (subTask.getStatus() == Status.DONE) {
                        doneSubTaskCount++;
                    }
                }
            }

            if (newSubTaskCount > 0 && doneSubTaskCount != 0) {
                epic.setStatus(Status.IN_PROGRESS);
            } else if (doneSubTaskCount == subTaskIds.size()) {
                epic.setStatus(Status.DONE);
            } else {
                epic.setStatus(Status.NEW);
            }
        }
    }
    @Override
    public void updateEpicDurationAndStartTimeAndEndTime() {
        for (Epic epic : epics.values()) {
            ArrayList<Integer> subTaskIds = epic.getSubTasks();

            if (subTaskIds == null || subTaskIds.isEmpty()) {
                continue;
            }
            int totalDuration = 0;
            LocalDate earliestStartDate = null;
            LocalDateTime latestEndDate = null;

            for (int subTaskId : subTaskIds) {
                SubTask subTask = subTasks.get(subTaskId);
                if (subTask != null) {
                    totalDuration += subTask.getDuration();

                    LocalDate subTaskStartDate = subTask.getStartTime();
                    if (earliestStartDate == null || subTaskStartDate.isBefore(earliestStartDate)) {
                        earliestStartDate = subTaskStartDate;
                    }

                    LocalDateTime subTaskEndDate = subTask.getEndTime();
                    if (latestEndDate == null || subTaskEndDate.isAfter(latestEndDate)) {
                        latestEndDate = subTaskEndDate;
                    }
                }
            }

            epic.setDuration(totalDuration);
            epic.setStartTime(earliestStartDate);
            epic.setEndTime(latestEndDate);
        }
    }

    public boolean checkForTimeIntersections(Task task) {
        for (Task otherTask : prioritizedTasks) {
            if (task.getTaskId() != otherTask.getTaskId()) {
                if (hasTimeIntersection(task, otherTask)) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean hasTimeIntersection(Task task1, Task task2) {
        LocalDate rowStartTime1 = task1.getStartTime();
        LocalDate rowStartTime2 = task2.getStartTime();

        if (rowStartTime1 != null && rowStartTime2 != null) {
            LocalDateTime startTime1 = task1.getStartTime().atTime(12, 0);
            LocalDateTime startTime2 = task2.getStartTime().atTime(12, 0);
            LocalDateTime endTime1 = task1.getEndTime();
            LocalDateTime endTime2 = task2.getEndTime();

            if (startTime1 != null && endTime1 != null && startTime2 != null && endTime2 != null) {
                if (startTime1.isBefore(endTime2) && startTime2.isBefore(endTime1)) {
                    return true;

                }
            }
        }
        return false;
    }
}
