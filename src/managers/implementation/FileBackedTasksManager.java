package managers.implementation;

import data.Epic;
import data.SubTask;
import data.Task;
import enums.Status;
import enums.TaskTypes;
import exceptions.ManagerSaveException;
import utilities.IdGenerator;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class FileBackedTasksManager extends InMemoryTaskManager {
    protected final String CSV_FILE_PATH;
    protected File csvFile ;
    public FileBackedTasksManager(String CSV_FILE_PATH) {
        super();
        this.CSV_FILE_PATH = CSV_FILE_PATH;
        this.csvFile = new File(CSV_FILE_PATH);
        loadTasksFromCsv(csvFile);

    }

    public void loadTasksFromCsv(File file) {
        if (csvFile.exists() && csvFile.length() > 1) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                boolean isHistorySection = false;
                boolean firstLineSkipped = false;

                while ((line = reader.readLine()) != null) {
                    if (!firstLineSkipped) {
                        firstLineSkipped = true;
                        continue;
                    }
                    if (line.isEmpty()) {
                        isHistorySection = true;
                    }

                    if (!isHistorySection) {
                        parseTasks(line);
                    } else {
                        parseHistory(line);
                    }
                }
                loadIdsToGenerator();

            } catch (IOException e) {
                System.out.println("Ошибка при загрузке задач из CSV файла: " + e.getMessage());
            }
        }
    }

    private void parseTasks(String line) {
        String[] values = line.split(",");

        int taskId = Integer.parseInt(values[0]);
        String title = values[1];
        String description = values[2];
        Status status = Status.valueOf(values[3]);
        LocalDate startTime = parseLocalDate(values[4]);
        int duration = Integer.parseInt(values[5]);
        TaskTypes type = TaskTypes.valueOf(values[6]);

        switch (type) {
            case TASK:
                Task task = new Task(taskId, title, description, status, duration, startTime);

                super.addTask(task);
                break;
            case EPIC:
                parseEpic(values, taskId, title, description, status, duration, startTime);
                break;
            case SUBTASK:
                int parentEpicId = Integer.parseInt(values[7]);
                SubTask subTask = new SubTask(taskId, title, description, status, parentEpicId, duration, startTime);
                super.addSubTask(subTask);
                break;
            default:
                System.out.println("Неизвестный тип задачи: " + type);
                break;
        }
    }

    private void parseEpic(String[] values, int taskId, String title, String description, Status status, int duration, LocalDate startTime) {
        Epic epic;

        if (values[7].equals("null")) {
            epic = new Epic(taskId, title, description, status, duration, startTime);
        } else {
            ArrayList<Integer> epicSubTasks = new ArrayList<>();
            for (int i = 7; i < values.length; i++) {
                epicSubTasks.add(Integer.parseInt(values[i]));
            }
            epic = new Epic(taskId, title, description, status, epicSubTasks);
        }

        super.addEpic(epic);
    }

    private void parseHistory(String line) {
        if (!line.isEmpty()) {
            if (line.contains(",")) {
                String[] historyTaskIds = line.split(",");
                for (String taskId : historyTaskIds) {
                    int taskIdInt = Integer.parseInt(taskId.trim());
                    Task task = super.getAnyTaskById(taskIdInt);
                    if (task != null) {
                        super.getHistoryManager().add(task);
                    } else {
                        System.out.println("Задачи с идентификатором " + taskIdInt + " не существует.");
                    }
                }
            } else {
                int taskIdInt = Integer.parseInt(line.trim());
                Task task = super.getAnyTaskById(taskIdInt);
                if (task != null) {
                    super.getHistoryManager().add(task);
                } else {
                    System.out.println("Задачи с идентификатором " + taskIdInt + " не существует.");
                }
            }
        }
    }

    private LocalDate parseLocalDate(String value) {
        if (!(value.equals("null") )) {
            return LocalDate.parse(value);
        }
        return null;
    }

    public void saveTasksToCsv() {
        try {
            FileWriter writer = new FileWriter(CSV_FILE_PATH);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write("id,title,description,status,startTime,duration,typ,SUBTASKS/PARENTEPICID");
            bufferedWriter.newLine();

            for (Task task : super.getAllTasks()) {
                bufferedWriter.write(task.toCSV());
                bufferedWriter.newLine();
            }
            for (Task epic : super.getAllEpics()) {
                bufferedWriter.write(epic.toCSV());
                bufferedWriter.newLine();
            }
            for (Task subTask : super.getAllSubTasks()) {
                bufferedWriter.write(subTask.toCSV());
                bufferedWriter.newLine();
            }

            bufferedWriter.newLine();

            boolean isFirstHistoryTask = true;
            for (Task history : super.getHistoryManager().getHistory()) {
                if (isFirstHistoryTask) {
                    isFirstHistoryTask = false;
                } else {
                    bufferedWriter.write(",");
                }
                bufferedWriter.write(Integer.toString(history.getTaskId()));
            }

            bufferedWriter.close();

        } catch (IOException e) {
            throw new ManagerSaveException("Ошибка при сохранении задач в CSV файл: " + e.getMessage());
        }
    }

    public void loadIdsToGenerator() {
            Set<Integer> loadedIds = new HashSet<>();

            for (Task task : super.getAllTasks()) {
                loadedIds.add(task.getTaskId());
            }

            for (Task epic : super.getAllEpics()) {
                loadedIds.add(epic.getTaskId());
            }

            for (Task subTask : super.getAllSubTasks()) {
                loadedIds.add(subTask.getTaskId());
            }
            IdGenerator.loadUsedIds(loadedIds);
    }

    @Override
    public void addSubTask(SubTask subTask) {
        super.addSubTask(subTask);
        saveTasksToCsv();
    }

    @Override
    public void addTask(Task task) {
        super.addTask(task);
        saveTasksToCsv();
    }

    @Override
    public void addEpic(Epic epic) {
        super.addEpic(epic);
        saveTasksToCsv();
    }

    @Override
    public void removeTask(int taskId) {
        super.removeTask(taskId);
        saveTasksToCsv();
    }

    @Override
    public void removeSubTask(int subTaskId) {
        super.removeSubTask(subTaskId);
        saveTasksToCsv();
    }

    @Override
    public void removeEpic(int epicId) {
        super.removeEpic(epicId);
        saveTasksToCsv();
    }

    public void clearCsvFileExample() {
            String filePath = "test_empty_task_list.csv";

            try {
                FileWriter fileWriter = new FileWriter(filePath, false);

                fileWriter.write("");

                fileWriter.close();

                System.out.println("Содержимое файла успешно очищено.");
            } catch (IOException e) {
                System.out.println("Ошибка при очистке файла: " + e.getMessage());
            }
    }
}