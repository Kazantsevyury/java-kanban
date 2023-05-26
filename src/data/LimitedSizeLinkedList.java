package data;
import java.util.LinkedList;
public class LimitedSizeLinkedList extends LinkedList<Task> {
    private int maxSize;

    public LimitedSizeLinkedList(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public boolean add(Task task){
        super.add(task);
        if (size()>maxSize){
            removeFirst();
        }
        return true;
    }
}
