package data;

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

    public Task getTask(int taskId) {
        return tasks.get(taskId);
    }

    public void addSubTask(SubTask subTask) {
        subTasks.put(subTask.getTaskId(), subTask);
    }

    public SubTask getSubTask(int subTaskId) {
        return subTasks.get(subTaskId);
    }

    public void addEpic(Epic epic) {
        epics.put(epic.getTaskId(), epic);
    }

    public Epic getEpic(int epicId) {
        return epics.get(epicId);
    }
}
