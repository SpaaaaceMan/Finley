package items.weapons.ranged.munitions;

import characters.Actor;
import items.Item;

public class Munition extends Item{
	
	private int damage;
	
	public Munition(String name, int damage, double weight, double value) {
		super(name, weight, value, false);
		this.damage = damage;
	}

	@Override
	public void use(Actor characterTarget) {
		System.out.println("You can't use this " + this.getName() +
", use it on an appropriate ranged weapon.");
	}

	public int getDamage() {
		return damage;
	}
}
