package com.sksm.java_apis;

import java.io.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
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
        logger.log(Level.INFO, "While running the main function, testing archive created successfully");

        try {
            addFilesToArchive(NAMEOFARCHIVE, files);
/* ---- собственно, добавляем тестовые файлы из createBunchOfFiles в только что созданный архив
*  ---- идея в том, чтобы прочитать файлы из имеющегося архива -> открыть на запись новый -> добавить в него и старые, и новые файлы вместе
* ----- т.е мы не добавляем, а перезаписываем с новыми файлами */
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
/* ---- link_FileName_with_ItsContents возвращает словарь, где каждому из им файлов из существующего архива сопоставлено
* ----- его содержимое в виде массива байтов*/
        FileOutputStream fileOutputStream =  new FileOutputStream(pathZip);
/* ---- оборачиваем путь до архива в FileOutputStream */
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);) {
            /* ---- оборачиваем FileOutputStream в ZipOutputStream - архив представляется в виде объекта, с которым может работать java*/

            zipEntryMap.forEach((zipEntryName, bytes) -> {
                /* ---- здесь в цикле для каждой пары <название-строка байтов> вызываем функцию, чтобы скопировать их в новый архив */
                logger.log(Level.INFO, zipEntryName+" "+ Arrays.toString(bytes));
                try {
                    copyExistingFileToTheNewArchive(pathZip, bytes, zipEntryName, zipOutputStream);
                    /* ---- отдельный файл из старого архива добавляется в новый архив с тем же именем */
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });


        for (String fileName: namesOfTheFiles) {
/* ---- в цикле перебираем все новые файлы, предназначенные для записи, и добавляем каждый из них архив из предыдущего цикла*/
            try {
                addNewFileInOpenedArchive(fileName, pathZip, zipOutputStream);
/* ---- добавляем отдельный файл в новый архив. При этом поток на запись будет открываться заново */
            } catch (java.util.zip.ZipException e) {
                logger.log(Level.INFO, String.format("%s is already in archive", fileName));
            } catch (Exception e) {
                logger.log(Level.INFO, "error occured while running add new file to archive" );
            }

        }

     }
    }
        private static Map<String, byte[]> link_FileName_With_ItsContents(String pathZip) throws IOException {
        Logger logger = Logger.getLogger(WorkingWithZip.class.getName());

        Map<String, byte[]> zipEntryMap = new HashMap<>();
/* ---- создаём пустой словарь под пары <название - содержимое в байтах> */
        byte[] buffer = new byte[1024];
/* ---- создаём временный байтовый массив для копирования содержимого файла */
        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(pathZip))){
;
/* ---- оборачиваем путь до архива в ZipOutputStream - чтобы можно было в него записывать*/
            ZipEntry zipEntry;
/* ---- инициализируем объект, с помощью которое отдельный файл добавляется в архив */

            while((zipEntry = zipInputStream.getNextEntry())!= null){
/* ---- здесь одновременно:
*        - считаем файл из архива в перменную ZipEntry
*        - сравниваем с null. если null - значит дошли до конца архива */
                ByteArrayOutputStream builder = new ByteArrayOutputStream();
/* ---- создаём объект для работы потоками байтов */
                int end = zipInputStream.read(buffer);
/* ---- первоначально прочитываем порцию байт из архивного файла*/
                while(end > 0){
                    builder.write(buffer, 0, end);
/* ---- пока файл не прочитан до конца, добавляем байты в поток */
                    end = zipInputStream.read(buffer);
/* ---- прочитываем следующую порцию байт */
                }
                zipEntryMap.put(zipEntry.getName(), builder.toByteArray());
/* ---- превращаем поток байтов в массив и записываем в словарь вместе с именем файла  */
            }
        }catch(IOException ex){
            logger.log(Level.INFO, "Exception while trying to write byte array in side link Filename with its Contents");
/* ---- до того, как почистил catch, функция выбрасывала GZipInputStream unexpected end of ZLib input stream и крашилась. Решение - магическое, смысла пока не понимаю */
    }
        return zipEntryMap;
/* ---- возвращаем пары <название - массив байтов> */
    }


// ..копируем отдельный файл из старого архива в новый архив - с тем же именем
    private static void copyExistingFileToNewArchive(String pathZip, byte[] bytes, String zipEntryName, ZipOutputStream zipOutputStream) throws Exception{
        ZipEntry zipEntry2nd = new ZipEntry(zipEntryName);
/* ---- создаем новый ZipEntry из имени файла */
        zipEntry2nd.setSize(bytes.length);
/* ---- устанавливаем значение, равное длине файла в байтах */
        zipOutputStream.putNextEntry(new ZipEntry(zipEntryName));
/* ---- открываем запись файла в архив */
        zipOutputStream.write(bytes);
/* ---- записываем в архив сам файл */
    }

    private static void addNewFileInOpenedArchive(String fileToBeAdded, String archiveToAddAFile, ZipOutputStream zos) throws IOException {
        Logger logger = Logger.getLogger(WorkingWithZip.class.getName());

        Path pathToAddedFile = Paths.get((new File(fileToBeAdded)).getPath());
/* ---- получаем абсолютный путь до нового файла, в который будем добавлять в архив */
        try {
            zos.putNextEntry(new ZipEntry((new File(fileToBeAdded)).getPath()));
/* ---- открываем запись файла в архив */
            Files.copy(pathToAddedFile, zos);
/* ---- копируем содержимое файла */
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
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(f))){

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
    } //delete File IfExists
}
