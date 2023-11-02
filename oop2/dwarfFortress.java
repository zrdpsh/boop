import java.util.*;
import java.io.*;

class Creature {
   int health = 20;
   String gender = "male";
   String description = "it's alive";
} //class Creature

class Dwarf extends Creature{
   String name;
   String description = "A short, sturdy creature fond of drink and industry";
   String profession = "Craftsman";
   
   boolean canShearAlpacas = true;
   boolean canAttackWithHammer = true;
   boolean isDead = false;

   int health = 25;
   int disposableWool = 0;
   
   public Dwarf(String name, int health) {
      this.name = name;
      this.health = health;
   } //class constructor
   
   public String presentYourself() {
      return this.name;
   } //presentYourself
   
   public void dead() {
      this.isDead = true;
   }// dead function
   
   public int showHealth() {
      return this.health;
   }//showHealth function
   
   public void increaseWool(int howMuchIncrease) {
      this.disposableWool += howMuchIncrease;
   } //inreaseWool function
   
   public int returnWool() {
      int returnedWool = this.disposableWool;
      this.disposableWool = 0;
      return returnedWool;
   } //returnWool function
   
   public void shearAlpaca(Alpaca alpaca) {
      if (!alpaca.isSheared) {
         increaseWool(alpaca.quantityOfWool);
         alpaca.beingSheared();

      };

   } //function shear
   
   public void gotDamage(int damage) {
      this.health -= damage;
   } //gotDamage function
   
   public void attack(Hammer hammer, Dwarf enemy) {
   hammer.useHammer();
   enemy.gotDamage(hammer.damage);
   if (enemy.health <= 5) {
      enemy.isDead = true;
      this.disposableWool += enemy.returnWool();
   } //if enemy health
} //function hammer

} // class dwarf 

class Alpaca extends Creature {
   String description = "A large domestic animal with a long neck.  It has been bred for its valuable hair";
   boolean isSheared = false;
   int quantityOfWool;
   
   Alpaca(int wool) {
      this.quantityOfWool = wool;
   } //Alpaca constructor
   
   
   public void attack(Creature enemy) {
      enemy.health -= 1;
   } //function attack
   
   public void beingSheared() {
      this.isSheared = true;
      this.quantityOfWool = 0;
   } //function beingSheared
} //class Alpaca


class Hammer {
   int resource  = 25;
   int damage = 5;
   
   public void useHammer() {
      this.resource -= 5;
   } //useHammer function
   
   public int getDamageValue() {
      return this.damage;
   } //getDamageValue
} //class Hammer


public class dwarfFortress{
   public static void main(String[] args) throws FileNotFoundException {
      Dwarf ouzie = new Dwarf("ouzie", 100);
      Dwarf uozie = new Dwarf("uozie", 141);
      Alpaca ouzieAlpaca = new Alpaca(7);
      Alpaca uozieAlpaca = new Alpaca(11);
      Hammer ouzieHammer = new Hammer();
      Hammer uozieHammer = new Hammer();
      
      System.out.println("There were two dwarfs, " + ouzie.presentYourself() + " and " + uozie.presentYourself() + ", each had one alpaca");
      System.out.println("Then one of them went greedy");
      
      ouzie.shearAlpaca(ouzieAlpaca);
      ouzie.shearAlpaca(uozieAlpaca);
      
      System.out.println("other one decided to revenge");
      System.out.println("they fight");
      
      while (!ouzie.isDead) {
         uozie.attack(uozieHammer, ouzie);
         ouzie.attack(ouzieHammer, uozie);
         System.out.println("..and fight");
         System.out.println("(ouzie health: " + ouzie.showHealth() + " uozie health: " + uozie.showHealth() + ")");
      }
      
         uozie.attack(uozieHammer, ouzie);
      
      System.out.println("until one of them is dead, and other get " + uozie.disposableWool + " units of wool");
      
      
      
   } //main
} //class