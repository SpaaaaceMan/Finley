package items.weapons.meles;

import items.weapons.Weapon;

import java.awt.Color;

import javax.swing.ImageIcon;

import characters.Actor;

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
	}//attack()
}
