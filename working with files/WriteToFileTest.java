import org.junit.jupiter.api.Assertions;

import java.io.*;
import java.util.logging.Logger;
import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WriteToFileTest {
    private String pathToFiles = "C:\\Users\\Admin\\Documents\\J_files\\boop\\boop\\working with files\\";

    @org.junit.jupiter.api.Test
    void parseTheStringFromFile() throws IOException {
        BufferedReader buffer = new BufferedReader(new FileReader(new File(pathToFiles+"31.txt")));
        String[] testArray = "Some numbers expected to be out there".split(" ");
        Assertions.assertArrayEquals(testArray, WriteToFile.parseTheStringFromFile(buffer));
    }

//    @org.junit.jupiter.params.ParameterizedTest
//    @org.junit.jupiter.params.provider.ValueSource()
//    void sumTheContents_With_Defaults_Test() throws FileNotFoundException  {
//        assertEquals(WriteToFile.sumTheContents(pathToFiles, 27, 29), 51);
//    }

    @org.junit.jupiter.api.Test
    void sumTheContents_With_Defaults_Test() {
        Logger logger = Logger.getLogger(WriteToFile.class.getName());
        try {
            assertEquals(WriteToFile.sumTheContents(pathToFiles, 27, 29), 51);
        } catch (Exception e){
            logger.log(Level.ALL, e.toString());
        }
    }

    @org.junit.jupiter.api.Test
    void sumTheContents_With_Null_Test() {
        Assertions.assertThrows(FileNotFoundException.class, () -> WriteToFile.sumTheContents("C\\uss", 1));
    }

    @org.junit.jupiter.api.Test
    void sumTheContents_With_Broken_Values_Test() {
        Logger logger = Logger.getLogger(WriteToFile.class.getName());
        try {
            assertEquals(0.0, WriteToFile.sumTheContents(pathToFiles, 31));
        } catch (Exception e){
            logger.log(Level.ALL, e.toString());
        }
    }
}