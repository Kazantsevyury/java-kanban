package tests;

import static org.junit.jupiter.api.Assertions.*;

import data.Epic;
import data.SubTask;
import data.Task;
import managers.TaskManager;
import managers.TaskManagerTest;
import managers.implementation.FileBackedTasksManager;
import java.io.File;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FileBackedTasksManagerTest extends TaskManagerTest {
    private File csvFile;


    protected TaskManager createTaskManager() {
        return FileBackedTasksManager.loadFromFile(csvFile);
    }

    @BeforeEach
    void setUp() {

        csvFile = new File("src/resources/test_example.csv");
    }

    @Test
    void addTask() {
        TaskManager taskManager = createTaskManager();
        Task task = new Task("Task 1", "Description 1");
        taskManager.addTask(task);

        TaskManager loadedTaskManager = FileBackedTasksManager.loadFromFile(csvFile);
        Task addedTask = loadedTaskManager.getTask( task.getTaskId());
        assertEquals(task, addedTask);
    }

    @Test
    void addSubTask() {
        TaskManager taskManager = createTaskManager();
        Task task = new Task( "Task 1", "Description 1");
        taskManager.addTask(task);

        SubTask subTask = new SubTask("SubTask 1", "SubTask Description", task.getTaskId());
        taskManager.addSubTask(subTask);

        TaskManager loadedTaskManager = FileBackedTasksManager.loadFromFile(csvFile);
        SubTask addedSubTask = loadedTaskManager.getSubTask(subTask.getTaskId());
        assertEquals(subTask, addedSubTask);
    }
    @Test
    void saveAndLoadTaskManager() {
        // Проверка сохранения и восстановления состояния
        FileBackedTasksManager taskManager = new FileBackedTasksManager();

        // a. Пустой список задач.
        assertTrue(taskManager.getAllTasks().isEmpty());

        // b. Эпик без подзадач.
        Epic epic = new Epic( "Epic 1", "Epic Description");
        taskManager.addEpic(epic);

        // c. Пустой список истории.
        assertTrue(taskManager.getHistoryManager().getHistory().isEmpty());

        // Сохраняем менеджер в файл
        taskManager.saveTasksToCsv();

        // Загружаем менеджер из файла
        TaskManager loadedTaskManager = FileBackedTasksManager.loadFromFile(csvFile);

        // Проверяем, что состояние было восстановлено правильно
        assertEquals(0, loadedTaskManager.getAllTasks().size()); // Пустой список задач
        assertEquals(epic, loadedTaskManager.getEpic(1)); // Эпик без подзадач
        assertEquals(0, loadedTaskManager.getHistoryManager().getHistory().size()); // Пустой список истории

    }

}
