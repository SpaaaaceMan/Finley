package items.potions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JMenuItem;

import characters.Actor;
import characters.Monster;
import characters.Warrior;
import ihm.TestWindow;
import items.Item;

public abstract class Potion extends Item {
	private int healingPoints;

	public Potion(String name, double weight, double value, int healingPoints) {
		super(name, weight, value, false);
		this.setHealingPoints(healingPoints);
		
		if (this.getOwner() == null) {
			JMenuItem menuPickup = new JMenuItem("Ramasser");
			this.getListMenuItems().add(menuPickup);
			menuPickup.addActionListener(new ActionListener() {
				 
	            public void actionPerformed(ActionEvent e)
	            {
	            	//use(Actor character);
	            }
	        });
		}
		else {
			JMenuItem menuDrink = new JMenuItem("Boire");
			this.getListMenuItems().add(menuDrink);
			menuDrink.addActionListener(new ActionListener() {
				 
	            public void actionPerformed(ActionEvent e)
	            {
	            	use(getOwner());
	            }
	        }); 
			
			JMenuItem menuDrop = new JMenuItem("Lâcher");
			this.getListMenuItems().add(menuDrop);
			final Potion pThis = this;
			menuDrop.addActionListener(new ActionListener() {
				 
	            public void actionPerformed(ActionEvent e)
	            {
	            	getOwner().dropItem(pThis);
	            }
	        }); 
		}	
	}

	public void use(Actor characterToHeal){
		int lifeBeforeHeal = characterToHeal.getLife();
		characterToHeal.earnLife(healingPoints);
		System.out.println(characterToHeal.getName() + " boit une potion et regagne " + 
		(characterToHeal.getLife() - lifeBeforeHeal) + " points de vie" );
	}

	public int getHealingPoints() {
		return healingPoints;
	}

	public void setHealingPoints(int healingPoints) {
		this.healingPoints = healingPoints;
	}
}
