package abilities.spells.damage;

import abilities.spells.Spell;

public abstract class DamageSpell extends Spell {

	private int damage;
	
	public DamageSpell(String name, int cost, String description, int damage) {
		super(name, cost, description);
		this.damage = damage;
	}

	public int getDamage() {
		return damage;
	}

}
