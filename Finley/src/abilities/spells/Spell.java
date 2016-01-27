package abilities.spells;

import abilities.Ability;
import characters.Actor;

public abstract class Spell extends Ability {

	public Spell(String name, int cost, String description) {
		super(name, cost, description);
	}

	@Override
	public void activate(Actor caster) {}

	@Override
	public void activate(Actor caster, Actor target) {}

}
