import Managers.TaskManager;
import Managers.InMemoryTaskManager;
public class Managers {
    public TaskManager getDefault() {
        return new InMemoryTaskManager();
    }
}
