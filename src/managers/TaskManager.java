package managers;

import data.Epic;
import data.SubTask;
import data.Task;
import managers.implementation.FileBackedTasksManager;
import utilities.FieldModifier;

import java.util.ArrayList;

public interface TaskManager {
    void addTask(Task task) ;
    void addSubTask(SubTask subTask) ;
    void addEpic(Epic epic) ;

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
}
