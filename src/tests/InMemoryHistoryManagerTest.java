package tests;

import managers.implementation.InMemoryHistoryManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import data.Task;
import enums.Status;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryHistoryManagerTest {
    private InMemoryHistoryManager historyManager;

    @BeforeEach
    void setUp() {
        historyManager = new InMemoryHistoryManager();
    }

    @Test
    void testAddAndGetHistory() {
        Task task1 = new Task("Задача 1", "Описание задачи 1");
        Task task2 = new Task("Задача 2", "Описание задачи 2");

        historyManager.add(task1);
        historyManager.add(task2);

        ArrayList<Task> history = historyManager.getHistory();
        assertEquals(2, history.size());
        assertTrue(history.contains(task1));
        assertTrue(history.contains(task2));
    }

    @Test
    void testAddDuplicateTask() {
        Task task1 = new Task("Задача 1", "Описание задачи 1");

        historyManager.add(task1);
        historyManager.add(task1);

        ArrayList<Task> history = historyManager.getHistory();
        assertEquals(1, history.size());
    }

    @Test
    void testRemoveNonExistingTask() {
        Task task1 = new Task("Задача 1", "Описание задачи 1");

        historyManager.add(task1);
        int initialSize = historyManager.getHistory().size();

        historyManager.remove(-1);

        ArrayList<Task> history = historyManager.getHistory();
        assertEquals(initialSize, history.size());
    }
    @Test
    void testPrinterWithTasks() {
        Task task1 = new Task("Задача 1", "Описание задачи 1");
        Task task2 = new Task("Задача 2", "Описание задачи 2");

        historyManager.add(task1);
        historyManager.add(task2);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        historyManager.printer();
        System.setOut(originalOut);

        String printedOutput = outputStream.toString();
        assertTrue(printedOutput.contains(task1.toString()));
        assertTrue(printedOutput.contains(task2.toString()));
    }
    @Test
    void testRemoveExistingTask() {
        Task task1 = new Task("Задача 1", "Описание задачи 1");

        historyManager.add(task1);
        int initialSize = historyManager.getHistory().size();

        historyManager.remove(task1.getTaskId());

        ArrayList<Task> history = historyManager.getHistory();
        assertEquals(initialSize - 1, history.size());
        assertFalse(history.contains(task1));
    }
    @Test
    void testRemoveFromHistory() {
        Task task1 = new Task("Задача 1", "Описание задачи 1");
        Task task2 = new Task("Задача 2", "Описание задачи 2");
        Task task3 = new Task("Задача 3", "Описание задачи 3");

        historyManager.add(task1);
        historyManager.add(task2);
        historyManager.add(task3);

        historyManager.remove(task2.getTaskId());

        ArrayList<Task> history = historyManager.getHistory();
        assertEquals(2, history.size());
        assertTrue(history.contains(task1));
        assertFalse(history.contains(task2));
        assertTrue(history.contains(task3));

        historyManager.remove(task3.getTaskId());

        history = historyManager.getHistory();
        assertEquals(1, history.size());
        assertTrue(history.contains(task1));
        assertFalse(history.contains(task3));

        historyManager.remove(task1.getTaskId());

        history = historyManager.getHistory();
        assertTrue(history.isEmpty());
    }
}