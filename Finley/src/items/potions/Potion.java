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
		JMenuItem menuBoire = new JMenuItem("Boire");
		this.getListMenuItems().add(menuBoire);
		menuBoire.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
            	use(getOwner());
            }
        });      
		this.getListMenuItems().add(new JMenuItem("Ramasser"));
		this.getListMenuItems().add(new JMenuItem("Lachez"));	
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
