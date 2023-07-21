package controller;

import managers.Managers;
import managers.implementation.FileBackedTasksManager;
import utilities.FieldModifier;
import tests.Tester;

public class AppController {
    private FileBackedTasksManager manager;
    private FieldModifier fieldModifier;
    private Tester tester;

    public AppController() {
        manager  = Managers.getFileBackendTaskManager();
        fieldModifier = new FieldModifier(manager);
        tester = new Tester();
    }
    public void run() {
        tester.runTests(manager,fieldModifier);

    }
}