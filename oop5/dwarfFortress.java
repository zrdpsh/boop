import java.util.*;
import java.io.*;
import java.lang.Math;

import Weaponry.*;
import Creatures.*;


public class dwarfFortress{
   public static void main(String[] args){
/*------------4.2 500 objects------------------*/

      System.out.println();
      System.out.println("/*----------------500 objects------------------*/");
		
		Creature[] creatures = new Creature[500];
		for (int i = 0; i < 500; i++) {
			if(Math.random() > 0.49) {
				creatures[i] = new Alpaca((int)(Math.random()*10),(int)(Math.random()*10),(int)(Math.random()*10));
			} else {
				creatures[i] = new Dwarf("another dwarf's name",(int)(Math.random()*100), (int)(Math.random()*10));
			}
		}
		
		for (Creature creature: creatures) {
			creature.makeSound();
		}
   } //main
} //class
