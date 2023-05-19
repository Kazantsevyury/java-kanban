package data;

import logic.IdGenerator;

public class Task {
    private int taskId;
    private String title;
    private String description;
    private String status;

    public Task(String title, String description) {
        this.taskId = IdGenerator.getNextId();
        this.title = title;
        this.description = description;
        this.status = "NEW";
    }

    public int getTaskId() {
        return taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
