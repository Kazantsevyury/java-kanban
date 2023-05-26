package logic;
import data.Epic;
import Managers.InMemoryTaskManager;
import data.Status;
import data.SubTask;
import data.Task;

public class FieldModifier {
    private InMemoryTaskManager inMemoryTaskManager;

    public FieldModifier(InMemoryTaskManager inMemoryTaskManager) {
        this.inMemoryTaskManager = inMemoryTaskManager;
    }

    public Task updateTask(int taskId, String field, Object value) {
        Task existingTask = inMemoryTaskManager.getTask(taskId);
        if (existingTask != null) {
            if (field.equals("title")) {
                existingTask.setTitle((String) value);
            } else if (field.equals("description")) {
                existingTask.setDescription((String) value);
            } else if (field.equals("status")) {
                existingTask.setStatus((Status) value);
            }
            inMemoryTaskManager.removeTask(taskId);
            inMemoryTaskManager.addTask(existingTask);
            return inMemoryTaskManager.getTask(taskId);
        }
        return null;
    }

    public Epic updateEpic(int epicId, String field, Object value) {
        Epic existingEpic = inMemoryTaskManager.getEpic(epicId);
        if (existingEpic != null) {
            if (field.equals("title")) {
                existingEpic.setTitle((String) value);
            } else if (field.equals("description")) {
                existingEpic.setDescription((String) value);
            } else if (field.equals("status")) {
                existingEpic.setStatus((Status) value);
            }
            inMemoryTaskManager.removeEpic(epicId);
            inMemoryTaskManager.addEpic(existingEpic);
            inMemoryTaskManager.updateEpicStatus();
            return inMemoryTaskManager.getEpic(epicId);
        }
        return null;
    }

    public SubTask updateSubTask(int subTaskId, String field, Object value) {
        SubTask existingSubTask = inMemoryTaskManager.getSubTask(subTaskId);
        if (existingSubTask != null) {
            if (field.equals("title")) {
                existingSubTask.setTitle((String) value);
            } else if (field.equals("description")) {
                existingSubTask.setDescription((String) value);
            } else if (field.equals("status")) {
                existingSubTask.setStatus((Status) value);
            }
            inMemoryTaskManager.removeSubTask(subTaskId);
            inMemoryTaskManager.addSubTask(existingSubTask);
            inMemoryTaskManager.updateEpicStatus();
            return inMemoryTaskManager.getSubTask(subTaskId);
        }
        return null;
    }
}
