package logic;
import data.Epic;
import data.Storage;
import data.SubTask;
import data.Task;

public class FieldModifier {
    private Storage storage;

    public FieldModifier(Storage storage) {
        this.storage = storage;
    }

    public Task updateTask(int taskId, String field, Object value) {
        Task existingTask = storage.getTask(taskId);
        if (existingTask != null) {
            if (field.equals("title")) {
                existingTask.setTitle((String) value);
            } else if (field.equals("description")) {
                existingTask.setDescription((String) value);
            } else if (field.equals("status")) {
                existingTask.setStatus((String) value);
            }
            storage.removeTask(taskId);
            storage.addTask(existingTask);
            return storage.getTask(taskId);
        }
        return null;
    }

    public Epic updateEpic(int epicId, String field, Object value) {
        Epic existingEpic = storage.getEpic(epicId);
        if (existingEpic != null) {
            if (field.equals("title")) {
                existingEpic.setTitle((String) value);
            } else if (field.equals("description")) {
                existingEpic.setDescription((String) value);
            } else if (field.equals("status")) {
                existingEpic.setStatus((String) value);
            }
            storage.removeEpic(epicId);
            storage.addEpic(existingEpic);
            storage.updateEpicStatus();
            return storage.getEpic(epicId);
        }
        return null;
    }

    public SubTask updateSubTask(int subTaskId, String field, Object value) {
        SubTask existingSubTask = storage.getSubTask(subTaskId);
        if (existingSubTask != null) {
            if (field.equals("title")) {
                existingSubTask.setTitle((String) value);
            } else if (field.equals("description")) {
                existingSubTask.setDescription((String) value);
            } else if (field.equals("status")) {
                existingSubTask.setStatus((String) value);
            }
            storage.removeSubTask(subTaskId);
            storage.addSubTask(existingSubTask);
            storage.updateEpicStatus();
            return storage.getSubTask(subTaskId);
        }
        return null;
    }
}
