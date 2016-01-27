package items.weapons.ranged;

import java.util.ArrayList;

import characters.Actor;
import items.weapons.Weapon;
import items.weapons.ranged.munitions.Munition;

public class RangedWeapon extends Weapon{
	ArrayList<Munition> munitions = new ArrayList<Munition>();

	public RangedWeapon(String name, int damage, double weight, double value, int durability) {
		super(name, damage, weight, value, durability);
		// TODO Auto-generated constructor stub
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
}
