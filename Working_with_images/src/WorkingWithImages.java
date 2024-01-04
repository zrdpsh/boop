import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;


public class WorkingWithImages {
    static Logger logger = Logger.getLogger(WorkingWithImages.class.getName());

    public static void main(String[] args) throws IOException {
        logger.log(Level.INFO, "main method started");
        ArrayList<File> convertedImages = returnConvertedImages("png", "jpg", 2, true);
        logger.log(Level.INFO, String.format("There are %s converted files after MAIN method execution", convertedImages.size()));

    }

    public static ArrayList<File> returnConvertedImages(String givenFormat, String desiredFormat, int depth, boolean addGraphics) throws IOException {
        logger.log(Level.INFO, "-------------------------------");
        logger.log(Level.INFO, "returnConvertedImages is called");
        logger.log(Level.INFO, "-------------------------------");

        ArrayList<File> arrayToTrackNamesOfFolder = new ArrayList<File>();
        ArrayList<File> result  = new ArrayList<>();

        arrayToTrackNamesOfFolder.add(new File(System.getProperty("user.dir")));
        logger.log(Level.INFO, "getting the name of the current folder inside the returnToConverterImages");

        logger.log(Level.INFO, "Start searching for files through the folders' tree");
        for(int u = 0; u < depth; u++) {

            File[] arrayToTrackNamesOfFolderCopy = arrayToTrackNamesOfFolder.toArray(new File[arrayToTrackNamesOfFolder.size()]);

            arrayToTrackNamesOfFolder.clear();
            for (File givenFile : arrayToTrackNamesOfFolderCopy) {
                logger.log(Level.INFO, "returnToConvertedImages:");
                logger.log(Level.INFO, String.format("searching for files %s at level deep in hierarchy", u));
                if (givenFile.isDirectory()) {
                    arrayToTrackNamesOfFolder.addAll(Arrays.asList(givenFile.listFiles()));
                } else {
                    String extensionOfTheGivenFile = getExtension(givenFile.getName());
                    logger.log(Level.INFO, String.format("we have %s file named %s", extensionOfTheGivenFile, givenFile.getName()));

                    if ((extensionOfTheGivenFile != null) && (extensionOfTheGivenFile.equalsIgnoreCase(givenFormat))) {
                        try {
                            result.add(resultOfConverting(givenFile.getName(), desiredFormat, addGraphics));
                            logger.log(Level.INFO, "Given file do have needed extension. Adding it to resulting array");
                        } catch (Exception e) {
                            logger.log(Level.INFO, e.getMessage());
                        }//try catch clause
                    } //if extension == null
                } //else clause inside inner loop
            } // inner loop to get all the files
        } //main loop inside converted images
        return result;
    } //return converted images

    private static File resultOfConverting(String nameOfTheGivenFile, String desiredFormat, boolean drawImage) throws IOException {
        logger.log(Level.INFO, "-------------------------------");
        logger.log(Level.INFO, () -> "getExtension is called with file %s from returnConvertedImages " + nameOfTheGivenFile);
        logger.log(Level.INFO, "-------------------------------");

        File resultingImage;

        try {
            logger.log(Level.INFO, "resultOfConverting function:");
            logger.log(Level.INFO, "try to convert image");
            BufferedImage img = ImageIO.read(new File(nameOfTheGivenFile));
            if (drawImage) drawSquare(img, "Hello,\nWorld!");
            logger.log(Level.INFO, "resultOfConverting draws square");
            resultingImage = new File(nameOfTheGivenFile.substring(0,nameOfTheGivenFile.lastIndexOf(".")) + "." + desiredFormat);
            ImageIO.write(img, desiredFormat, resultingImage);
            logger.log(Level.INFO, "converted image is written to disk");
            return resultingImage;
        } catch (Exception e) {
            logger.log(Level.INFO, "Exception while read write file");
            logger.log(Level.INFO, e.getMessage());
            logger.log(Level.INFO, "Heading to the next item after the error");
            return null;
        } //try catch clause

    }//return converted File

    private static String getExtension(String fileName) {
        logger.log(Level.INFO, "-------------------------------");
        logger.log(Level.INFO, () -> "getExtension is called with file %s from returnConvertedImages" + fileName);
        logger.log(Level.INFO, "-------------------------------");
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            logger.log(Level.INFO, () -> "file have extension  " + fileName.substring(i+1));
            logger.log(Level.INFO, "return to resultOfConvertingFunction");
            return fileName.substring(i+1);
        }//if (i > 0)
        logger.log(Level.INFO, () -> "Using getExtension_m we find nothing" + fileName);
        logger.log(Level.INFO, "return to resultOfConvertingFunction");
        return null;
    } //getExtension function


    private static void drawSquare(BufferedImage img, String textInsideTheFrame) {
        logger.log(Level.INFO, () -> "drawSquare function is called");

        Graphics2D g = img.createGraphics();

        try {
            int squareSide = img.getWidth() > img.getHeight()? img.getHeight()/3 : img.getWidth()/3;
            int xUpperLeft = img.getWidth()/2  - squareSide/2;
            int yUpperLeft = img.getHeight()/2 - squareSide/2;
            logger.log(Level.INFO, () -> "reference point of the square set at x: " + xUpperLeft + " y:" + yUpperLeft);
            Font f = new Font("Helvetica-bold-italic", Font.ITALIC, 20);

            g.setColor(Color.BLACK);
            g.fillRect((img.getWidth()-squareSide)/2, (img.getHeight()-squareSide)/2, squareSide, squareSide);
            logger.log(Level.INFO, () -> "Background square added");

            g.setColor(Color.WHITE);
            g.setFont(f);
            drawStringCustom(g, textInsideTheFrame, xUpperLeft+squareSide/3, yUpperLeft+squareSide/3);
            logger.log(Level.INFO, () -> "text added");
        } catch (Exception e) {
            logger.log(Level.INFO, e.getMessage());
        } finally {
            g.dispose();
            g.dispose();
        }
    }//drawSquare

    private static void drawStringCustom(Graphics2D g, String stringToPrint, int x, int y) {
        logger.log(Level.INFO, () -> "drawStringCustom is called");
        for (String line : stringToPrint.split("\n")) {
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
            logger.log(Level.INFO, () -> "drawing "+ line + " line");
        }
    }//draw string
}

