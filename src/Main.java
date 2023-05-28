import manag.InMemoryTaskManager;
import manag.Managers;
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
        Epic epic1 = new Epic("Epic 1", "Epic 1 description");
        manager.addEpic(epic1);
        SubTask subTask1 = new SubTask("SubTask 1", "SubTask 1 description", 3);
        manager.addSubTask(subTask1);

        manager.getTask(1);//1 - нет в истории
        manager.getTask(1);//2- нет в истории
        manager.getTask(1);//3- нет в истории
        manager.getEpic(2);//4
        manager.getEpic(2);//5
        manager.getEpic(2);//6
        manager.getEpic(2);//7
        manager.getEpic(2);//8
        manager.getEpic(2);//9
        manager.getEpic(2);//10
        manager.getSubTask(3);//11
        manager.getSubTask(3);//12
        manager.getSubTask(3);//13

        System.out.println(manager.getHistoryManager().getHistory());
    }
}
