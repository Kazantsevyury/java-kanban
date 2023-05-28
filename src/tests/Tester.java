package tests;

import data.Epic;
import enums.Field;
import utilities.FieldModifier;
import manag.InMemoryTaskManager;
import enums.Status;
import data.Task;
import data.SubTask;

public class Tester {
    public void runTests(InMemoryTaskManager inMemoryTaskManager, FieldModifier fieldModifier) {
        FieldModifier fieldModifier1 = new FieldModifier(inMemoryTaskManager);

        Task task1 = new Task("Task 1", "Task 1 description");
        Task task2 = new Task("Task 2", "Task 2 description");

        inMemoryTaskManager.addTask(task1);
        inMemoryTaskManager.addTask(task2);

        Epic epic1 = new Epic("Epic 1", "Epic 1 description");
        Epic epic2 = new Epic("Epic 2", "Epic 2 description");

        inMemoryTaskManager.addEpic(epic1);
        inMemoryTaskManager.addEpic(epic2);

        SubTask subTask1 = new SubTask("SubTask 1", "SubTask 1 description", 3);
        SubTask subTask2 = new SubTask("SubTask 2", "SubTask 1 description", 4);
        SubTask subTask3 = new SubTask("SubTask 3", "SubTask 1 description", 4);

        inMemoryTaskManager.addSubTask(subTask1);
        inMemoryTaskManager.addSubTask(subTask2);
        inMemoryTaskManager.addSubTask(subTask3);

        fieldModifier.updateSubTask(5, Field.STATUS, Status.DONE);
        fieldModifier.updateSubTask(6, Field.STATUS, Status.DONE);

        System.out.println(inMemoryTaskManager.getAllTasks());
        inMemoryTaskManager.updateEpicStatus();
        System.out.println(inMemoryTaskManager.getAllEpics());
        System.out.println("-----------------------");

        System.out.println(inMemoryTaskManager.getAllSubTasks());
        inMemoryTaskManager.removeTask(1);
        inMemoryTaskManager.removeEpic(3);
    }

    public void runTestsOfComplianceWithTR(InMemoryTaskManager inMemoryTaskManager, FieldModifier fieldModifier) {
        Task task1 = new Task("Task 1", "Task 1 description");
        Task task2 = new Task("Task 2", "Task 2 description");

        inMemoryTaskManager.addTask(task1);
        inMemoryTaskManager.addTask(task2);

        Epic epic1 = new Epic("Epic 3", "Epic 1 description");
        Epic epic2 = new Epic("Epic 4", "Epic 2 description");

        inMemoryTaskManager.addEpic(epic1);
        inMemoryTaskManager.addEpic(epic2);

        SubTask subTask1 = new SubTask("SubTask 5", "SubTask 1 description", 3);
        SubTask subTask2 = new SubTask("SubTask 6", "SubTask 1 description", 4);
        SubTask subTask3 = new SubTask("SubTask 7", "SubTask 1 description", 4);

        inMemoryTaskManager.addSubTask(subTask1);
        inMemoryTaskManager.addSubTask(subTask2);
        inMemoryTaskManager.addSubTask(subTask3);

        inMemoryTaskManager.getAllTasks();
        inMemoryTaskManager.getAllEpics();
        inMemoryTaskManager.getAllSubTasks();

        inMemoryTaskManager.getTask(1);
        inMemoryTaskManager.getEpic(3);
        inMemoryTaskManager.getSubTask(6);

        fieldModifier.updateTask(1, Field.TITLE, "newTitle");
        fieldModifier.updateEpic(3, Field.TITLE, "newtitle");
        fieldModifier.updateSubTask(6, Field.TITLE, "newTitle");

        inMemoryTaskManager.removeTask(1);
        inMemoryTaskManager.removeEpic(3);
        inMemoryTaskManager.removeSubTask(6);

        inMemoryTaskManager.getEpic(4).getSubTasks();

        inMemoryTaskManager.clearAllTasks();
        inMemoryTaskManager.clearAllEpics();
        inMemoryTaskManager.clearAllSubTasks();
    }
}
