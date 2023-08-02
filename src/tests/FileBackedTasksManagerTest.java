package tests;

import data.Epic;
import data.Task;import data.SubTask;
import enums.Status;
import managers.implementation.FileBackedTasksManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FileBackedTasksManagerTest extends TaskManagerTest {
    private static final String CSV_FILE_PATH = "test_tasks.csv";
    private FileBackedTasksManager fileBackedTasksManager;

    @BeforeEach
    void setUp() {
        fileBackedTasksManager = createTaskManager();
    }

    @AfterEach
    void tearDown() {
        clearTaskManager();
    }


    protected FileBackedTasksManager createTaskManager() {
        return new FileBackedTasksManager(CSV_FILE_PATH);
    }

    protected void clearTaskManager() {
        File file = new File(CSV_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }
    @Test
    void addTask() {
    }

    @Test
    void addEpic() {

    }

    @Test
    void addSubTask() {

    }


    @Test
    void getTask() {
    }

    @Test
    void getSubTask() {

    }

    @Test

    void getEpic() {

    }

    @Test

    void getAllTasks() {

    }

    @Test
    void getAllEpics() {

    }

    @Test
    void getAllSubTasks() {

    }

    @Test
    void clearAllTasks() {

    }

    @Test
    void clearAllEpics() {

    }

    @Test
    void clearAllSubTasks() {

    }


    @Test
    void removeTask() {

    }

    @Test
    void removeEpic() {

    }

    @Test
    void removeSubTask() {

    }

    @Test
    void getAnyTaskById() {

    }

    @Test
    void updateEpicStatus() {

    }
    @Test
    void testEmptyTaskListSaveAndRestore() {
        FileBackedTasksManager fileBackedTasksManager = new FileBackedTasksManager("test_empty_task_list.csv");
        fileBackedTasksManager.clearCsvFileExample();
        assertTrue(fileBackedTasksManager.getAllTasks().isEmpty());

        Task task1 = new Task(1, "Task 1", "Description 1", Status.NEW, 60, LocalDate.of(2023, 7, 30));
        Task task2 = new Task(2, "Task 2", "Description 2", Status.IN_PROGRESS, 90, LocalDate.of(2023, 7, 31));
        fileBackedTasksManager.addTask(task1);
        fileBackedTasksManager.addTask(task2);

        assertEquals(2, fileBackedTasksManager.getAllTasks().size());
        fileBackedTasksManager.clearAllTasks();

        assertTrue(fileBackedTasksManager.getAllTasks().isEmpty());
    }
}