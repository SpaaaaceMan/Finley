package utils;

import items.Item;
import items.potions.Potion;
import items.weapons.Weapon;
import items.weapons.ranged.RangedWeapon;
import items.weapons.ranged.munitions.Munition;
import items.wearables.armors.Armor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeMap;

public abstract class ButtonsInventoryManagement {
	
	public static final TreeMap<String, Integer> quantityOfItem = new TreeMap<String, Integer>();
	
	public static void initialiserListButtonItem(final Item item){
		/*Uniquement les armes*/
		if (item instanceof Weapon){
			/*uniquement les armes à distance*/
			if (item instanceof RangedWeapon){
				InventoryActionButton buttonReload = new InventoryActionButton("Recharger");
				item.getListButtonsItems().add(buttonReload);
				buttonReload.addActionListener(new ActionListener() {
					 
		            public void actionPerformed(ActionEvent e)
		            {
		            	//this.add(Munition munition);
		            }
		        }); 
			}
			if (item.getOwner().getWeapon() != item) {
				InventoryActionButton buttonEquip = new InventoryActionButton("Équiper");
				item.getListButtonsItems().add(buttonEquip);
				buttonEquip.addActionListener(new ActionListener() {
					 
		            public void actionPerformed(ActionEvent e)
		            {
		            	item.use(item.getOwner());
		            }
		        });
			}
			else {
				InventoryActionButton buttonUnEquip = new InventoryActionButton("Déséquiper");
				item.getListButtonsItems().add(buttonUnEquip);
				buttonUnEquip.addActionListener(new ActionListener() {
					 
		            public void actionPerformed(ActionEvent e)
		            {
		            	item.getOwner().setWeapon(null);
		            	System.out.println(item.getOwner().getName() + " se déséquipe de " + item.getName());
		            }
		        });
			}
		}
		/*uniquement les armures*/
		else if (item instanceof Armor){
			InventoryActionButton buttonAddTo = new InventoryActionButton("se dénuder");
			item.getListButtonsItems().add(buttonAddTo);
			buttonAddTo.addActionListener(new ActionListener() {
				 
	            public void actionPerformed(ActionEvent e)
	            {
	            	//lol
	            }
	        }); 
		}
		/*uniquement les munitions*/
		else if (item instanceof Munition){
			InventoryActionButton buttonAddTo = new InventoryActionButton("Ajouter à");
			item.getListButtonsItems().add(buttonAddTo);
			buttonAddTo.addActionListener(new ActionListener() {
				 
	            public void actionPerformed(ActionEvent e)
	            {
	            	//RangedWeapon.addMunition(this);
	            }
	        }); 
		}
		/*uniquement les potions*/
		else if (item instanceof Potion){
			InventoryActionButton buttonDrink = new InventoryActionButton("Boire");
			item.getListButtonsItems().add(buttonDrink);
			buttonDrink.addActionListener(new ActionListener() {
				 
	            public void actionPerformed(ActionEvent e)
	            {
	            	item.use(item.getOwner());
	            }
	        }); 
		}
		/*tous les items*/
		InventoryActionButton buttonDrop = new InventoryActionButton("Lâcher");
		item.getListButtonsItems().add(buttonDrop);
		buttonDrop.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
            	item.getOwner().dropItem(item);
            	SoundManagement.playSound("sounds/items/ui_items_melee_down.wav");
            }
        }); 
	}
}
