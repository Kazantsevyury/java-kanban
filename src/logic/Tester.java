package logic;

import data.Epic;
import data.Storage;
import data.Task;
import data.SubTask;

public class Tester {
    public void runTests(Storage storage,FieldModifier fieldModifier ) {
        FieldModifier fieldModifier1 = new FieldModifier(storage);
        // Создание задачи
        Task task1 = new Task("Task 1", "Task 1 description");
        Task task2 = new Task("Task 2", "Task 2 description");

        storage.addTask(task1);
        storage.addTask(task2);

        // Создание Epic
        Epic epic1 = new Epic("Epic 1", "Epic 1 description");
        Epic epic2 = new Epic("Epic 2", "Epic 2 description");


        storage.addEpic(epic1);
        storage.addEpic(epic2);

        // Создание SubTask и привязка к Epic
        SubTask subTask1 = new SubTask("SubTask 1", "SubTask 1 description",3);
        SubTask subTask2 = new SubTask("SubTask 2", "SubTask 1 description",4);
        SubTask subTask3 = new SubTask("SubTask 3", "SubTask 1 description",4);

        storage.addSubTask(subTask1);
        storage.addSubTask(subTask2);
        storage.addSubTask(subTask3);

        System.out.println(storage.getAllTasks());
        System.out.println(storage.getAllSubTasks());
        System.out.println(storage.getAllEpics());









    }
}