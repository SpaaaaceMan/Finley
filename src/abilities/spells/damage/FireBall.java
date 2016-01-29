package abilities.spells.damage;

import characters.Actor;

public class FireBall extends DamageSpell {

	public FireBall() {
		super("boule de feu", 10, "ben une boule de feu quoi!", 5);
	}

	@Override
	public void activate(Actor caster, Actor target) {
		target.looseLife(this.getDamage());
		System.out.println(caster.getName() +" lance une boule de feu sur " + 
		target.getName() + " et cause " + getDamage() + " points de dégâts");
	}
}
