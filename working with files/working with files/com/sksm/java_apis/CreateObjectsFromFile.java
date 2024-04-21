package com.sksm.java_apis;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateObjectsFromFile {
    static Logger logger = Logger.getLogger(CreateObjectsFromFile.class.getName());
    public static void main(String[] args) throws IndexOutOfBoundsException {
        logger.log(Level.INFO, () -> "main function is called");

        try {
            List<Alpaca> newAlpacas = createTheClassesFromSource(WriteToFile.PATHNAME + "working with files\\AlpacaNames.txt");
            Alpaca temp = newAlpacas.get(2);
            logger.log(Level.INFO, "Seems like there is no files out there " + temp.getTheName());
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.INFO, () -> "Seems like there is no files out there " + e);
        } catch (Exception e) {
            logger.log(Level.INFO, () -> "Exception is thrown" + e);
        }
    }

    // ..создаём список объектов на основе перечисленных в файле личных имён
    public static List<Alpaca> createClassesFromSource(String pathToFile) {
        logger.log(Level.INFO, () -> "createTheClassesFromSource is called with path " + pathToFile);
        ArrayList<Alpaca> result = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            logger.log(Level.INFO, "Start to create objects from strings: ");
            for (String lineRead = br.readLine(); lineRead != null; lineRead = br.readLine()) {
                result.add(createAlpacaFromString(lineRead));
                logger.log(Level.INFO, () -> "String " + pathToFile + " is added");
            }
        } catch (Exception e) {
            logger.log(Level.INFO, () -> "File doesnt read" + e);
        }
        return result;
    }

    public static Alpaca createAlpacaFromString(String rawString) throws IndexOutOfBoundsException {
        logger.log(Level.INFO, () -> "createAlpacaFromString is called with  " + rawString);

        try {
            String[] rawStringSplit = rawString.split(" ");
            return new Alpaca(rawStringSplit[0], Integer.parseInt(rawStringSplit[1]), Integer.parseInt(rawStringSplit[2]));
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.INFO, () -> "There is some problem with data " + e);
        } catch (Exception e) {
            logger.log(Level.INFO, () -> "There is some problem creating an Alpaca" + e);
        }
        logger.log(Level.INFO, () -> "returning to createClassesFromSource");
        return null;
    } //create Alpaca from string function

//    ArrayList
}
