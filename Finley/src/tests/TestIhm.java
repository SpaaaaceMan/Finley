package tests;

import characters.Actor;
import characters.Warrior;
import ihm.InventoryWindow;
import items.potions.LargePotion;
import items.potions.SmallPotion;
import items.weapons.meles.ListMeleWeapons;
import items.weapons.meles.MeleWeapon;

public class TestIhm {

	public static void main(String[] args) {
		Actor hero = new Warrior("Bob", 10, 5, 100);
		MeleWeapon spoon = new MeleWeapon(ListMeleWeapons.getMeleWeapons(0));
		hero.pickUpItem(spoon);
		hero.pickUpItem(new SmallPotion(1, 1));
		hero.pickUpItem(new LargePotion(10, 1));
		InventoryWindow fenetre = new InventoryWindow(hero);
	}

}
