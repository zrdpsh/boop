package ExamplePackage;

import java.util.Map;
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

      System.out.println(String.format("Array is %ssorted", isSortedInMiddle(temperatures)?"":"not "));
   } //main method

   static boolean isSorted(int[] x) {
      for (int i = 0; i < x.length - 1; i++) {
         if (x[i] > x[i + 1]) {
            logger.log(Level.INFO, () -> "isSorted returned false");
            return false; }
       }
       return true;
   } //isSorted method

   static boolean isSortedAlt(int[] x) {
      int i = x.length - 1;
      if (i <= 0) return true;
      if ((i & 1) > 0) {
         if (x[i] < x[i - 1]) {
            return false;
         }
         i--;
      }
      for (int xi = x[i]; i > 0; i -= 2) {
         if (xi < x[i-1] || xi < x[i-2]) return false;
      }

      return x[0] <= x[1];
   } //isSorted method

   static boolean isSortedInMiddle(int[] x) {

      if (x.length == 1) return true;
      if (x.length == 2) return x[1] >= x[0];

      int smpl = (int) Math.sqrt(x.length);
      int mddl = (x.length-1)/2;
      
      for (int i = mddl; i < mddl+smpl; i++) {
         if (x[i] > x[i+1]) return false;
      }
      return true;
   } //isSorted method


   static int[] sort(int[] arr)
   {
      logger.log(Level.INFO, () -> "sort function is called from the main with " + Arrays.toString(arr) + " array as an argument");

      int j;
      int a;
      int run = 0;
      int runningSum = 0;

      int finalRunningSum1 = 0;

      for (int o = 0; o < arr.length; o++) {
         if (o%2==0) {
            runningSum += arr[o];
         } else {
            runningSum -= arr[o];
         }
         finalRunningSum1 = runningSum;
         int finalO = o;
         int finalRunningSum3 = finalRunningSum1;
         logger.log(Level.INFO, () -> "Before sorting loop on the " + finalO + " element running sum equals " + finalRunningSum3);

      }


      logger.log(Level.INFO, () -> "main loop inside sort function to run through the given array:");
      for (int i = 1; i < arr.length; i++) {
         int finalI1 = i;
         logger.log(Level.INFO, () -> "checking " + finalI1 + " element");
         a = arr[i];
         j = i;
         logger.log(Level.INFO, () -> "sort function inner loop to move bigger element to the beginning:");
         while (j > 0 && arr[j - 1] > a) {
            arr[j] = arr[j - 1];
            j--;
         }
         arr[j] = a;
         if (i % 2 == 0) {
            runningSum+= a;
         } else {
            runningSum-= a;
         }
         int finalRunningSum = runningSum;
         int finalI = i;
         logger.log(Level.INFO, () -> "After " + finalI + " iteration array is " + Arrays.toString(arr));
         int finalJ = j;
//         run += j;
//         int finalRun = run;
         logger.log(Level.INFO, () -> "After " + finalI + " iteration j equals " + finalJ);
         logger.log(Level.INFO, () -> "After " + finalI + " iteration runningSum equals " + finalRunningSum);
//         logger.log(Level.INFO, () -> "After " + finalI + " iteration run equals " + finalRun);
      }// for loop inside the sort function

      runningSum = 0;

      int finalRunningSum2 = 0;
      for (int o = 0; o < arr.length; o++) {
         if (o%2==0) {
            runningSum += arr[o];
         } else {
            runningSum -= arr[o];
         }
         finalRunningSum2 = runningSum;
         int finalO = o;
         int finalRunningSum3 = finalRunningSum2;
         logger.log(Level.INFO, () -> "After the loop on the " + finalO + " element running sum equals " + finalRunningSum3);

      }

      int finalRunningSum = runningSum;
      logger.log(Level.INFO, () -> "After the loop running sum equals " + finalRunningSum);

      assert isSorted(arr): "array isn't sorted";
      assert arr[0]>-1: "temperature is below absolute zero somehow";

      return new int[]{finalRunningSum1, finalRunningSum2};
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