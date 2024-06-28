import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ExtensionsAndFolders {
    static Logger logger = Logger.getLogger(ExtensionsAndFolders.class.getName());

    public static void main(String[] args) {
        Logger logger = Logger.getLogger(ExtensionsAndFolders.class.getName());
        logger.log(Level.INFO, () -> {
            try {
                return Arrays.toString(returnFilesAndFolderNames("Yosemite", "srt", true));
            } catch (FileNotFoundException e) {
                logger.log(Level.INFO, () -> "while calling files and folder names forom the main function  " + e + " is thrown");
            }
            return null;
        });
    }

    public static ArrayList[] returnFilesAndFolderNames(String pathToRootFolder, String fileExtension, Boolean inspectDepth) throws FileNotFoundException {

        ArrayList<File> resultingListOfFiles = new ArrayList<File>();
        ArrayList<File> resultingListOfFolders = new ArrayList<File>();

        int depth = 1;
        if (inspectDepth) depth = 3;
        int finalDepth = depth;
        logger.log(Level.INFO, () -> "Inspection depth is " + finalDepth);

        File[] rootFolder = (new File(pathToRootFolder)).listFiles();
        File[] finalRootFolder1 = rootFolder;
        logger.log(Level.INFO, () ->"There are %s files in given pathname before initial loop" + finalRootFolder1.length);

        for (int i = 0; i < depth; i++) {

            File[][] tmpRes = returnNamesFromGivenFolder(rootFolder, fileExtension);
            int finalI = i;
            logger.log(Level.INFO, () ->"after looping " + finalI + " time through the folder tmpRes is " + Arrays.toString(tmpRes));

            resultingListOfFiles.addAll(Arrays.asList(tmpRes[0]));
            resultingListOfFolders.addAll(Arrays.asList(tmpRes[1]));

            logger.log(Level.INFO, () ->"%s files added to resulting array" + tmpRes[0].length);
            logger.log(Level.INFO, () ->"%s folders added to resulting array" + tmpRes[1].length);

            ArrayList<File> filesInTheNextLevelOfDepth = new ArrayList<>();

            for(File f: tmpRes[1]) {
                filesInTheNextLevelOfDepth.addAll(Arrays.asList(f.listFiles()));
            }

            rootFolder = filesInTheNextLevelOfDepth.toArray(new File[filesInTheNextLevelOfDepth.size()]);
            File[] finalRootFolder = rootFolder;
            logger.log(Level.INFO, () ->"After iterating over the subfolders, we get " + finalRootFolder.length + " new files");

        }

        ArrayList[] result = {resultingListOfFiles, resultingListOfFolders};
        return result;
    }

    private static File[][] returnNamesFromGivenFolder(File[] listOfFilenames, String fileExtensionNeeded) {
        Logger logger = Logger.getLogger(ExtensionsAndFolders.class.getName());
        logger.log(Level.INFO, () -> "returnNamesFromGivenFolder is called with " + listOfFilenames + " list" + " and " + fileExtensionNeeded + " extension");
        logger.log(Level.INFO, () ->"There are " + listOfFilenames.length + " files in that folder");

        ArrayList<File> listOfFiles = new ArrayList<File>();
        ArrayList<File> listOfFolders = new ArrayList<File>();

        logger.log(Level.INFO, "iteration inside returnNamesFromGivenFolder");

        for (File f: listOfFilenames) {
            logger.log(Level.INFO, () ->"current file is " + f.getName());
            logger.log(Level.INFO, () ->"from getExtension_m(f) we get " + getExtension_m(f));
            String tmp = getExtension_m(f);
            if (tmp != null && tmp.equalsIgnoreCase(fileExtensionNeeded)) {
                listOfFiles.add(f);
            }
            if (f.isDirectory()) listOfFolders.add(f);
            assert f.isDirectory() || f.isFile();
        }

        File[][] result = new File[2][];
        result[0] = listOfFiles.toArray(new File[0]);
        result[1] = listOfFolders.toArray(new File[0]);

        logger.log(Level.INFO, "returnNamesFromGivenFolder executed. return to filesAndFolderNames");
        return result;
    }

    private static String getExtension_m(File f) {
        Logger logger = Logger.getLogger(ExtensionsAndFolders.class.getName());
        logger.log(Level.INFO, () -> "getExtension_m is called with file named " + f);
        String fileName = f.getName();
        logger.log(Level.INFO, () ->"Inside getExtension_m we get " + fileName);
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            logger.log(Level.INFO, () ->"last index of is " + i);
            return fileName.substring(i+1);
        }
        logger.log(Level.INFO, () ->"Using getExtension_m we find nothing, file is" + fileName);
        return null;
    }
}
