import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExtensionsAndFolders {

    public static void main(String[] args) throws FileNotFoundException  {
        Logger logger = Logger.getLogger(ExtensionsAndFolders.class.getName());
        logger.log(Level.INFO, (returnFilesAndFolderNames("Yosemite", "srt", false)).toString());
    }

    public static ArrayList[] returnFilesAndFolderNames(String pathToRootFolder, String fileExtension, Boolean inspectDepth) throws FileNotFoundException {

        Logger logger = Logger.getLogger(ExtensionsAndFolders.class.getName());

        ArrayList<File> resultingListOfFiles = new ArrayList<File>();
        ArrayList<File> resultingListOfFolders = new ArrayList<File>();

        int depth = 1;
        if (inspectDepth) depth = 2;
        logger.log(Level.INFO, String.format("Inspection depth is %s", depth));

        File[] rootFolder = (new File(pathToRootFolder)).listFiles();
        logger.log(Level.INFO, String.format("There are %s files in given pathname before initial loop", rootFolder.length));

        for (int i = 0; i < depth; i++) {

            File[][] tmpRes = returnNamesFromGivenFolder(rootFolder, fileExtension);
            logger.log(Level.INFO, String.format("after looping %s time through the folder tmpRes is %s", i, tmpRes.toString()));

            resultingListOfFiles.addAll(Arrays.asList(tmpRes[0]));
            resultingListOfFolders.addAll(Arrays.asList(tmpRes[1]));

            logger.log(Level.INFO, String.format("%s files added to resulting array", tmpRes[0].length));
            logger.log(Level.INFO, String.format("%s folders added to resulting array", tmpRes[1].length));

            ArrayList<File> filesInTheNextLevelOfDepth = new ArrayList<>();

            for(File f: tmpRes[2]) {
                filesInTheNextLevelOfDepth.add(f);
            }

            rootFolder = filesInTheNextLevelOfDepth.toArray(new File[filesInTheNextLevelOfDepth.size()]);
            logger.log(Level.INFO, String.format("After iterating over the subfolders, we get %s new files", rootFolder.length));

        }

        ArrayList[] result = {resultingListOfFiles, resultingListOfFolders};
        return result;
    }

    private static File[][] returnNamesFromGivenFolder(File[] listOfFilenames, String fileExtensionNeeded) {
        Logger logger = Logger.getLogger(ExtensionsAndFolders.class.getName());
        logger.log(Level.INFO, "returnNamesFromGivenFolder is called");
        logger.log(Level.INFO, String.format("There are %s files in that folder", listOfFilenames.length));

        ArrayList<File> listOfFiles = new ArrayList<File>();
        ArrayList<File> listOfFolders = new ArrayList<File>();

        logger.log(Level.INFO, "iteration inside returnNames From Given Folder");

        for (File f: listOfFilenames) {
            logger.log(Level.INFO, String.format("current file is %s", f.getName()));
            logger.log(Level.INFO, String.format("from getExtension_m(f) we get %s", getExtension_m(f)));

            if (getExtension_m(f).equalsIgnoreCase(fileExtensionNeeded)) listOfFiles.add(f);
            if (f.isDirectory()) listOfFolders.add(f);
            assert f.isDirectory() || f.isFile();
        }

        File[][] result = new File[2][];
        result[0] = listOfFiles.toArray((new File[listOfFiles.size()]));
        result[1] = listOfFolders.toArray((new File[listOfFolders.size()]));

        return result;
    }

    private static String getExtension_m(File f) {
        Logger logger = Logger.getLogger(ExtensionsAndFolders.class.getName());
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
