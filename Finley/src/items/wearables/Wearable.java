package items.wearables;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JMenuItem;

import characters.Actor;
import items.Item;
import items.weapons.meles.MeleWeapon;

public abstract class Wearable extends Item {

	private int armorPoint;
	
	public Wearable(String name, double weight, double value, int armorPoint) {
		super(name, weight, value, true);
		this.armorPoint = armorPoint;
	}

	@Override
	public void use(Actor characterTarget) {
		//ToDo : make an actor equiped with it
	}

	public int getArmorPoint() {
		return armorPoint;
	}
	
	@Override
	public ArrayList<JMenuItem> getListMenuItems() {
		this.listMenuItems = new ArrayList<JMenuItem>();
		
		if (this.getOwner() == null) {
			JMenuItem menuPickup = new JMenuItem("Ramasser");
			this.listMenuItems.add(menuPickup);
			menuPickup.addActionListener(new ActionListener() {
				 
	            public void actionPerformed(ActionEvent e)
	            {
	            	//use(Actor character);
	            }
	        });
		}
		else {			
			JMenuItem menuDrop = new JMenuItem("Lâcher");
			this.listMenuItems.add(menuDrop);
			final Wearable wThis = this;
			menuDrop.addActionListener(new ActionListener() {
				 
	            public void actionPerformed(ActionEvent e)
	            {
	            	getOwner().dropItem(wThis);
	            }
	        }); 
		}
		return this.listMenuItems;
	}
}
