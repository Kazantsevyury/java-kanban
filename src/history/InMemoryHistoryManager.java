package history;

import data.Task;
import implementations.LimitedSizeLinkedList;
import interfaces.HistoryManager;

public class InMemoryHistoryManager implements HistoryManager {
    private LimitedSizeLinkedList history;
    public InMemoryHistoryManager(int size) {
        history = new LimitedSizeLinkedList(size);
    }
    @Override
    public void add(Task task) {
        history.add(task);
    }
    @Override
    public LimitedSizeLinkedList getHistory() {
        return history;
    }
}