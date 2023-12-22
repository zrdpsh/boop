import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;


public class WorkingWithImages {
    static Logger logger = Logger.getLogger(WorkingWithImages.class.getName());

    public static void main(String[] args) throws IOException {
        logger.log(Level.INFO, String.format("main method started"));
        ArrayList<File> convertedImages = returnConvertedImages("png", "jpg", 2, true);
        logger.log(Level.INFO, String.format("There are %s converted files", convertedImages.size()));

    }

    public static ArrayList<File> returnConvertedImages(String givenFormat, String desiredFormat, int depth, boolean addGraphics) throws IOException {
        logger.log(Level.INFO, "-------------------------------");
        logger.log(Level.INFO, "returnConvertedImages is called");
        logger.log(Level.INFO, "-------------------------------");

        ArrayList<File> expand = new ArrayList<File>();
        ArrayList<File> result  = new ArrayList<>();

        expand.add(new File(System.getProperty("user.dir")));

        for(int u = 0; u < depth; u++) {

            File[] expandCopy = expand.toArray(new File[expand.size()]);
            /* переписываем данные из списка expand в массив expandCopy
             * В качестве аргумента нужно передать инциализированный массив нужной длины. Длину из expand извлекаем с помощью .size()
             * В результате получится массив ссылок на объекты типа File, в который записаны все названия папок на текущем уровне*/

            expand.clear();
            /* освободить первоначальный список для новых названий папок. старые - из предыдущего уровня - остаются в только что созданной копии */
            for (File givenFile : expandCopy) {
                logger.log(Level.INFO, String.format("we are searching %s level deep in hierarchy", depth));
                if (givenFile.isDirectory()) {
                    expand.addAll(Arrays.asList(givenFile.listFiles()));
                    /* .. если является - получаем файлы, лежащие внутри file c помощью .listFiles() в виде массива с объектами File ->
                     *   -> Arrays.asList превращает наш массив файлов в список
                     *   добавляем названия файлов в expand, освобождённый перед началом работы цикла*/
                } else {
                    String extensionOfTheGivenFile = getExtension(givenFile.getName());
                    logger.log(Level.INFO, String.format("we have %s file named %s", extensionOfTheGivenFile, givenFile.getName()));

                    if ((extensionOfTheGivenFile != null) && (extensionOfTheGivenFile.equalsIgnoreCase(givenFormat))) {
                        result.add(resultOfConverting(givenFile.getName(), desiredFormat,addGraphics));
                        logger.log(Level.INFO, "Given file do have needed extension. Adding it to resulting array");
                    }
                } //else clause inside inner loop
            } // inner loop to get all the files
        } //main loop inside converted images
        return result;
    } //return converted images

    private static File resultOfConverting(String nameOfTheGivenFile, String desiredFormat, boolean drawImage) throws IOException {
        logger.log(Level.INFO, "-------------------------------");
        logger.log(Level.INFO, String.format("getExtension is called with file %s from returnConvertedImages", nameOfTheGivenFile));
        logger.log(Level.INFO, "-------------------------------");

        File resultingImage;

        try {
            logger.log(Level.INFO, String.format("try to convert image"));
            BufferedImage img = ImageIO.read(new File(nameOfTheGivenFile));
            if (drawImage) drawSquare(img, "Hello,\n World!");
            resultingImage = new File(nameOfTheGivenFile.substring(0,nameOfTheGivenFile.lastIndexOf(".")) + "." + desiredFormat);
            ImageIO.write(img, desiredFormat, resultingImage);
            return resultingImage;
        } catch (Exception e) {
            logger.log(Level.INFO, "Exception while read write file");
            throw e;
        }
    }//return converted File

    private static String getExtension(String fileName) {
        logger.log(Level.INFO, "-------------------------------");
        logger.log(Level.INFO, String.format("getExtension is called with file %s from returnConvertedImages", fileName));
        logger.log(Level.INFO, "-------------------------------");
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            logger.log(Level.INFO, String.format("file have extension '.%s'", fileName.substring(i+1)));
            return fileName.substring(i+1);
        }
        logger.log(Level.INFO, String.format("Using getExtension_m we find nothing", fileName));
        return null;
    }

    private static void drawSquare(BufferedImage img, String textInsideTheFrame) throws IOException {
        logger.log(Level.INFO, String.format("drawSquare is called"));

        Graphics2D g = img.createGraphics();
        int squareSide = img.getWidth() > img.getHeight()? img.getHeight()/3 : img.getWidth()/3;
        int xUpperLeft = img.getWidth()/2  - squareSide/2;
        int yUpperLeft = img.getHeight()/2 - squareSide/2;
        logger.log(Level.INFO, String.format("reference point of the square set at x:%s y:%s", xUpperLeft, yUpperLeft));
//        FontMetrics m = g.getFontMetrics(f);

        FontRenderContext context = g.getFontRenderContext();
        Font f = new Font("Helvetica-bold-italic", Font.ITALIC, 20);
//        TextLayout txt = new TextLayout(textInsideTheFrame, f, context);
//        Rectangle2D bounds = txt.getBounds();
//
//        int x = (int) ((g.getWidth() - (int) bounds.getWidth())/2);

        g.setColor(Color.BLACK);
        g.fillRect((img.getWidth()-squareSide)/2, (img.getHeight()-squareSide)/2, squareSide, squareSide);
        logger.log(Level.INFO, String.format("Background square added"));

        g.setColor(Color.WHITE);
        g.setFont(f);
//        drawStringCustom(textInsideTheFrame, img.getWidth()/3,img.getHeight()/3);
        drawStringCustom(g, textInsideTheFrame, xUpperLeft,yUpperLeft);
        logger.log(Level.INFO, String.format("text added"));
        g.dispose();
        g.dispose();
    }//drawSquare

    private static void drawStringCustom(Graphics2D g, String stringToPrint, int x, int y) {
        for (String line : stringToPrint.split("\n")) {
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
        }
    }//draw string
}
