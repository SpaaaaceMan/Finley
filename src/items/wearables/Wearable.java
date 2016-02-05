package items.wearables;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import characters.Actor;
import items.Item;

public abstract class Wearable extends Item {

	private int armorPoint;
	
	public Wearable(String name, double weight, int placeOccupiedInventory, double value, int armorPoint, ImageIcon icon) {
		super(name, weight, placeOccupiedInventory, value, true, icon);
		this.armorPoint = armorPoint;
	}
	

	@Override
	public void use(Actor characterTarget) {
		characterTarget.addWearable(this);
	}

	public int getArmorPoint() {
		return armorPoint;
	}
	
	@Override
	public Color getItemColor() {
		return Color.YELLOW;
	}
}
