import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteFileWithFlag {
    static Logger logger = Logger.getLogger(DeleteFileWithFlag.class.getName());
    public static void main(String[] args) {
        logger.log(Level.INFO, () -> "main function is called");
        logger.log(Level.INFO, () -> (deleteFilesWithFlag("TestFolderForDeleting")).toString());
    }

    public static Boolean deleteFilesWithFlag(String folderName) {

        logger.log(Level.INFO, () -> "deleteFilesWithFlag function is called");

        try {
            File indexOfFiles = new File(folderName);
            File[] entries = indexOfFiles.listFiles();

            logger.log(Level.INFO, () -> "looping through the folder's hierarchy to delete files");
            assert entries != null;

            for (File f: entries) {
//                File currentFile = new File(indexOfFiles.getPath(), f);
                if (f.isDirectory()) return false;
                f.delete();
            }
            indexOfFiles.delete();
        } catch (Exception e) {
            logger.log(Level.INFO, "Smth wrong in the loop");
            e.printStackTrace();
            throw e;
        }
        return true;
    }
}
