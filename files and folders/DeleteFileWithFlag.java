import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteFileWithFlag {
    public static void main(String[] args) {

    }

    public static Boolean deleteFilesWithFlag(String folderName) {

        Logger logger = Logger.getLogger(DeleteFileWithFlag.class.getName());

        try {
            File indexOfFiles = new File(folderName);
            File[] entries = indexOfFiles.listFiles();
            for (File f: entries) {
//                File currentFile = new File(indexOfFiles.getPath(), f);
                if (f.isDirectory()) return false;
                f.delete();
            }
        } catch (Exception e) {
            logger.log(Level.INFO, "Smth wrong in the loop");
            e.printStackTrace();
            throw e;
        } finally {

        }
        return true;
    }
}
