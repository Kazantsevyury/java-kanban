package data;

public class SubTask extends Task {
    private int epicId;
    public SubTask(String title, String description,int epicID) {
        super(title, description);
        this.epicId = epicID  ;
    }
    public int getEpicId() {
        return epicId;
    }
    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }

    @Override
    public String toString() {
        return "ParentEpicID" +epicId+ "\nSubTask ID: " + getTaskId() + "\nTitle: " +  getTitle() + "\nDescription: " + getDescription() + "\nStatus: " + getStatus() ;
    }
}
