package items.weapons.ranged;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JMenuItem;

import characters.Actor;
import items.potions.Potion;
import items.weapons.Weapon;
import items.weapons.ranged.munitions.Munition;

public class RangedWeapon extends Weapon{
	ArrayList<Munition> munitions = new ArrayList<Munition>();

	public RangedWeapon(String name, int damage, double weight, double value, int durability) {
		super(name, damage, weight, value, durability);
	}
	
	public RangedWeapon (RangedWeapon weapon) {
		super(weapon.getName(), weapon.getDamage(), weapon.getWeight(), weapon.getValue(), weapon.getDurability());
		this.munitions = weapon.munitions;
	}

	@Override
	public void attack(Actor targetedCharacter) {
		if (this.getDurability() <= 0) {
			System.out.println("Your " + this.getName() + " is broken. Too bad.");
		}
		else if (munitions.size() <= 0){
			System.out.println("Your " + this.getName() + " is empty. Fill it up boy!");
		}
		else {
			System.out.println(targetedCharacter.getName() + 
					" has been attacked by a "+ this.getName() + " for " + 
					(this.getDamage() + munitions.get(0).getDamage()) + " damage.");
			targetedCharacter.looseLife(this.getDamage() + munitions.get(0).getDamage());
			this.setDurability(this.getDurability() - 1);
			this.munitions.remove(0);
			if (this.getDurability() <= 0) {
				System.out.println("Your " + this.getName() + " has broken. Toooo baaaaaaaad.");
			}
			else if (munitions.size() <= 0){
				System.out.println("Your " + this.getName() + " is now empty. Fill it up boy!");
			}
		}
	}

	public void addMunition (Munition munition) {
		this.munitions.add(munition);
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
			JMenuItem menuReload = new JMenuItem("Recharger");
			this.listMenuItems.add(menuReload);
			menuReload.addActionListener(new ActionListener() {
				 
	            public void actionPerformed(ActionEvent e)
	            {
	            	//this.add(Munition munition);
	            }
	        }); 
			
			JMenuItem menuDrop = new JMenuItem("Lâcher");
			this.listMenuItems.add(menuDrop);
			final RangedWeapon rThis = this;
			menuDrop.addActionListener(new ActionListener() {
				 
	            public void actionPerformed(ActionEvent e)
	            {
	            	getOwner().dropItem(rThis);
	            }
	        }); 
		}
		return this.listMenuItems;
	}
}
