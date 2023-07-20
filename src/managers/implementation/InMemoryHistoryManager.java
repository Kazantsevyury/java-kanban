package managers.implementation;

import data.Task;
import managers.HistoryManager;
import java.util.ArrayList;
import java.util.HashMap;

public class InMemoryHistoryManager implements HistoryManager {
    private Node first;
    private Node last;
    private HashMap<Integer, Node> nodeMap;

    static class Node {
        Task task;
        Node prev;
        Node next;

        Node(Task task) {
            this.task = task;
        }
    }

    public InMemoryHistoryManager() {
        first = null;
        last = null;
        nodeMap = new HashMap<>();
    }

    @Override
    public void add(Task task) {

        int taskId = task.getTaskId();
        Node existingNode = nodeMap.get(taskId);

        if (existingNode != null) {
            removeNode(existingNode);
        }

        Node newNode = linkLast(task);
        nodeMap.put(taskId, newNode);
    }

    @Override
    public ArrayList<Task> getHistory() {
        ArrayList<Task> tasks = new ArrayList<>();
        Node current = first;
        while (current != null) {
            tasks.add(current.task);
            current = current.next;
        }
        return tasks;
    }
    @Override
    public void printer() {
        ArrayList<Task> tasks = getHistory();

        if (tasks.isEmpty()) {
            System.out.println("История задач пуста.");
            return;
        }

        System.out.println("История задач:");
        for (Task task : tasks) {
            System.out.println(task.toString());
        }
    }

    @Override
    public void remove(int id) {
        Node nodeToRemove = nodeMap.get(id);
        if (nodeToRemove != null) {
            removeNode(nodeToRemove);
            nodeMap.remove(id);
        }
    }

    private Node linkLast(Task task) {
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

    private void removeNode(Node node) {
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

    private int size() {
        int count = 0;
        Node current = first;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
}