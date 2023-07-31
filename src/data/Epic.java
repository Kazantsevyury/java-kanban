package data;

import enums.Status;
import enums.TaskTypes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Integer> subTasks;
    private LocalDateTime endTime;

    public Epic(int taskId, String title, String description, Status status, int duration, LocalDate startTime) {
        super(taskId,title,description,status,duration,startTime);
        this.subTasks = new ArrayList<Integer>();
    }

    @Override
    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setTaskTypes(TaskTypes taskTypes) {
        this.taskTypes = taskTypes;
    }

    private TaskTypes taskTypes = TaskTypes.EPIC;

    public Epic(String title, String description ) {
        super(title, description);
        this.subTasks = new ArrayList<>();
    }

    public Epic(int taskId, String title, String description, Status status, ArrayList<Integer> subTasks) {
        super(taskId, title, description, status);
        this.subTasks = subTasks;
    }

    public Epic(String title, String description, ArrayList<Integer> subTasks, LocalDateTime endTime, TaskTypes taskTypes, LocalDate startTime, int duration) {
        super(title, description,duration, startTime);
        this.subTasks = subTasks;
        this.taskTypes = taskTypes;
    }

    public Epic(String title, String description, int duration, LocalDate startTime, ArrayList<Integer> subTasks, LocalDateTime endTime, TaskTypes taskTypes) {
        super(title, description, duration, startTime);
        this.subTasks = subTasks;
        this.endTime = endTime;
        this.taskTypes = taskTypes;
    }

    public Epic(int taskId, String title, String description, Status status, int duration, LocalDate startTime, ArrayList<Integer> subTasks, LocalDateTime endTime, TaskTypes taskTypes) {
        super(taskId, title, description, status, duration, startTime);
        this.subTasks = subTasks;
        this.endTime = endTime;
        this.taskTypes = taskTypes;
    }

    public void setSubTasks(ArrayList<Integer> subTasks) {
        this.subTasks = subTasks;
    }

    public ArrayList<Integer> getSubTasks() {
        return subTasks;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Epic ID: ").append(getTaskId()).append("\nTitle: ").append(getTitle())
                .append("\nDescription: ").append(getDescription()).append("\nStatus: ").append(getStatus())
                .append("\nDuration: ").append(getDuration()).append(" minutes")
                .append("\nStart Time: ").append(getStartTime())
                .append("\nEnd Time: ").append(getEndTime())
                .append("\nSubtasks: ").append(subTasks);
        return sb.toString();
    }

    @Override
    public String toCSV() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toCSV());
        String delimiter = ",";

        if (!subTasks.isEmpty()) {
            for (int i = 0; i < subTasks.size(); i++) {
                sb.append(delimiter);
                sb.append(subTasks.get(i).toString());
            }
        }
        else {
            sb.append(delimiter);
            sb.append("null");

        }

        return sb.toString();
    }

    @Override
    public TaskTypes getTaskTypes() {
        return taskTypes;
    }

    @Override
    public int getTaskId() {
        return taskId;
    }


}