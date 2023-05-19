package logic;

import data.Epic;
import data.Storage;
import data.Task;
import data.SubTask;

import java.util.HashMap;

public class Tester {
    public void runTests(Storage storage, FieldModifier fieldModifier) {
        // Создание задачи
        Task task1 = new Task("Task 1", "Task 1 description");
        storage.addTask(task1);

        // Получение задачи по ID
        Task retrievedTask = storage.getTask(task1.getTaskId());
        System.out.println("Retrieved Task: " + retrievedTask.getTitle());

        // Изменение поля задачи
        fieldModifier.modifyField(task1.getTaskId(), "title", "Updated Task 1");

        // Получение измененного поля
        System.out.println("Updated Task: " + retrievedTask.getTitle());

        // Создание Epic
        Epic epic1 = new Epic("Epic 1", "Epic 1 description");
        storage.addEpic(epic1);

        // Создание SubTask и привязка к Epic
        SubTask subTask1 = new SubTask("SubTask 1", "SubTask 1 description");
        epic1.addSubTask(subTask1);
        storage.addSubTask(subTask1);

        // Получение всех SubTask Epic
        HashMap<Integer, SubTask> subTasks = epic1.getSubTasks();
        for (SubTask subTask : subTasks.values()) {
            System.out.println("SubTask of Epic: " + subTask.getTitle());
        }
    }
}