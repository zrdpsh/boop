package com.sksm.java_apis;


import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WorkingWithMaps {
    static Logger logger = Logger.getLogger(WorkingWithMaps.class.getName());
    static Random r = new Random();
    static int FILTER_NUMBER = 10;

    public static void main(String[] args) {
        logger.log(Level.INFO, () -> "main method started");

        int[] arrayToFilter = generateArrayOfGivenLength(100, 10);

        ArrayList<Integer> filteredArray = filterArrayByHowLargeCombined(arrayToFilter, FILTER_NUMBER);

        for (int e: filteredArray) {
            System.out.print(e + " ");
            logger.log(Level.INFO, () -> " " + e + " appear in the given array " + FILTER_NUMBER + " times or more");
        }
    }// main function



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


        logger.log(Level.INFO, () -> "Pretty print our Map in printValueKeys function");
        logger.log(Level.INFO,() -> "There are " + howMuch + " pairs in the map");
        for (Map.Entry<Integer, Integer> entry : keyValuePairs.entrySet()) {
            int o = entry.getKey();
            System.out.println("" + o + " -> " + entry.getValue() + "");
            logger.log(Level.INFO, () -> "removing " + o);
            keyValuePairs.remove(entry.getKey());

        }

        logger.log(Level.INFO, () -> "PrintKeyValuePairs is finished");
    } //printKeyValuePairs


    public static ArrayList<Integer> filterArrayByHowLargeCombined(int[] arrayToFilter, int thresholdNumber ) {
        logger.log(Level.INFO, () -> "filterArrayByHowLargeCombined is called from MAIN with 2 parameters: ");
        logger.log(Level.INFO, () -> "Array to filter: " + Arrays.toString((arrayToFilter)));
        logger.log(Level.INFO, () -> "Threshold number: " + thresholdNumber );

        Map<Integer, Integer> valuesAndFrequencies = new HashMap<>();
        ArrayList<Integer> result = new ArrayList<>();

        logger.log(Level.INFO, () -> "Filtering values exceeding threshold inside the loop:");

        for (int e: arrayToFilter) {
            if (valuesAndFrequencies.get(e) == null) {
                valuesAndFrequencies.put(e, 1);
                logger.log(Level.INFO, () ->  e + " is added to map");
            } else {
                valuesAndFrequencies.put(e, valuesAndFrequencies.get(e)+1);
                logger.log(Level.INFO, () ->  e + " found one more time");
            }

            if (valuesAndFrequencies.get(e) == thresholdNumber ) {
                result.add(e);
                logger.log(Level.INFO, () ->  e + " is added to final result");
            }

            logger.log(Level.INFO, () -> "filterArrayByHowLarge function");
        } //for loop in filterArrayByHowLargeCombined

        logger.log(Level.INFO, () -> "There are " + result.size() + " values in a given array, that appear at least " + thresholdNumber  + " times");
        logger.log(Level.INFO, () -> "Filter array by how large is OK. returning to main function");
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
            logger.log(Level.INFO, () -> "function generateArrayOfGivenLength");

            assert(keys[e] != 0);
        }
        logger.log(Level.INFO, () -> "Given array is " + keys.length + " units long");

        return keys;
    } //generate array of given length

}//main class
