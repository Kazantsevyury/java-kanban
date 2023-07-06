package managers.implementation;

import data.Task;
import data.SubTask;
import data.Epic;
import enums.Status;
import managers.HistoryManager;
import managers.Managers;
import managers.TaskManager;

import java.util.ArrayList;
import java.util.HashMap;

public class InMemoryTaskManager implements TaskManager {
    private HistoryManager historyManager;
    private HashMap<Integer, Task> tasks;
    private HashMap<Integer, SubTask> subTasks;
    private HashMap<Integer, Epic> epics;

    public InMemoryTaskManager() {

        historyManager = Managers.getDefaultHistory();
        tasks = new HashMap<>();
        subTasks = new HashMap<>();
        epics = new HashMap<>();
    }

    public HistoryManager getHistoryManager() {
        return historyManager;
    }

    @Override
    public void addTask(Task task) {
        tasks.put(task.getTaskId(), task);
    }
    @Override
    public void addSubTask(SubTask subTask) {
        subTasks.put(subTask.getTaskId(), subTask);

        int parentEpicID = subTask.getEpicId();
        Epic parentEpic = epics.get(parentEpicID);
        if (parentEpic != null) {
            ArrayList<Integer> parentEpicArrayList = parentEpic.getSubTasks();
            if (!parentEpicArrayList.contains(subTask.getTaskId())) {
                parentEpicArrayList.add(subTask.getTaskId());
            }
        } else {
            System.out.println("Parent Epic not found for SubTask: " + subTask.getTaskId());
        }
        updateEpicStatus();
    }
    @Override
    public void addEpic(Epic epic) {
        epics.put(epic.getTaskId(), epic);
        updateEpicStatus();
    }

    @Override
    public Task getTask(int taskId) {
        historyManager.add(tasks.get(taskId));
        return tasks.get(taskId);
    }
    @Override
    public SubTask getSubTask(int subTaskId) {
        historyManager.add(subTasks.get(subTaskId));
        return subTasks.get(subTaskId);
    }
    @Override
    public Epic getEpic(int epicId) {
        historyManager.add(epics.get(epicId));
        return epics.get(epicId);
    }

    @Override
    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> allTasks = new ArrayList<>();
        allTasks.addAll(tasks.values());
        return allTasks;
    }
    @Override
    public ArrayList<Task> getAllEpics() {
        ArrayList<Task> allTasks = new ArrayList<>();
        allTasks.addAll(epics.values());
        return allTasks;
    }
    @Override
    public ArrayList<Task> getAllSubTasks() {
        ArrayList<Task> allTasks = new ArrayList<>();
        allTasks.addAll(subTasks.values());
        return allTasks;
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
        tasks.remove(taskId);
        historyManager.remove(taskId);
    }
    @Override
    public void removeEpic(int epicId) {
        ArrayList<Integer> subTaskIdsToRemove = new ArrayList<>();

        // Находим все подзадачи с заданным epicId и добавляем их ID в список subTaskIdsToRemove
        for (SubTask subTask : subTasks.values()) {
            if (subTask.getEpicId() == epicId) {
                subTaskIdsToRemove.add(subTask.getTaskId());
            }
        }
        // Удаляем найденные подзадачи из subTasks
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
}