package tests;

import static org.junit.jupiter.api.Assertions.*;

import data.Epic;
import data.SubTask;
import data.Task;
import managers.TaskManager;
import managers.TaskManagerTest;
import managers.implementation.InMemoryTaskManager;
import org.junit.jupiter.api.Test;

public class InMemoryTaskManagerTest extends TaskManagerTest {


    protected TaskManager createTaskManager() {
        return new InMemoryTaskManager();
    }

    @Test
    void addTask() {
        TaskManager taskManager = createTaskManager();
        Task task = new Task( "Task 1", "Description 1");
        taskManager.addTask(task);
        Task addedTask = taskManager.getTask(task.getTaskId());
        assertEquals(task, addedTask);
    }

    @Test
    void addSubTask() {
        TaskManager taskManager = createTaskManager();
        Task task = new Task( "Task 1", "Description 1");
        taskManager.addTask(task);
        SubTask subTask = new SubTask( "SubTask 1", "SubTask Description", task.getTaskId());
        taskManager.addSubTask(subTask);
        SubTask addedSubTask = taskManager.getSubTask(subTask.getTaskId());
        assertEquals(subTask, addedSubTask);
    }

    @Test
    void addEpic() {
        TaskManager taskManager = createTaskManager();
        Epic epic = new Epic( "Epic 1", "Epic Description");
        taskManager.addEpic(epic);
        Epic addedEpic = taskManager.getEpic(epic.getTaskId());
        assertEquals(epic, addedEpic);
    }

}
