import java.io.*;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class WorkingWithZip {
    public static void main(String[] args) throws IOException {

        String[] tstString = createBunchOfFiles(10, 3);
        int[] zipResult = zipGivenFiles("tst.zip", tstString);
        System.out.println(String.format("Function worked %s", zipResult[1] == 0? "FINE" : "BAD"));
    }

    public static int[] zipGivenFiles(String nameOfTheArchive, String[] namesOfTheFiles) throws IOException {
        Logger logger = Logger.getLogger(WorkingWithZip.class.getName());
        int[] rs = {1, 0};

        ZipOutputStream zipOtpt = new ZipOutputStream(new FileOutputStream(nameOfTheArchive));

        for (String fileName : namesOfTheFiles ) {
            zipFile(zipOtpt, fileName);
            logger.log(Level.INFO, String.format("%s is written to archive", fileName));
        }//for fileName in namesOfTheFiles

        ZipOutputStream zo =  new ZipOutputStream(new FileOutputStream(new File(nameOfTheArchive)));


        return rs;
    }

    public static void zipFile(ZipOutputStream zpOpStr, String fileName) throws IOException {
        zpOpStr.putNextEntry(new ZipEntry(fileName));

        FileInputStream filInpStr = new FileInputStream(new File(fileName));

        byte[] buffer = new byte[4092];
        int byteCount = 0;

        while((byteCount = filInpStr.read(buffer)) != -1) {
            zpOpStr.write(buffer, 0, byteCount);
            System.out.flush();
        }

        filInpStr.close();
        zpOpStr.closeEntry();
    }


    public static String[] createBunchOfFiles(int howMuchFiles, int howMuchContent) {
        Logger logger = Logger.getLogger(WorkingWithZip.class.getName());
        String[] result = new String[howMuchFiles];
        for (int i = 1; i <= howMuchFiles; i++) {
            String name = String.format("%s.txt", i);
            File nfile = new File(name);
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(nfile))){
                for (int j = 0; j < howMuchContent; j++) {
                    int someRandomNumber = (int)(Math.random()*10);
                    bw.write(""+someRandomNumber);
                    bw.newLine();
//               String loggerMessage = String.format("%s added to a file number %s", someRandomNumber, i);
//               logger.log(Level.INFO, loggerMessage);
                }//loop j to write random numbers
                result[i] = name;
            } catch (Exception e) {
                logger.log(Level.INFO, "There is the exception", e);
            }//try catch exception
        } //for loop inside function
        String loggerMessage = String.format("%s files created", howMuchFiles);
        logger.log(Level.INFO, loggerMessage);
        return result;
    } //bunch of files function
}
