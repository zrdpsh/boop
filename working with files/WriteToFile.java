import java.nio.file.*;
import java.io.*;
// import static java.nio.file.StandartOpenOption; 
import java.lang.Math;
import java.util.logging.Logger;
import java.util.Random;

public class WriteToFile {
   public static void main(String[] args) {
      Random rand = new Random();
      bunchOfFiles(10, 3);
      double a = openAndSumTheFiles(rand.nextInt(11), rand.nextInt(11), "C:\\Users\\Admin\\Documents\\J_files\\boop\\boop\\working with files\\");
      System.out.println(a);
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
      double sum1;
      double sum2;
      
      try {
         File fl1 = new File(pathToFiles+file1name);
         BufferedReader br = new BufferedReader(new FileReader(fl1));
         sum1 = sumOfThreeNumbers(br);
         File fl2 = new File(pathToFiles+file2name);
         BufferedReader br = new BufferedReader(new FileReader(fl2));
         sum2 = sumOfThreeNumbers(br);
      } catch(Exception e) {
         logger.log(e);
      } finally {
         fl.close();
      }
         return sum1 + sum2;
   } //sumOfFilecontents function
   
   public static double[] parseTheString(BufferedReader buffer) {
      Logger logger = Logger.getLogger(WriteToFile.class.getName());
      try {
         String lineFromTheFile = buffer.nextLine();
         return sumOfThreeNumbers(lineFromTheFile.split(" "));
      } catch (Exception e) {
         logger.log(e);
      }
   } //sum of file contents function
   
   private static double sumOfThreeNumbers(BufferedReader br) {
      double resultingSum = 0;
      String[] numbersAsStrings = parseTheString(br);
      for (int i = 0; i < numbersAsStrings.length; i++) {
         result += (double)numbersAsStrings[i];
      } //summation for loop
      return resultingSum;
   }//sum of three numbers
}//Write to File class