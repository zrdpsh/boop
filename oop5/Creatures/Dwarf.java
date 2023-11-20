package Creatures;
import Weaponry.*;

public class Dwarf extends Creature{
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
