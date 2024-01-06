package ExamplePackage;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.Arrays.sort;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

public class SortedArrayTest {
    public static final Logger logger = Logger.getLogger(SortedArray.class.getName());
    public static final int ARRAYLENGTH = 30;

    @Ignore
    public void testSortBasic() {
        int[] testArray = {45, 18, 90, 102, 54, 82, 88, 145, 123, 10001, 10000, 21873456, 8365091, 234, 563410, 241};
        int[] idealArray = {18, 45, 54, 82, 88, 90, 102, 123, 145, 234, 241, 10000, 10001, 563410, 8365091, 21873456};
        SortedArray.sort(testArray);
        logger.log(Level.INFO, () -> "after the test arrays is " + Arrays.toString(testArray));
        assertArrayEquals(idealArray, testArray);
    }

    @Ignore
    public void testSortRandom() {
        int[] testArray = generateRandomArray(ARRAYLENGTH);
        int[] testArrayExemplar = Arrays.copyOf(testArray, testArray.length);

        SortedArray.sort(testArray);
        Arrays.sort(testArrayExemplar);

        logger.log(Level.INFO, () -> "after the test arrays is " + Arrays.toString(testArray));
        assertArrayEquals(testArrayExemplar, testArray);
    }

    @Ignore
    public void testIsSorted() {
        int[] testArray = generateRandomArray(ARRAYLENGTH);

        SortedArray.sort(testArray);

        logger.log(Level.INFO, () -> "after the test arrays is " + Arrays.toString(testArray));

        long st = System.nanoTime();
        assertTrue(SortedArray.isSorted(testArray));
        long nd = System.nanoTime() - st;

        logger.log(Level.INFO, () -> "isSorted function takes " + nd + " seconds to run");
    }

    @Ignore
    public void testIsSortedAlt() {
        int[] testArray = generateRandomArray(ARRAYLENGTH);

        SortedArray.sort(testArray);

        long st = System.nanoTime();
        assertTrue(SortedArray.isSortedAlt(testArray));
        long nd = System.nanoTime() - st;

        logger.log(Level.INFO, () -> "isSortedAlt function takes " + nd + " seconds to run");
    }

    @Ignore
    public void testIsSortedSimumlt() {
        float[] runningTimes = new float[20];
        for (int i = 0; i < 20; i++) {
            runningTimes[i] = testIsSortedBoth();
        }
        System.out.println(Arrays.toString(runningTimes));
        System.out.println(Arrays.toString(runningTimes));

    }

    @Test
    public void testIsSortedInMiddle() {
        int[] testArray = generateRandomArray(ARRAYLENGTH);

        SortedArray.sort(testArray);

        long st = System.nanoTime();
        assertTrue(SortedArray.isSortedInMiddle(testArray));
        long nd = System.nanoTime() - st;

        logger.log(Level.INFO, () -> "isSortedAlt function takes " + nd + " seconds to run");

        assertTrue(SortedArray.isSortedInMiddle(testArray));
    }

    public float testIsSortedBoth() {
        int[] testArray = generateRandomArray(ARRAYLENGTH);

        SortedArray.sort(testArray);

        long st = System.nanoTime();
        assertTrue(SortedArray.isSorted(testArray));
        long nd = System.nanoTime() - st;

        long stAlt = System.nanoTime();
        assertTrue(SortedArray.isSortedAlt(testArray));
        long ndAlt = System.nanoTime() - stAlt;

        logger.log(Level.INFO, () -> "isSorted function takes " + nd + " seconds to run");
        logger.log(Level.INFO, () -> "isSortedAlt function takes " + ndAlt + " seconds to run");
        logger.log(Level.INFO, () -> "returning to testIsSortedSimult");


        return (float) ((((nd - ndAlt)*1.0) / nd) * 100);
    }


    public int[] generateRandomArray(int length) {
        int[] arr = new int[length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (10000));
        }
        return arr;
    } //generate random array function

}