package managers.implementation;
import data.Task;
import managers.HistoryManager;
import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {

    private CustomLinkedList history;
    private HashMap<Integer, CustomLinkedList.Node> nodeMap;
    private final int MAX_SIZE;

    public InMemoryHistoryManager() {
        history = new CustomLinkedList();
        nodeMap = new HashMap<>();
        MAX_SIZE = 10;
    }

    @Override
    public void add(Task task) {
        int taskId = task.getTaskId();
        CustomLinkedList.Node existingNode = nodeMap.get(taskId);

        if (existingNode != null) {
            history.removeNode(existingNode);
        }

        CustomLinkedList.Node newNode = history.linkLast(task);
        nodeMap.put(taskId, newNode);

        if (history.size() > MAX_SIZE) {
            CustomLinkedList.Node firstNode = history.getFirst();
            history.removeNode(firstNode);
            nodeMap.remove(firstNode.task.getTaskId());
        }
    }

    @Override
    public ArrayList<Task> getHistory() {
        return history.getTasks();
    }

    @Override
    public void remove(int id) {
        CustomLinkedList.Node nodeToRemove = nodeMap.get(id);
        if (nodeToRemove != null) {
            history.removeNode(nodeToRemove);
            nodeMap.remove(id);
        }
    }
}

class CustomLinkedList {
    private Node first;
    private Node last;

    static class Node {
        Task task;
        Node prev;
        Node next;

        Node(Task task) {
            this.task = task;
        }
    }

    public Node getFirst() {
        return first;
    }

    public Node linkLast(Task task) {
        final Node newNode = new Node(task);
        final Node lastNode = last;
        newNode.prev = lastNode;
        last = newNode;
        if (lastNode == null)
            first = newNode;
        else
            lastNode.next = newNode;
        return newNode;
    }

    public void removeNode(Node node) {
        final Node prevNode = node.prev;
        final Node nextNode = node.next;

        if (prevNode == null) {
            first = nextNode;
        } else {
            prevNode.next = nextNode;
            node.prev = null;
        }

        if (nextNode == null) {
            last = prevNode;
        } else {
            nextNode.prev = prevNode;
            node.next = null;
        }
    }

    public ArrayList<Task> getTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        Node current = first;
        while (current != null) {
            tasks.add(current.task);
            current = current.next;
        }
        return tasks;
    }

    public int size() {
        int count = 0;
        Node current = first;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
}
