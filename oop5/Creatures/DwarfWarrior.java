package Creatures;
import Weaponry.*;

public class DwarfWarrior extends Dwarf {
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