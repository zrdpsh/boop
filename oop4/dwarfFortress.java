import java.util.*;
import java.io.*;
import java.lang.Math;

class Thing {
   protected static String description = "Some element of the game";
} //class Thing


class Weapon extends Thing {
   protected static String description = "Some kind of weapon";
   protected static int costOfOneHit = 3;
   protected static int kindOfWeapon = 1;
   protected int damage;
   protected int resource;
   
   Weapon(int damage, int resource) {
      this.damage = damage;
      this.resource = resource;
   }// constructor Weapon
   
   public int howMuchDamage() {
      return this.damage;
   } //damage getter
   
   public int getKindOfWeapon() {
      return this.kindOfWeapon;
   }// getKindOfWeapon getter
   
   public void lowerWeaponResource() {
      this.resource -= costOfOneHit;
   }//lowerWeaponResource
   
} //class Weapon

class Creature extends Thing{
   protected static String description = "it's alive";
   protected int health;
   protected int damage;
   protected boolean isDead = false;
  
   public Creature(int health, int damage) {
      this.health = health;
      this.damage = damage;
   } //constructor Creature
   
/*-----------------"health" methods------------------*/   
   public void setHealth(int health) {
      this.health = health;
   } //setHealth setter
   
   public int getHealth() {
      return this.health;
   } //getHealth method
   
   protected void gotDamage(int damage) {
      if(!this.isDead) {
         setHealth(this.health - damage);
      } //if !this.isDead
      if(this.health <= 0) {
         this.isDead = true;
         this.health = 0;
      } //if this.health >= 0
   } //gotDamage method
     
   public void attack(Creature enemy, int distance) {
      if (distance <= 1) {
         enemy.gotDamage(this.damage);
      } //if distance <= 1
   } //attack method
   
   
   public void makeSound() {
		System.out.println("*some random creature sound*");
   }//makeSound method
   
} //class Creature

/*--------------------------------Units-----------------------------------*/  

class Dwarf extends Creature{
   private static String description = "A short, sturdy creature fond of drink and industry";
   private static Weapon handWeapon = new Hammer(5, 105);
   private static Weapon distanceWeapon = new Longbow(5, 105, 5);
   private String name;
   private Alpaca[] alpacasHerd = new Alpaca[10]; //drawf can own up to 10 animals
   private int alpacasCounter = 0;

   
   public Dwarf(String name, int health, int damage) {
      super(health, damage);
      this.name = name;
   } //class constructor
   
/*------------------methods-------------------*/    
   public String presentYourself() {
      return this.name;
   } //presentYourself
   
   protected void useWeapon(Weapon weapon, Creature enemy) {
      enemy.gotDamage(weapon.howMuchDamage());
      weapon.lowerWeaponResource();
   }
   
   protected void useWeapon(Weapon weapon, Creature enemy, int coefficient) {
      enemy.gotDamage(weapon.howMuchDamage()*coefficient);
      weapon.lowerWeaponResource();
   }
   
/*------attack methods-------*/   
   public void attack(Creature enemy, int distance) { //attack with bare hands
	   if (distance >= 1) {
		   enemy.gotDamage(this.damage);
		} else {
			return;
		}			
   }

   public void attack(Creature enemy, int distance, int useWeapon) { //attack with some weapon
      if (distance <= 1 && useWeapon == 1) {
         useWeapon(this.handWeapon, enemy);
      } else if (useWeapon == 2) {
         useWeapon(this.distanceWeapon, enemy);
      } else return;

//       if ((distance <= 1 && weapon.getKindOfWeapon() == 1) || weapon.getKindOfWeapon() == 2) {
//          useWeapon(weapon, enemy);
//     }
   } //if enemy health
   
   public void tameTheAlpaca(Alpaca newAlpaca) {
	   if (alpacasCounter < alpacasHerd.length) {
			alpacasHerd[alpacasCounter] = newAlpaca;
			alpacasCounter += 1;
	   }
   }
   
   public int howMuchAlpacasHas() {
		return this.alpacasCounter;
   }
   
   	public void makeSound() {
		System.out.println("aaaaargh");
	} //makeSound method

} // class dwarf 


class DwarfWarrior extends Dwarf {
   private static String description = "Dwarf with extra attack and health options";
   private static int healthIncrease = 2;
   private static Weapon handWeapon = new Hammer(5, 105);
   private static Weapon distanceWeapon = new Longbow(5, 105, 5);
   private String name;
   
   public DwarfWarrior(String name, int health, int damage) {
      super(name, health*healthIncrease, damage*2);
   } //class constructor
   
   public void attack(Creature enemy, int distance, int useWeapon, int boostAttack) { //attack with special force
      if (distance <= 1 && useWeapon == 1) {
		 useWeapon(this.handWeapon, enemy, boostAttack);
      } else if (useWeapon == 2) {
         useWeapon(this.distanceWeapon, enemy, boostAttack);
      } else return;
	} //attack method
	
	public void makeSound() {
		System.out.println("very dangerous aaaaargh");
	} //makeSound method
	

} //class DwarfWarrior



class Alpaca extends Creature {
   private static String description = "A large domestic animal with a long neck.  It has been bred for its valuable hair";
   private boolean isSheared = false;
   private int amountOfWool;
   
   Alpaca(int health, int damage, int wool) {
      super(health, damage);
      this.amountOfWool = wool;
   } //Alpaca constructor
   
/*------------------methods-------------------*/  

   public boolean isSheared() {
      return this.isSheared;
   }//getter isSheared
   
   public int getAmountOfWool() {
      return this.amountOfWool;
   } //get Quantity of Wool
   
   public void beingSheared() {
      this.isSheared = true;
      this.amountOfWool = 0;
   } //function beingSheared
   
   	public void makeSound() {
		System.out.println("*some illegible alpaca sound*");
	} //makeSound method
   
} //class Alpaca



/*--------------------------------Weaponry-----------------------------------*/  

class Hammer extends Weapon {
   private static String description = "Ancient two-sided gavel with elf runes on it";
   private static int kindOfWeapon = 1;
   private static int costOfOneHit = 5;
   
   Hammer(int damage, int resource) {
      super(damage, resource);
      
   } //constructor

} //class Hammer

class Longbow extends Weapon{
   private static String description = "Archers like this instrument. Very effective against knights";
   private static int kindOfWeapon = 2;
   private static int costOfOneHit = 3;
   private int range;
   
   Longbow (int damage, int resource, int range) {
      super(damage, resource);
      this.range = range;
   } //constructor Longbow
} //class Longbow



/*--------------------------------Program itself-----------------------------------*/  

public class dwarfFortress{
   public static void main(String[] args) throws FileNotFoundException {
/*--------- 4.1 composition example -----------*/
	   
      System.out.println("/*----------------Composition example------------------*/");
      
	   System.out.print("We have 1 dwarf ");    
      Dwarf ouzie = new Dwarf("ouzie", 100, 3);
      System.out.println(", whose name is " + ouzie.presentYourself() + " and who has " + ouzie.howMuchAlpacasHas() + " alpacas");


	  Alpaca betsie = new Alpaca(7, 3, 6);
	  Alpaca daisy = new Alpaca(8, 19, 1);
     System.out.println("and two completely independent alpacas.");
	  
	  //благодаря композции, альпаки становятся частью объекта Дворф
     System.out.println("Then the dwarf tame them");
	  ouzie.tameTheAlpaca(betsie);
	  ouzie.tameTheAlpaca(daisy);
	  System.out.println(".. and now ouzie has " + ouzie.howMuchAlpacasHas() + " alpacas");
	  

	  
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
	  System.out.println("***Randolf has " + randolf.getHealth() + " points of health, Rorie has " + rorie.getHealth() + " points of health***");
	  System.out.println("They start as usual..");
	  randolf.attack(rorie, 1, 1);
	  rorie.attack(randolf, 1, 1);
	  System.out.println("***after the attack Randolf has " + randolf.getHealth() + " points of health, Rorie has " + rorie.getHealth() + " points of health***");
	  System.out.println("...but then one of them use his special force");
	  randolf.attack(rorie, 1, 1);
	  rorie.attack(randolf, 1, 1, 10); 
	  System.out.println("***after the attack Randolf has " + randolf.getHealth() + " points of health, Rorie has " + rorie.getHealth() + " points of health***");
	  
	  
      
      
   } //main
} //class