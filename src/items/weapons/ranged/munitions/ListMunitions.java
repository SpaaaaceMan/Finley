package items.weapons.ranged.munitions;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class ListMunitions {
	private static ArrayList<Munition> munitions = new ArrayList<Munition>();
	
	static {
		munitions.add(new Munition("Flèches", 2, 0.1, 1, 5.0, new ImageIcon("icons/S_Bow04.png")));
	}

	public static Munition getMunitions(int index, int number) {
		Munition munition = new Munition(munitions.get(index));
		munition.addMunition(number);
		return munition;
	}
}
