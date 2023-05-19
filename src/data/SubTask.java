package data;

public class SubTask extends Task {
    private int epicId;

    public SubTask(String title, String description) {
        super(title, description);
        this.epicId = -1;
    }

    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }
}
