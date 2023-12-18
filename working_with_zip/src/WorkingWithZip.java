import java.io.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class WorkingWithZip {
    public static void main(String[] args) throws IOException {

        String[] tstString = createBunchOfFiles(10, 3);
//        int[] zipResult = zipGivenFiles("tst.zip", tstString);
        zipGivenFiles("tst.zip", tstString);
        System.out.println(String.format("Function worked %s", zipResult[1] == 0? "FINE" : "BAD"));
    }


    public static void zipGivenFiles(String nameOfTheArchive, String[] namesOfTheFiles) throws IOException {
/* Чтобы добавить файл в архив, надо:
* - превратить имя файла в исходящий поток File -> FileOutputStream -> ZipOutputStream
* - сделать сам архив входящим потоком, открыть его на запись File -> */
    }

    public static void saveFileInArchive(String fileToBeAdded, String archiveToAddAFile) throws FileNotFoundException {

        Path pathToBeAdded = Paths.get((new File(fileToBeAdded)).getAbsolutePath());
        ZipOutputStream zos = null;

        try {
            zos = new ZipOutputStream(new FileOutputStream(archiveToAddAFile));
            zos.putNextEntry(new ZipEntry(fileToBeAdded));
            /* открывает zip файл на запись и перемещает указатель в начало потока ??? */
            Files.copy(pathToBeAdded, zos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            zos.close();
        }

    }


    public static int[] zipGivenFilesExp(String nameOfTheArchive, String[] namesOfTheFiles) throws IOException {
        Logger logger = Logger.getLogger(WorkingWithZip.class.getName());
        int[] rs = {1, 0};
/*-----------------what this line do ?----------------*/
        ZipOutputStream zipOtPt = new ZipOutputStream(new FileOutputStream(nameOfTheArchive));
/*-----------------what this line do ?----------------*/
        for (String fileName : namesOfTheFiles ) {
/*-----------------what this line do ?----------------*/
            zipFile(zipOtPt, fileName);
/*-----------------what this line do ?----------------*/
            logger.log(Level.INFO, String.format("%s is written to archive", fileName));
        }//for fileName in namesOfTheFiles

        ZipOutputStream zo =  new ZipOutputStream(new FileOutputStream(new File(nameOfTheArchive)));
/*-----------------what this line do ?----------------*/


        return rs;
    }

    public static void zipFile(ZipOutputStream zpOtPtStr, String fileName) throws IOException {
        zpOtPtStr.putNextEntry(new ZipEntry(fileName));
/*-----------------what this line do ?----------------*/
        FileInputStream filInpStr = new FileInputStream(new File(fileName));
/*-----------------what this line do ?----------------*/
        byte[] buffer = new byte[4092];
/*-----------------what this line do ?----------------*/
        int byteCount = 0;
/*-----------------what this line do ?----------------*/
        while((byteCount = filInpStr.read(buffer)) != -1) {
/*-----------------what this line do ?----------------*/
            zpOtPtStr.write(buffer, 0, byteCount);
/*-----------------what this line do ?----------------*/
            System.out.flush();
/*-----------------what this line do ?----------------*/
        }

        filInpStr.close();
/*-----------------what this line do ?----------------*/
        zpOtPtStr.closeEntry();
/*-----------------what this line do ?----------------*/
    }


    public static String[] createBunchOfFiles(int howMuchFiles, int howMuchContent) throws IOException {
        Logger logger = Logger.getLogger(WorkingWithZip.class.getName());
        String[] result = new String[howMuchFiles];
        logger.log(Level.INFO, "Startin' creating bunch of files inside bunchOfFiles");
        for (int i = 1; i <= howMuchFiles; i++) {
            String name = String.format("%s.txt", i);
            File nfile = new File(name);
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(nfile))){
                for (int j = 0; j < howMuchContent; j++) {
                    int someRandomNumber = (int)(Math.random()*10);
                    bw.write(""+someRandomNumber);
                    bw.newLine();
                    logger.log(Level.INFO, String.format("%s added to file named %s", someRandomNumber, name));
                }//loop j to write random numbers
                result[i] = name;
            } catch (Exception e) {
                logger.log(Level.INFO, "There is the exception", e);
                throw e;
            }//try catch exception
        } //for loop inside function
        String loggerMessage = String.format("%s files created with %s numbers in each", howMuchFiles, howMuchContent);
        logger.log(Level.INFO, loggerMessage);
        return result;
    } //bunch of files function
}
