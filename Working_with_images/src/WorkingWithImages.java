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
        ArrayList<File> convertedImages = returnConvertedImages("png", "jpg");
        logger.log(Level.INFO, String.format("There are %s converted files", convertedImages.size()));

    }

    public static ArrayList<File> returnConvertedImages(String givenFormat, String desiredFormat) throws IOException {
        logger.log(Level.INFO, "-------------------------------");
        logger.log(Level.INFO, "returnConvertedImages is called");
        logger.log(Level.INFO, "-------------------------------");

        File root = new File(".");
        /* инициализировать файл с именем "." - верхняя папка в иерархии */

        ArrayList<File> expand = new ArrayList<File>();
        /* объявить список ArrayList, который называется expand - в него можно записать произвольное количество File" */

        ArrayList<File> result  = new ArrayList<>();

        expand.add(root);
        /* добавить в ArrayList File, созданный в первой строчке */

        for(int depth = 0; depth < 10; depth++) {
            /* запустить цикл на 10 повторений - число равно нужной глубине вложенности */

            File[] expandCopy = expand.toArray(new File[expand.size()]);
            /* переписываем данные из списка expand в массив expandCopy
             * В качестве аргумента нужно передать инциализированный массив нужной длины. Длину из expand извлекаем с помощью .size()
             * В результате получится массив ссылок на объекты типа File, в который записаны все названия папок на текущем уровне*/

            expand.clear();
            /* освободить первоначальный список для новых названий папок. старые - из предыдущего уровня - остаются в только что созданной копии */
            for (File givenFile : expandCopy) {
                logger.log(Level.INFO, String.format("main loop runs through %s sublevel", depth));
                if (givenFile.isDirectory()) {
                    /* проверить, является ли файл из полученного списка папкой - так мы получаем список файлов следующего уровня вложенности... */
                    expand.addAll(Arrays.asList(givenFile.listFiles()));
                    /* .. если является - получаем файлы, лежащие внутри file c помощью .listFiles() в виде массива с объектами File ->
                     *   -> Arrays.asList превращает наш массив файлов в список
                     *   добавляем названия файлов в expand, освобождённый перед началом работы цикла*/
                } else {
//                    String[] t = getNameAndExtension(file);
//                    logger.log(Level.INFO, String.format("we tried to separate name and extension " +
//                            "of the given file and write them into temporary variable"));
//
//                    String givenFile = t[0];
//                    String extensionOfTheGivenFile = t[1];
                    String extensionOfTheGivenFile = getExtension(givenFile.getName());
                    logger.log(Level.INFO, String.format("we have %s file named %s", extensionOfTheGivenFile, givenFile.getName()));

                    if ((extensionOfTheGivenFile != null) && (extensionOfTheGivenFile.equalsIgnoreCase(desiredFormat))) {
                        result.add(resultOfConverting(givenFile.getName(), desiredFormat));
                        logger.log(Level.INFO, "Given file do have needed extension. Adding it to resulting array");
                    }
                } //else clause inside inner loop
                /* на самой первой итерации папка будет всего одна - "." */
            } // inner loop to get all the files
        } //main loop inside converted images
        return result;
    } //return converted images

    private static File resultOfConverting(String nameOfTheGivenFile, String desiredFormat) throws IOException {
        File resultingImage;

        try {
            logger.log(Level.INFO, String.format("converting image"));
            BufferedImage img = ImageIO.read(new File(nameOfTheGivenFile));
            resultingImage = new File(nameOfTheGivenFile.substring(0,nameOfTheGivenFile.lastIndexOf(".")) + "." + desiredFormat);
            ImageIO.write(img, desiredFormat, resultingImage);
            return resultingImage;
        } catch (Exception e) {
            logger.log(Level.INFO, "Exeption while read write file");
            throw e;
        }
    }//return converted File

    private static String getExtension(String fileName) {
        logger.log(Level.INFO, "getNameAndExtension is called");
        logger.log(Level.INFO, String.format("Inside getNameAndExtension we get %s extension", fileName));
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            logger.log(Level.INFO, String.format("last index of is %s", i));
            return fileName.substring(i+1);
        }
        logger.log(Level.INFO, String.format("Using getExtension_m we find nothing", fileName));
        return null;
    }
}
