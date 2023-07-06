import managers.implementation.InMemoryTaskManager;
import managers.Managers;
import data.Epic;
import data.SubTask;
import data.Task;
import utilities.FieldModifier;

public class Main {
    public static void main(String[] args) {

        InMemoryTaskManager manager = Managers.getDefault();
        FieldModifier fieldModifier = new FieldModifier(manager);
        FieldModifier fieldModifier1 = new FieldModifier(manager);

        Task task1 = new Task("Task 1", "Task 1 description");
        manager.addTask(task1);
        Task task2 = new Task("Task 2", "Task 2 description");
        manager.addTask(task2);

        Epic epic1 = new Epic("Epic 1", "Epic 1 description");
        manager.addEpic(epic1);
        SubTask subTask1 = new SubTask("SubTask 1", "SubTask 1 description", 3);
        SubTask subTask2 = new SubTask("SubTask 2", "SubTask 2 description", 3);
        SubTask subTask3 = new SubTask("SubTask 3", "SubTask 3 description", 3);

        manager.addSubTask(subTask1);
        manager.addSubTask(subTask2);
        manager.addSubTask(subTask3);


        Epic epic2 = new Epic("Epic 2", "Epic 2 description");

        manager.getTask(1);//1 - нет в истории
        System.out.println(manager.getHistoryManager().getHistory());
        System.out.println("-------------------------------------------");


        manager.getTask(1);
        manager.getTask(1);
        System.out.println(manager.getHistoryManager().getHistory());
        System.out.println("-------------------------------------------");

        manager.getEpic(3);
        System.out.println(manager.getHistoryManager().getHistory());
        System.out.println("-------------------------------------------");

        manager.getEpic(3);
        System.out.println(manager.getHistoryManager().getHistory());
        System.out.println("-------------------------------------------");

        manager.getEpic(3);
        manager.getEpic(3);
        manager.getEpic(3);
        manager.getEpic(3);
        manager.getEpic(3);
        System.out.println(manager.getHistoryManager().getHistory());
        System.out.println("-------------------------------------------");
        manager.getTask(1);
        System.out.println(manager.getHistoryManager().getHistory());
        System.out.println("-------------------------------------------");
        manager.getSubTask(4);
        System.out.println(manager.getHistoryManager().getHistory());
        System.out.println("-------------------------------------------");

        manager.getSubTask(4);
        System.out.println(manager.getHistoryManager().getHistory());
        System.out.println("-------------------------------------------");

        manager.getSubTask(4);

        System.out.println(manager.getHistoryManager().getHistory());
        System.out.println("-------------------------------------------");

        manager.removeTask(1);
        System.out.println(manager.getHistoryManager().getHistory());
        System.out.println("-------------------------------------------");

        manager.removeEpic(3);
        System.out.println(manager.getHistoryManager().getHistory());


    }
}
