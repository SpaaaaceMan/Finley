package items.potions;

import characters.Actor;
import items.Item;

public abstract class Potion extends Item {
	private int healingPoints;

	public Potion(String name, double weight, double value, int healingPoints) {
		super(name, weight, value, false);
		this.setHealingPoints(healingPoints);
	}

	public void use(Actor characterToHeal){
		int lifeBeforeHeal = characterToHeal.getLife();
		characterToHeal.earnLife(healingPoints);
		System.out.println(characterToHeal.getName() + " boit une potion et regagne " + 
		(characterToHeal.getLife() - lifeBeforeHeal) + " points de vie" );
	}

	public int getHealingPoints() {
		return healingPoints;
	}

	public void setHealingPoints(int healingPoints) {
		this.healingPoints = healingPoints;
	}
}
