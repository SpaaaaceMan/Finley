package tests;

import characters.Actor;
import characters.Warrior;
import ihm.InventoryWindow;

public class TestIhm {

	public static void main(String[] args) {
		Actor hero = new Warrior("Bob", 10, 5, 100);
		InventoryWindow fenetre = new InventoryWindow(hero);
	}

}
