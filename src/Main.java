import Managers.InMemoryTaskManager;
import Managers.TaskManager;
import data.Epic;
import data.SubTask;
import data.Task;
import logic.FieldModifier;

public class Main {
    public static void main(String[] args) {
        Managers managers = new Managers();

        InMemoryTaskManager manager = new InMemoryTaskManager();

        FieldModifier fieldModifier = new FieldModifier(manager);

        FieldModifier fieldModifier1 = new FieldModifier(manager);
        Task task1 = new Task("Task 1", "Task 1 description");
        manager.addTask(task1);

        Epic epic1 = new Epic("Epic 1", "Epic 1 description");
        manager.addEpic(epic1);

        SubTask subTask1 = new SubTask("SubTask 1", "SubTask 1 description", 3);
        manager.addSubTask(subTask1);


    }
}
