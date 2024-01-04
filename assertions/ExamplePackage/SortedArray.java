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
      logger.log(Level.INFO, () -> "For how many months you have average temperature values?");
      Scanner input = new Scanner(System.in);
      int[] temperatures = getKelvs(input.nextInt());
      sort(temperatures);
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
      int j, a;
      for (int i = 1; i < arr.length; i++)
      {
         a = arr[i];
         j = i;
         while (j > 0 && arr[j - 1] > a)
         {
            arr[j] = arr[j - 1];
            j--;
         }
         arr[j] = a;
         logger.log(Level.INFO, () -> "After " + i + " iteration array is " + Arrays.toString(arr));
      }//for clause

      assert isSorted(arr): "array isn't sorted";
      assert arr[0]>-1: "temperature is below absolute zero somehow";
   } //sort method
   
   
   private static int[] getKelvs(int months) {
      int[] kelvs = new int[months];
      int counter = 0;
      Scanner scanner = new Scanner(System.in);
      while (counter < months) {
           kelvs[counter] = valueFromKeyboard(scanner);
           counter++;
      } //while counter < months
      
      return kelvs;   
   }// getKelvs method
   
   private static int valueFromKeyboard(Scanner scanner) throws InputMismatchException {
         logger.info("valueFromKeyboard is called");
      logger.log(Level.INFO, () -> "valueFromKeyboard function is called");
         try {
            logger.info("try clause is called");
            System.out.println("Please, enter the number in Kelvins: ");
            return scanner.nextInt();
         } catch (InputMismatchException e) {
            logger.info("wrong input. temperature must be integer number");
            scanner.nextLine();
            return valueFromKeyboard(scanner);
         } 
   } //valueFromKeyboard function
}