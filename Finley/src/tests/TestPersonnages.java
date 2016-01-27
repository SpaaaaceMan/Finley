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

public class TestPersonnages {

	public static void main(String[] args) throws Throwable {
		BeginWindow fenetre = new BeginWindow();
		
		Actor hero = new Warrior("Bob", 10, 5, 100);
		Actor clown = new Clown(4, 2, 3, 20);
		
		hero.pickUpItem(ListMeleWeapons.getMeleWeapons().get(0));
		
		hero.getInventory().get(0).use(hero);
		
		SimpleFight.fight(hero, clown);
	}
}
