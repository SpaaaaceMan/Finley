package items.weapons.ranged.munitions;

import java.util.ArrayList;

public class ListMunitions {
	private static ArrayList<Munition> munitions = new ArrayList<Munition>();
	
	static {
		munitions.add(new Munition("Arrow in the knee", 0.0, 5, 2));
	}

	public static ArrayList<Munition> getMunitions() {
		return munitions;
	}
}
