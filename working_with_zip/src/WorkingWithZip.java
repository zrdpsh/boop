import java.io.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;


public class WorkingWithZip {
    public static void main(String[] args) throws IOException {
        Logger logger = Logger.getLogger(WorkingWithZip.class.getName());
        String NAMEOFARCHIVE = "ArchiveToTest.zip";

        deleteFileIfExists(NAMEOFARCHIVE);
/* ---- удаляем архив из предыдущей попытки запустить код */


        String[] files = createBunchOfFiles(10, 10);
/* ---- создаем 10 тестовых файлов, которые будем добавлять */
        logger.log(Level.INFO, String.format("While running the main function, %s names of new files added to list", files.length));

        createArchiveToTest(NAMEOFARCHIVE);
/* ---- создаем непустой тестовый архив с текстовым файлом внутри */
        logger.log(Level.INFO, String.format("While running the main function, testing archive created successfully"));

        try {
            addFilesToArchive(NAMEOFARCHIVE, files);
/* ---- собственно, добавляем тестовые файлы из createBunchOfFiles в только что созданный архив */
        } catch (Exception e) {
            logger.log(Level.INFO, "Exception happened while calling Files to Archive");
            throw e;
        }
        logger.log(Level.INFO, "Files added correctly");

        for (String file : files) {
            deleteFileIfExists(file);
/* ---- удаляем тестовые файлы */
        }

        logger.log(Level.INFO, "Testing files deleted correctly");
    }

    public static void addFilesToArchive(String nameOfTheArchive, String[] namesOfTheFiles) throws IOException {
        Logger logger = Logger.getLogger(WorkingWithZip.class.getName());

        String pathZip = new File(nameOfTheArchive).getAbsolutePath();
/* ---- получаем абсолютный путь до архива, в который будем добавлять файл */

        Map<String, byte[]> zipEntryMap = link_FileName_With_ItsContents(pathZip);
/* ---- link_FileName_with_ItsContents возвращает словарь, где каждому из им */
        FileOutputStream fileOutputStream =  new FileOutputStream(pathZip);
/* ---- получаем абсолютный путь до архива, в который будем добавлять файл */
        ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
/* ---- получаем абсолютный путь до архива, в который будем добавлять файл */

        zipEntryMap.forEach((zipEntryName, bytes) -> {
/* ---- получаем абсолютный путь до архива, в который будем добавлять файл */
            logger.log(Level.INFO, zipEntryName+" "+bytes.toString());
            try {
                copyExistingFile_ToTheNewArchive(pathZip, bytes, zipEntryName, zipOutputStream);
/* ---- получаем абсолютный путь до архива, в который будем добавлять файл */
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        zipOutputStream.closeEntry();
/* ---- получаем абсолютный путь до архива, в который будем добавлять файл */

        for (String fileName: namesOfTheFiles) {
/* ---- получаем абсолютный путь до архива, в который будем добавлять файл */
            try {
                addNewFileInOpenedArchive(fileName, pathZip, zipOutputStream);
/* ---- получаем абсолютный путь до архива, в который будем добавлять файл */
            } catch (java.util.zip.ZipException e) {
                logger.log(Level.INFO, String.format("%s is already in archive", fileName));
            } catch (Exception e) {
                logger.log(Level.INFO, "error occured while running add new file to archive" );
            }

        }

        zipOutputStream.close();
/* ---- получаем абсолютный путь до архива, в который будем добавлять файл */

    }
        private static Map<String, byte[]> link_FileName_With_ItsContents(String pathZip) throws IOException {
        Logger logger = Logger.getLogger(WorkingWithZip.class.getName());

        Map<String, byte[]> zipEntryMap = new HashMap<>();
/* ---- получаем абсолютный путь до архива, в который будем добавлять файл */
        byte[] buffer = new byte[1024];
/* ---- получаем абсолютный путь до архива, в который будем добавлять файл */
        try {
            FileInputStream fileInputStream = new FileInputStream(pathZip);
/* ---- получаем абсолютный путь до архива, в который будем добавлять файл */
            ZipInputStream zipInputStream = new ZipInputStream(fileInputStream);
/* ---- получаем абсолютный путь до архива, в который будем добавлять файл */
            ZipEntry zipEntry;
/* ---- получаем абсолютный путь до архива, в который будем добавлять файл */

            while((zipEntry = zipInputStream.getNextEntry())!= null){
/* ---- получаем абсолютный путь до архива, в который будем добавлять файл */
                ByteArrayOutputStream builder = new ByteArrayOutputStream();
/* ---- получаем абсолютный путь до архива, в который будем добавлять файл */
                int end = zipInputStream.read(buffer);
/* ---- получаем абсолютный путь до архива, в который будем добавлять файл */
                while(end > 0){
                    builder.write(buffer, 0, end);
/* ---- получаем абсолютный путь до архива, в который будем добавлять файл */
                    end = zipInputStream.read(buffer);
/* ---- получаем абсолютный путь до архива, в который будем добавлять файл */
                }
                zipEntryMap.put(zipEntry.getName(), builder.toByteArray());
/* ---- получаем абсолютный путь до архива, в который будем добавлять файл */
            }
        }catch(IOException ex){
            logger.log(Level.INFO, "Exception while trying to write byte array in side link Filename with its Contents");
    }
        return zipEntryMap;
/* ---- получаем абсолютный путь до архива, в который будем добавлять файл */
    }

    private static void copyExistingFile_ToTheNewArchive(String pathZip, byte[] bytes, String zipEntryName, ZipOutputStream zipOutputStream) throws Exception{
        ZipEntry zipEntry2nd = new ZipEntry(zipEntryName);
/* ---- получаем абсолютный путь до архива, в который будем добавлять файл */
        zipEntry2nd.setSize(bytes.length);
/* ---- получаем абсолютный путь до архива, в который будем добавлять файл */
        zipOutputStream.putNextEntry(new ZipEntry(zipEntryName));
/* ---- получаем абсолютный путь до архива, в который будем добавлять файл */
        zipOutputStream.write(bytes);
/* ---- получаем абсолютный путь до архива, в который будем добавлять файл */
    }

    private static void addNewFileInOpenedArchive(String fileToBeAdded, String archiveToAddAFile, ZipOutputStream zos) throws IOException {
        Logger logger = Logger.getLogger(WorkingWithZip.class.getName());

        Path pathToAddedFile = Paths.get((new File(fileToBeAdded)).getPath());
/* ---- получаем абсолютный путь до архива, в который будем добавлять файл */
        try {
            zos.putNextEntry(new ZipEntry((new File(fileToBeAdded)).getPath()));
/* ---- получаем абсолютный путь до архива, в который будем добавлять файл */
            Files.copy(pathToAddedFile, zos);
/* ---- получаем абсолютный путь до архива, в который будем добавлять файл */
        } catch (Exception e) {
            logger.log(Level.INFO, "Exception occured while running addNewFileInOpenedArchive");
            throw e;
        }

    }

/* ---- вспомогательные функции:  */

    private static String[] createBunchOfFiles(int howMuchFiles, int howMuchContent) throws IOException {
        Logger logger = Logger.getLogger(WorkingWithZip.class.getName());
        String[] result = new String[howMuchFiles];
        logger.log(Level.INFO, "Startin' creating bunch of files inside bunchOfFiles");

        for (int i = 0; i < howMuchFiles; i++) {
            String name = String.format("%s.txt", i);
            logger.log(Level.INFO, String.format("%s file created", name));
            File nfile = new File(name);

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(nfile))){
                for (int j = 0; j < howMuchContent; j++) {
                    int someRandomNumber = (int)(Math.random()*10);
                    bw.write(""+someRandomNumber);
                    bw.newLine();
                    logger.log(Level.INFO, String.format("%s added to file named %s", someRandomNumber, name));
                }//loop j to write random numbers
            } catch (Exception e) {
                logger.log(Level.INFO, "There is the exception", e);
                throw e;
            }//try catch exception
            result[i] = nfile.getName();
            logger.log(Level.INFO, String.format("created file named %s added to resulting list", nfile.getName()));
        } //for loop inside function
        String loggerMessage = String.format("%s files created with %s numbers in each", howMuchFiles, howMuchContent);
        logger.log(Level.INFO, loggerMessage);
        return result;
    } //bunch of files function

    private static void createArchiveToTest(String archiveName) throws IOException {
        Logger logger = Logger.getLogger(WorkingWithZip.class.getName());
        String testString = "once upon a time";

        File f = new File(archiveName);
        try {
            ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(f));
            ZipEntry e = new ZipEntry("testFile.txt");
            zos.putNextEntry(e);

            byte[] data = testString.getBytes();
            zos.write(data, 0, data.length);
            zos.closeEntry();

            zos.close();
        } catch (Exception e) {
            logger.log(Level.INFO, "exception while creating a test file");
        }
    } //create archive to test function

    private static void deleteFileIfExists(String nameOfTheArchive) {
        Logger logger = Logger.getLogger(WorkingWithZip.class.getName());

        File fd = new File(nameOfTheArchive);
        if (fd.delete()) {
            logger.log(Level.INFO, "previous archive successfully deleted");
        } else {
            logger.log(Level.INFO, "error while deleting the file");
        }
    }
}
