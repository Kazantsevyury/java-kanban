package tester;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import data.Epic;
import data.SubTask;
import enums.Field;
import enums.Status;
import http.HttpTaskServer;
import http.KVServer;
import managers.HistoryManager;
import managers.Managers;
import managers.TaskManager;
import managers.implementation.FileBackedTasksManager;
import data.Task;
import utilities.LocalDateTimeAdapter;

import java.io.File;
import java.time.Instant;

public class Tester {
public void runHttpTests(FileBackedTasksManager manager){
    KVServer server;
    try {
        HttpTaskServer httpTaskServer = new HttpTaskServer();
        httpTaskServer.start();
        Gson gson = new GsonBuilder().registerTypeAdapter(Instant.class, new LocalDateTimeAdapter()).create();

        server = new KVServer();
        server.start();
        HistoryManager historyManager = Managers.getDefaultHistory();
        TaskManager httpTaskManager = Managers.getDefault(historyManager);

        Task task1 = new Task("Task 1", "descriptionT" );
        httpTaskManager.createTask(task1);

        Epic epic1 = new Epic("Epic1", "descriptionE");
        httpTaskManager.createEpic(epic1);

        SubTask subtask1 = new SubTask("ST1", "descriptionST", epic1.getTaskId());
        httpTaskManager.createSubtask(subtask1);


        httpTaskManager.getTask(task1.getTaskId());
        httpTaskManager.getEpic(epic1.getTaskId());
        httpTaskManager.getSubTask(subtask1.getTaskId());

        System.out.println("Печать всех задач");
        System.out.println(gson.toJson(httpTaskManager.getAllTasks()));
        System.out.println("Печать всех эпиков");
        System.out.println(gson.toJson(httpTaskManager.getAllEpics()));
        System.out.println("Печать всех подзадач");
        System.out.println(gson.toJson(httpTaskManager.getAllSubTasks()));
        System.out.println("Загруженный менеджер");
        System.out.println(httpTaskManager);
        server.stop();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
public void runFileBackedTaskManagerTests(FileBackedTasksManager manager) {

        String CSV_FILE_PATH = "src/resources/example.csv";

        File csvFile = new File(CSV_FILE_PATH);

        manager.loadTasksFromCsv(csvFile);

        Epic epicX = new Epic("EpicX", "Description EX");
        SubTask subTaskZ = new SubTask("subTaskY","Description subTaskY",epicX.getTaskId());
        Task taskY = new Task("task Y","Description task Y");
        manager.addEpic(epicX);
        manager.addSubTask(subTaskZ);
        manager.addTask(taskY);
        manager.getAnyTaskById(epicX.getTaskId());
        manager.getFieldModifier().updateEpic(epicX, Field.ID,11111);
        SubTask subTaskZy = new SubTask("subTaskYy","Description subTaskYy",epicX.getTaskId());
        manager.addSubTask(subTaskZy);
        manager.updateEpicStatus();
        manager.updateEpicDurationAndStartTimeAndEndTime();
        manager.getAnyTaskById(epicX.getTaskId());
        for (Task task :manager.getPrioritizedTasks()) {
            System.out.println(task.toString());
        }



        manager.saveTasksToCsv();
    }
}