package tests;

import data.Epic;
import enums.Status;
import managers.Managers;
import managers.implementation.FileBackedTasksManager;
import utilities.FieldModifier;
import data.Task;
import data.SubTask;
import java.io.File;
import java.time.LocalDate;


public class Tester {
    private static final String CSV_FILE_PATH = "C:\\Users\\Юра\\dev\\java-kanban\\src\\resources\\example.csv";

    public void runTests(FileBackedTasksManager manager, FieldModifier fieldModifier){
        File csvFile = new File(CSV_FILE_PATH);
        Task epicX = new Task("Name","description");
        manager.addTask(epicX);
        manager.saveTasksToCsv();
    }
}