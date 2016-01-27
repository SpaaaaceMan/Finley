package tests;

import abilities.spells.damage.FireBall;
import characters.Actor;
import characters.Clown;
import characters.Warrior;
import characters.Monster;
import fights.SimpleFight;
import ihm.BeginWindow;
import ihm.TestWindow;
import items.potions.Potion;
import items.potions.SmallPotion;
import items.weapons.meles.ListMeleWeapons;
import items.weapons.ranged.ListRangedWeapons;
import items.weapons.ranged.RangedWeapon;
import items.weapons.ranged.munitions.ListMunitions;

public class TestPersonnages {

	public static void main(String[] args) throws Throwable {
		BeginWindow fenetre = new BeginWindow();
		
		Actor hero = new Warrior("Bob", 10, 5, 100);
		Actor clown = new Clown(10, 2, 3, 20);
		
		hero.pickUpItem(ListMeleWeapons.getMeleWeapons(0));
		RangedWeapon bow = ListRangedWeapons.getRangedWeapons(0);
		
		for (int i = 0; i < 20; ++i) {
			bow.addMunition(ListMunitions.getMunitions(0));
		}
		hero.pickUpItem(bow);
		
		hero.getInventory().get(1).use(hero);
		
		SimpleFight.fight(hero, clown);
	}
}
