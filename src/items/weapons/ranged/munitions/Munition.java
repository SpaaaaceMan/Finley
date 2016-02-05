package items.weapons.ranged.munitions;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import characters.Actor;
import items.Item;

public class Munition extends Item{
	
	private String realName;
	
	private int damage;
	
	private int number = 0;
	
	public Munition(String name, int damage, double weight, int placeOccupiedInventory, double value, ImageIcon icon) {
		super(name, weight, placeOccupiedInventory, value, true, icon);
		this.realName = name;
		this.setName(realName + "[" + this.number + "]");
		this.damage = damage;
	}
	
	public Munition (Munition munition) {
		super(munition.getRealName(), munition.getWeight(), munition.getPlaceOccupiedInventory(), munition.getValue(), true, munition.getIcon());
		this.realName = munition.getRealName();
		this.damage   = munition.getDamage();
		this.number   = munition.getNumber();
		this.setName(realName + "[" + this.number + "]");
	}
	
	public void addMunition (int number) {
		this.number += number;
		this.setName(realName + "[" + this.number + "]");
	}
	
	public String getRealName() {
		return realName;
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

	@Override
	public ArrayList<JButton> getListButtonsItem() {		
		
		return this.getListMenuItems();
	}
}
