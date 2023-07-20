package data;

import enums.Status;
import enums.TaskTypes;

public class SubTask extends Task {
    private int parentEpicId;
    private TaskTypes taskTypes = TaskTypes.SUBTASK;

    public SubTask(String title, String description,int epicID) {
        super(title, description);
        this.parentEpicId = epicID  ;
    }

    public SubTask(int taskId, String title, String description, Status status, int parentEpicId) {
        super(taskId, title, description, status);
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
        return  "SubTask ID: " + getTaskId()  + "\nTitle: " +  getTitle() + "\nDescription: " + getDescription() + "\nStatus: " + getStatus() + "\nParentEpicID" + parentEpicId;

    }
    @Override
    public String toCSV(){
        StringBuffer sb = new StringBuffer();
        sb.append(super.toCSV()); // Вызываем родительский метод toCSV() и добавляем его результат в StringBuffer
        sb.append(",");
        sb.append(parentEpicId);

        return sb.toString();

    }
    @Override
    public TaskTypes getTaskTypes() {
        return taskTypes;
    }
}
