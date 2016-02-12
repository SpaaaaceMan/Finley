package items.weapons.meles;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public abstract class ListMeleWeapons {

	static ArrayList<MeleWeapon> meleWeapons = new ArrayList<>();
	
	static {
		meleWeapons.add(new MeleWeapon("Cuillère", 10, 0.5, 5, 100, 3, 
				new ImageIcon((new ImageIcon("icons/spoon.png")).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT))));
		meleWeapons.add(new MeleWeapon("Couteau", 11, 0.8, 5, 50, 6,
				new ImageIcon("icons/W_Dagger006.png")));
		meleWeapons.add(new MeleWeapon("Sabre laser", 40, 5, 5, 1000, 15000, 
				new ImageIcon((new ImageIcon("icons/lightsaber.png")).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT))));
	}

	public static MeleWeapon getMeleWeapons(int index) {
		return new MeleWeapon(meleWeapons.get(index));
	}
}
