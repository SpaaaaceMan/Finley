package items.weapons.ranged;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;

import characters.Actor;
import items.weapons.Weapon;
import items.weapons.ranged.munitions.Munition;

public class RangedWeapon extends Weapon{
	
	Munition munitionEquiped;

	public RangedWeapon(String name, int damage, double weight, double value, int durability, ImageIcon icon) {
		super(name, damage, weight, value, durability, icon);
	}
	
	public RangedWeapon (RangedWeapon weapon) {
		super(weapon.getName(), weapon.getDamage(), weapon.getWeight(), weapon.getValue(),
				weapon.getDurability(), weapon.getIcon());
		if (weapon.getMunitions() != null) {
			this.munitionEquiped = new Munition (weapon.getMunitions());
		}
	}
	
	@Override
	public Color getItemColor() {
		return Color.CYAN;
	}

	public Munition getMunitions() {
		return munitionEquiped;
	}

	@Override
	public void attack(Actor targetedCharacter) {
		if (this.getDurability() <= 0) {
			System.out.println("Your " + this.getName() + " is broken. Too bad.");
		}
		else if (munitionEquiped.getNumber() <= 0){
			System.out.println("Your " + this.getName() + " is empty. Fill it up boy!");
		}
		else {
			System.out.println(targetedCharacter.getName() + 
					" a �t� attqu� avec un(e) "+ this.getName() + " pour " + 
					(this.getDamage() + munitionEquiped.getDamage()) + " d�gats.");
			targetedCharacter.looseLife(this.getDamage() + munitionEquiped.getDamage());
			this.setDurability(this.getDurability() - 1);
			this.munitionEquiped.use(null);;
			if (this.getDurability() <= 0) {
				System.out.println("Your " + this.getName() + " has broken. Toooo baaaaaaaad.");
			}
			else if (munitionEquiped.getNumber() <= 0){
				System.out.println("Your " + this.getName() + " is now empty. Fill it up boy!");
			}
		}
	}
	
	public void addMunition (int number) {
		this.munitionEquiped.addMunition(number);
	}
	
	public void setMunition (Munition munition) {
		this.munitionEquiped = munition;
	}
	
	@Override
	public ArrayList<JMenuItem> getListMenuItems() {
		this.listMenuItems = new ArrayList<JMenuItem>();
		
		if (this.getOwner().getWeapon() != this) {
			JMenuItem menuPickup = new JMenuItem("Equiper");
			this.listMenuItems.add(menuPickup);
			menuPickup.addActionListener(new ActionListener() {
				 
	            public void actionPerformed(ActionEvent e)
	            {
	            	use(getOwner());
	            }
	        });
			
			JMenuItem menuDrop = new JMenuItem("L�cher");
			this.listMenuItems.add(menuDrop);
			final RangedWeapon rThis = this;
			menuDrop.addActionListener(new ActionListener() {
				 
	            public void actionPerformed(ActionEvent e)
	            {
	            	getOwner().dropItem(rThis);       	
	            }
	        }); 
		}
		else {
			JMenuItem menuPickup = new JMenuItem("Des�quiper");
			this.listMenuItems.add(menuPickup);
			menuPickup.addActionListener(new ActionListener() {
				 
	            public void actionPerformed(ActionEvent e)
	            {
	            	getOwner().setWeapon(null);
	            }
	        });
		}
		JMenuItem menuReload = new JMenuItem("Recharger");
		this.listMenuItems.add(menuReload);
		menuReload.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
            	//this.add(Munition munition);
            }
        }); 
		return this.listMenuItems;
	}
}
