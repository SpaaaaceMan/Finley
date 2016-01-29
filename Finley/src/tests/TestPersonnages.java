package tests;

import abilities.spells.damage.FireBall;
import characters.Actor;
import characters.Clown;
import characters.Warrior;
import characters.Monster;
import fights.SimpleFight;
import ihm.BeginWindow;
//import ihm.InventoryWindow;
import ihm.TestWindow;
import items.potions.Potion;
import items.potions.SmallPotion;
import items.weapons.meles.ListMeleWeapons;
import items.weapons.meles.MeleWeapon;
import items.weapons.ranged.ListRangedWeapons;
import items.weapons.ranged.RangedWeapon;
import items.weapons.ranged.munitions.ListMunitions;
import items.weapons.ranged.munitions.Munition;

public class TestPersonnages {

	public static void main(String[] args) throws Throwable {
		//BeginWindow fenetre = new BeginWindow();
		
		Actor hero = new Warrior("Bob", 10, 5, 100);
		Actor clown = new Clown(10, 2, 3, 20);
		
		MeleWeapon spoon = new MeleWeapon(ListMeleWeapons.getMeleWeapons(0));
		hero.pickUpItem(spoon);
		
		RangedWeapon bow = new RangedWeapon(ListRangedWeapons.getRangedWeapons(0));
		
		for (int i = 0; i < 20; ++i) {
			Munition mun = new Munition(ListMunitions.getMunitions(0));
			bow.addMunition(mun);
		}
		hero.pickUpItem(bow);
		hero.pickUpItem(new SmallPotion(1, 1));
		//Julien, les fichiers ne sont toujours pas sur le git regarde gitHub, ils n'y sont pas. Au pir ajoute les pas terminal.
		//InventoryWindow fenetreInventaire = new InventoryWindow(hero);
	}
}
