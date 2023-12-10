import org.junit.jupiter.api.function.Executable;

import java.io.*;
// import static java.nio.file.StandartOpenOption; 
import java.lang.Math;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.Random;

public class WriteToFile {
   public static void main(String[] args) throws FileNotFoundException {
      Random rand = new Random();
      createBunchOfFiles(10, 3);
      double a = sumTheContents("C:\\Users\\Admin\\Documents\\J_files\\boop\\boop\\working with files\\", rand.nextInt(10)+1, rand.nextInt(10)+1);
      System.out.println(a);
      deleteTheFiles("C:\\Users\\Admin\\Documents\\J_files\\boop\\boop\\working with files\\");
   } //public static void main
   
   public static void createBunchOfFiles(int howMuchFiles, int howMuchContent) {
      Logger logger = Logger.getLogger(WriteToFile.class.getName());
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
            } //loop j to write random numbers
         } catch (Exception e) {
            logger.log(Level.INFO, "There is the exception", e);
         }//try catch exception
      } //for loop inside function
      String loggerMessage = String.format("%s files created", howMuchFiles);
      logger.log(Level.INFO, loggerMessage);
   } //bunch of files function

   public static double sumTheContents(String pathToFiles, int... nums) throws FileNotFoundException{

      Logger logger = Logger.getLogger(WriteToFile.class.getName());
      double resultingWholeSum = 0;

      for (int i = 0; i < nums.length; i++) {
         String fileName = pathToFiles + String.format("%s.txt", nums[i]);
         try (FileReader fr = new FileReader(new File(fileName))) {
            resultingWholeSum += sumOfReadNumbers(fr);
            String loggerMessage = String.format("After the contents of the %s added, the resulting sum is %s", fileName, resultingWholeSum);
            logger.log(Level.INFO, loggerMessage);
         } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "File wasn't found", e);
            throw e;
         } catch (IOException e) {
            logger.log(Level.SEVERE, "System cannot resolve the path", e);
         } catch (Exception e) {
            logger.log(Level.SEVERE, "Contents of files didn't summed", e);
         }// try catch block
      } //for loop inside sumTheContents

      return resultingWholeSum;

   } //sumTheContents function

   private static double sumOfReadNumbers(FileReader fr) {
      Logger logger = Logger.getLogger(WriteToFile.class.getName());
      double resultingSum = 0;

      try (BufferedReader br = new BufferedReader(fr)) {
         String[] contentsOfTheFile = parseTheStringFromFile(br);
         String loggerMessage = String.format("While reading the file with SumOfReadNumbers:");
         logger.log(Level.INFO, loggerMessage);
         for (int i = 0; i < contentsOfTheFile.length; i++) {
            resultingSum += Integer.parseInt(contentsOfTheFile[i]);
            loggerMessage = String.format("we get %s after the %sst iteration", resultingSum, i);
            logger.log(Level.INFO, loggerMessage);
         }//summation for loop
      } catch (Exception e){
         logger.log(Level.SEVERE, "Numbers from file didn't summed", e);
      }
      return resultingSum;
   }//sum of three numbers

   static String[] parseTheStringFromFile(BufferedReader buffer) throws IOException {
      Logger logger = Logger.getLogger(WriteToFile.class.getName());
      String linesFromTheFile = "";

      try {
         for (String lineRead = buffer.readLine(); lineRead != null; lineRead = buffer.readLine()) {
            linesFromTheFile += lineRead;
            linesFromTheFile += " ";
         }
      } catch (Exception e) {
         logger.log(Level.INFO, e.toString());
      }//try catch block

      String loggerMessage = String.format("Inside the parseTheStringFromFile contents of file are %s", linesFromTheFile);
      logger.log(Level.INFO, loggerMessage);
      return linesFromTheFile.split(" ");
   } //sum of file contents function

   private static void deleteTheFiles(String pathToFiles){
      Logger logger = Logger.getLogger(WriteToFile.class.getName());
      logger.log(Level.ALL, "Deleting Files from 1 to 10:");
      for (int i = 1; i < 11; i++) {
         String pathToCurrentFile = pathToFiles + String.format("%s.txt", i);
         String loggerMessage = String.format("removing %sst file", i);
         logger.log(Level.ALL, loggerMessage);
         try {
            File fileToDelete = new File(pathToCurrentFile);
            fileToDelete.delete();
         } catch (Exception e) {
            loggerMessage = String.format("while deleting file number %s, exception is thrown -", i);
            logger.log(Level.ALL, loggerMessage);
            logger.log(Level.ALL, e.toString());
         }
      }
   }

}//Write to File class