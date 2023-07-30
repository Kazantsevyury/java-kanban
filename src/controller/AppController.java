package controller;

import managers.Managers;
import managers.implementation.FileBackedTasksManager;
import utilities.FieldModifier;
import tester.Tester;
import utilities.IdGenerator;

public class AppController {
    private FileBackedTasksManager manager;
    // private FieldModifier fieldModifier;
    private Tester tester;
    String CSV_FILE_PATH = "src/resources/example.csv";

    public AppController() {
        manager  = Managers.getFileBackendTaskManager(CSV_FILE_PATH);
        //fieldModifier = new FieldModifier(manager);
        tester = new Tester(CSV_FILE_PATH);
    }
    public void run() {
        tester.runTests(manager);

    }
}