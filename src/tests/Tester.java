package tests;

import data.Epic;
import managers.Managers;
import managers.implementation.FileBackedTasksManager;
import utilities.FieldModifier;
import data.Task;
import data.SubTask;
import java.io.File;

public class Tester {
    public void runTests(FileBackedTasksManager manager, FieldModifier fieldModifier) {
        Task task1 = new Task("Task 1", "Description of Task 1");
        Task task2 = new Task("Task 2", "Description of Task 2");
        SubTask subTask1 = new SubTask("SubTask 1", "Description of SubTask 1", task1.getTaskId());
        SubTask subTask2 = new SubTask("SubTask 2", "Description of SubTask 2", task1.getTaskId());
        Epic epic1 = new Epic("Epic 1", "Description of Epic 1");
        Epic epic2 = new Epic("Epic 2", "Description of Epic 2");

        manager.addTask(task1);
        manager.addTask(task2);
        manager.addSubTask(subTask1);
        manager.addSubTask(subTask2);
        manager.addEpic(epic1);
        manager.addEpic(epic2);

        manager.getTaskById(task1.getTaskId());
        manager.getSubTask(subTask1.getTaskId());
        manager.getEpic(epic2.getTaskId());

        manager.saveTasksToCsv();

        // Создаем новый менеджер задач из этого же файла
        FileBackedTasksManager newManager = Managers.getFileBackendTaskManager();
        newManager.loadTasksFromCsv(new File("example.csv"));

        // Проверяем, что история просмотра восстановилась верно
        newManager.getHistoryManager().printer();

        // Проверяем, что все задачи, эпики и подзадачи из старого менеджера есть в новом менеджере
        System.out.println("Проверка, что все задачи восстановились:");
        System.out.println("Задача 1: " + newManager.getTaskById(task1.getTaskId()));
        System.out.println("Задача 2: " + newManager.getTaskById(task2.getTaskId()));
        System.out.println("Подзадача 1: " + newManager.getSubTask(subTask1.getTaskId()));
        System.out.println("Подзадача 2: " + newManager.getSubTask(subTask2.getTaskId()));
        System.out.println("Эпик 1: " + newManager.getEpic(epic1.getTaskId()));
        System.out.println("Эпик 2: " + newManager.getEpic(epic2.getTaskId()));
    }
}