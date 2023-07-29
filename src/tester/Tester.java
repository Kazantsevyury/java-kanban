package tester;

import data.Epic;
import managers.implementation.FileBackedTasksManager;
import utilities.FieldModifier;
import data.Task;

import java.io.File;


public class Tester {
    private static final String CSV_FILE_PATH = "C:\\Users\\Юра\\dev\\java-kanban\\src\\resources\\example.csv";

    public void runTests(FileBackedTasksManager manager, FieldModifier fieldModifier){
        File csvFile = new File(CSV_FILE_PATH);

        Epic epicX = new Epic("Name","description");

        manager.addEpic(epicX);
        manager.getAnyTaskById(444);
        manager.getAnyTaskById(epicX.getTaskId());

        manager.saveTasksToCsv();

    }
}