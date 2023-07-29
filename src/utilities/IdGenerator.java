package utilities;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class IdGenerator {
    private static Random random = new Random();
    private static Set<Integer> usedIds = new HashSet<>();

    public static int getNextId() {
        int newId;
        do {
            newId = generateUniqueId();
        } while (isUniqueId(newId));

        return newId;
    }

    private static int generateUniqueId() {
        int currentTime = (int) System.currentTimeMillis();
        int randomValue = random.nextInt(1000);
        int uniqueId = currentTime * 1000 + randomValue;

        if (uniqueId <= 0) {
            uniqueId = -uniqueId;
        }
        return uniqueId;
    }

    private static boolean isUniqueId(int id) {
        return usedIds.contains(id);
    }

    public static boolean exists(int id) {
        return isUniqueId(id);
    }

    public static void loadUsedIds(Set<Integer> keys) {
        usedIds.addAll(keys);
    }
}
