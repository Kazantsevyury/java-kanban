package tests;

import data.Epic;
import data.SubTask;
import data.Task;
import enums.Status;
import managers.TaskManager;
import managers.implementation.InMemoryTaskManager;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

abstract class TaskManagerTest<T extends TaskManager> {
    private TaskManager taskManager;

    @Test
    void addTask() {

        Task task = new Task(1, "Task 1", "Description of Task 1", Status.IN_PROGRESS, 5, LocalDate.now());
        taskManager.addTask(task);
        Task retrievedTask = taskManager.getTask(1);
        assertEquals(task, retrievedTask);
    }

    @Test
    void addSubTask() {
        Epic parentTask = new Epic(1, "Epic 1", "Description of Epic 1", Status.NEW, new ArrayList<>());
        taskManager.addEpic(parentTask);

        SubTask subTask = new SubTask(2, "Subtask 1", "Description of Subtask 1", Status.NEW, 1, 3, LocalDate.now());
        taskManager.addSubTask(subTask);

        SubTask retrievedSubTask = taskManager.getSubTask(2);
        assertEquals(subTask, retrievedSubTask);
    }

    @Test
    void addEpic() {
        Epic epic = new Epic(1, "Epic 1", "Description of Epic 1", Status.NEW, new ArrayList<>());
        taskManager.addEpic(epic);
        Epic retrievedEpic = taskManager.getEpic(1);
        assertEquals(epic, retrievedEpic);
    }

    @Test
    void getTask() {

        assertNull(taskManager.getTask(999));

        Task task = new Task(1, "Task 1", "Description of Task 1", Status.IN_PROGRESS, 5, LocalDate.now());
        taskManager.addTask(task);
        Task retrievedTask = taskManager.getTask(1);
        assertEquals(task, retrievedTask);

        assertNull(taskManager.getTask(10));

    }

    @Test
    void getSubTask() {
        assertNull(taskManager.getSubTask(999));

        Epic parentTask = new Epic(1, "Epic 1", "Description of Epic 1", Status.NEW, new ArrayList<>());
        taskManager.addEpic(parentTask);

        SubTask subTask = new SubTask(2, "Subtask 1", "Description of Subtask 1", Status.NEW, 1, 3, LocalDate.now());
        taskManager.addSubTask(subTask);

        SubTask retrievedSubTask = taskManager.getSubTask(2);
        assertEquals(subTask, retrievedSubTask);
        assertNull(taskManager.getSubTask(10));

    }

    @Test
    void getEpic() {
        assertNull(taskManager.getEpic(999));

        Epic epic = new Epic(1, "Epic 1", "Description of Epic 1", Status.NEW, new ArrayList<>());
        taskManager.addEpic(epic);
        Epic retrievedEpic = taskManager.getEpic(1);
        assertEquals(epic, retrievedEpic);

        assertNull(taskManager.getEpic(10));

    }

    @Test
    void getAllTasks() {
        assertNull(taskManager.getAllTasks());


        Task task = new Task(1, "Task 1", "Description of Task 1", Status.IN_PROGRESS, 5, LocalDate.now());
        Task task2 = new Task(2, "Task 2", "Description of Task 2", Status.IN_PROGRESS, 5, LocalDate.now());
        taskManager.addTask(task);
        taskManager.addTask(task2);

        ArrayList<Task> allTasks = taskManager.getAllTasks();
        assertEquals(2, allTasks.size());
        assertTrue(allTasks.contains(task));
        assertTrue(allTasks.contains(task2));
    }

    @Test
    void getAllEpics() {
        assertNull(taskManager.getAllEpics());

        Epic epic1 = new Epic(1, "Epic 1", "Description of Epic 1", Status.NEW, new ArrayList<>());
        Epic epic2 = new Epic(2, "Epic 2", "Description of Epic 2", Status.IN_PROGRESS, new ArrayList<>());
        taskManager.addEpic(epic1);
        taskManager.addEpic(epic2);

        ArrayList<Epic> allEpics = taskManager.getAllEpics();
        assertEquals(2, allEpics.size());
        assertTrue(allEpics.contains(epic1));
        assertTrue(allEpics.contains(epic2));
    }

    @Test
    void getAllSubTasks() {
        assertNull(taskManager.getAllSubTasks());

        Epic parentTask = new Epic(1, "Epic 1", "Description of Epic 1", Status.NEW, new ArrayList<>());
        taskManager.addEpic(parentTask);

        SubTask subTask1 = new SubTask(2, "Subtask 1", "Description of Subtask 1", Status.NEW, 1, 3, LocalDate.now());
        SubTask subTask2 = new SubTask(3, "Subtask 2", "Description of Subtask 2", Status.IN_PROGRESS, 1, 2, LocalDate.now().minusDays(1));
        taskManager.addSubTask(subTask1);
        taskManager.addSubTask(subTask2);

        ArrayList<SubTask> allSubTasks = taskManager.getAllSubTasks();
        assertEquals(2, allSubTasks.size());
        assertTrue(allSubTasks.contains(subTask1));
        assertTrue(allSubTasks.contains(subTask2));
    }

    @Test
    void clearAllTasks() {
        Task task1 = new Task(1, "Task 1", "Description of Task 1", Status.IN_PROGRESS, 5, LocalDate.now());
        Task task2 = new Task(2, "Task 2", "Description of Task 2", Status.NEW, 3, LocalDate.now().minusDays(1));
        taskManager.addTask(task1);
        taskManager.addTask(task2);

        ArrayList<Task> allTasks = taskManager.getAllTasks();
        assertEquals(2, allTasks.size());
        assertTrue(allTasks.contains(task1));
        assertTrue(allTasks.contains(task2));
    }

    @Test
    void clearAllEpics() {
        Epic epic1 = new Epic(1, "Epic 1", "Description of Epic 1", Status.NEW, new ArrayList<>());
        Epic epic2 = new Epic(2, "Epic 2", "Description of Epic 2", Status.IN_PROGRESS, new ArrayList<>());
        taskManager.addEpic(epic1);
        taskManager.addEpic(epic2);

        taskManager.clearAllEpics();

        ArrayList<Epic> allEpics = taskManager.getAllEpics();
        assertEquals(0, allEpics.size());
    }

    @Test
    void clearAllSubTasks() {
        Epic parentTask = new Epic(1, "Epic 1", "Description of Epic 1", Status.NEW, new ArrayList<>());
        taskManager.addEpic(parentTask);

        SubTask subTask1 = new SubTask(2, "Subtask 1", "Description of Subtask 1", Status.NEW, 1, 3, LocalDate.now());
        SubTask subTask2 = new SubTask(3, "Subtask 2", "Description of Subtask 2", Status.IN_PROGRESS, 1, 2, LocalDate.now().minusDays(1));
        taskManager.addSubTask(subTask1);
        taskManager.addSubTask(subTask2);

        taskManager.clearAllSubTasks();

        ArrayList<SubTask> allSubTasks = taskManager.getAllSubTasks();
        assertEquals(0, allSubTasks.size());
    }

    @Test
    void removeTask() {
        Task emptyTask = taskManager.getTask(999);
        assertNull(emptyTask);

        Task task = new Task(1, "Task 1", "Description of Task 1", Status.IN_PROGRESS, 5, LocalDate.now());
        taskManager.addTask(task);

        taskManager.removeTask(1);

        Task retrievedTask = taskManager.getTask(1);
        assertNull(retrievedTask);
    }

    @Test
    void removeEpic() {
        Task emptyEpic = taskManager.getEpic(999);
        assertNull(emptyEpic);


        Epic epic = new Epic(1, "Epic 1", "Description of Epic 1", Status.NEW, new ArrayList<>());
        taskManager.addEpic(epic);

        taskManager.removeEpic(1);

        Epic removedEpic = taskManager.getEpic(1);
        assertNull(removedEpic);
    }

    @Test
    void removeSubTask() {
        Task emptySubTTask = taskManager.getSubTask(999);
        assertNull(emptySubTTask);

        Epic parentTask = new Epic(1, "Epic 1", "Description of Epic 1", Status.NEW, new ArrayList<>());
        taskManager.addEpic(parentTask);

        SubTask subTask = new SubTask(2, "Subtask 1", "Description of Subtask 1", Status.NEW, 1, 3, LocalDate.now());
        taskManager.addSubTask(subTask);

        taskManager.removeSubTask(2);

        SubTask removedSubTask = taskManager.getSubTask(2);
        assertNull(removedSubTask);
    }

    @Test
    void getAnyTaskById() {
        assertNull(taskManager.getAnyTaskById(999));


        TaskManager taskManager = new InMemoryTaskManager();
        Task task = new Task(1, "Task 1", "Description of Task 1", Status.IN_PROGRESS, 5, LocalDate.now());
        taskManager.addTask(task);
        Task retrievedTask = taskManager.getAnyTaskById(1);
        assertNotNull(retrievedTask);
        assertEquals(task, retrievedTask);
        Epic epic = new Epic(2, "Epic 1", "Description of Epic 1", Status.NEW, new ArrayList<>());
        taskManager.addEpic(epic);
        Task retrievedEpic = taskManager.getAnyTaskById(2);
        assertNotNull(retrievedEpic);
        assertEquals(epic, retrievedEpic);
        SubTask subTask = new SubTask(3, "Subtask 1", "Description of Subtask 1", Status.NEW, 2, 3, LocalDate.now());
        taskManager.addSubTask(subTask);
        Task retrievedSubTask = taskManager.getAnyTaskById(3);

        assertNotNull(retrievedSubTask);
        assertEquals(subTask, retrievedSubTask);
    }

    @Test
    void updateEpicStatus() {

        TaskManager taskManager = new InMemoryTaskManager();
        Epic epic = new Epic(1, "Epic 1", "Description of Epic 1", Status.NEW, new ArrayList<>());
        taskManager.addEpic(epic);
        SubTask subTask1 = new SubTask(2, "Subtask 1", "Description of Subtask 1", Status.DONE, 1, 3, LocalDate.now());
        taskManager.addSubTask(subTask1);
        SubTask subTask2 = new SubTask(3, "Subtask 2", "Description of Subtask 2", Status.DONE, 1, 2, LocalDate.now().minusDays(1));
        taskManager.addSubTask(subTask2);
        taskManager.updateEpicStatus();
        Epic updatedEpic = taskManager.getEpic(1);
        assertEquals(Status.DONE, updatedEpic.getStatus());
    }
}