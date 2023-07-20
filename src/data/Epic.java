package data;
import enums.Status;
import enums.TaskTypes;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private ArrayList<Integer> subTasks;
    private TaskTypes taskTypes = TaskTypes.EPIC;


    public Epic(String title, String description) {
        super(title,description);
        this.subTasks = new ArrayList<>();
    }

    public Epic(int taskId, String title, String description, Status status, ArrayList<Integer> subTasks) {
        super(taskId, title, description, status);
        this.subTasks = subTasks;
    }

    public void setSubTasks(ArrayList<Integer> subTasks) {
        this.subTasks = subTasks;
    }
    public ArrayList<Integer> getSubTasks() {
        return subTasks;
    }

    @Override
    public String toString() {
        return "Epic ID: " + getTaskId() + "\nTitle: " +  getTitle() + "\nDescription: " + getDescription() + "\nStatus: " + getStatus() + "\nSubtasks: " + subTasks;
    }


    @Override
    public String toCSV() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toCSV());
        if (!(subTasks.isEmpty())){
            String delimiter = ",";
            for (int i = 0; i < subTasks.size(); i++) {
                sb.append(delimiter);
                sb.append(subTasks.get(i).toString());
            }
        }

        return sb.toString();
    }
    @Override
    public TaskTypes getTaskTypes() {
        return taskTypes;
    }

}