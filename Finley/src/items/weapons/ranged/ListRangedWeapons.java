package items.weapons.ranged;

import java.util.ArrayList;

public class ListRangedWeapons {
	private static ArrayList<RangedWeapon> rangedWeapons = new ArrayList<RangedWeapon>();
	
	static {
		rangedWeapons.add(new RangedWeapon("Bow", 5, 5, 60, 50));
	}

	public static ArrayList<RangedWeapon> getMunitions() {
		return rangedWeapons;
	}
}
