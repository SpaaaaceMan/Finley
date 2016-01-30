package items.weapons.meles;

import java.util.ArrayList;

public abstract class ListMeleWeapons {

	static ArrayList<MeleWeapon> meleWeapons = new ArrayList<>();
	
	static {
		meleWeapons.add(new MeleWeapon("Spoon", 10, 0.1, 100, 3));
		meleWeapons.add(new MeleWeapon("Knife", 11, 0.1, 50, 6));
	}

	public static MeleWeapon getMeleWeapons(int index) {
		if (meleWeapons.size() < index || index < 0) {
			return null;
		}
		return new MeleWeapon(meleWeapons.get(index));
	}
}