package tests;

import data.Epic;
import enums.TaskTypes;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EpicTest {

    @Test
    void testEpicConstructorAndGetters() {
        String title = "Epic Sample";
        String description = "This is an epic sample for testing.";
        int duration = 240;
        LocalDate startTime = LocalDate.of(2023, 7, 28);
        TaskTypes taskType = TaskTypes.EPIC;
        ArrayList<Integer> subTasks = new ArrayList<>();
        subTasks.add(1);
        subTasks.add(2);
        LocalDateTime endTime = LocalDateTime.of(2023, 7, 29, 12, 0);

        Epic epic = new Epic(title, description, duration, startTime, subTasks, endTime, taskType);

        assertEquals(title, epic.getTitle());
        assertEquals(description, epic.getDescription());
        assertEquals(duration, epic.getDuration());
        assertEquals(startTime, epic.getStartTime());
        assertEquals(taskType, epic.getTaskTypes());
        assertEquals(subTasks, epic.getSubTasks());
        assertEquals(endTime, epic.getEndTime());

        assertNotNull(epic.getTaskId());
        assertNotNull(epic.getStatus());
    }

    @Test
    void testSetEndTime() {
        Epic epic = new Epic("Epic Sample", "Description", 240, LocalDate.of(2023, 7, 28), new ArrayList<>(), null, TaskTypes.EPIC);

        LocalDateTime newEndTime = LocalDateTime.of(2023, 7, 30, 16, 30);
        epic.setEndTime(newEndTime);

        assertEquals(newEndTime, epic.getEndTime());
    }

    @Test
    void testToString() {
        ArrayList<Integer> subTasks = new ArrayList<>();
        subTasks.add(1);
        subTasks.add(2);
        LocalDateTime endTime = LocalDateTime.of(2023, 7, 29, 12, 0);
        Epic epic = new Epic("Epic Sample", "Description", 240, LocalDate.of(2023, 7, 28), subTasks, endTime, TaskTypes.EPIC);

        String expectedString = "Epic ID: " + epic.getTaskId() + "\nTitle: Epic Sample\nDescription: Description\nStatus: NEW\nDuration: 240 minutes\nStart Time: 2023-07-28\nEnd Time: 2023-07-29T12:00\nSubtasks: [1, 2]";
        assertEquals(expectedString, epic.toString());
    }

    @Test
    void testToCSV() {
        ArrayList<Integer> subTasks = new ArrayList<>();
        subTasks.add(1);
        subTasks.add(2);
        Epic epic = new Epic("Epic Sample", "Description", 240, LocalDate.of(2023, 7, 28), subTasks, null, TaskTypes.EPIC);

        String expectedCSV = epic.getTaskId() + ",Epic Sample,Description,NEW,2023-07-28,240,EPIC,1,2";
        assertEquals(expectedCSV, epic.toCSV());
    }

    @Test
    void testGetTaskTypes() {
        Epic epic = new Epic("Epic Sample", "Description", 240, LocalDate.of(2023, 7, 28), new ArrayList<>(), null, TaskTypes.EPIC);

        assertEquals(TaskTypes.EPIC, epic.getTaskTypes());
    }

    @Test
    void testSetSubTasks() {
        Epic epic = new Epic("Epic Sample", "Description", 240, LocalDate.of(2023, 7, 28), new ArrayList<>(), null, TaskTypes.EPIC);

        ArrayList<Integer> newSubTasks = new ArrayList<>();
        newSubTasks.add(3);
        newSubTasks.add(4);
        epic.setSubTasks(newSubTasks);

        assertEquals(newSubTasks, epic.getSubTasks());
    }

    @Test
    void testGetSubTasks() {
        ArrayList<Integer> subTasks = new ArrayList<>();
        subTasks.add(1);
        subTasks.add(2);
        Epic epic = new Epic("Epic Sample", "Description", 240, LocalDate.of(2023, 7, 28), subTasks, null, TaskTypes.EPIC);

        assertEquals(subTasks, epic.getSubTasks());
    }
}
