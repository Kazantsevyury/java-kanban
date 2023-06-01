package managers;

import managers.implementation.InMemoryHistoryManager;
import managers.implementation.InMemoryTaskManager;

public class Managers  {
    public static InMemoryTaskManager getDefault() {
        return new InMemoryTaskManager();
    }
    public static InMemoryHistoryManager getDefaultHistory(){ return new InMemoryHistoryManager();
    }
}