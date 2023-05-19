package logic;

import data.Epic;
import data.Storage;
import data.Task;
import data.SubTask;

import java.lang.reflect.Field;

public class FieldModifier {
    private Storage storage;

    public FieldModifier(Storage storage) {
        this.storage = storage;
    }

    public void modifyField(int id, String fieldName, Object value) {
        Task task = storage.getTask(id);
        if (task != null) {
            modifyObjectField(task, fieldName, value);
            return;
        }

        SubTask subTask = storage.getSubTask(id);
        if (subTask != null) {
            modifyObjectField(subTask, fieldName, value);
            return;
        }

        Epic epic = storage.getEpic(id);
        if (epic != null) {
            modifyObjectField(epic, fieldName, value);
        }
    }

    private void modifyObjectField(Object object, String fieldName, Object value) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
