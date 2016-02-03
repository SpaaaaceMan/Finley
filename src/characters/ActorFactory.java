package characters;

import items.weapons.meles.ListMeleWeapons;
import items.weapons.meles.MeleWeapon;
import items.weapons.ranged.ListRangedWeapons;
import items.weapons.ranged.RangedWeapon;
import items.weapons.ranged.munitions.ListMunitions;
import items.weapons.ranged.munitions.Munition;

public class ActorFactory {

	public static Actor clown() {
		return new Actor("Clown", 5, 2, 8, 20);
	}
	
	public static Actor ghost() {
		return new Actor("Fant�me", 40, 9, 6, 10);
	}
	
	public static Actor grandMother() {
		return new Actor("Grand-m�re", 10, 2, 0, 5);
	}
	
	public static Actor hunter() {
		Actor hunter = new Actor("Chasseur", 30, 10, 0, 50);
		RangedWeapon bow = ListRangedWeapons.getRangedWeapons(0);
		Munition arrow = ListMunitions.getMunitions(0, 10);
		bow.setMunition(arrow);
		hunter.pickUpItem(arrow);
		hunter.pickUpItem(bow);
		bow.use(hunter);
		return hunter;
	}
	
	public static Actor mage() {
		return new Actor("Mage", 30, 8, 50, 50);
	}
	
	public static Actor monster() {
		return new Actor("Monstre", 15, 10, 0, 15);
	}
	
	public static Actor robot() {
		return new Actor("Robot", 50, 20, 0, 150);
	}
	
	public static Actor warrior() {
		return new Actor("Guerrier", 25, 15, 0, 100);
	}
	
	public static Actor jedi() {
		Actor jedi = new Actor("jedi", 30, 10, 20, 45);
		MeleWeapon lightSaber = ListMeleWeapons.getMeleWeapons(2);
		return jedi;
	}
}
