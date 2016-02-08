package items;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import characters.Actor;
import utils.ButtonsInventoryManagement;
import utils.InventoryActionButton;

public abstract class Item {
	private double weight;				//poids de l'objet
	private int placeOccupiedInventory;	//place prise par l'objet dans l'inventaire
	private double value;				//prix de l'objet
	private Actor owner;				//possesseur de l'objet
	private boolean isReusable;			//l'objet est-il réutilisable (true) ou à usage unique (false)
	private String name;				//nom de l'objet
	private int sizeInventoryX;			//la place en largeur que prend l'objet dans un inventaire
	private int sizeInventoryY; 		//la place en hauteur que prend l'objet dans un inventaire
	private ImageIcon icon;				//icone représentant l'objet
	private ArrayList<InventoryActionButton> listButtonsItems = new ArrayList<InventoryActionButton>();
	
	public Item(String name, double weight, int placeOccupiedInventory, double value, boolean isReusable, ImageIcon icon) {
		this.name = name;
		this.weight = weight;
		this.placeOccupiedInventory = placeOccupiedInventory;
		this.value = value;
		this.isReusable = isReusable;
		this.icon = icon;
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

	public ImageIcon getIcon() {
		return icon;
	}

	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}

	public int getPlaceOccupiedInventory() {
		return placeOccupiedInventory;
	}

	public ArrayList<InventoryActionButton> getListButtonsItems() {
		return listButtonsItems;
	}

	public void setListButtonsItems(ArrayList<InventoryActionButton> listButtonsItems) {
		this.listButtonsItems = listButtonsItems;
	}
}
