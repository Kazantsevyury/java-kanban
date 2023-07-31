package tests;

import managers.TaskManager;
import managers.implementation.InMemoryTaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InMemoryTaskManagerTest extends TaskManagerTest {

    private TaskManager taskManager;

    @BeforeEach
    void setUp() {
        taskManager = new InMemoryTaskManager();
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
    void addTask() {
    }
    @Test
    void addSubTask() {
    }
    @Test
    void addEpic() {
    }
    @Test
    void removeEpic() {
    }
    @Test
    void removeSubTask() {
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
    void getAnyTaskById() {
    }
    @Test
    void updateEpicStatus() {
    }
}