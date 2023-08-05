package managers;

import data.Epic;
import data.SubTask;
import data.Task;
import utilities.FieldModifier;

import java.util.ArrayList;
import java.util.List;

public interface TaskManager {
    void addTask(Task task);

    void addSubTask(SubTask subTask);

    void addEpic(Epic epic);
    Task createTask(Task task);

    Epic createEpic(Epic epic);

    SubTask createSubtask(SubTask subtask);

    Task getTask(int taskId);

    SubTask getSubTask(int subTaskId);

    Epic getEpic(int epicId);

    ArrayList<Task> getAllTasks();

    ArrayList<Epic> getAllEpics();

    ArrayList<SubTask> getAllSubTasks();


    void clearAllTasks();

    void clearAllEpics();

    void clearAllSubTasks();

    void removeTask(int taskId);

    void removeEpic(int epicId);

    void removeSubTask(int subTaskId);

    HistoryManager getHistoryManager();

    FieldModifier getFieldModifier();

    Task getAnyTaskById(int taskId);

    void updateEpicStatus();
    void updateTask(Task task);

    void updateEpic(Epic epic);

    void updateSubtask(SubTask subtask);

    void updateEpicDurationAndStartTimeAndEndTime();
    List<Task> getPrioritizedTasks();


}