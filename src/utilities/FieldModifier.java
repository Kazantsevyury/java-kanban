package utilities;

import data.Epic;
import managers.implementation.FileBackedTasksManager;
import managers.implementation.InMemoryTaskManager;
import enums.Status;
import enums.Field;
import data.SubTask;
import data.Task;

public class FieldModifier {
    private InMemoryTaskManager inMemoryTaskManager;

    public FieldModifier(InMemoryTaskManager inMemoryTaskManager) {
        this.inMemoryTaskManager = inMemoryTaskManager;
    }
    public FieldModifier(FileBackedTasksManager fileBackedTasksManager) {
        this.inMemoryTaskManager = fileBackedTasksManager;
    }

    public Task updateTask(int taskId, Field field, Object value) {
        Task existingTask = inMemoryTaskManager.getTask(taskId);
        if (existingTask != null) {
            switch (field) {
                case TITLE:
                    existingTask.setTitle((String) value);
                    break;
                case DESCRIPTION:
                    existingTask.setDescription((String) value);
                    break;
                case STATUS:
                    existingTask.setStatus((Status) value);
                    break;
                case ID:
                    existingTask.setTaskId((Integer) value);
                    break;

                default:
                    return null;
            }
            inMemoryTaskManager.removeTask(taskId);
            inMemoryTaskManager.addTask(existingTask);
            return inMemoryTaskManager.getTask(taskId);
        }
        return null;
    }

    public Epic updateEpic(int epicId, Field field, Object value) {
        Epic existingEpic = inMemoryTaskManager.getEpic(epicId);
        if (existingEpic != null) {
            switch (field) {
                case TITLE:
                    existingEpic.setTitle((String) value);
                    break;
                case DESCRIPTION:
                    existingEpic.setDescription((String) value);
                    break;
                case STATUS:
                    existingEpic.setStatus((Status) value);
                    break;
                case ID:
                    existingEpic.setTaskId((Integer) value);
                    break;
                default:
                    return null;
            }
            inMemoryTaskManager.removeEpic(epicId);
            inMemoryTaskManager.addEpic(existingEpic);
            inMemoryTaskManager.updateEpicStatus();
            return inMemoryTaskManager.getEpic(epicId);
        }
        return null;
    }

    public SubTask updateSubTask(int subTaskId, Field field, Object value)  {
        SubTask existingSubTask = inMemoryTaskManager.getSubTask(subTaskId);
        if (existingSubTask != null) {
            switch (field) {
                case TITLE:
                    existingSubTask.setTitle((String) value);
                    break;
                case DESCRIPTION:
                    existingSubTask.setDescription((String) value);
                    break;
                case STATUS:
                    existingSubTask.setStatus((Status) value);
                    break;
                case ID:
                    existingSubTask.setTaskId((Integer) value);
                default:
                    break;
            }
            inMemoryTaskManager.removeSubTask(subTaskId);
            inMemoryTaskManager.addSubTask(existingSubTask);
            inMemoryTaskManager.updateEpicStatus();
            return inMemoryTaskManager.getSubTask(subTaskId);
        }
        return null;
    }
}
