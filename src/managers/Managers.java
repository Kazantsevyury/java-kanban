package managers;

import managers.implementation.FileBackedTasksManager;
import managers.implementation.InMemoryHistoryManager;
import managers.implementation.InMemoryTaskManager;

public class Managers  {
    public static FileBackedTasksManager getFileBackendTaskManager(String CSV_FILE_PATH){return new FileBackedTasksManager(CSV_FILE_PATH);}
    public static InMemoryTaskManager getDefault() {
        return new InMemoryTaskManager();
    }
    public static InMemoryHistoryManager getDefaultHistory(){ return new InMemoryHistoryManager();
    }
}