package controller;

import manag.InMemoryTaskManager;
import utilities.FieldModifier;
import tests.Tester;

public class AppController {
    private InMemoryTaskManager inMemoryTaskManager;
    private FieldModifier fieldModifier;
    private Tester tester;

    public AppController() {
        inMemoryTaskManager = new InMemoryTaskManager();
        fieldModifier = new FieldModifier(inMemoryTaskManager);
        tester = new Tester();
    }
    public void run() {
        tester.runTests(inMemoryTaskManager,fieldModifier);
    }
}