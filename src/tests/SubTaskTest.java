package tests;

import data.SubTask;
import enums.Status;
import enums.TaskTypes;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SubTaskTest {

    @Test
    void testSubTaskConstructorAndGetters() {
        // Test the constructors and getters of the SubTask class
        String title = "SubTask Sample";
        String description = "This is a subtask sample for testing.";
        int parentEpicId = 123;
        int duration = 60;
        LocalDate startTime = LocalDate.of(2023, 7, 28);
        Status status = Status.NEW;

        SubTask subTask = new SubTask(title, description, parentEpicId, duration, startTime);

        assertEquals(title, subTask.getTitle());
        assertEquals(description, subTask.getDescription());
        assertEquals(parentEpicId, subTask.getParentEpicId());
        assertEquals(duration, subTask.getDuration());
        assertEquals(startTime, subTask.getStartTime());
        assertEquals(status, subTask.getStatus());

        assertNotNull(subTask.getTaskId());
        assertNotNull(subTask.getTaskTypes());
    }

    @Test
    void testToString() {
        // Test the toString method
        int parentEpicId = 123;
        SubTask subTask = new SubTask("SubTask Sample", "Description", Status.IN_PROGRESS, parentEpicId, 120, LocalDate.of(2023, 7, 28));

        String expectedString = "SubTask ID: " + subTask.getTaskId() + "\nTitle: SubTask Sample\nDescription: Description\nStatus: IN_PROGRESS\nParentEpicID: " + parentEpicId + "\nDuration: 120 minutes\nStart Time: 2023-07-28";
        assertEquals(expectedString, subTask.toString());
    }

    @Test
    void testToCSV() {
        // Test the toCSV method
        int parentEpicId = 123;
        SubTask subTask = new SubTask("SubTask Sample", "Description", Status.IN_PROGRESS, parentEpicId, 120, LocalDate.of(2023, 7, 28));

        String expectedCSV = subTask.getTaskId() + ",SubTask Sample,Description,IN_PROGRESS,2023-07-28,120,SUBTASK," + parentEpicId;
        assertEquals(expectedCSV, subTask.toCSV());
    }

    @Test
    void testGetTaskTypes() {
        // Test the getTaskTypes method
        SubTask subTask = new SubTask("SubTask Sample", "Description", 123);

        assertEquals(TaskTypes.SUBTASK, subTask.getTaskTypes());
    }

    @Test
    void testSetParentEpicId() {
        // Test the setParentEpicId method
        SubTask subTask = new SubTask("SubTask Sample", "Description", 123);

        int newParentEpicId = 456;
        subTask.setParentEpicId(newParentEpicId);

        assertEquals(newParentEpicId, subTask.getParentEpicId());
    }
}
