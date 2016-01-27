package items.weapons;

import characters.Actor;
import items.Item;

public class Weapon extends Item {
	private int damage;	//les dommages causés par l'arme

	public Weapon(String name, double weight, double value, boolean isReusable) {
		super(name, weight, value, isReusable);
		this.damage = 10;
	}
	
	public void use(Actor characterToEquipe){
		characterToEquipe.setWeapon(this);
		System.out.println(characterToEquipe.getName() + "s'équipe avec " + this.toString());
	}

	public int getDamage() {
		return damage;
	}

	@Override
	public void use(Character characterTarget) {
		// TODO Auto-generated method stub
		
	}
}
