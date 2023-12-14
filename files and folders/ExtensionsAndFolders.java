import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExtensionsAndFolders {
    public static void main(String[] args) {

    }

    public static ArrayList[] returnFilesAndFolderNames(String pathToRootFolder, String fileExtension, Boolean inspectDepth) throws FileNotFoundException {

        Logger logger = Logger.getLogger(ExtensionsAndFolders.class.getName());

        ArrayList<File> listOfFiles = new ArrayList<File>();
        ArrayList<File> listOfFolders = new ArrayList<File>();

        int depth = 1;
        if (inspectDepth) depth = 10;
        logger.log(Level.INFO, "Inspection depth is ", depth);

        File rootFolder = new File(pathToRootFolder);

        File[] filesFromIteration = listOfFiles.toArray(new File[listOfFiles.size()]);
        File[] foldersFromIteration = listOfFiles.toArray(new File[listOfFiles.size()]);

        File[] filesInFolder = rootFolder.listFiles();

        for (int i = 0; i < depth; i++) {

            ArrayList[] tmpRes = returnNamesFromGivenFolder(filesInFolder, fileExtension);
            logger.log(Level.INFO, "after looping through the folder tmpRes is ", tmpRes.toString());

            filesFromIteration = tmpRes[0].addAll(); Arrays.asList(file.listFiles());
            foldersFromIteration = tmpRes[1].addAll();

            filesInFolder = tmpRes[0].addAll(); Arrays.asList(file.listFiles());


        }

        ArrayList[] result = {listOfFiles, listOfFolders};
        return result;
    }

    private static ArrayList[] returnNamesFromGivenFolder(File[] listOfFilenames, String fileExtensionNeeded) {

        ArrayList<File> listOfFiles = new ArrayList<File>();
        ArrayList<File> listOfFolders = new ArrayList<File>();

        for (File f: listOfFilenames) {
            if (getExtension(f) == fileExtensionNeeded) listOfFiles.add(f);
            if (f.isDirectory()) listOfFolders.add(f);
            assert f.isDirectory() || f.isFile();
        }
        return new ArrayList[]{listOfFiles, listOfFolders};
    }


    private static String getExtension(File f) {
        String fileName = f.getName();
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            return fileName.substring(i+1);
        }
        return null;
    }
}
