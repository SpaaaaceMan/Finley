package tests;

import characters.Actor;
import characters.Warrior;
import ihm.InventoryWindow;
import items.potions.LargePotion;
import items.potions.Potion;
import items.potions.SmallPotion;
import items.weapons.meles.ListMeleWeapons;
import items.weapons.meles.MeleWeapon;
import items.weapons.ranged.ListRangedWeapons;
import items.weapons.ranged.RangedWeapon;
import items.weapons.ranged.munitions.ListMunitions;
import items.weapons.ranged.munitions.Munition;

public class TestIhm {

	public static void main(String[] args) {
		Actor hero = new Warrior("Bob", 10, 5, 100);
		MeleWeapon spoon = new MeleWeapon(ListMeleWeapons.getMeleWeapons(0));
		RangedWeapon bow = new RangedWeapon(ListRangedWeapons.getRangedWeapons(0));
		
		for (int i = 0; i < 20; ++i) {
			Munition mun = new Munition(ListMunitions.getMunitions(0));
			bow.addMunition(mun);
		}
		hero.pickUpItem(spoon);
		hero.pickUpItem(bow);
		hero.pickUpItem(new SmallPotion(1, 1));
		Potion grosse = new LargePotion(2, 1);
		hero.pickUpItem(grosse);
		InventoryWindow fenetre = new InventoryWindow(hero);
	}

}
