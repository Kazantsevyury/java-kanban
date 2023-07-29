package tests;

import data.Task;
import enums.Status;
import enums.TaskTypes;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class TaskTest {

    @Test
    void testTaskConstructorAndGetters() {
        // Test the constructors and getters
        String title = "Sample Task";
        String description = "This is a sample task for testing.";
        Status status = Status.NEW;
        int duration = 120;
        LocalDate startTime = LocalDate.of(2023, 7, 28);
        Task task = new Task(title, description, status, duration, startTime);

        assertEquals(title, task.getTitle());
        assertEquals(description, task.getDescription());
        assertEquals(status, task.getStatus());
        assertEquals(duration, task.getDuration());
        assertEquals(startTime, task.getStartTime());

        assertNotNull(task.getTaskId());
        assertNotNull(task.getTaskTypes());
    }

    @Test
    void testSetDuration() {
        // Test the setDuration method
        Task task = new Task("Sample Task", "Description", Status.NEW, 120, LocalDate.of(2023, 7, 28));

        int newDuration = 180;
        task.setDuration(newDuration);

        assertEquals(newDuration, task.getDuration());
    }

    @Test
    void testSetStartTime() {
        // Test the setStartTime method
        Task task = new Task("Sample Task", "Description", Status.NEW, 120, LocalDate.of(2023, 7, 28));

        LocalDate newStartTime = LocalDate.of(2023, 7, 29);
        task.setStartTime(newStartTime);

        assertEquals(newStartTime, task.getStartTime());
    }

    @Test
    void  testGetEndTime() {
        // Test the getEndTime method
        int duration = 120;
        LocalDate startTime = LocalDate.of(2023, 7, 28);
        Task task = new Task("Sample Task", "Description", Status.NEW, duration, startTime);

        LocalDateTime expectedEndTime = startTime.atTime(LocalTime.of(0, 0)).plusMinutes(duration);
        assertEquals(expectedEndTime, task.getEndTime());
    }

    @Test
    void testToString() {
        // Test the toString method
        Task task = new Task("Sample Task", "Description", Status.NEW, 120, LocalDate.of(2023, 7, 28));

        String expectedString = "Task ID: " + task.getTaskId() + "\nTitle: Sample Task\nDescription: Description\nStatus: NEW\nDuration: 120 minutes\nStart Time: 2023-07-28";
        assertEquals(expectedString, task.toString());
    }

    @Test
    void testToCSV() {
        // Test the toCSV method
        Task task = new Task("Sample Task", "Description", Status.NEW, 120, LocalDate.of(2023, 7, 28));

        String expectedCSV = task.getTaskId() + ",Sample Task,Description,NEW,2023-07-28,120,TASK";
        assertEquals(expectedCSV, task.toCSV());
    }
}
