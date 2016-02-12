package abilities.spells.damage;

import abilities.spells.Spell;
import characters.Actor;

public class DamageSpell extends Spell {

	private int damage;
	
	public DamageSpell(String name, int cost, String description, int damage) {
		super(name, cost, description);
		this.damage = damage;
	}

	public DamageSpell(DamageSpell damageSpell) {
		 super(damageSpell.getName(), damageSpell.getCost(), damageSpell.getDescription());
		 this.damage = damageSpell.getDamage();
	}

	public int getDamage() {
		return damage;
	}
	
	@Override
	public void activate(Actor caster, Actor target) {
		target.looseLife(this.getDamage());
		System.out.println(caster.getName() +" lance " + this.getName() + 
		target.getName() + " et cause " + getDamage() + " points de dégâts");
	}
}
