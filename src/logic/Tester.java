package logic;

import data.Epic;
import Managers.InMemoryTaskManager;
import data.Status;
import data.Task;
import data.SubTask;

public class Tester {
    public void runTests(InMemoryTaskManager inMemoryTaskManager, FieldModifier fieldModifier ) {

        FieldModifier fieldModifier1 = new FieldModifier(inMemoryTaskManager);
        // Создание задачи
        Task task1 = new Task("Task 1", "Task 1 description");
        Task task2 = new Task("Task 2", "Task 2 description");

        inMemoryTaskManager.addTask(task1);
        inMemoryTaskManager.addTask(task2);

        // Создание Epic
        Epic epic1 = new Epic("Epic 1", "Epic 1 description");
        Epic epic2 = new Epic("Epic 2", "Epic 2 description");

        inMemoryTaskManager.addEpic(epic1);
        inMemoryTaskManager.addEpic(epic2);

        // Создание SubTask и привязка к Epic
        SubTask subTask1 = new SubTask("SubTask 1", "SubTask 1 description", 3);
        SubTask subTask2 = new SubTask("SubTask 2", "SubTask 1 description", 4);
        SubTask subTask3 = new SubTask("SubTask 3", "SubTask 1 description", 4);

        inMemoryTaskManager.addSubTask(subTask1);
        inMemoryTaskManager.addSubTask(subTask2);
        inMemoryTaskManager.addSubTask(subTask3);

        //fieldModifier.updateEpic(3,"status","DONE");
        fieldModifier.updateSubTask(5,"status", Status.DONE);
        fieldModifier.updateSubTask(6,"status", Status.DONE);

        System.out.println(inMemoryTaskManager.getAllTasks());
        inMemoryTaskManager.updateEpicStatus();
        System.out.println(inMemoryTaskManager.getAllEpics());
        System.out.println("-----------------------");

        System.out.println(inMemoryTaskManager.getAllSubTasks());
        inMemoryTaskManager.removeTask(1);
        inMemoryTaskManager.removeEpic(3);

    }
    public void RunTestsOfComplianceWithTR(InMemoryTaskManager inMemoryTaskManager, FieldModifier fieldModifier ) {
        //2.4   Создание. Сам объект должен передаваться в качестве параметра.
        // Создание задачи
        Task task1 = new Task("Task 1", "Task 1 description");
        Task task2 = new Task("Task 2", "Task 2 description");

        inMemoryTaskManager.addTask(task1);
        inMemoryTaskManager.addTask(task2);

        // Создание Epic
        Epic epic1 = new Epic("Epic 3", "Epic 1 description");
        Epic epic2 = new Epic("Epic 4", "Epic 2 description");

        inMemoryTaskManager.addEpic(epic1);
        inMemoryTaskManager.addEpic(epic2);

        // Создание SubTask и привязка к Epic
        SubTask subTask1 = new SubTask("SubTask 5", "SubTask 1 description", 3);
        SubTask subTask2 = new SubTask("SubTask 6", "SubTask 1 description", 4);
        SubTask subTask3 = new SubTask("SubTask 7", "SubTask 1 description", 4);

        inMemoryTaskManager.addSubTask(subTask1);
        inMemoryTaskManager.addSubTask(subTask2);
        inMemoryTaskManager.addSubTask(subTask3);

        // Тест всех возможностей из ТЗ
        //2.1   Получение списка всех задач.
        inMemoryTaskManager.getAllTasks();
        inMemoryTaskManager.getAllEpics();
        inMemoryTaskManager.getAllSubTasks();

        //2.3   Получение по идентификатору.
        inMemoryTaskManager.getTask(1);
        inMemoryTaskManager.getEpic(3);
        inMemoryTaskManager.getSubTask(6);

        //2.5   Обновление. Новая версия объекта с верным идентификатором передаётся в виде параметра.
        fieldModifier.updateTask(1,"title","newTitle");
        fieldModifier.updateEpic(3,"title","newtitle");
        fieldModifier.updateSubTask(6,"title","newTitle");

        //2.6   Удаление по идентификатору.
        inMemoryTaskManager.removeTask(1);
        inMemoryTaskManager.removeEpic(3);
        inMemoryTaskManager.removeSubTask(6);

        // 3 Получение списка всех подзадач определённого эпика.
        inMemoryTaskManager.getEpic(4).getSubTasks();

        //2.2   Удаление всех задач.
        inMemoryTaskManager.clearAllTasks();
        inMemoryTaskManager.clearAllEpics();
        inMemoryTaskManager.clearAllSubTasks();

    }
}