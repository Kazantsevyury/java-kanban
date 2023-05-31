package manag;

import data.Task;
import interfaces.HistoryManager;

import java.util.LinkedList;

public class InMemoryHistoryManager implements HistoryManager {
    private LinkedList history;
    private int MAX_SIZE  ;


    public InMemoryHistoryManager() {
        history = new LinkedList();
        MAX_SIZE = 10;
    }
    @Override
    public void add(Task task) {
        if (history.size()>= MAX_SIZE){
            history.removeFirst();
        }
        history.add(task);
    }
    @Override
    public LinkedList getHistory() {
        return history;
    }

}