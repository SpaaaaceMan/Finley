package tests;

import abilities.spells.damage.FireBall;
import characters.Actor;
import characters.ActorFactory;
import fights.SimpleFight;
import ihm.BeginWindow;
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
		
		Actor hero = new Actor("Bob", 10, 5, 5, 100);
		Actor ghost = ActorFactory.clown();
		
		MeleWeapon spoon = new MeleWeapon(ListMeleWeapons.getMeleWeapons(0));
		hero.pickUpItem(spoon);
		
		RangedWeapon bow = new RangedWeapon(ListRangedWeapons.getRangedWeapons(0));
		Munition arrow = ListMunitions.getMunitions(0, 10);
		bow.setMunition(arrow);
		
		hero.pickUpItem(bow);
		hero.pickUpItem(arrow);
		bow.use(hero);
		hero.pickUpItem(new SmallPotion(1, 1));
		
		while (!hero.isDead() && !ghost.isDead()) {
			SimpleFight.fight(hero, ghost);
		}
	}
}
