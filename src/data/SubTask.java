package data;
import enums.Status;
import enums.TaskTypes;
import java.time.LocalDate;

public class SubTask extends Task {
    private int parentEpicId;
    private TaskTypes taskTypes = TaskTypes.SUBTASK;

    public SubTask(String title, String description, int epicID, int duration, LocalDate startTime) {
        super(title, description, duration, startTime);
        this.parentEpicId = epicID;
    }
    public SubTask(String title, String description, int epicID) {
        super(title, description);
        this.parentEpicId = epicID;
    }

    public SubTask(int taskId, String title, String description, Status status, int parentEpicId, int duration, LocalDate startTime) {
        super(taskId, title, description, status, duration, startTime);
        this.parentEpicId = parentEpicId;
    }
    public SubTask (String title, String description, int parentEpicId,int duration,String dateAsString ){
        super(title,description,duration,dateAsString);
        this.parentEpicId = parentEpicId;
    }

    public SubTask(String title, String description, Status status, int parentEpicId, int duration, LocalDate startTime) {
        super(title, description, status,duration, startTime );
        this.parentEpicId = parentEpicId;
    }

    public int getParentEpicId() {
        return parentEpicId;
    }

    public void setParentEpicId(int parentEpicId) {
        this.parentEpicId = parentEpicId;
    }

    @Override
    public String toString() {
        return "SubTask ID: " + getTaskId() + "\nTitle: " + getTitle() + "\nDescription: " + getDescription()
                + "\nStatus: " + getStatus() + "\nParentEpicID: " + getParentEpicId()
                + "\nDuration: " + getDuration() + " minutes" + "\nStart Time: " + getStartTime();
    }

    @Override
    public String toCSV() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toCSV());
        sb.append(",");
        sb.append(getParentEpicId());
        return sb.toString();
    }
    @Override
    public TaskTypes getTaskTypes() {
        return taskTypes;
    }
}