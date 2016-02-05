package tests;

import characters.Actor;
import ihm.MapWindow;
import items.potions.LargePotion;
import items.potions.Potion;
import items.potions.SmallPotion;
import items.weapons.meles.ListMeleWeapons;
import items.weapons.meles.MeleWeapon;
import items.weapons.ranged.ListRangedWeapons;
import items.weapons.ranged.RangedWeapon;
import items.weapons.ranged.munitions.ListMunitions;
import items.weapons.ranged.munitions.Munition;
import items.wearables.armors.Armor;
import items.wearables.armors.ListArmors;
import maps.Map;

public class TestMap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map testMap = new Map (16);
		
		System.out.println(testMap);
		
		//|CREATION HERO|
		Actor hero = new Actor("Bob", 10, 5, 5, 100);
		@SuppressWarnings("unused")
		MeleWeapon spoon = new MeleWeapon(ListMeleWeapons.getMeleWeapons(0));
		MeleWeapon spoon1 = new MeleWeapon(ListMeleWeapons.getMeleWeapons(0));
		Armor nudisme = new Armor(ListArmors.getArmors(0));
		
		RangedWeapon bow = new RangedWeapon(ListRangedWeapons.getRangedWeapons(0));
		Munition arrow = ListMunitions.getMunitions(0, 10);
		bow.setMunition(arrow);
		
		hero.pickUpItem(bow);
		bow.use(hero);
		hero.pickUpItem(arrow);
		
		
		hero.pickUpItem(spoon1);
		hero.pickUpItem(new SmallPotion(1, 1));
		hero.pickUpItem(nudisme);
		Potion grosse = new LargePotion(2, 1);
		hero.pickUpItem(grosse);
		//||
		
		@SuppressWarnings("unused")
		MapWindow mapWindow = new MapWindow (hero, testMap);
	}

}
