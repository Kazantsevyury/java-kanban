package controller;

import managers.implementation.HTTPTaskManager;
import managers.Managers;
import managers.implementation.InMemoryHistoryManager;
import utilities.FieldModifier;
import tester.Tester;

import java.io.IOException;

public class AppController {
    private final HTTPTaskManager manager;
    private final FieldModifier fieldModifier;
    private final Tester tester;
    private InMemoryHistoryManager inMemoryHistoryManager;

    public AppController() throws IOException, InterruptedException {
        manager = Managers.getDefault(inMemoryHistoryManager);
        fieldModifier = new FieldModifier(manager);
        tester = new Tester();
    }

    public void run() {

        tester.runHttpTests(manager);
    }
}