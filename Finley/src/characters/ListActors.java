package characters;

import java.util.ArrayList;

import items.weapons.ranged.ListRangedWeapons;
import items.weapons.ranged.RangedWeapon;
import items.weapons.ranged.munitions.ListMunitions;
import items.weapons.ranged.munitions.Munition;

public abstract class ListActors {

	static ArrayList<Actor> actors = new ArrayList<>();
	
	static {
		actors.add(new Actor("Clown", 5, 2, 8, 20));
		
		actors.add(new Actor("Fantôme", 40, 9, 6, 10));
		
		actors.add(new Actor("Grand-mère", 10, 2, 0, 5));
		
		Actor hunter = new Actor("Chasseur", 30, 10, 0, 50);
		RangedWeapon bow = new RangedWeapon(ListRangedWeapons.getRangedWeapons(0));
		for (int i = 0; i < 20; ++i) {
			Munition mun = new Munition(ListMunitions.getMunitions(0));
			bow.addMunition(mun);
		}
		hunter.pickUpItem(bow);
		//bow.use(hunter);
		actors.add(hunter);
		
		actors.add(new Actor("Mage", 30, 8, 50, 50));
		
		actors.add(new Actor("Monstre", 15, 10, 0, 15));
		
		actors.add(new Actor("Robot", 50, 20, 0, 150));
		
		actors.add(new Actor("Guerrier", 25, 15, 0, 100));
	}

	public static Actor getActor(int index) {
		return new Actor(actors.get(index));
	}
}
