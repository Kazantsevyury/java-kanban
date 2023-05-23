package controller;

import Managers.Manager;
import logic.FieldModifier;
import logic.Tester;

public class AppController {
    private Manager manager;
    private FieldModifier fieldModifier;
    private Tester tester;

    public AppController() {
        manager = new Manager();
        fieldModifier = new FieldModifier(manager);
        tester = new Tester();
    }
    public void run() {
        tester.runTests(manager,fieldModifier);
    }
}