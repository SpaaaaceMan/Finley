package items;

import characters.Actor;

public abstract class Item {
	private double weight;		//poids de l'objet
	private double value;		//prix de l'objet
	private boolean isReusable;	//l'objet est-il r�utilisable (true) ou � usage unique (false)
	private String name;		//nom de l'objet
	
	public Item(String name, double weight, double value, boolean isReusable) {
		this.name = name;
		this.weight = weight;
		this.value = value;
		this.isReusable = isReusable;
	}
	
	public abstract void use(Actor characterTarget);

	public double getWeight() {
		return weight;
	}

	public double getValue() {
		return value;
	}

	public boolean isReusable() {
		return isReusable;
	}

	@Override
	public String toString() {
		return "Item [weight=" + weight + ", value=" + value + ", isReusable=" + isReusable + "]";
	}

	public String getName() {
		return name;
	}
}
