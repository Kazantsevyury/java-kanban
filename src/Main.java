import Managers.Manager;
import data.Epic;
import data.SubTask;
import data.Task;
import logic.FieldModifier;

public class Main {
    // Метод Main оформлен в соответствии с ТЗ
    public static void main(String[] args) {
        /*
        Тестеры, на будующее

        controller.AppController appController = new controller.AppController();
        appController.run();
        */

        Manager manager;
        FieldModifier fieldModifier;

        manager = new Manager();
        fieldModifier = new FieldModifier(manager);

        FieldModifier fieldModifier1 = new FieldModifier(manager);

        // Создание и сохранение 2х задач
        Task task1 = new Task("Task 1", "Task 1 description");
        Task task2 = new Task("Task 2", "Task 2 description");
        manager.addTask(task1);
        manager.addTask(task2);

        // Создание и сохранение 2х Epic-ов
        Epic epic1 = new Epic("Epic 1", "Epic 1 description");
        Epic epic2 = new Epic("Epic 2", "Epic 2 description");
        manager.addEpic(epic1);
        manager.addEpic(epic2);

        // Создание и сохранение 2х SubTask для 1го эпика и 1 SubTask для 2го эпика, привязка к Epic-ам
        SubTask subTask1 = new SubTask("SubTask 1", "SubTask 1 description", 3);
        SubTask subTask2 = new SubTask("SubTask 2", "SubTask 1 description", 4);
        SubTask subTask3 = new SubTask("SubTask 3", "SubTask 1 description", 4);
        manager.addSubTask(subTask1);
        manager.addSubTask(subTask2);
        manager.addSubTask(subTask3);

        // Печать
        System.out.println("Создвны 2 задачи\n");
        System.out.println(manager.getAllTasks());
        System.out.println("Созданы 2 эпика. У первого 1 подзадача, у второго - 2 подзадачи\n");
        manager.updateEpicStatus();
        System.out.println(manager.getAllEpics());
        System.out.println("3 SubTusk. У всех статус- NEW\n");
        System.out.println(manager.getAllSubTasks());
        System.out.println("-----------------------\n");

        // Изменение статусов 1го Task , и 2х SubTask-ов
        fieldModifier.updateTask(1,"status", "DONE");
        fieldModifier.updateSubTask(5,"status", "DONE");
        fieldModifier.updateSubTask(6,"status", "DONE");

        // Печать
        System.out.println("Статус первой задачи - DONE, статус второй задачи - NEW\n");
        System.out.println(manager.getAllTasks());
        System.out.println("Статут первого эпика должен быть DONE, статус второго - IN_PROGRESS\n");
        manager.updateEpicStatus();
        System.out.println(manager.getAllEpics());
        System.out.println("Статус 1ой и 2ой SubTask - DONE, 3ей SubTask - NEW  \n");
        System.out.println(manager.getAllSubTasks());
        System.out.println("-----------------------\n");

        //Удаление эпика и задачи
        manager.removeTask(1);
        manager.removeEpic(3);

        // Печать
        System.out.println("Осталась 1 задача\n");
        System.out.println(manager.getAllTasks());
        System.out.println("Остался 1 эпик\n");
        manager.updateEpicStatus();
        System.out.println(manager.getAllEpics());
        System.out.println("Осталось 2 подзадачи\n");
        System.out.println(manager.getAllSubTasks());
        System.out.println("-----------------------");
    }
}
