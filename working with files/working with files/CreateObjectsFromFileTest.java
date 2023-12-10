import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class CreateObjectsFromFileTest {


    @Test
    void createTheClassesFromSource() {
        Logger logger = Logger.getLogger(WriteToFile.class.getName());
        Alpaca testAlpaca = new Alpaca("Andrew", 100, 11);
        try {
//            ArrayList<Alpaca> tmp = CreateObjectsFromFile.createTheClassesFromSource("C:\\Users\\Admin\\Documents\\J_files\\boop\\boop\\working with files\\working with files\\AlpacaNameTest");
            ArrayList<Alpaca> tmp = CreateObjectsFromFile.createTheClassesFromSource(WriteToFile.PATHNAME + "working with files\\AlpacaNameTest.txt");
            Alpaca alp = tmp.get(0);
            assertTrue(alp.compare(testAlpaca));
        } catch (Exception e){
            logger.log(Level.ALL, e.toString());
        }
    }

    @Test
    void createAlpacaFromTheStringTest() {
        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> CreateObjectsFromFile.createAlpacaFromString
                        ("mm17"));
    }
}