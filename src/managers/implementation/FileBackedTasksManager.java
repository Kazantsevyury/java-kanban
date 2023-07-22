package managers.implementation;

import data.Epic;
import data.SubTask;
import data.Task;
import enums.Status;
import enums.TaskTypes;
import exceptions.ManagerSaveException;

import java.io.*;
import java.util.ArrayList;

public class FileBackedTasksManager extends InMemoryTaskManager {

    File csvFile = new File(CSV_FILE_PATH);

    private static final String CSV_FILE_PATH = "C:\\Users\\Юра\\dev\\java-kanban\\src\\resources\\example.csv";
    public FileBackedTasksManager() {
        super();
        loadTasksFromCsv(csvFile);
    }
    public void loadTasksFromCsv(File file) {
        if (csvFile.exists() && csvFile.length() > 1) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH));
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
                        continue;
                    }

                    if (!isHistorySection) {
                        String[] values = line.split(",");

                        int taskId = Integer.parseInt(values[0]);
                        String title = values[1];
                        String description = values[2];
                        Status status = Status.valueOf(values[3]);
                        TaskTypes type = TaskTypes.valueOf(values[4]);

                        switch (type) {
                            case TASK:
                                Task task = new Task(taskId, title, description, status);
                                addTask(task);
                                break;
                            case EPIC:
                                ArrayList<Integer> epicSubTasks = new ArrayList<>();
                                for (int i = 5; i < values.length; i++) {
                                    epicSubTasks.add(Integer.parseInt(values[i]));
                                }
                                Epic epic = new Epic(taskId, title, description, status, epicSubTasks);
                                addEpic(epic);
                                break;
                            case SUBTASK:
                                int parentEpicId = Integer.parseInt(values[5]);
                                SubTask subTask = new SubTask(taskId, title, description, status, parentEpicId);
                                addSubTask(subTask);
                                break;
                            default:
                                System.out.println("Неизвестный тип задачи: " + type);
                                break;
                        }
                    } else {
                        if (!line.isEmpty()) {
                            String[] historyTaskIds = line.split(",");
                            for (String taskId : historyTaskIds) {
                                int taskIdInt = Integer.parseInt(taskId.trim());
                                getHistoryManager().add(super.getTaskById(taskIdInt));
                            }
                        }
                    }
                }

                reader.close();
            } catch (IOException e) {
                System.out.println("Ошибка при загрузке задач из CSV файла: " + e.getMessage());
            }
        }
    }
    public static FileBackedTasksManager loadFromFile(File file) {
        FileBackedTasksManager manager = new FileBackedTasksManager();
        manager.loadTasksFromCsv(file);
        return manager;
    }
    public void saveTasksToCsv() {
        try {
            FileWriter writer = new FileWriter(CSV_FILE_PATH);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write("id,title,description,status,typ,SUBTASKS/PARENTEPICID");
            bufferedWriter.newLine();

            for (Task tasks : super.getAllTasks()) {
                bufferedWriter.write(tasks.toCSV());
                bufferedWriter.newLine();
            }
            for (Task tasks : super.getAllEpics()) {
                bufferedWriter.write(tasks.toCSV());
                bufferedWriter.newLine();
            }
            for (Task tasks : super.getAllSubTasks()) {
                bufferedWriter.write(tasks.toCSV());
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
}