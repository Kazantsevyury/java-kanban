package data;

import enums.Status;
import enums.TaskTypes;
import utilities.IdGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class Task {
    protected int taskId;
    private String title;
    private String description;
    private Status status;
    private TaskTypes taskTypes = TaskTypes.TASK;
    private LocalDateTime startTime;

    private int duration;
    public Task(String title, String description ) {
        this.taskId = IdGenerator.getNextId();
        this.title = title;
        this.description = description;
        this.status = Status.NEW;
        this.duration = 240;
        this.startTime = LocalDateTime.now();
    }

    public Task(int taskId, String title, String description) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
    }

    public Task(int taskId, String title, String description, Status status) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public Task(String title, String description, int duration, String dateAsString ) {
        this.taskId = IdGenerator.getNextId();
        this.title = title;
        this.description = description;
        this.status = Status.NEW;
        this.duration = duration;
        this.startTime = LocalDateTime.parse(dateAsString);
    }

    public Task(String title, String description, Status status, int duration, LocalDate startTime ) {
        this.taskId = IdGenerator.getNextId();
        this.title = title;
        this.description = description;
        this.status = status;
        this.duration = duration;
        this.startTime = startTime.atStartOfDay();    }

    public Task(String title, String description, int duration, LocalDate startTime) {
        this.taskId = IdGenerator.getNextId();
        this.title = title;
        this.description = description;
        this.status = Status.NEW;
        this.duration = duration;
        this.startTime = startTime.atStartOfDay();    }

    public Task(String title, String description, Status status, TaskTypes taskTypes, LocalDate startTime, int duration) {
        this.taskId = IdGenerator.getNextId();
        this.title = title;
        this.description = description;
        this.status = status;
        this.taskTypes = taskTypes;
        this.startTime = startTime.atStartOfDay();
        this.duration = duration;
    }

    public Task(int taskId, String title, String description, Status status, int duration, LocalDate startTime) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.status = status;
        this.duration = duration;
        this.startTime = startTime.atStartOfDay();    }

    public Task(String title, String description, Status status, TaskTypes taskTypes, int duration,String dateAsString) {
        this.taskId = IdGenerator.getNextId();
        this.title = title;
        this.description = description;
        this.status = status;
        this.taskTypes = taskTypes;
        this.duration = duration;
        this.startTime = LocalDateTime.parse(dateAsString);
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

    public int getDuration() {
        return duration;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        if (startTime != null && duration > 0) {
            LocalDateTime startDateTime = startTime;
            LocalDateTime endDateTime = startDateTime.plusMinutes(duration);
            return endDateTime;
        }
        return null;
    }
    public TaskTypes getTaskTypes() {
        return taskTypes;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setStartTime(String dateAsString) {
        this.startTime = LocalDate.parse(dateAsString).atStartOfDay();
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
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

    public String toCSV() {
        StringBuilder sb = new StringBuilder();
        sb.append(taskId);
        sb.append(",");
        sb.append(title);
        sb.append(",");
        sb.append(description);
        sb.append(",");
        sb.append(status);
        sb.append(",");
        sb.append(startTime);
        sb.append(",");
        sb.append(duration);
        sb.append(",");
        sb.append(getTaskTypes());

        return sb.toString();
    }

    @Override
    public String toString() {
        return "Task ID: " + taskId + "\nTitle: " + title + "\nDescription: " + description + "\nStatus: " + status
                + "\nDuration: " + duration + " minutes" + "\nStart Time: " + startTime;
    }
}