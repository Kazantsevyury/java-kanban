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
        SubTask subTask1 = new SubTask("SubTask 1", "SubTask 1 description", 3);
        SubTask subTask2 = new SubTask("SubTask 2", "SubTask 1 description", 4);
        SubTask subTask3 = new SubTask("SubTask 3", "SubTask 1 description", 4);

        storage.addSubTask(subTask1);
        storage.addSubTask(subTask2);
        storage.addSubTask(subTask3);


        //fieldModifier.updateEpic(3,"status","DONE");
        fieldModifier.updateSubTask(5,"status", "DONE");

        fieldModifier.updateSubTask(6,"status", "DONE");



        System.out.println(storage.getAllTasks());
        storage.updateEpicStatus();
        System.out.println(storage.getAllEpics());
        System.out.println("-----------------------");

        System.out.println(storage.getAllSubTasks());
        storage.removeTask(1);
        storage.removeEpic(3);

    }

    public void RunTestsOfComplianceWithTR(Storage storage,FieldModifier fieldModifier ) {
        //2.4   Создание. Сам объект должен передаваться в качестве параметра.

        // Создание задачи
        Task task1 = new Task("Task 1", "Task 1 description");
        Task task2 = new Task("Task 2", "Task 2 description");

        storage.addTask(task1);
        storage.addTask(task2);

        // Создание Epic
        Epic epic1 = new Epic("Epic 3", "Epic 1 description");
        Epic epic2 = new Epic("Epic 4", "Epic 2 description");

        storage.addEpic(epic1);
        storage.addEpic(epic2);

        // Создание SubTask и привязка к Epic
        SubTask subTask1 = new SubTask("SubTask 5", "SubTask 1 description", 3);
        SubTask subTask2 = new SubTask("SubTask 6", "SubTask 1 description", 4);
        SubTask subTask3 = new SubTask("SubTask 7", "SubTask 1 description", 4);

        storage.addSubTask(subTask1);
        storage.addSubTask(subTask2);
        storage.addSubTask(subTask3);

        // Тест всех возможностей из ТЗ
        //2.1   Получение списка всех задач.
        storage.getAllTasks();
        storage.getAllEpics();
        storage.getAllSubTasks();

        //2.3   Получение по идентификатору.
        storage.getTask(1);
        storage.getEpic(3);
        storage.getSubTask(6);


        //2.5   Обновление. Новая версия объекта с верным идентификатором передаётся в виде параметра.
        fieldModifier.updateTask(1,"title","newTitle");
        fieldModifier.updateEpic(3,"title","newtitle");
        fieldModifier.updateSubTask(6,"title","newTitle");

        //2.6   Удаление по идентификатору.
        storage.removeTask(1);
        storage.removeEpic(3);
        storage.removeSubTask(6);

        // 3 Получение списка всех подзадач определённого эпика.
        storage.getEpic(4).getSubTasks();

        //2.2   Удаление всех задач.
        storage.clearAllTasks();
        storage.clearAllEpics();
        storage.clearAllSubTasks();

    }
}