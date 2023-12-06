import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;
import java.util.Random;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class SelectionTest {

    public static Logger logger = Logger.getLogger(SelectionTest.class.getName());

    @org.junit.jupiter.api.Test
    void testBasicSort() {
        double[] basicList = {5, 9, 1, 0, 15};
        double[] basicListModel = {0, 1, 5, 9, 15};
        SelectionSort.sort(basicList);
        assertArrayEquals(basicListModel, basicList);
    }

    @org.junit.jupiter.api.Test
    void testNullSort() {
        double[] basicList = {};
        double[] basicListModel = {};
        SelectionSort.sort(basicList);
        assertArrayEquals(basicListModel, basicList);
    }

    @org.junit.jupiter.api.Test
    void testMinimumMaximumSort() {
        double[] basicList = {1.7976931348623157E+308, 2.2250738585072014E-308};
        double[] basicListModel = {2.2250738585072014E-308, 1.7976931348623157E+308};
        SelectionSort.sort(basicList);
        assertArrayEquals(basicListModel, basicList);
    }

    @org.junit.jupiter.api.Test
    void testIdenticalValuesSort() {
        double[] basicList = {2,2,0,1,0,2,4,0,0};
        double[] basicListModel = {0,0,0,0,1,2,2,2,4};
        SelectionSort.sort(basicList);
        assertArrayEquals(basicListModel, basicList);
    }


    @org.junit.jupiter.api.Test
    void testRandomSort() {
        double[] basicList = generateList();
        double[] copyToTest = Arrays.copyOf(basicList, basicList.length);
        SelectionSort.sort(basicList);
        Arrays.sort(copyToTest);
        assertArrayEquals(basicList, copyToTest);
    }

    public static double[] generateList() {
        Random rand = new Random();

//        int arrayLength = Math.abs(rand.nextInt());
        int arrayLength = rand.nextInt(200000);
        double[] unlimitedTestArray = new double[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            unlimitedTestArray[i] = 1000000*(rand.nextDouble()*(Math.pow(-1, rand.nextInt())));
        }
        logger.info("Random sequence of positive and negative numbers is generated");
        return unlimitedTestArray;
    }
}