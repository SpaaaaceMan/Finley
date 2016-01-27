package items.wearables;

import characters.Actor;
import items.Item;

public abstract class Wearable extends Item {

	private int armorPoint;
	
	public Wearable(String name, double weight, double value, int armorPoint) {
		super(name, weight, value, true);
		this.armorPoint = armorPoint;
	}

	@Override
	public void use(Actor characterTarget) {
		//ToDo : make an actor equiped with it
	}

	public int getArmorPoint() {
		return armorPoint;
	}
}
