package tests;

import data.Epic;
import data.SubTask;
import data.Task;
import enums.Field;
import enums.Status;
import managers.implementation.InMemoryTaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.FieldModifier;

import java.time.LocalDate;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryTaskManagerTest extends TaskManagerTest {

    private InMemoryTaskManager taskManager;
    @BeforeEach
    void setUp() {
        taskManager = new InMemoryTaskManager();
        setTaskManager(taskManager);
    }
    @Test
    void addAllTasksToPrioritizedSet() {
        Task task = new Task(1, "Task 1", "Description of Task 1", Status.IN_PROGRESS, 5, LocalDate.now());
        SubTask subTask = new SubTask("Subtask 1", "Description of Subtask 1", 1, 3, LocalDate.now());
        Epic epic = new Epic("Epic 1", "Description of Epic 1");

        taskManager.addTask(task);
        taskManager.addSubTask(subTask);
        taskManager.addEpic(epic);

        assertTrue(taskManager.getPrioritizedTasks().contains(task));
        assertTrue(taskManager.getPrioritizedTasks().contains(subTask));
        assertTrue(taskManager.getPrioritizedTasks().contains(epic));
    }

    @Test
    void getFieldModifier() {
        FieldModifier fieldModifier = taskManager.getFieldModifier();
        assertNotNull(fieldModifier);

        Task task = new Task(1, "Task 1", "Description of Task 1", Status.IN_PROGRESS, 5, LocalDate.now());
        taskManager.addTask(task);
        fieldModifier.updateTask(task, Field.DESCRIPTION, "Updated description");

        Task retrievedTask = taskManager.getTask(1);
        assertEquals("Updated description", retrievedTask.getDescription());
    }

    @Test
    void getPrioritizedTasks() {
        LocalDate now = LocalDate.now();
        Task task1 = new Task(1, "Task 1", "Description of Task 1", Status.IN_PROGRESS, 5, now.minusDays(2));
        Task task2 = new Task(2, "Task 2", "Description of Task 2", Status.IN_PROGRESS, 5, now);
        Task task3 = new Task(3, "Task 3", "Description of Task 3", Status.IN_PROGRESS, 5, now.plusDays(1));

        taskManager.addTask(task2);
        taskManager.addTask(task1);
        taskManager.addTask(task3);

        TreeSet<Task> prioritizedTasks = taskManager.getPrioritizedTasks();
        assertEquals(3, prioritizedTasks.size());
        assertEquals(task1, prioritizedTasks.first());
        assertEquals(task3, prioritizedTasks.last());
    }
}