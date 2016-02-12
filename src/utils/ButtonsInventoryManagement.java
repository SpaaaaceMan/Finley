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

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ihm.MunitionsWindow;

public abstract class ButtonsInventoryManagement {
	
	public static final TreeMap<String, Integer> quantityOfItem = new TreeMap<String, Integer>();
	
	public static void initialiserListButtonItem(final Item item){
		/*Uniquement les armes*/
		if (item instanceof Weapon){
			/*uniquement les armes à distance*/
			if (item instanceof RangedWeapon){
				InventoryActionButton buttonReload = new InventoryActionButton("Changer de munitions");
				item.getListButtonsItems().add(buttonReload);
				buttonReload.addActionListener(new ActionListener() {
					 
		            public void actionPerformed(ActionEvent e)
		            {
		            	MunitionsWindow munitionsWindow = new MunitionsWindow((RangedWeapon) item);
		            }
		        }); 
			}
			if (item.getOwner().getWeaponEquiped() != item) {
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
			InventoryActionButton buttonAddTo = new InventoryActionButton("Enfiler");
			item.getListButtonsItems().add(buttonAddTo);
			buttonAddTo.addActionListener(new ActionListener() {
				 
	            public void actionPerformed(ActionEvent e)
	            {
	            	item.use(item.getOwner());
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
            	int nbToRemove;
            	if ((ButtonsInventoryManagement.quantityOfItem.get(item.getName())) == 1)
            		nbToRemove = 1;
            	else
            		nbToRemove = displayRemoveWindow(item);
            	item.getOwner().dropItem(item, nbToRemove);
            	SoundManagement.playSound("sounds/items/ui_items_melee_down.wav");
            }
        }); 
	}
	
	public static int displayRemoveWindow(Item item){
    	JFrame parent 			= new JFrame();
	    JOptionPane optionPane  = new JOptionPane();
	    JSlider slider 			= getSlider(optionPane, 
	    						ButtonsInventoryManagement.quantityOfItem.get(item.getName()));
	    optionPane.setMessage(new Object[] { "Combien voulez-vous en lâcher ? ", slider });
	    optionPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
	    optionPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
	    JDialog dialog = optionPane.createDialog(parent, "Lâcher " + item.getName());
	    dialog.setVisible(true);
    	return (int) optionPane.getInputValue();
    }

    static JSlider getSlider(final JOptionPane optionPane, int maxQuantity) {
	    JSlider slider = new JSlider();
	    slider.setMaximum(maxQuantity);
	    slider.setMajorTickSpacing(1);
	    slider.setPaintTicks(true);
	    slider.setPaintLabels(true);
	    optionPane.setInputValue(new Integer(slider.getValue()));
	    ChangeListener changeListener = new ChangeListener() {
	      public void stateChanged(ChangeEvent changeEvent) {
	        JSlider theSlider = (JSlider) changeEvent.getSource();
	        if (!theSlider.getValueIsAdjusting()) {
	          optionPane.setInputValue(new Integer(theSlider.getValue()));
	        }
	      }
	    };
	    slider.addChangeListener(changeListener);
	    return slider;
	}
}
