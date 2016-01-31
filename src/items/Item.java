package items;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JMenuItem;

import characters.Actor;

public abstract class Item {
	private double weight;		//poids de l'objet
	private double value;		//prix de l'objet
	private Actor owner;		//possesseur de l'objet
	private boolean isReusable;	//l'objet est-il r�utilisable (true) ou � usage unique (false)
	private String name;		//nom de l'objet
	private int sizeInventoryX;	//la place en largeur que prend l'objet dans un inventaire
	private int sizeInventoryY; //la place en hauteur que prend l'objet dans un inventaire
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

	public abstract Color getItemColor();
	
	public abstract ArrayList<JMenuItem> getListMenuItems();

	public Actor getOwner() {
		return owner;
	}

	public void setOwner(Actor owner) {
		this.owner = owner;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSizeInventoryX() {
		return sizeInventoryX;
	}

	public int getSizeInventoryY() {
		return sizeInventoryY;
	}
}
