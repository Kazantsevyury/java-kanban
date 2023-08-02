package tester;

import data.Epic;
import data.SubTask;
import enums.Field;
import managers.implementation.FileBackedTasksManager;
import utilities.FieldModifier;
import data.Task;
import java.io.File;
public class Tester {
    String CSV_FILE_PATH;

    public Tester(String CSV_FILE_PATH) {
        this.CSV_FILE_PATH = CSV_FILE_PATH;
    }
    public void runTests(FileBackedTasksManager manager) {
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