package items.weapons.meles;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;

import characters.Actor;
import items.weapons.Weapon;

public class MeleWeapon extends Weapon {
	
	public MeleWeapon(String name, int damage, double weight, int placeOccupiedInventory, double value, int durability, ImageIcon icon) {
		super(name, damage, weight, placeOccupiedInventory, value, durability, icon);
	}
	
	public MeleWeapon (MeleWeapon weapon) {
		super(weapon.getName(), weapon.getDamage(), weapon.getWeight(), weapon.getPlaceOccupiedInventory(), weapon.getValue(), weapon.getDurability(), weapon.getIcon());
	}
	
	@Override
	public Color getItemColor() {
		return Color.GREEN;
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
				
		if (this.getOwner().getWeapon() != this) {
			JMenuItem menuPickup = new JMenuItem("Equiper");
			this.listMenuItems.add(menuPickup);
			menuPickup.addActionListener(new ActionListener() {
				 
	            public void actionPerformed(ActionEvent e)
	            {
	            	use(getOwner());
	            }
	        });
			
			JMenuItem menuDrop = new JMenuItem("Lâcher");
			this.listMenuItems.add(menuDrop);
			final MeleWeapon mThis = this;
			menuDrop.addActionListener(new ActionListener() {
				 
	            public void actionPerformed(ActionEvent e)
	            {
	            	getOwner().dropItem(mThis);
	            }
	        }); 
		}
		else {
			JMenuItem menuPickup = new JMenuItem("Deséquiper");
			this.listMenuItems.add(menuPickup);
			menuPickup.addActionListener(new ActionListener() {
				 
	            public void actionPerformed(ActionEvent e)
	            {
	            	getOwner().setWeapon(null);
	            	System.out.println(getOwner().getName() + " se deséquipe de " + getName());
	            }
	        });
		}
		return this.listMenuItems;
	}
}
