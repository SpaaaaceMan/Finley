package items.weapons;

import characters.Actor;
import items.Item;

public abstract class Weapon extends Item {
	private int damage;	//les dommages caus�s par l'arme

	private int durability;

	public Weapon(String name, int damage, double weight, double value, int durability) {
		super(name, weight, value, true);
		this.damage = damage;
		this.durability = durability;
	}
	
	public void use(Actor characterToEquipe){
		characterToEquipe.setWeapon(this);
		System.out.println(characterToEquipe.getName() + " s'�quipe avec " + this.getName());
	}
	
	public abstract void attack (Actor targetedCharacter);

	public int getDamage() {
		return damage;
	}

	public int getDurability() {
		return durability;
	}

	public void setDurability(int durability) {
		this.durability = durability;
	}
}