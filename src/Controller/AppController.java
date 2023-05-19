package controller;

import data.Storage;
import logic.FieldModifier;
import logic.Tester;

public class AppController {
    private Storage storage;
    private FieldModifier fieldModifier;
    private Tester tester;

    public AppController() {
        storage = new Storage();
        fieldModifier = new FieldModifier(storage);
        tester = new Tester();
    }

    public void run() {
        tester.runTests(storage, fieldModifier);
    }
}
