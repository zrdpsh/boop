package Creatures;

public class Alpaca extends Creature {
   private static String description = "A large domestic animal with a long neck.  It has been bred for its valuable hair";
   private boolean isSheared = false;
   private int amountOfWool;
   
   public Alpaca(int health, int damage, int wool) {
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

