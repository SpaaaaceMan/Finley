package items.weapons.ranged.munitions;

import java.util.ArrayList;

public class ListMunitions {
	private static ArrayList<Munition> munitions = new ArrayList<Munition>();
	
	static {
		munitions.add(new Munition("Flèche dans le genou", 2, 0.1, 5.0));
	}

	public static Munition getMunitions(int index, int number) {
		Munition munition = new Munition(munitions.get(index));
		munition.addMunition(number);
		return munition;
	}
}
