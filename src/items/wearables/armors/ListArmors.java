package items.wearables.armors;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class ListArmors {
	private static ArrayList<Armor> armors = new ArrayList<Armor>();
	
	static {
		armors.add(new Armor("Nudisme", 5, 0, 0, 10, 
				new ImageIcon(new ImageIcon("icons/pegi18.jpg").getImage().getScaledInstance(34, 34, Image.SCALE_DEFAULT))));
	}

	public static Armor getArmors(int index) {
		if (armors.size() < index || index < 0) {
			return null;
		}
		return new Armor(armors.get(index));
	}
}
