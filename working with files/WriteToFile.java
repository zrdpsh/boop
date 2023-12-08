import java.nio.file.*;
import java.io.*;
// import static java.nio.file.StandartOpenOption; 
import java.lang.Math;

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
   
   public static double sumOfFileContents(int num1, int num2, String pathToFiles) {
   
   } //sumOfFilecontents function
}//Write to File class