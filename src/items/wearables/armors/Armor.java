package items.wearables.armors;

import javax.swing.ImageIcon;

import items.wearables.Wearable;

public class Armor extends Wearable {

	public Armor(String name, double weight, int placeOccupiedInventory, double value, int armorPoint, ImageIcon icon) {
		super(name, weight, placeOccupiedInventory, value, armorPoint, icon);
	}

	public Armor (Armor armor) {
		super(armor.getName(), armor.getWeight(), armor.getPlaceOccupiedInventory(), armor.getValue(), armor.getArmorPoint(), armor.getIcon());
	}
}
