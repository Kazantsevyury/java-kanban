import managers.implementation.FileBackedTasksManager;
import managers.implementation.InMemoryHistoryManager;
import managers.implementation.InMemoryTaskManager;
import managers.Managers;
import data.Epic;
import data.SubTask;
import data.Task;
import utilities.FieldModifier;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        FileBackedTasksManager manager = Managers.getFileBackendTaskManager();

        manager.getHistoryManager().printer();


        Task task1 = new Task("Task 1", "Task 1 description");
        Task task2 = new Task("Task 2", "Task 2 description");

        manager.addTask(task1);
        manager.addTask(task2);

        Epic epic1 = new Epic("Epic 3", "Epic 1 description");
        Epic epic2 = new Epic("Epic 4", "Epic 2 description");

        manager.addEpic(epic1);
        manager.addEpic(epic2);
        SubTask subTask1 = new SubTask("SubTask 5", "SubTask 1 description", 3);
        SubTask subTask2 = new SubTask("SubTask 6", "SubTask 1 description", 4);
        SubTask subTask3 = new SubTask("SubTask 7", "SubTask 1 description", 4);

        manager.addSubTask(subTask1);
        manager.addSubTask(subTask2);
        manager.addSubTask(subTask3);


        manager.getEpic(3);
        manager.getSubTask(5);

        manager.getHistoryManager().printer();

    }
}
