package items.weapons.ranged.munitions;

import java.awt.Color;
import javax.swing.ImageIcon;
import characters.Actor;
import items.Item;

public class Munition extends Item{
		
	private int damage;
	
	private int number = 0;
	
	public Munition(String name, int damage, double weight, int placeOccupiedInventory, double value, ImageIcon icon) {
		super(name, weight, placeOccupiedInventory, value, true, icon);
		this.damage = damage;
	}
	
	public Munition (Munition munition) {
		super(munition.getName(), munition.getWeight(), munition.getPlaceOccupiedInventory(), munition.getValue(), true, munition.getIcon());
		this.damage   = munition.getDamage();
		this.number   = munition.getNumber();
	}
	
	public void addMunition (int number) {
		this.number += number;
	}
	
	@Override
	public Color getItemColor() {
		return Color.ORANGE;
	}

	@Override
	public void use(Actor characterTarget) {
		this.number -= 1;
	}

	public int getDamage() {
		return damage;
	}
	
	public int getNumber() {
		return number;
	}
}
