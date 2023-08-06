package managers.implementation;

import com.google.gson.*;
import data.Epic;
import data.SubTask;
import data.Task;
import http.KVTaskClient;
import managers.HistoryManager;
import managers.implementation.FileBackedTasksManager;
import utilities.LocalDateTimeAdapter;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.util.stream.Collectors;

public class HTTPTaskManager extends FileBackedTasksManager {

    final static String KEY_TASKS = "tasks";
    final static String KEY_SUBTASKS = "subtasks";
    final static String KEY_EPICS = "epics";
    final static String KEY_HISTORY = "history";
    final KVTaskClient client;
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(Instant.class, new LocalDateTimeAdapter()).create();

        public HTTPTaskManager( String path) throws IOException, InterruptedException {
            client = new KVTaskClient(path);

            JsonElement jsonTasks = JsonParser.parseString(client.load(KEY_TASKS));
            if (!jsonTasks.isJsonNull()) {
            JsonArray jsonTasksArray = jsonTasks.getAsJsonArray();
            for (JsonElement jsonTask : jsonTasksArray) {
                Task task = gson.fromJson(jsonTask, Task.class);
                this.addTask(task);
            }
        }

            JsonElement jsonEpics = JsonParser.parseString(client.load(KEY_EPICS));
            if (!jsonEpics.isJsonNull()) {
            JsonArray jsonEpicsArray = jsonEpics.getAsJsonArray();
            for (JsonElement jsonEpic : jsonEpicsArray) {
                Epic task = gson.fromJson(jsonEpic, Epic.class);
                this.addEpic(task);
            }
        }

            JsonElement jsonSubtasks = JsonParser.parseString(client.load(KEY_SUBTASKS));
            if (!jsonSubtasks.isJsonNull()) {
            JsonArray jsonSubtasksArray = jsonSubtasks.getAsJsonArray();
            for (JsonElement jsonSubtask : jsonSubtasksArray) {
                SubTask task = gson.fromJson(jsonSubtask, SubTask.class);
                this.addSubTask(task);
            }
        }

            JsonElement jsonHistoryList = JsonParser.parseString(client.load(KEY_HISTORY));
            if (!jsonHistoryList.isJsonNull()) {
            JsonArray jsonHistoryArray = jsonHistoryList.getAsJsonArray();
            for (JsonElement jsonTaskId : jsonHistoryArray) {
                int taskId = jsonTaskId.getAsInt();
                if (this.getSubTasks().containsKey(taskId)) {
                    this.getSubTask(taskId);
                } else if (this.getEpics().containsKey(taskId)) {
                    this.getEpic(taskId);
                } else if (this.getTasks().containsKey(taskId)) {
                    this.getTask(taskId);
                }
            }
        }
        }

    public void save() {
        client.put(KEY_TASKS, gson.toJson(getTasks().values()));
        client.put(KEY_SUBTASKS, gson.toJson(getSubTasks().values()));
        client.put(KEY_EPICS, gson.toJson(getEpics()));
        client.put(KEY_HISTORY, gson.toJson(this.getHistoryManager().getHistory()
                .stream()
                .map(Task::getTaskId)
                .collect(Collectors.toList())));
    }

}