package ExamplePackage;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class SortedArrayTest {
    public static final Logger logger = Logger.getLogger(SortedArray.class.getName());
    public static final int ARRAYLENGTH = 30;
    @Test
    public void testIsSorted() {
        int[] testArray = generateRandomArray(ARRAYLENGTH);
        int[] testArrayCopy = Arrays.copyOf(testArray, testArray.length);
        SortedArray.sort(testArray);
        Arrays.sort(testArrayCopy);
        assertArrayEquals(testArrayCopy, testArray);
    }


    public int[] generateRandomArray(int length) {
        int[] arr = new int[length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (10000));
        }
        return arr;
    } //generate random array function

}