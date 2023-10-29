package first;

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
   
   public void increaseWool() {
      this.disposableWool += 1;
   } //inreaseWool function
   
   public int returnWool() {
      int returnedWool = this.disposableWool;
      this.disposableWool = 0;
      return returnedWool;
   } //returnWool function
   
   public void shearAlpaca(Alpaca alpaca) {
      alpaca.beingSheared();
      increaseWool();
   } //function shear
   
   public void gotDamage(int damage) {
      this.health -= damage;
   } //gotDamage function
   
   public void attack(Hammer hammer, Dwarf enemy) {
   hammer.useHammer();
   enemy.gotDamage(hammer.damage);
   if (enemy.health <= 0) {
      enemy.isDead = true;
      this.disposableWool += enemy.returnWool();
   } //if enemy health
} //function hammer

} // class dwarf 

class Alpaca extends Creature {
   String description = "A large domestic animal with a long neck.  It has been bred for its valuable hair";
   boolean isSheared = false;
   
   public void attack(Creature enemy) {
      enemy.health -= 1;
   } //function attack
   
   public void beingSheared() {
      this.isSheared = true;
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
      Dwarf ouzie = new Dwarf("ouzie", 25);
      Dwarf uozie = new Dwarf("uozie", 39);
      Alpaca ouzieAlpaca = new Alpaca();
      Alpaca uozieAlpaca = new Alpaca();
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
      
      
      System.out.println("until one of them is dead, and other get " + uozie.disposableWool + " units of wool");
      
      
      
   } //main
} //class