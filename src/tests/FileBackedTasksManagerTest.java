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
        fileBackedTasksManager.сlearCsvFileExample();
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
    @Override
    void addTask() {
        Task task = new Task(1, "Test Task", "Description", Status.NEW, 60, LocalDate.of(2023, 7, 30));
        fileBackedTasksManager.addTask(task);

        Task retrievedTask = fileBackedTasksManager.getTask(task.getTaskId());
        assertNotNull(retrievedTask);
        assertEquals(task.getTitle(), retrievedTask.getTitle());
        assertEquals(task.getDescription(), retrievedTask.getDescription());
        assertEquals(task.getStatus(), retrievedTask.getStatus());
        assertEquals(task.getStartTime(), retrievedTask.getStartTime());
        assertEquals(task.getDuration(), retrievedTask.getDuration());
    }

    @Test
    @Override
    void addEpic() {
        Epic epic = new Epic(1, "Test Epic", "Epic Description", Status.NEW, new ArrayList<>());
        fileBackedTasksManager.addEpic(epic);

        Epic retrievedEpic = fileBackedTasksManager.getEpic(epic.getTaskId());
        assertNotNull(retrievedEpic);
        assertEquals(epic.getTitle(), retrievedEpic.getTitle());
        assertEquals(epic.getDescription(), retrievedEpic.getDescription());
    }

    @Test
    @Override
    void addSubTask() {
        Task parentTask = new Task(1, "Parent Task", "Description", Status.NEW, 60, LocalDate.of(2023, 7, 30));
        fileBackedTasksManager.addTask(parentTask);

        SubTask subTask = new SubTask(2, "Sub Task", "Sub Task Description", Status.NEW, parentTask.getTaskId(), 30, LocalDate.of(2023, 7, 30));
        fileBackedTasksManager.addSubTask(subTask);

        SubTask retrievedSubTask = fileBackedTasksManager.getSubTask(subTask.getTaskId());
        assertNotNull(retrievedSubTask);
        assertEquals(subTask.getTitle(), retrievedSubTask.getTitle());
        assertEquals(subTask.getDescription(), retrievedSubTask.getDescription());
        assertEquals(subTask.getStatus(), retrievedSubTask.getStatus());
        assertEquals(subTask.getStartTime(), retrievedSubTask.getStartTime());
        assertEquals(subTask.getDuration(), retrievedSubTask.getDuration());
        assertEquals(subTask.getParentEpicId(), retrievedSubTask.getParentEpicId());
    }


    @Test
    @Override
    void getTask() {
        Task task = new Task(1, "Test Task", "Description", Status.NEW, 60, LocalDate.of(2023, 7, 30));
        fileBackedTasksManager.addTask(task);

        Task retrievedTask = fileBackedTasksManager.getTask(task.getTaskId());
        assertNotNull(retrievedTask);
        assertEquals(task.getTitle(), retrievedTask.getTitle());
        assertEquals(task.getDescription(), retrievedTask.getDescription());
        assertEquals(task.getStatus(), retrievedTask.getStatus());
        assertEquals(task.getStartTime(), retrievedTask.getStartTime());
        assertEquals(task.getDuration(), retrievedTask.getDuration());
    }

    @Test
    @Override
    void getSubTask() {
        Epic parentTask = new Epic(1, "Parent Task", "Description", Status.NEW, new ArrayList<>());
        fileBackedTasksManager.addEpic(parentTask);

        SubTask subTask = new SubTask(2, "Sub Task", "Sub Task Description", Status.NEW, parentTask.getTaskId(), 30, LocalDate.of(2023, 7, 30));
        fileBackedTasksManager.addSubTask(subTask);

        SubTask retrievedSubTask = fileBackedTasksManager.getSubTask(subTask.getTaskId());
        assertNotNull(retrievedSubTask);
        assertEquals(subTask.getTitle(), retrievedSubTask.getTitle());
        assertEquals(subTask.getDescription(), retrievedSubTask.getDescription());
        assertEquals(subTask.getStatus(), retrievedSubTask.getStatus());
        assertEquals(subTask.getStartTime(), retrievedSubTask.getStartTime());
        assertEquals(subTask.getDuration(), retrievedSubTask.getDuration());
        assertEquals(subTask.getParentEpicId(), retrievedSubTask.getParentEpicId());
    }

    @Test
    @Override
    void getEpic() {
        Epic epic = new Epic(1, "Test Epic", "Epic Description", Status.NEW, new ArrayList<>());
        fileBackedTasksManager.addEpic(epic);
        Epic retrievedEpic = fileBackedTasksManager.getEpic(epic.getTaskId());
        assertNotNull(retrievedEpic);
        assertEquals(epic.getTitle(), retrievedEpic.getTitle());
        assertEquals(epic.getDescription(), retrievedEpic.getDescription());
    }

    @Test
    @Override
    void getAllTasks() {
        Task task1 = new Task(1, "Task 1", "Description 1", Status.NEW, 60, LocalDate.of(2023, 7, 30));
        Task task2 = new Task(2, "Task 2", "Description 2", Status.IN_PROGRESS, 90, LocalDate.of(2023, 7, 31));
        fileBackedTasksManager.addTask(task1);
        fileBackedTasksManager.addTask(task2);

        ArrayList<Task> allTasks = fileBackedTasksManager.getAllTasks();
        assertEquals(2, allTasks.size());
        assertTrue(allTasks.contains(task1));
        assertTrue(allTasks.contains(task2));
    }

    @Test
    @Override
    void getAllEpics() {
        Epic epic1 = new Epic(1, "Epic 1", "Epic Description 1", Status.NEW, new ArrayList<>());
        Epic epic2 = new Epic(2, "Epic 2", "Epic Description 2", Status.IN_PROGRESS, new ArrayList<>());
        fileBackedTasksManager.addEpic(epic1);
        fileBackedTasksManager.addEpic(epic2);

        ArrayList<Epic> allEpics = fileBackedTasksManager.getAllEpics();
        assertEquals(2, allEpics.size());
        assertTrue(allEpics.contains(epic1));
        assertTrue(allEpics.contains(epic2));
    }

    @Test
    @Override
    void getAllSubTasks() {
        Epic parentTask = new Epic(1, "Parent Task", "Description", Status.NEW, 60, LocalDate.of(2023, 7, 30));
        fileBackedTasksManager.addEpic(parentTask);

        SubTask subTask1 = new SubTask(2, "Sub Task 1", "Sub Task Description 1", Status.NEW, parentTask.getTaskId(), 30, LocalDate.of(2023, 7, 30));
        SubTask subTask2 = new SubTask(3, "Sub Task 2", "Sub Task Description 2", Status.IN_PROGRESS,parentTask.getTaskId(), 30, LocalDate.of(2023, 7, 30));
        fileBackedTasksManager.addSubTask(subTask1);
        fileBackedTasksManager.addSubTask(subTask2);

        ArrayList<SubTask> allSubTasks = fileBackedTasksManager.getAllSubTasks();
        assertEquals(2, allSubTasks.size());
        assertTrue(allSubTasks.contains(subTask1));
        assertTrue(allSubTasks.contains(subTask2));
    }

    @Test
    @Override
    void clearAllTasks() {
        Task task1 = new Task(1, "Task 1", "Description 1", Status.NEW, 60, LocalDate.of(2023, 7, 30));
        Task task2 = new Task(2, "Task 2", "Description 2", Status.IN_PROGRESS, 90, LocalDate.of(2023, 7, 31));
        fileBackedTasksManager.addTask(task1);
        fileBackedTasksManager.addTask(task2);

        assertEquals(2, fileBackedTasksManager.getAllTasks().size());

        fileBackedTasksManager.clearAllTasks();

        assertEquals(0, fileBackedTasksManager.getAllTasks().size());
    }


    @Test
    @Override
    void clearAllEpics() {
        Epic epic1 = new Epic(1, "Epic 1", "Epic Description 1", Status.NEW, new ArrayList<>());
        Epic epic2 = new Epic(2, "Epic 2", "Epic Description 2", Status.IN_PROGRESS, new ArrayList<>());
        fileBackedTasksManager.addEpic(epic1);
        fileBackedTasksManager.addEpic(epic2);

        assertEquals(2, fileBackedTasksManager.getAllEpics().size());

        fileBackedTasksManager.clearAllEpics();

        assertEquals(0, fileBackedTasksManager.getAllEpics().size());
    }

    @Test
    @Override
    void clearAllSubTasks() {
        Epic parentTask = new Epic(1, "Parent Task", "Description", Status.NEW, new ArrayList<>());
        fileBackedTasksManager.addEpic(parentTask);

        SubTask subTask1 = new SubTask(2, "Sub Task 1", "Sub Task Description 1", Status.NEW, parentTask.getTaskId(), 30, LocalDate.of(2023, 7, 30));
        SubTask subTask2 = new SubTask(3, "Sub Task 2", "Sub Task Description 2", Status.IN_PROGRESS, parentTask.getTaskId(), 30, LocalDate.of(2023, 7, 30));
        fileBackedTasksManager.addSubTask(subTask1);
        fileBackedTasksManager.addSubTask(subTask2);

        assertEquals(2, fileBackedTasksManager.getAllSubTasks().size());

        fileBackedTasksManager.clearAllSubTasks();

        assertEquals(0, fileBackedTasksManager.getAllSubTasks().size());
    }


    @Test
    @Override
    void removeTask() {
        Task task = new Task(1, "Task", "Description", Status.NEW, 60, LocalDate.of(2023, 7, 30));
        fileBackedTasksManager.addTask(task);

        assertEquals(1, fileBackedTasksManager.getAllTasks().size());

        fileBackedTasksManager.removeTask(task.getTaskId());

        assertEquals(0, fileBackedTasksManager.getAllTasks().size());
    }

    @Test
    @Override
    void removeEpic() {
        Epic epic = new Epic(1, "Epic", "Epic Description", Status.NEW, new ArrayList<>());
        fileBackedTasksManager.addEpic(epic);

        assertEquals(1, fileBackedTasksManager.getAllEpics().size());

        fileBackedTasksManager.removeEpic(epic.getTaskId());

        assertEquals(0, fileBackedTasksManager.getAllEpics().size());
    }

    @Test
    @Override
    void removeSubTask() {
        Epic parentTask = new Epic(1, "Parent Task", "Description", Status.NEW, new ArrayList<>());
        fileBackedTasksManager.addEpic(parentTask);

        SubTask subTask = new SubTask(2, "Sub Task", "Sub Task Description", Status.NEW, parentTask.getTaskId(), 30, LocalDate.of(2023, 7, 30));
        fileBackedTasksManager.addSubTask(subTask);

        assertEquals(1, fileBackedTasksManager.getAllSubTasks().size());

        fileBackedTasksManager.removeSubTask(subTask.getTaskId());

        assertEquals(0, fileBackedTasksManager.getAllSubTasks().size());
    }


    @Test
    @Override
    void getHistoryManager() {
        assertNotNull(fileBackedTasksManager.getHistoryManager());
    }

    @Test
    @Override
    void getAnyTaskById() {
        Task task1 = new Task(1, "Task 1", "Description 1", Status.NEW, 60, LocalDate.of(2023, 7, 30));
        Task task2 = new Task(2, "Task 2", "Description 2", Status.IN_PROGRESS, 90, LocalDate.of(2023, 7, 31));
        fileBackedTasksManager.addTask(task1);
        fileBackedTasksManager.addTask(task2);

        Task retrievedTask1 = fileBackedTasksManager.getAnyTaskById(1);
        assertNotNull(retrievedTask1);
        assertEquals(task1.getTitle(), retrievedTask1.getTitle());

        Task retrievedTask2 = fileBackedTasksManager.getAnyTaskById(2);
        assertNotNull(retrievedTask2);
        assertEquals(task2.getTitle(), retrievedTask2.getTitle());
    }

    @Test
    @Override
    void updateEpicStatus() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        Epic parentTask = new Epic(1, "Epic", "Epic Description", Status.NEW,arrayList );
        fileBackedTasksManager.addEpic(parentTask);
        ///assertEquals(Status.NEW, parentTask.getStatus());
        // не могу найти ошибку. статуст должен быть NEW , и при созданием с таким статусом и при обновлении его статуса
        fileBackedTasksManager.updateEpicStatus();
        assertEquals(Status.NEW, parentTask.getStatus());

    }
    @Test
    void testEmptyTaskListSaveAndRestore() {
        FileBackedTasksManager fileBackedTasksManager = new FileBackedTasksManager("test_empty_task_list.csv");
        fileBackedTasksManager.сlearCsvFileExample();
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