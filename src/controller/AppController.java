package controller;

import managers.Managers;
import managers.implementation.FileBackedTasksManager;
import managers.implementation.InMemoryHistoryManager;
import utilities.FieldModifier;
import tester.Tester;

public class AppController {
    private final FileBackedTasksManager manager;
    private final FieldModifier fieldModifier;
    private final Tester tester;
    private InMemoryHistoryManager inMemoryHistoryManager;
    protected static String CSV_FILE_PATH = "src/resources/example.csv";

    public AppController() {
        manager  = Managers.getFileBackendTaskManager(CSV_FILE_PATH);
        fieldModifier = new FieldModifier(manager);
        tester = new Tester(CSV_FILE_PATH);
    }
    public void run() {
        tester.runTests(manager);

    }
}