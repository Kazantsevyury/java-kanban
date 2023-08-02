package utilities;

import java.util.HashSet;
import java.util.Set;

public class IdGenerator {
    private static int  nextId = 1;
    private static Set<Integer> usedIds = new HashSet<>();

    public static int getNextId() {
        while (usedIds.contains(nextId)) {
            nextId++;
        }
        int newId = nextId;
        usedIds.add(newId);
        nextId++;
        return newId;
    }

    public static void loadUsedIds(Set<Integer> keys) {
        usedIds.addAll(keys);
    }}