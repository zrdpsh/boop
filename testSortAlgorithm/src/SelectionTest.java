import org.junit.jupiter.api.Test;
//import org.junit.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class SelectionTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        Selection selectionClass = new Selection();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        Selection selectionClass = null;
    }


    @org.junit.jupiter.api.Test
    void testBasicSort() {
        double[] basicList = {5, 9, 1, 0, 15};
        double[] basicListModel = {0, 1, 5, 9, 15};
        Selection.sort(basicList);
        assertArrayEquals(basicListModel, basicList);
    }

    @org.junit.jupiter.api.Test
    void testRandomIntSort() {
        int[] basicList = generateList();
        Selection.sort(basicList);
        assertTrue(countBackwards(basicList));
    }

    @org.junit.jupiter.api.Test
    void testNullSort() {
        double[] basicList = {};
        double[] basicListModel = {};
        Selection.sort(basicList);
        assertArrayEquals(basicListModel, basicList);
    }

    @org.junit.jupiter.api.Test
    void testMaximumSort() {
        double[] basicList = {1.7976931348623157E+308, 0};
        double[] basicListModel = {0, 1.7976931348623157E+308};
        Selection.sort(basicList);
        assertArrayEquals(basicListModel, basicList);
    }

    @org.junit.jupiter.api.Test
    void testIdenticalSort() {
        double[] basicList = {2,2,0,1,0,2,4,0,0};
        double[] basicListModel = {0,0,0,0,1,2,2,2,4};
        Selection.sort(basicList);
        assertArrayEquals(basicListModel, basicList);
    }

    @org.junit.jupiter.api.Test
    void main() {
    }

    public static int[] generateList() {
        Random rand = new Random();
        int arrayLength = rand.nextInt();
        int[] unlimitedTestArray = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            unlimitedTestArray[i] = rand.nextInt();
        }
        return unlimitedTestArray;
    }
}