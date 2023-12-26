import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WorkingWithMaps {
    static Logger logger = Logger.getLogger(WorkingWithMaps.class.getName());
    static Random r = new Random();

    public static void main(String[] args) {
        logger.log(Level.INFO, String.format("main method started"));
        printKeyValuePairs(100);

    }

    public static void printKeyValuePairs(int howMuch) {
        logger.log(Level.INFO, String.format("PrintKeyValuePairs method started"));

        int[] keys = new int[howMuch];
        logger.log(Level.INFO, String.format("populating the keys array"));
        for (int e = 0; e < howMuch; e++) {
            keys[e] = r.nextInt(101);
            logger.log(Level.INFO, String.format("%s is added", keys[e]));
        }

        Map<Integer, Integer> keyValuePairs = new HashMap<>();

        logger.log(Level.INFO, String.format("populating the map with key value pairs"));
        for (int e : keys) {
            keyValuePairs.put(e, e*(r.nextInt(101)));
            logger.log(Level.INFO, String.format("%s:%s is added", e, keyValuePairs.get(e)));
        }

        logger.log(Level.INFO, String.format("pretty print our Map"));
        System.out.println(String.format("There are %s pairs in the map:", howMuch));
        for (Map.Entry<Integer, Integer> entry : keyValuePairs.entrySet()) {
            System.out.println(String.format("%s -> %s", entry.getKey(), entry.getValue()));
        }

        logger.log(Level.INFO, String.format("emptying the Map"));
        keyValuePairs.clear();
    }
}
