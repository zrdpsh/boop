import java.util.*;
import java.io.*;

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
   
} //class Creature

/*--------------------------------Units-----------------------------------*/  

class Dwarf extends Creature{
   private static String description = "A short, sturdy creature fond of drink and industry";
   private String name;
//    String profession = "Craftsman";
   private int profession;
   private int disposableWool = 0;
   
   public Dwarf(String name, int health, int damage, int profession, int wool) {
      super(health, damage);
      this.name = name;
      this.profession = profession;
      this.disposableWool = wool;
   } //class constructor
   
/*------------------methods-------------------*/    
   public String presentYourself() {
      return this.name;
   } //presentYourself
   
   private void useWeapon(Weapon weapon, Creature enemy) {
      enemy.gotDamage(weapon.howMuchDamage());
      weapon.lowerWeaponResource();
   }
   
   public void attack(Weapon weapon, int distance, Creature enemy) {
      if (distance <= 1 && weapon.getKindOfWeapon() == 1) {
         useWeapon(weapon, enemy);
      } else if (weapon.getKindOfWeapon() == 2) {
         useWeapon(weapon, enemy);
      } else return;

//       if ((distance <= 1 && weapon.getKindOfWeapon() == 1) || weapon.getKindOfWeapon() == 2) {
//          useWeapon(weapon, enemy);
//     }
   } //if enemy health
   
   public void setWool(int wool) {
      this.disposableWool += wool;
   } //inreaseWool function
   
   public int getDisposableWool() {
      return this.disposableWool;
   } //get Disposable wool
   
   public int returnWool() {
      int returnedWool = this.disposableWool;
      this.disposableWool = 0;
      return returnedWool;
   } //returnWool function
   
   public void shearAlpaca(Alpaca alpaca) {
      if (!alpaca.isSheared()) {
         setWool(alpaca.getAmountOfWool());
         alpaca.beingSheared();
      };

   } //function shear

} // class dwarf 



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
      Dwarf ouzie = new Dwarf("ouzie", 100, 3, 1, 0);
      Dwarf uozie = new Dwarf("uozie", 141, 3, 1, 0);
      Alpaca ouzieAlpaca = new Alpaca(6,1,7);
      Alpaca uozieAlpaca = new Alpaca(6,1,11);
      Hammer ouzieHammer = new Hammer(5, 120);
      Hammer uozieHammer = new Hammer(5, 120);
      
      System.out.println("There were two dwarfs, " + ouzie.presentYourself() + " and " + uozie.presentYourself() + ", each had one alpaca");
      System.out.println("Then one of them went greedy");
      
      ouzie.shearAlpaca(ouzieAlpaca);
      ouzie.shearAlpaca(uozieAlpaca);
      
      System.out.println("..other one decided to revenge.");
      System.out.println("they fight");
      
      while (!ouzie.isDead) {
         uozie.attack(uozieHammer, 1, ouzie);
         ouzie.attack(ouzieHammer, 1, uozie);
         System.out.println("..and fight");
         System.out.println("(ouzie health: " + ouzie.getHealth() + " uozie health: " + uozie.getHealth() + ")");
      }
      
 //         uozie.attack(uozieHammer, ouzie);
      
      uozie.setWool(ouzie.returnWool());
      
      System.out.println("until one of them is dead, and other get " + uozie.getDisposableWool() + " units of wool");
      
      
      
   } //main
} //class