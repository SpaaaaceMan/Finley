package items.weapons.meles;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JMenuItem;

import characters.Actor;
import items.potions.Potion;
import items.weapons.Weapon;

public class MeleWeapon extends Weapon {
	
	public MeleWeapon(String name, int damage, double weight, double value, int durability) {
		super(name, damage, weight, value, durability);
	}
	
	public MeleWeapon (MeleWeapon weapon) {
		super(weapon.getName(), weapon.getDamage(), weapon.getWeight(), weapon.getValue(), weapon.getDurability());
	}

	@Override
	public void attack(Actor targetedCharacter) {
		if (this.getDurability() <= 0) {
			System.out.println("Your " + this.getName() + " is broken. Too bad.");
		}
		else {
			System.out.println(targetedCharacter.getName() + 
					" has been attacked by a "+ this.getName() + " for " + this.getDamage() + " damage.");
			targetedCharacter.looseLife(this.getDamage());
			this.setDurability(this.getDurability() - 1);
			if (this.getDurability() <= 0) {
				System.out.println("Your " + this.getName() + " has broken. Toooo baaaaaaaad.");
			}
		}
	}

	@Override
	public ArrayList<JMenuItem> getListMenuItems() {
		this.listMenuItems = new ArrayList<JMenuItem>();
		
		if (this.getOwner() == null) {
			JMenuItem menuPickup = new JMenuItem("Ramasser");
			this.listMenuItems.add(menuPickup);
			menuPickup.addActionListener(new ActionListener() {
				 
	            public void actionPerformed(ActionEvent e)
	            {
	            	//use(Actor character);
	            }
	        });
		}
		else {			
			JMenuItem menuDrop = new JMenuItem("L�cher");
			this.listMenuItems.add(menuDrop);
			final MeleWeapon mThis = this;
			menuDrop.addActionListener(new ActionListener() {
				 
	            public void actionPerformed(ActionEvent e)
	            {
	            	getOwner().dropItem(mThis);
	            }
	        }); 
		}
		return this.listMenuItems;
	}
}