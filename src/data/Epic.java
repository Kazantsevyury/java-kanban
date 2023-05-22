package data;
import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Integer> subTasks;

    public Epic(String title, String description) {
        super(title, description);
        this.subTasks = new ArrayList<>();
    }

    public Epic(String title, String description, ArrayList<Integer> subTasks) {
        super(title, description);
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
}