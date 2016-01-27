package abilities;

import characters.Actor;

public abstract class Ability {
	
	private String name;
	private int cost;
	private String description;
	
	public Ability(String name, int cost, String description) {
		super();
		this.name = name;
		this.cost = cost;
		this.description = description;
	}
	
	public abstract void activate(Actor character);
	public abstract void activate(Actor caster, Actor target);

	public String getName() {
		return name;
	}

	public int getCost() {
		return cost;
	}

	public String getDescription() {
		return description;
	}

}
