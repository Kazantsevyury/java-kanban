package manag;
import history.InMemoryHistoryManager;
public class Managers  {
    public static InMemoryTaskManager getDefault() {
        return new InMemoryTaskManager();
    }
    public static InMemoryHistoryManager  getDefaultHistory(){ return new InMemoryHistoryManager(10);
    }
}