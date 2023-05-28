package interfaces;

import implementations.LimitedSizeLinkedList;
import data.Task;

public interface HistoryManager {
    void add(Task task);
    LimitedSizeLinkedList getHistory();
}
