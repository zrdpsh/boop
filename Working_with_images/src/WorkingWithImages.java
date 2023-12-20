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
    public static void main(String[] args) throws IOException {
        Logger logger = Logger.getLogger(WorkingWithImages.class.getName());
        logger.log(Level.INFO, String.format("main method started"));
        ArrayList<File> convertedImages = returnConvertedImages("png", "jpg");
//        Logger.log(Level.INFO, String.format("There are %s converted files", convertedImages.size()));

    }

    public static ArrayList<File> returnConvertedImages(String formatIn, String formatOut) throws IOException {
        Logger logger = Logger.getLogger(WorkingWithImages.class.getName());

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
            for (File file : expandCopy) {
                /* запустить цикл, в котором перебираем все названия файлов*/
                System.out.println(depth + " " + file);
                /* напечатать в консоль пару "уровень вложенности - название файла" - собственно, итог работы функции*/
                if (file.isDirectory()) {
                    /* проверить, является ли файл из полученного списка папкой - так мы получаем список файлов следующего уровня вложенности... */
                    expand.addAll(Arrays.asList(file.listFiles()));
                    /* .. если является - получаем файлы, лежащие внутри file c помощью .listFiles() в виде массива с объектами File ->
                     *   -> Arrays.asList превращает наш массив файлов в список
                     *   добавляем названия файлов в expand, освобождённый перед началом работы цикла*/
                } else {
                    String ext = getExtension_m(file);
                    logger.log(Level.INFO, String.format("we get %s extension inside the folder", ext));
                    if (ext == formatIn) {
                        logger.log(Level.INFO, String.format("converting image"));
                        BufferedImage img = ImageIO.read(new File(String.valueOf(file)));
                        ImageIO.write(img, formatOut, new File(String.valueOf(file)));
                    }
                }
                /* на самой первой итерации папка будет всего одна - "." */
            }
        }
        return result;
    } //return converted images

    private static String getExtension_m(File f) {
        Logger logger = Logger.getLogger(WorkingWithImages.class.getName());
        logger.log(Level.INFO, "getExtension_m is called");
        String fileName = f.getName();
        logger.log(Level.INFO, String.format("Inside getExtension_m we get %s extension", fileName));
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            logger.log(Level.INFO, String.format("last index of is %s", i));
            return fileName.substring(i+1);
        }
        logger.log(Level.INFO, String.format("Using getExtension_m we find nothing", fileName));
        return null;
    }
}
