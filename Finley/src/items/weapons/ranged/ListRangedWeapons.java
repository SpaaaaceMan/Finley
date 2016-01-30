package items.weapons.ranged;

import java.util.ArrayList;

public class ListRangedWeapons {
	private static ArrayList<RangedWeapon> rangedWeapons = new ArrayList<RangedWeapon>();
	
	static {
		rangedWeapons.add(new RangedWeapon("Arc", 5, 5, 60, 50));
	}

	public static RangedWeapon getRangedWeapons(int index) {
		if (rangedWeapons.size() < index || index < 0) {
			return null;
		}
		return new RangedWeapon(rangedWeapons.get(index));
	}
}
