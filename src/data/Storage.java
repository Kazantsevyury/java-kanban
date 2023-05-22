package data;

import java.util.ArrayList;
import java.util.HashMap;

public class Storage {
    private HashMap<Integer, Task> tasks;
    private HashMap<Integer, SubTask> subTasks;
    private HashMap<Integer, Epic> epics;

    public Storage() {
        tasks = new HashMap<>();
        subTasks = new HashMap<>();
        epics = new HashMap<>();
    }

    public void addTask(Task task) {
        tasks.put(task.getTaskId(), task);
    }

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
    public void addEpic(Epic epic)                       {
        epics.put(epic.getTaskId(), epic);
        updateEpicStatus();
    }
    public Task getTask(int taskId) {
        return tasks.get(taskId);
    }
    public SubTask getSubTask(int subTaskId) {
        return subTasks.get(subTaskId);
    }
    public Epic getEpic(int epicId) {
        return epics.get(epicId);
    }

    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> allTasks = new ArrayList<>();
        allTasks.addAll(tasks.values());
        return allTasks;
    }
    public ArrayList<Task> getAllEpics() {
        ArrayList<Task> allTasks = new ArrayList<>();
        allTasks.addAll(epics.values());
        return allTasks;
    }
    public ArrayList<Task> getAllSubTasks() {
        ArrayList<Task> allTasks = new ArrayList<>();
        allTasks.addAll(subTasks.values());
        return allTasks;
    }
    public void clearAllTasks() {
        tasks.clear();
        updateEpicStatus();

    }
    public void clearAllEpics() {
        epics.clear();
        subTasks.clear();
    }
    public void clearAllSubTasks() {
        subTasks.clear();
        updateEpicStatus();
    }
    public void removeTask(int taskId) {
        tasks.remove(taskId);
    }
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
        epics.remove(epicId);
        updateEpicStatus();

    }
    public void removeSubTask(int subTaskId) {
        subTasks.remove(subTaskId);
        updateEpicStatus();

    }

    public void updateEpicStatus() {
        for (Epic epic : epics.values()) {
            ArrayList<Integer> subTaskIds = epic.getSubTasks();
            int newSubTaskCount = 0;
            int doneSubTaskCount = 0;

            for (int subTaskId : subTaskIds) {
                SubTask subTask = subTasks.get(subTaskId);
                if (subTask != null) {
                    if (subTask.getStatus().equals("NEW")) {
                        newSubTaskCount++;
                    } else if (subTask.getStatus().equals("DONE")) {
                        doneSubTaskCount++;
                    }
                }
            }

            if ((newSubTaskCount > 0)&&(doneSubTaskCount != 0) ) {
                epic.setStatus("IN_PROGRESS");
            } else if (doneSubTaskCount == subTaskIds.size()) {
                epic.setStatus("DONE");
            } else {
                epic.setStatus("NEW");
            }
        }
    }

}


