package Creatures;

import java.util.*;
import java.io.*;
import java.lang.Math;
import Weaponry.*;

public class Creature{
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
