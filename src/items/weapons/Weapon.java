package items.weapons;

import javax.swing.ImageIcon;

import characters.Actor;
import items.Item;

public abstract class Weapon extends Item {
	
	private int damage;			//les dommages causés par l'arme

	private int durability;		//la durée de vide de l'arme

	public Weapon(String name, int damage, double weight,int placeOccupiedInventory, double value, int durability, ImageIcon icon) {
		super(name, weight, placeOccupiedInventory, value, true, icon);
		this.damage = damage;
		this.durability = durability;
	}
	
	public void use(Actor characterToEquipe){
		characterToEquipe.setWeaponEquiped(this);
		System.out.println(characterToEquipe.getName() + " s'équipe avec " + this.getName());
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
