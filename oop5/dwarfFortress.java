import java.util.*;
import java.io.*;
import java.lang.Math;

import Weaponry.*;
import Creatures.*;


public class dwarfFortress{
   public static void main(String[] args){

	   String RORIEHAS = " points of health, Rorie has ";
	   String POINTSOFHEALTH = " points of health***";

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
      
/*----------- 4.3 ad hoc polymorphism--------------*/

     System.out.println();
     System.out.println("/*----------------ad hoc------------------*/");
      
	  Dwarf randolf = new Dwarf("Randolf", 100, 8);
	  DwarfWarrior rorie = new DwarfWarrior("Rorie", 100, 8);
	  
	  System.out.println("Two dwarfs mess with each other, but one of them now is way more stronger");
	  System.out.println("***Randolf has " + randolf.getHealth() +  + rorie.getHealth() + " points of health***");
	  System.out.println("They start as usual..");
	  randolf.attack(rorie, 1, 1);
	  rorie.attack(randolf, 1, 1);
	  System.out.println("***after the attack Randolf has " + randolf.getHealth() + RORIEHAS + rorie.getHealth() + POINTSOFHEALTH);
	  System.out.println("...but then one of them use his special force");
	  randolf.attack(rorie, 1, 1);
	  rorie.attack(randolf, 1, 1, 10); 
	  System.out.println("***after the attack Randolf has " + randolf.getHealth() + RORIEHAS + rorie.getHealth() + POINTSOFHEALTH);
   } //main
} //class
