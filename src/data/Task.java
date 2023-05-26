package data;

import logic.IdGenerator;

public class Task {
    protected int taskId;
    private String title;
    private String description;
    private Status status;

    public Task(String title, String description) {
        this.taskId = IdGenerator.getNextId();
        this.title = title;
        this.description = description;
        this.status = Status.NEW;
    }

    public int getTaskId() {
        return taskId;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public Status getStatus() {
        return status;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task ID: " + taskId + "\nTitle: " + title + "\nDescription: " + description + "\nStatus: " + status;
    }
}
