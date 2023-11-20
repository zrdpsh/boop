package Weaponry;

public class Longbow extends Weapon{
   private static String description = "Archers like this instrument. Very effective against knights";
   private static int kindOfWeapon = 2;
   private static int costOfOneHit = 3;
   private int range;
   
   public Longbow (int damage, int resource, int range) {
      super(damage, resource);
      this.range = range;
   } //constructor Longbow
} //class Longbow