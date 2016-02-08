package items.weapons.meles;

import java.util.ArrayList;

public abstract class ListMeleWeapons {

	static ArrayList<MeleWeapon> meleWeapons = new ArrayList<>();
	
	static {
		meleWeapons.add(new MeleWeapon("Cuillère", 10, 0.5, 5, 100, 3, null));
		meleWeapons.add(new MeleWeapon("Couteau", 11, 0.8, 5, 50, 6, null));
		meleWeapons.add(new MeleWeapon("Sabre laser", 40, 5, 5, 1000, 15000, null));
	}

	public static MeleWeapon getMeleWeapons(int index) {
		return new MeleWeapon(meleWeapons.get(index));
	}
}
