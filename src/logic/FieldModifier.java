package logic;
import data.Epic;
import Managers.Manager;
import data.SubTask;
import data.Task;

public class FieldModifier {
    private Manager manager;

    public FieldModifier(Manager manager) {
        this.manager = manager;
    }

    public Task updateTask(int taskId, String field, Object value) {
        Task existingTask = manager.getTask(taskId);
        if (existingTask != null) {
            if (field.equals("title")) {
                existingTask.setTitle((String) value);
            } else if (field.equals("description")) {
                existingTask.setDescription((String) value);
            } else if (field.equals("status")) {
                existingTask.setStatus((String) value);
            }
            manager.removeTask(taskId);
            manager.addTask(existingTask);
            return manager.getTask(taskId);
        }
        return null;
    }

    public Epic updateEpic(int epicId, String field, Object value) {
        Epic existingEpic = manager.getEpic(epicId);
        if (existingEpic != null) {
            if (field.equals("title")) {
                existingEpic.setTitle((String) value);
            } else if (field.equals("description")) {
                existingEpic.setDescription((String) value);
            } else if (field.equals("status")) {
                existingEpic.setStatus((String) value);
            }
            manager.removeEpic(epicId);
            manager.addEpic(existingEpic);
            manager.updateEpicStatus();
            return manager.getEpic(epicId);
        }
        return null;
    }

    public SubTask updateSubTask(int subTaskId, String field, Object value) {
        SubTask existingSubTask = manager.getSubTask(subTaskId);
        if (existingSubTask != null) {
            if (field.equals("title")) {
                existingSubTask.setTitle((String) value);
            } else if (field.equals("description")) {
                existingSubTask.setDescription((String) value);
            } else if (field.equals("status")) {
                existingSubTask.setStatus((String) value);
            }
            manager.removeSubTask(subTaskId);
            manager.addSubTask(existingSubTask);
            manager.updateEpicStatus();
            return manager.getSubTask(subTaskId);
        }
        return null;
    }
}
