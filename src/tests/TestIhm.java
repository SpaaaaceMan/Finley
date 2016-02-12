package tests;

import characters.Actor;
import characters.ActorFactory;
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
import items.wearables.armors.Armor;
import items.wearables.armors.ListArmors;

public class TestIhm {

	public static void main(String[] args) {
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
		hero.earnMoney(500);
		
		hero.pickUpItem(spoon1);
		hero.pickUpItem(new SmallPotion(1, 1));
		hero.pickUpItem(nudisme);
		Potion grosse = new LargePotion(2, 1);
		hero.pickUpItem(grosse);
		
		//Bob a trouvé plein de cuillères!
		hero.pickUpItem(new MeleWeapon(ListMeleWeapons.getMeleWeapons(0)));
		hero.pickUpItem(new MeleWeapon(ListMeleWeapons.getMeleWeapons(0)));
		hero.pickUpItem(new MeleWeapon(ListMeleWeapons.getMeleWeapons(0)));
		hero.pickUpItem(new MeleWeapon(ListMeleWeapons.getMeleWeapons(1)));
		hero.pickUpItem(new MeleWeapon(ListMeleWeapons.getMeleWeapons(2)));
		
		@SuppressWarnings("unused")
		Actor hero1 = ActorFactory.hunter();
		@SuppressWarnings("unused")
		Actor hero2 = ActorFactory.hunter();
		
		InventoryWindow fenetre = new InventoryWindow(hero);		

		//InventoryWindow fenetre1 = new InventoryWindow(hero1);
		//InventoryWindow fenetre2 = new InventoryWindow(hero2);
		
		//GroundInventory ground = GroundInventory.getInstance(hero);
		/*hero.addObserver(ground);
		hero1.addObserver(ground);
		hero2.addObserver(ground);*/
	}

}
