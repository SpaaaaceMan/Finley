package abilities.spells.damage;

import java.util.ArrayList;

public abstract class ListDamageSpells {

	static ArrayList<DamageSpell> damageSpells = new ArrayList<>();
	
	static {
		damageSpells.add(new DamageSpell("Boule de feu", 3, "FIREBALL!!!", 15));
		damageSpells.add(new DamageSpell("Eclairs de force", 10, "POWER!!!UNLIMITED POWEEER!!!", 30));
	}

	public static DamageSpell getDamageSpell(int index) {
		return new DamageSpell(damageSpells.get(index));
	}
}
