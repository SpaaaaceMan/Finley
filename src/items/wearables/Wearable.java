package items.wearables;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JMenuItem;

import characters.Actor;
import items.Item;

public abstract class Wearable extends Item {

	private int armorPoint;
	
	public Wearable(String name, double weight, double value, int armorPoint) {
		super(name, weight, value, true);
		this.armorPoint = armorPoint;
	}
	

	@Override
	public void use(Actor characterTarget) {
		characterTarget.addWearable(this);
	}

	public int getArmorPoint() {
		return armorPoint;
	}
	
	@Override
	public Color getItemColor() {
		return Color.YELLOW;
	}
	
	@Override
	public ArrayList<JMenuItem> getListMenuItems() {
		this.listMenuItems = new ArrayList<JMenuItem>();
			
		if (!this.getOwner().getArmorSet().contains(this)) {
			JMenuItem menuEquip = new JMenuItem("Equiper");
			this.listMenuItems.add(menuEquip);
			menuEquip.addActionListener(new ActionListener() {
				 
	            public void actionPerformed(ActionEvent e)
	            {
	            	use(getOwner());
	            }
	        });
			
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
		else {
			JMenuItem menuUnEquip = new JMenuItem("Déséquiper");
			this.listMenuItems.add(menuUnEquip);
			final Wearable fThis = this;
			menuUnEquip.addActionListener(new ActionListener() {
				 
	            public void actionPerformed(ActionEvent e)
	            {
	            	getOwner().removeWearable(fThis);
	            }
	        });
		}
		return this.listMenuItems;
	}
}
