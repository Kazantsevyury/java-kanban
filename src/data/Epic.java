package data;

import logic.IdGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Epic extends Task {
    private ArrayList<Integer> subTasks;

    public Epic(String title, String description) {
        super(title, description);
        this.subTasks = new ArrayList<>();
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