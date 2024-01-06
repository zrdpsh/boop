package com.sksm.java_apis;

import java.io.*;
import java.util.Arrays;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.Random;

public class WriteToFile {

   static Logger logger = Logger.getLogger(WriteToFile.class.getName());
   public static final String  PATHNAME = "C:\\Users\\Admin\\Documents\\J_files\\boop\\boop\\working with files\\";

   public static void main(String[] args) throws FileNotFoundException {
      logger.log(Level.INFO, () -> "main function is called");

      Random rand = new Random();
      createBunchOfFiles(10, 3);
      double a = sumTheContents(PATHNAME, rand.nextInt(10)+1, rand.nextInt(10)+1);
      logger.log(Level.INFO, () -> "Sum of the contents of the file inside main function is " + a);
      deleteTheFiles(PATHNAME);
   } //public static void main
   
   public static void createBunchOfFiles(int howMuchFiles, int howMuchContent) {
      logger.log(Level.INFO, () -> "createBunchOfFiles function is called to create " + howMuchFiles + " files with " + howMuchContent + " numbers within each" );

      for (int i = 1; i <= howMuchFiles; i++) {
         String name = String.format("%s.txt", i);
         File nfile = new File(name);
         try (BufferedWriter bw = new BufferedWriter(new FileWriter(nfile))){
            logger.log(Level.INFO, () -> "Writing numbers to file");
            for (int j = 0; j < howMuchContent; j++) {
               int someRandomNumber = (int)(Math.random()*10);
               bw.write(""+someRandomNumber);
               bw.newLine();
            } //loop j to write random numbers
         } catch (Exception e) {
            logger.log(Level.INFO, "There is the exception", e);
         }//try catch exception
      } //for loop inside function
      logger.log(Level.INFO, () -> "" + howMuchFiles + " files created");
      logger.log(Level.INFO, "Returning to main function");
   } //bunch of files function

   public static double sumTheContents(String pathToFiles, int... nums) {
      logger.log(Level.INFO, () -> "sumTheContents function is called with " + pathToFiles + " path and " + Arrays.toString(nums) + " array");

      double resultingWholeSum = 0;

      for (int i = 0; i < nums.length; i++) {
         String fileName = pathToFiles + String.format("%s.txt", nums[i]);
         try (FileReader fr = new FileReader(fileName)) {
            resultingWholeSum += sumOfReadNumbers(fr);
            String loggerMessage = String.format("After the contents of the %s added, the resulting sum is %s", fileName, resultingWholeSum);
            logger.log(Level.INFO, loggerMessage);
         } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, () -> "File wasn't found" + e);
         } catch (IOException e) {
            logger.log(Level.SEVERE, "System cannot resolve the path", e);
         } catch (Exception e) {
            logger.log(Level.SEVERE, "Contents of files didn\'t summed", e);
         }// try catch block
      } //for loop inside sumTheContents

      return resultingWholeSum;

   } //sumTheContents function

   private static double sumOfReadNumbers(FileReader fr) {
      logger.log(Level.INFO, () -> "sumOfReadNumbers is called with " + fr.toString());
      double resultingSum = 0;

      try (BufferedReader br = new BufferedReader(fr)) {
         String[] contentsOfTheFile = parseTheStringFromFile(br);
         logger.log(Level.INFO, () -> "While reading the file with SumOfReadNumbers:");
         for (int i = 0; i < contentsOfTheFile.length; i++) {
            resultingSum += Integer.parseInt(contentsOfTheFile[i]);
            double finalResultingSum = resultingSum;
            int finalI = i;
            logger.log(Level.INFO, () -> "we get " + finalResultingSum + " after " + finalI);
         }//summation for loop
      } catch (Exception e){
         logger.log(Level.SEVERE, "Numbers from file didn't summed", e);
      }
      logger.log(Level.SEVERE, "return sumOfTheContents function");
      return resultingSum;
   }//sum of three numbers

   public static String[] parseTheStringFromFile(BufferedReader buffer) throws IOException {
      Logger logger = Logger.getLogger(WriteToFile.class.getName());
      String linesFromTheFile = "";

      try {
         for (String lineRead = buffer.readLine(); lineRead != null; lineRead = buffer.readLine()) {
            linesFromTheFile += lineRead;
            linesFromTheFile += " ";
         }
      } catch (FileNotFoundException e) {
         logger.log(Level.SEVERE, "File wasn't found", e);
         throw e;
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