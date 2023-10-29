package first;

import java.util.*;
import java.io.*;
import first.Dwarf;

public class setByReference{
   public static void main(String[] args) throws FileNotFoundException {
      System.out.println("There were 10 alpacas");
      Dwarf firstDwarf = new Dwarf("firstDwarf", 27);
      Alpaca alpaca = new Alpaca();
      Alpaca secondAlpaca = alpaca;
      Alpaca thirdAlpaca = alpaca;
      
      firstDwarf.shearAlpaca(alpaca);
      firstDwarf.shearAlpaca(secondAlpaca);
      firstDwarf.shearAlpaca(thirdAlpaca);
      
      System.out.println("..but only " + firstDwarf.disposableWool + " units of wool");
   } //main
} //class