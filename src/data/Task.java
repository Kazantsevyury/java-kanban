package data;

import enums.Status;
import enums.TaskTypes;
import utilities.IdGenerator;

public class Task {
    protected int taskId;
    private String title;
    private String description;
    private Status status;
    private TaskTypes taskTypes = TaskTypes.TASK;



    public Task(String title, String description) {
        this.taskId = IdGenerator.getNextId();
        this.title = title;
        this.description = description;
        this.status = Status.NEW;
    }

    public Task(int taskId, String title, String description, Status status) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.status = status;
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
    public TaskTypes getTaskTypes() {
        return taskTypes;
    }
    public String toCSV(){
        StringBuffer sb = new StringBuffer();
        sb.append(taskId);
        sb.append(",");
        sb.append(title);
        sb.append(",");
        sb.append(description);
        sb.append(",");
        sb.append(status);
        sb.append(",");
        sb.append(getTaskTypes());

        return sb.toString();
    }
}
