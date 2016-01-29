package items;

import java.util.ArrayList;

import javax.swing.JMenuItem;

import characters.Actor;

public abstract class Item {
	private double weight;		//poids de l'objet
	private double value;		//prix de l'objet
	private Actor owner;		//possesseur de l'objet
	private boolean isReusable;	//l'objet est-il réutilisable (true) ou à usage unique (false)
	private String name;		//nom de l'objet
	protected ArrayList<JMenuItem> listMenuItems = new ArrayList<JMenuItem>();
	
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
		return "Item [weight=" + weight + ", value=" + value + ", name=" + name + "]";
	}

	public String getName() {
		return name;
	}

	public abstract ArrayList<JMenuItem> getListMenuItems();

	public Actor getOwner() {
		return owner;
	}

	public void setOwner(Actor owner) {
		this.owner = owner;
	}
}
