package items.weapons.ranged.munitions;

import java.util.ArrayList;

public class ListMunitions {
	private static ArrayList<Munition> munitions = new ArrayList<Munition>();
	
	static {
		munitions.add(new Munition("Flèche dans le genou", 2, 0.0, 5.0));
	}

	public static Munition getMunitions(int index) {
		if (munitions.size() < index || index < 0) {
			return null;
		}
		Munition munitionSelected = munitions.get(index);
		return new Munition(munitionSelected.getName(), munitionSelected.getDamage(),
				munitionSelected.getWeight(), munitionSelected.getValue());
	}
}
