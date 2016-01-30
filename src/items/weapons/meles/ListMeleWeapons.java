package items.weapons.meles;

import java.util.ArrayList;

public abstract class ListMeleWeapons {

	static ArrayList<MeleWeapon> meleWeapons = new ArrayList<>();
	
	static {
		meleWeapons.add(new MeleWeapon("Cuillère", 10, 0.5, 100, 3));
		meleWeapons.add(new MeleWeapon("Couteau", 11, 0.8, 50, 6));
	}

	public static MeleWeapon getMeleWeapons(int index) {
		return new MeleWeapon(meleWeapons.get(index));
	}
}
