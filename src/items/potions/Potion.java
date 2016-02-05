package items.potions;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import characters.Actor;
import items.Item;

public abstract class Potion extends Item {
	private int healingPoints;

	public Potion(String name, double weight,int placeOccupiedInventory, double value, int healingPoints, ImageIcon icon) {
		super(name, weight, placeOccupiedInventory, value, false, icon);
		this.setHealingPoints(healingPoints);	
		
	}

	public void use(Actor characterToHeal){
		int lifeBeforeHeal = characterToHeal.getLife();
		characterToHeal.earnLife(healingPoints);
		System.out.println(characterToHeal.getName() + " boit une potion et regagne " + 
		(characterToHeal.getLife() - lifeBeforeHeal) + " points de vie" );
		this.getOwner().dropItem(this);
	}
	
	@Override
	public ArrayList<JButton> getListButtonsItem() {
		
		return this.getListMenuItems();
	}

	public int getHealingPoints() {
		return healingPoints;
	}

	public void setHealingPoints(int healingPoints) {
		this.healingPoints = healingPoints;
	}
	
	@Override
	public Color getItemColor() {
		return Color.RED;
	}
}
