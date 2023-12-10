import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateObjectsFromFile {
    public static void main(String[] args) throws FileNotFoundException, IOException, IndexOutOfBoundsException {
        Logger logger = Logger.getLogger(CreateObjectsFromFile.class.getName());
        try {
            ArrayList<Alpaca> newAlpacas = createTheClassesFromSource(WriteToFile.PATHNAME + "working with files\\AlpacaNames.txt");
            Alpaca temp = newAlpacas.get(2);
            System.out.println(temp.getTheName());
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.INFO, "Seems like there is no files out there", e.toString());
            throw e;
        } catch (Exception e) {
            logger.log(Level.INFO, "Exception is thrown", e.toString());
            throw e;
        }
    }

    public static ArrayList createTheClassesFromSource(String pathToFile) {
        Logger logger = Logger.getLogger(CreateObjectsFromFile.class.getName());
        ArrayList<Alpaca> result = new ArrayList<Alpaca>();

        try(BufferedReader br = new BufferedReader(new FileReader(new File(pathToFile)))) {
            logger.log(Level.INFO, "Start to create objects from strings: ");
            for (String lineRead = br.readLine(); lineRead != null; lineRead = br.readLine()) {

                result.add(createAlpacaFromString(lineRead));
                String loggerMessage = String.format("String %s added ", lineRead);
                logger.log(Level.INFO, loggerMessage);
            }
        } catch (Exception e) {
            logger.log(Level.INFO, "File doesnt read" , e.toString());
        }

        return result;
    }

    public static Alpaca createAlpacaFromString(String rawString) throws IndexOutOfBoundsException {
        try {
            String[] rawStringSplit = rawString.split(" ");
            return new Alpaca(rawStringSplit[0], Integer.parseInt(rawStringSplit[1]), Integer.parseInt(rawStringSplit[2]));
        } catch (IndexOutOfBoundsException e) {
            Logger logger = Logger.getLogger(CreateObjectsFromFile.class.getName());
            logger.log(Level.INFO, "There is some problem with data", e.toString());
            throw e;
        } catch (Exception e) {
            Logger logger = Logger.getLogger(CreateObjectsFromFile.class.getName());
            logger.log(Level.INFO, "There is some problem creating an Alpaca", e.toString());
            throw e;
        }
    } //create Alpaca from string function

//    ArrayList
}
