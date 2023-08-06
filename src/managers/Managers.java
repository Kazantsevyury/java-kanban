package managers;

import managers.implementation.HTTPTaskManager;
import http.KVServer;
import managers.implementation.FileBackedTasksManager;
import managers.implementation.InMemoryHistoryManager;
import managers.implementation.InMemoryTaskManager;

import java.io.IOException;

public class Managers  {
    public static FileBackedTasksManager getFileBackendTaskManager( ){return new FileBackedTasksManager();}
    public static InMemoryTaskManager getInMemoryTaskManager() {
        return new InMemoryTaskManager();
    }
    public static InMemoryHistoryManager getDefaultHistory(){
        return new InMemoryHistoryManager();
    }
    public static HTTPTaskManager getDefault(HistoryManager historyManager) throws IOException, InterruptedException {
            return new HTTPTaskManager( "http://localhost:" + KVServer.PORT);
    }
}
