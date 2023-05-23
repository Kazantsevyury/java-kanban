package logic;

import data.Epic;
import Managers.Manager;
import data.Task;
import data.SubTask;

public class Tester {
    public void runTests(Manager manager, FieldModifier fieldModifier ) {

        FieldModifier fieldModifier1 = new FieldModifier(manager);
        // Создание задачи
        Task task1 = new Task("Task 1", "Task 1 description");
        Task task2 = new Task("Task 2", "Task 2 description");

        manager.addTask(task1);
        manager.addTask(task2);

        // Создание Epic
        Epic epic1 = new Epic("Epic 1", "Epic 1 description");
        Epic epic2 = new Epic("Epic 2", "Epic 2 description");

        manager.addEpic(epic1);
        manager.addEpic(epic2);

        // Создание SubTask и привязка к Epic
        SubTask subTask1 = new SubTask("SubTask 1", "SubTask 1 description", 3);
        SubTask subTask2 = new SubTask("SubTask 2", "SubTask 1 description", 4);
        SubTask subTask3 = new SubTask("SubTask 3", "SubTask 1 description", 4);

        manager.addSubTask(subTask1);
        manager.addSubTask(subTask2);
        manager.addSubTask(subTask3);

        //fieldModifier.updateEpic(3,"status","DONE");
        fieldModifier.updateSubTask(5,"status", "DONE");
        fieldModifier.updateSubTask(6,"status", "DONE");

        System.out.println(manager.getAllTasks());
        manager.updateEpicStatus();
        System.out.println(manager.getAllEpics());
        System.out.println("-----------------------");

        System.out.println(manager.getAllSubTasks());
        manager.removeTask(1);
        manager.removeEpic(3);
    }
    public void RunTestsOfComplianceWithTR(Manager manager, FieldModifier fieldModifier ) {
        //2.4   Создание. Сам объект должен передаваться в качестве параметра.
        // Создание задачи
        Task task1 = new Task("Task 1", "Task 1 description");
        Task task2 = new Task("Task 2", "Task 2 description");

        manager.addTask(task1);
        manager.addTask(task2);

        // Создание Epic
        Epic epic1 = new Epic("Epic 3", "Epic 1 description");
        Epic epic2 = new Epic("Epic 4", "Epic 2 description");

        manager.addEpic(epic1);
        manager.addEpic(epic2);

        // Создание SubTask и привязка к Epic
        SubTask subTask1 = new SubTask("SubTask 5", "SubTask 1 description", 3);
        SubTask subTask2 = new SubTask("SubTask 6", "SubTask 1 description", 4);
        SubTask subTask3 = new SubTask("SubTask 7", "SubTask 1 description", 4);

        manager.addSubTask(subTask1);
        manager.addSubTask(subTask2);
        manager.addSubTask(subTask3);

        // Тест всех возможностей из ТЗ
        //2.1   Получение списка всех задач.
        manager.getAllTasks();
        manager.getAllEpics();
        manager.getAllSubTasks();

        //2.3   Получение по идентификатору.
        manager.getTask(1);
        manager.getEpic(3);
        manager.getSubTask(6);

        //2.5   Обновление. Новая версия объекта с верным идентификатором передаётся в виде параметра.
        fieldModifier.updateTask(1,"title","newTitle");
        fieldModifier.updateEpic(3,"title","newtitle");
        fieldModifier.updateSubTask(6,"title","newTitle");

        //2.6   Удаление по идентификатору.
        manager.removeTask(1);
        manager.removeEpic(3);
        manager.removeSubTask(6);

        // 3 Получение списка всех подзадач определённого эпика.
        manager.getEpic(4).getSubTasks();

        //2.2   Удаление всех задач.
        manager.clearAllTasks();
        manager.clearAllEpics();
        manager.clearAllSubTasks();

    }
}