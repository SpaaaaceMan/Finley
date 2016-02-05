package items.weapons.meles;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;

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
	public ArrayList<JButton> getListButtonsItem() {
		
		return this.getListMenuItems();
	}
}
