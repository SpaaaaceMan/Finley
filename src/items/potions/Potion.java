package items.potions;

import items.Item;

import java.awt.Color;

import javax.swing.ImageIcon;

import characters.Actor;

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
