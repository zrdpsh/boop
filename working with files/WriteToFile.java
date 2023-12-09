import java.nio.file.*;
import java.io.*;
// import static java.nio.file.StandartOpenOption; 
import java.lang.Math;
import java.util.logging.Logger;

public class WriteToFile {
   public static void main(String[] args) {
      bunchOfFiles(10, 3);
   } //public static void main
   
   public static void bunchOfFiles(int howMuchFiles, int howMuchContent) {
      for (int i = 1; i <= howMuchFiles; i++) {
         try {
            String name = String.format("%s.txt", i);
            File nfile = new File(name);
            BufferedWriter bw = new BufferedWriter(new FileWriter(nfile));
            
            for (int j = 0; j < howMuchContent; j++) {
               bw.write(""+Math.random());
               bw.newLine();
            } //loop j to write random numbers
            
            bw.close();
         } catch (IOException e) {
            System.out.println("Error! - "+e);
         }//try catch exception
      } //for loop inside function
   } //bunch of files function
   
   
   
   
   public static double openAndSumTheFiles(int num1, int num2, String pathToFiles) {
      Logger logger = Logger.getLogger(WriteToFile.class.getName());
      String file1name = pathToFiles+String.format("%s.txt", num1);
      String file2name = pathToFiles+String.format("%s.txt", num2);
      
      try {
         File
         BufferedReader br = new BufferedReader(new FileReader(pathToFiles+file1name));

      } catch(Exception e) {
      
      } finally {
      
      }
      
   } //sumOfFilecontents function
   
   public static double[] sumOfFileContents(BufferedReader buffer) {
      Logger logger = Logger.getLogger(WriteToFile.class.getName());
      try {
         String lineFromTheFile = buffer.nextLine();
         return sumOfThreeNumbers(lineFromTheFile.split(" ");
      } catch (Exception e) {
         logger.log(e);
      }
   } //sum of file contents function
   
   private static double sumOfThreeNumbers(String[] numbersAsStrings) {
      double resultingSum = 0;
      for (int i = 0; i < numbersAsStrings.length; i++) {
         result += (double)numbersAsStrings[i];
      } //summation for loop
      return resultingSum;
   }//sum of three numbers
}//Write to File class