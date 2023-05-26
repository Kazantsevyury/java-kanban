package controller;

import Managers.InMemoryTaskManager;
import logic.FieldModifier;
import logic.Tester;

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