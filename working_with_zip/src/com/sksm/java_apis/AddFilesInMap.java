package com.sksm.java_apis;

import java.io.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;




public class AddFilesInMap {

    static Logger logger = Logger.getLogger(AddFilesInMap.class.getName());
    static String PATHZIP = "C:\\Users\\Admin\\Documents\\J_files\\boop\\boop\\working_with_zip\\src\\test.zip";
    public static void main(String[] args) throws IOException {
        Path fileName = Paths.get("C:\\Users\\Admin\\Documents\\J_files\\boop\\boop\\working_with_zip\\src\\Infin.txt");

        Map<String, byte[]> zipEntryMap = addFilesInMapFunction(PATHZIP);
        FileOutputStream fileOutputStream =  new FileOutputStream(PATHZIP);
        ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
        zipEntryMap.forEach((zipEntryName, bytes) -> {
            logger.log(Level.INFO, String.format("%s %s",zipEntryName, Arrays.toString(bytes)));
            try {
                addBufferedFilesToArchiveAndSave(bytes, zipEntryName, zipOutputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        saveFileInArchive(fileName, zipOutputStream);
        zipOutputStream.close();
        fileOutputStream.close();
    }
    private static Map<String, byte[]> addFilesInMapFunction(String PATHZIP) throws IOException {
        Map<String, byte[]> zipEntryMap = new HashMap<>();
        FileInputStream fileInputStream = new FileInputStream(PATHZIP);
        try(ZipInputStream zipInputStream = new ZipInputStream(fileInputStream);) {
            ZipEntry zipEntry;
            while((zipEntry = zipInputStream.getNextEntry())!= null){
                byte[] buffer = new byte[1024];
                ByteArrayOutputStream builder = new ByteArrayOutputStream();
                int end;
                while((end = zipInputStream.read(buffer)) > 0){
                    builder.write(buffer, 0, end);
                }
                zipEntryMap.put(zipEntry.getName(), builder.toByteArray());
            }
        }
        return zipEntryMap;
    }

    // ..добавить файлы из буфера в новый архив и записать на диск
    private static void addBufferedFilesToArchiveAndSave(byte[] bytes, String zipEntryName, ZipOutputStream zipOutputStream) throws IOException {
        ZipEntry zipEntry2 = new ZipEntry(zipEntryName);
        zipEntry2.setSize(bytes.length);
        zipOutputStream.putNextEntry(new ZipEntry(zipEntryName));
        zipOutputStream.write(bytes);
    }
    private static void saveFileInArchive(Path fileToBeAdded, ZipOutputStream zipOutputStream) throws IOException {
        zipOutputStream.putNextEntry(new ZipEntry(""+fileToBeAdded.getFileName()));
        Files.copy(fileToBeAdded, zipOutputStream);
    }
}
