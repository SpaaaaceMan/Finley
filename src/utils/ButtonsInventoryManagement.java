package utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import items.Item;
import items.potions.Potion;
import items.weapons.Weapon;
import items.weapons.ranged.RangedWeapon;
import items.weapons.ranged.munitions.Munition;
import items.wearables.Wearable;
import items.wearables.armors.Armor;

public class ButtonsInventoryManagement {
	public static void initialiserListButtonItem(final Item item){
		/*Uniquement les armes*/
		if (item instanceof Weapon){
			/*uniquement les armes � distance*/
			if (item instanceof RangedWeapon){
				JButton buttonReload = new JButton("Recharger");
				item.getListMenuItems().add(buttonReload);
				buttonReload.addActionListener(new ActionListener() {
					 
		            public void actionPerformed(ActionEvent e)
		            {
		            	//this.add(Munition munition);
		            }
		        }); 
			}
			if (item.getOwner().getWeapon() != item) {
				JButton buttonEquip = new JButton("Equiper");
				item.getListMenuItems().add(buttonEquip);
				buttonEquip.addActionListener(new ActionListener() {
					 
		            public void actionPerformed(ActionEvent e)
		            {
		            	item.use(item.getOwner());
		            }
		        });
			}
			else {
				JButton buttonUnEquip = new JButton("Des�quiper");
				item.getListMenuItems().add(buttonUnEquip);
				buttonUnEquip.addActionListener(new ActionListener() {
					 
		            public void actionPerformed(ActionEvent e)
		            {
		            	item.getOwner().setWeapon(null);
		            	System.out.println(item.getOwner().getName() + " se d�s�quipe de " + item.getName());
		            }
		        });
			}
		}
		/*uniquement les armures*/
		else if (item instanceof Armor){
			JButton buttonAddTo = new JButton("se d�nuder");
			item.getListMenuItems().add(buttonAddTo);
			buttonAddTo.addActionListener(new ActionListener() {
				 
	            public void actionPerformed(ActionEvent e)
	            {
	            	//lol
	            }
	        }); 
		}
		/*uniquement les munitions*/
		else if (item instanceof Munition){
			JButton buttonAddTo = new JButton("Ajouter �");
			item.getListMenuItems().add(buttonAddTo);
			buttonAddTo.addActionListener(new ActionListener() {
				 
	            public void actionPerformed(ActionEvent e)
	            {
	            	//RangedWeapon.addMunition(this);
	            }
	        }); 
		}
		/*uniquement les potions*/
		else if (item instanceof Potion){
			JButton buttonDrink = new JButton("Boire");
			item.getListMenuItems().add(buttonDrink);
			buttonDrink.addActionListener(new ActionListener() {
				 
	            public void actionPerformed(ActionEvent e)
	            {
	            	item.use(item.getOwner());
	            }
	        }); 
		}
		/*tous les items*/
		JButton buttonDrop = new JButton("L�cher");
		item.getListMenuItems().add(buttonDrop);
		buttonDrop.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
            	item.getOwner().dropItem(item);
            }
        }); 
	}
}
