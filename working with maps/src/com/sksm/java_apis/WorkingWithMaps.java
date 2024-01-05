package com.sksm.java_apis;


import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WorkingWithMaps {
    static Logger logger = Logger.getLogger(WorkingWithMaps.class.getName());
    static Random r = new Random();
    static int FILTER_NUMBER = 11;

    public static void main(String[] args) {
        logger.log(Level.INFO, () -> "main method started");

        int[] arrayToFilter = generateArrayOfGivenLength(100, 10);

        int[] filteredArray = filterArrayByHowLarge(arrayToFilter, FILTER_NUMBER);

        for (int e: filteredArray) {
            System.out.print(e + " ");
        }

        logger.log(Level.INFO, () -> "appear in the array at least " + FILTER_NUMBER + " times");

    }

    public static void printKeyValuePairs(int howMuch) {
        logger.log(Level.INFO, () -> "PrintKeyValuePairs method is called from main to print Map of length " + howMuch);

        Map<Integer, Integer> keyValuePairs = new HashMap<>();

        logger.log(Level.INFO, () -> "Populating the map with key value pairs inside printKeyValuePairs function");
        while (keyValuePairs.size() < howMuch) {
            int e = r.nextInt();
            if (keyValuePairs.containsKey(e)) continue;
            keyValuePairs.put(e, e*(r.nextInt(101)));
            logger.log(Level.INFO, () -> "" + e + ":" + keyValuePairs.get(e) + " is added");
            logger.log(Level.INFO, () -> "function printKeyValuePairs");
        }
        logger.log(Level.INFO, () -> "Map is " + keyValuePairs.size() + " units long after initial run");


        logger.log(Level.INFO, () -> "Pretty print our Map");
        System.out.println("There are " + howMuch + " pairs in the map");
        for (Map.Entry<Integer, Integer> entry : keyValuePairs.entrySet()) {
            int o = entry.getKey();
            System.out.println("" + o + " -> " + entry.getValue() + "");
            logger.log(Level.INFO, () -> "removing " + o);
            keyValuePairs.remove(entry.getKey());

        }

        logger.log(Level.INFO, () -> "PrintKeyValuePairs is finished");
    } //printKeyValuePairs

    public static int[] filterArrayByHowLarge(int[] arrayToFilter, int filterNumber) {
        logger.log(Level.INFO, () -> "filterArrayByHowLarge is called from MAIN  with 2 parameters: ");
        logger.log(Level.INFO, () -> "Array to filter: " + Arrays.toString((arrayToFilter)));
        logger.log(Level.INFO, () -> "Threshold number: " + filterNumber);

        Map<Integer, Integer> valuesAndFrequencies = new HashMap<>();

        logger.log(Level.INFO, () -> "Converting array to value-frequency pairs:");
        for (int e: arrayToFilter) {
            valuesAndFrequencies.put(e, valuesAndFrequencies.get(e)==null? 0 :valuesAndFrequencies.get(e)+1);
            logger.log(Level.INFO, () ->  e + " is presented " +  valuesAndFrequencies.get(e) + " times");
            logger.log(Level.INFO, () -> "filterArrayByHowLarge function");
        }
        logger.log(Level.INFO, () -> "OK");

        logger.log(Level.INFO, () -> "Filtering map:");
        Map<Integer, Integer> copy = new HashMap<>(valuesAndFrequencies);
        for (Map.Entry<Integer, Integer> entry : valuesAndFrequencies.entrySet()) {
            if (entry.getValue() < filterNumber) copy.remove(entry.getValue());
        }
        logger.log(Level.CONFIG, () -> "Map became " + copy.size() + " units long");

        logger.log(Level.INFO, () -> "Converting map to array:");
        Set<Integer> keys = copy.keySet();
        Integer[] tmpIntegers = keys.toArray(new Integer[copy.size()]);
        logger.log(Level.INFO, () -> "OK");

        logger.log(Level.INFO, () -> "Casting from Integer to int:");
        int[] result = new int[tmpIntegers.length];
        int i = 0;
        for (Integer e: tmpIntegers) result[i++] = e;
        logger.log(Level.INFO, () -> "OK");

        return result;
    }//filter array by how large


    public static int[] generateArrayOfGivenLength(int givenLength, int range) {
        logger.log(Level.INFO, () -> "generateArrayOfGivenLength is started");
        int[] keys = new int[givenLength];
        logger.log(Level.INFO, () -> "Populating the given array");
        for (int e = 0; e < givenLength; e++) {
            keys[e] = r.nextInt(range)+1;
            int finalE = e;
            logger.log(Level.INFO, () -> "" + keys[finalE] + " is added at [" + finalE + "]");
            assert(keys[e] != 0);
        }
        logger.log(Level.INFO, () -> "Given array is " + keys.length + " units long");

        return keys;
    } //generate array of given length

}//main class
