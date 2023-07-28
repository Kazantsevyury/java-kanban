package data;

import enums.Status;
import enums.TaskTypes;
import utilities.IdGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Task {
    protected int taskId;
    private String title;
    private String description;
    private Status status;
    private TaskTypes taskTypes = TaskTypes.TASK;
    private LocalDate startTime;

    private int duration;
    public Task(String title, String description ) {
        this.taskId = IdGenerator.getNextId();
        this.title = title;
        this.description = description;
        this.status = Status.NEW;
        this.duration = 240;
        this.startTime = LocalDate.now();
    }
    public Task(String title, String description, Status status, int duration, LocalDate startTime ) {
        this.taskId = IdGenerator.getNextId();
        this.title = title;
        this.description = description;
        this.status = status;
        this.duration = duration;
        this.startTime = startTime ;
    }
    public Task(String title, String description, int duration, LocalDate startTime) {
        this.taskId = IdGenerator.getNextId();
        this.title = title;
        this.description = description;
        this.status = Status.NEW;
        this.duration = duration;
        this.startTime = startTime;
    }

    public Task(String title, String description, Status status, TaskTypes taskTypes, LocalDate startTime, int duration) {
        this.taskId = IdGenerator.getNextId();
        this.title = title;
        this.description = description;
        this.status = status;
        this.taskTypes = taskTypes;
        this.startTime = startTime;
        this.duration = duration;
    }

    public Task(int taskId, String title, String description, Status status, int duration, LocalDate startTime) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.status = status;
        this.duration = duration;
        this.startTime = startTime;
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
    public LocalDate getStartTime() {
        return startTime;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
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


    public LocalDateTime getEndTime() {
        if (startTime != null && duration > 0) {
            LocalDateTime startDateTime = startTime.atStartOfDay();
            LocalDateTime endDateTime = startDateTime.plusMinutes(duration);
            return endDateTime;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Task ID: " + taskId + "\nTitle: " + title + "\nDescription: " + description + "\nStatus: " + status
                + "\nDuration: " + duration + " minutes" + "\nStart Time: " + startTime;
    }

    public TaskTypes getTaskTypes() {
        return taskTypes;
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
}




