package Weaponry;

import java.util.*;
import java.io.*;
import java.lang.Math;

public class Weapon{
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
