package data;

import java.util.HashMap;

public class Epic extends Task {
    private HashMap<Integer, SubTask> subTasks;

    public Epic(String title, String description) {
        super(title, description);
        this.subTasks = new HashMap<>();
    }

    public void addSubTask(SubTask subTask) {
        subTasks.put(subTask.getTaskId(), subTask);
    }

    public HashMap<Integer, SubTask> getSubTasks() {
        return subTasks;
    }
}