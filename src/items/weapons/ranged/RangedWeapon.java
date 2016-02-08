package items.weapons.ranged;

import items.weapons.Weapon;
import items.weapons.ranged.munitions.Munition;

import java.awt.Color;

import javax.swing.ImageIcon;

import characters.Actor;

public class RangedWeapon extends Weapon{
	
	Munition munitionEquiped;

	public RangedWeapon(String name, int damage, double weight,int placeOccupiedInventory, double value, int durability, ImageIcon icon) {
		super(name, damage, weight, placeOccupiedInventory, value, durability, icon);
	}
	
	public RangedWeapon (RangedWeapon weapon) {
		super(weapon.getName(), weapon.getDamage(), weapon.getWeight(), weapon.getPlaceOccupiedInventory(), weapon.getValue(),
				weapon.getDurability(), weapon.getIcon());
		if (weapon.getMunitions() != null) {
			this.munitionEquiped = new Munition (weapon.getMunitions());
		}
	}
	
	@Override
	public Color getItemColor() {
		return Color.CYAN;
	}

	public Munition getMunitions() {
		return munitionEquiped;
	}

	@Override
	public void attack(Actor targetedCharacter) {
		if (this.getDurability() <= 0) {
			System.out.println("Your " + this.getName() + " is broken. Too bad.");
		}
		else if (munitionEquiped.getNumber() <= 0){
			System.out.println("Your " + this.getName() + " is empty. Fill it up boy!");
		}
		else {
			System.out.println(targetedCharacter.getName() + 
					" a �t� attqu� avec un(e) "+ this.getName() + " pour " + 
					(this.getDamage() + munitionEquiped.getDamage()) + " d�gats.");
			targetedCharacter.looseLife(this.getDamage() + munitionEquiped.getDamage());
			this.setDurability(this.getDurability() - 1);
			this.munitionEquiped.use(null);;
			if (this.getDurability() <= 0) {
				System.out.println("Your " + this.getName() + " has broken. Toooo baaaaaaaad.");
			}
			else if (munitionEquiped.getNumber() <= 0){
				System.out.println("Your " + this.getName() + " is now empty. Fill it up boy!");
			}
		}
	}
	
	public void addMunition (int number) {
		this.munitionEquiped.addMunition(number);
	}
	
	public void setMunition (Munition munition) {
		this.munitionEquiped = munition;
	}
}
