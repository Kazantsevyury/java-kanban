package controller;

import managers.Managers;
import managers.implementation.FileBackedTasksManager;
import utilities.FieldModifier;
import tests.Tester;
import utilities.IdGenerator;

public class AppController {
    private FileBackedTasksManager manager;
    private FieldModifier fieldModifier;
    private Tester tester;
    private IdGenerator idGenerator;

    public AppController() {
        manager  = Managers.getFileBackendTaskManager();
        fieldModifier = new FieldModifier(manager);
        tester = new Tester();
        idGenerator = new IdGenerator();
    }
    public void run() {
        tester.runTests(manager,fieldModifier);

    }
}