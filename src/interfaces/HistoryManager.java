package interfaces;

import data.Task;
import java.util.LinkedList;

public interface HistoryManager {
    void add(Task task);
    LinkedList getHistory();
}