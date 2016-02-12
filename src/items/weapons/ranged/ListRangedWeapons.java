package items.weapons.ranged;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class ListRangedWeapons {
	
	private static ArrayList<RangedWeapon> rangedWeapons = new ArrayList<RangedWeapon>();
	
	static {
		rangedWeapons.add(new RangedWeapon("Arc", 5, 5, 6, 60, 50, new ImageIcon("icons/W_Bow01.png", "a simple bow")));
	}

	public static RangedWeapon getRangedWeapons(int index) {
		if (rangedWeapons.size() < index || index < 0) {
			return null;
		}
		return new RangedWeapon(rangedWeapons.get(index));
	}
}
