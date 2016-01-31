package items.wearables.armors;

import java.awt.Color;

import items.wearables.Wearable;

public class Armor extends Wearable {

	public Armor(String name, double weight, double value, int armorPoint) {
		super(name, weight, value, armorPoint);
	}

	public Armor (Armor armor) {
		super(armor.getName(), armor.getWeight(), armor.getValue(), armor.getArmorPoint());
	}
}
