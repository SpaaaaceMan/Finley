package items.wearables.armors;

import java.util.ArrayList;

public class ListArmors {
	private static ArrayList<Armor> armors = new ArrayList<Armor>();
	
	static {
		armors.add(new Armor("Nudisme", 5, 5, 10));
	}

	public static Armor getArmors(int index) {
		if (armors.size() < index || index < 0) {
			return null;
		}
		return new Armor(armors.get(index));
	}
}
