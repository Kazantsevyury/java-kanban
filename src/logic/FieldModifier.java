package logic;
import data.Epic;
import data.Storage;
import data.SubTask;
import data.Task;
import java.util.ArrayList;

public class FieldModifier {
    private Storage storage;

    public FieldModifier(Storage storage) {
        this.storage = storage;
    }

    public void updateTask(Task updatedTask) {
        int taskId = updatedTask.getTaskId();
        Task existingTask = storage.getTask(taskId);
        if (existingTask != null) {
            existingTask.setTitle(updatedTask.getTitle());
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setStatus(updatedTask.getStatus());
        }
    }
    public void updateEpic(Epic updatedEpic) {
        int epicId = updatedEpic.getTaskId();
        Epic existingEpic = storage.getEpic(epicId);
        if (existingEpic != null) {
            existingEpic.setTitle(updatedEpic.getTitle());
            existingEpic.setDescription(updatedEpic.getDescription());
            existingEpic.setStatus(updatedEpic.getStatus());
        }
    }
    public void updateSubTask(SubTask updatedSubTask) {
        int subTaskId = updatedSubTask.getTaskId();
        SubTask existingSubTask = storage.getSubTask(subTaskId);
        if (existingSubTask != null) {
            existingSubTask.setTitle(updatedSubTask.getTitle());
            existingSubTask.setDescription(updatedSubTask.getDescription());
            existingSubTask.setStatus(updatedSubTask.getStatus());
        }
    }
}
