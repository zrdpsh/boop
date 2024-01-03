package com.sksm.java_apis;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WorkingWithMaps {
    static Logger logger = Logger.getLogger(WorkingWithMaps.class.getName());
    static Random r = new Random();
    static int FILTER_NUMBER = 11;

    public static void main(String[] args) {
        logger.log(Level.INFO, String.format("main method started"));

        int[] arrayToFilter = generateArrayOfGivenLength(100, 10);

        int[] filteredArray = filterArrayByHowLarge(arrayToFilter, FILTER_NUMBER);

        for (int e: filteredArray) {
            System.out.print(e + " ");
        }
        System.out.println(String.format("appear in the array at least %s times", FILTER_NUMBER));
        

    }

    public static void printKeyValuePairs(int howMuch) {
        int iterator = howMuch;

        logger.log(Level.INFO, String.format("PrintKeyValuePairs method started"));

        int[] keys = generateArrayOfGivenLength(100, 10101);

        Map<Integer, Integer> keyValuePairs = new HashMap<>();


        logger.log(Level.INFO, String.format("Populating the map with key value pairs"));
        for (int e : keys) {
            keyValuePairs.put(e, e*(r.nextInt(101)));
            logger.log(Level.INFO, String.format("%s:%s is added", e, keyValuePairs.get(e)));
        }
        logger.log(Level.INFO, String.format("Map is %s units long after initial run", keyValuePairs.size()));


        logger.log(Level.INFO, String.format("Checking if Map is long enough:"));
        while (keyValuePairs.size() < howMuch) {
            int e = r.nextInt();
            keyValuePairs.put(e, e*r.nextInt());
            logger.log(Level.INFO, String.format("extra value %s:%s added", e, keyValuePairs.get(e)));
        }
        logger.log(Level.INFO, String.format("Map is OK"));

        logger.log(Level.INFO, String.format("Pretty print our Map"));
        System.out.println(String.format("There are %s pairs in the map:", howMuch));
        for (Map.Entry<Integer, Integer> entry : keyValuePairs.entrySet()) {
            System.out.println(String.format("%s -> %s", entry.getKey(), entry.getValue()));
        }


        logger.log(Level.INFO, String.format("Emptying the Map"));
        keyValuePairs.clear();
        logger.log(Level.INFO, String.format("PrintKeyValuePairs is finished"));
    } //printKeyValuePairs

    public static int[] filterArrayByHowLarge(int[] arrayToFilter, int filterNumber) {
        Map<Integer, Integer> valuesAndFrequencies = new HashMap<>();

        logger.log(Level.INFO, String.format("Converting array to value-frequency pairs:"));
        for (int e: arrayToFilter) {
            valuesAndFrequencies.put(e, valuesAndFrequencies.get(e)==null? 0 :valuesAndFrequencies.get(e)+1);
            logger.log(Level.INFO, String.format("{0} is presented {1} times", e, valuesAndFrequencies.get(e)));
        }
        logger.log(Level.INFO, String.format("OK"));

        logger.log(Level.INFO, String.format("Filtering map:"));
        Map<Integer, Integer> copy = new HashMap<>();
        copy.putAll(valuesAndFrequencies);
        for (Map.Entry<Integer, Integer> entry : valuesAndFrequencies.entrySet()) {
            if (entry.getValue() < filterNumber) copy.remove(entry.getValue());
        }
        logger.log(Level.CONFIG, String.format("Map became {0} units long", copy.size()));

        logger.log(Level.INFO, String.format("Converting map to array:"));
        Set<Integer> keys = copy.keySet();
        Integer[] tmpIntegers = keys.toArray(new Integer[copy.size()]);
        logger.log(Level.INFO, String.format("OK"));

        logger.log(Level.INFO, String.format("Casting from Integer to int:"));
        int[] result = new int[tmpIntegers.length];
        int i = 0;
        for (Integer e: tmpIntegers) result[i++] = e.intValue();
        logger.log(Level.INFO, String.format("OK"));

        return result;
    }//filter array by how large


    public static int[] generateArrayOfGivenLength(int givenLength, int range) {
        logger.log(Level.INFO, String.format("generateArrayOfGivenLength is started"));
        int[] keys = new int[givenLength];
        logger.log(Level.INFO, String.format("Populating the given array"));
        for (int e = 0; e < givenLength; e++) {
            keys[e] = r.nextInt(range)+1;
            logger.log(Level.INFO, String.format("%s is added at [%s]", keys[e], e));
            assert(keys[e] != 0);
        }
        logger.log(Level.INFO, String.format("Given array is %s units long", keys.length));

        return keys;
    } //generate array of given length

}//main class
