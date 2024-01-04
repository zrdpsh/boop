package ExamplePackage;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Arrays;
import java.util.Scanner;
import java.util.InputMismatchException;

public class SortedArray
{
   public static final Logger logger = Logger.getLogger(SortedArray.class.getName());
   public static void main(String[] args)
   {
      logger.log(Level.INFO, () -> "main method is called");
      logger.log(Level.INFO, () -> "For how many months you have average temperature values?");
      Scanner input = new Scanner(System.in);
      int[] temperatures = getKelvs(input.nextInt());
      sort(temperatures);
      logger.log(Level.INFO, () -> "Printing all temperatures inside the loop:");
      for (int n: temperatures)
         logger.log(Level.INFO, () -> "Temperature is " + n + "\n");
   } //main method

   private static boolean isSorted(int[] x) {
      for (int i = 0; i < x.length - 1; i++) {
         if (x[i] > x[i + 1]) {
            logger.log(Level.INFO, () -> "isSorted returned false");
            return false; }
       }
       return true;
   } //isSorted method

   private static void sort(int[] arr)
   {
      logger.log(Level.INFO, () -> "sort function is called from the main with " + Arrays.toString(arr) + " array as an argument");

      int j, a;

      logger.log(Level.INFO, () -> "main loop inside sort function to run through the given array:");
      for (int i = 1; i < arr.length; i++)
      {
         int finalI1 = i;
         logger.log(Level.INFO, () -> "checking " + finalI1 + " element");
         a = arr[i];
         j = i;
         logger.log(Level.INFO, () -> "sort function inner loop to move bigger element to the beginning:");
         while (j > 0 && arr[j - 1] > a)
         {
            arr[j] = arr[j - 1];
            j--;
         }
         arr[j] = a;
         int finalI = i;
         logger.log(Level.INFO, () -> "After " + finalI + " iteration array is " + Arrays.toString(arr));
      }//for clause

      assert isSorted(arr): "array isn't sorted";
      assert arr[0]>-1: "temperature is below absolute zero somehow";
   } //sort method
   
   
   private static int[] getKelvs(int months) {
      logger.log(Level.INFO, () -> "getKelvs is called with " + months + "as an argument");
      int[] kelvs = new int[months];
      int counter = 0;
      Scanner scanner = new Scanner(System.in);
      logger.log(Level.INFO, () -> "getting temperature values from the keyboard:");
      while (counter < months) {
           kelvs[counter] = valueFromKeyboard(scanner);
           counter++;
      } //while counter < months

      logger.log(Level.INFO, () -> "returning to main function");
      return kelvs;   
   }// getKelvs method
   
   private static int valueFromKeyboard(Scanner scanner) throws InputMismatchException {
         logger.info("valueFromKeyboard is called");
         logger.log(Level.INFO, () -> "valueFromKeyboard is called with " + scanner.toString() + " argument");
         try {
            logger.info("try clause is called");
            System.out.println("Please, enter the number in Kelvins: ");
            logger.log(Level.INFO, () -> "returning to getKelvs function");
            return scanner.nextInt();
         } catch (InputMismatchException e) {
            logger.info("wrong input. temperature must be integer number");
            scanner.nextLine();
            logger.log(Level.INFO, () -> "returning to getKelvs function");
            return valueFromKeyboard(scanner);
         } 
   } //valueFromKeyboard function
}