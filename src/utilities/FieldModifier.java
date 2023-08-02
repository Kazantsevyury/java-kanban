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

    public Task updateTask(Task task, Field field, Object value) {
        if (task != null) {
            int taskIdToRemove = task.getTaskId();
            switch (field) {
                case TITLE:
                    task.setTitle((String) value);
                    break;
                case DESCRIPTION:
                    task.setDescription((String) value);
                    break;
                case STATUS:
                    task.setStatus((Status) value);
                    break;
                case ID:
                    task.setTaskId((Integer) value);
                    break;

                default:
                    return null;
            }
            inMemoryTaskManager.removeTask(taskIdToRemove);
            inMemoryTaskManager.addTask(task);
            return task;
        }
        return null;
    }

    public Epic updateEpic(Epic epic, Field field, Object value) {
        if (epic != null) {
            int epicToRemove = epic.getTaskId();

            switch (field) {
                case TITLE:
                    epic.setTitle((String) value);
                    break;
                case DESCRIPTION:
                    epic.setDescription((String) value);
                    break;
                case STATUS:
                    epic.setStatus((Status) value);
                    break;
                case ID:
                    epic.setTaskId((Integer) value);
                    break;
                default:
                    return null;
            }
            inMemoryTaskManager.removeEpic(epicToRemove);
            inMemoryTaskManager.addEpic(epic);
            inMemoryTaskManager.updateEpicStatus();
            return epic;
        }
        return null;
    }

    public SubTask updateSubTask(SubTask subTask, Field field, Object value)  {
        if (subTask != null) {
            int subTaskIdToRemove = subTask.getTaskId();

            switch (field) {
                case TITLE:
                    subTask.setTitle((String) value);
                    break;
                case DESCRIPTION:
                    subTask.setDescription((String) value);
                    break;
                case STATUS:
                    subTask.setStatus((Status) value);
                    break;
                case ID:
                    subTask.setTaskId((Integer) value);
                default:
                    break;
            }
            inMemoryTaskManager.removeSubTask(subTaskIdToRemove);
            inMemoryTaskManager.addSubTask(subTask);
            inMemoryTaskManager.updateEpicStatus();
            return subTask;
        }
        return null;
    }
}
