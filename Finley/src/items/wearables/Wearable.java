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
		characterTarget.addWearable(this);
		System.out.println(characterTarget.getName() + " s'équipe avec " + this.getName());
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
	            	//Actor.pickUpItem(this);
	            }
	        });
		}
		else {		
			if (!this.getOwner().getArmorSet().contains(this)) {
				JMenuItem menuPickup = new JMenuItem("Equiper");
				this.listMenuItems.add(menuPickup);
				menuPickup.addActionListener(new ActionListener() {
					 
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
				JMenuItem menuPickup = new JMenuItem("Deséquiper");
				this.listMenuItems.add(menuPickup);
				final Wearable fThis = this;
				menuPickup.addActionListener(new ActionListener() {
					 
		            public void actionPerformed(ActionEvent e)
		            {
		            	getOwner().removeWearable(fThis);
		            	
		            }
		        });
			}
		}
		return this.listMenuItems;
	}
}
