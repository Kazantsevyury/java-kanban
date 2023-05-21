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
        ArrayList<Integer> parentEpicArrayList = parentEpic.getSubTasks();
        parentEpicArrayList.add(subTask.getTaskId());
    }
    public void addEpic(Epic epic) {
        epics.put(epic.getTaskId(), epic);
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
    }
    public void clearAllEpics() {
        epics.clear();
    }
    public void clearAllSubTasks() {
        subTasks.clear();
    }
    public void removeTask(int taskId) {
        tasks.remove(taskId);
    }
    public void removeEpic(int epicId) {

        epics.remove(epicId);

    }
    public void removeSubTask(int subTaskId) {
        subTasks.remove(subTaskId);
    }





}


