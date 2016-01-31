package items.weapons.ranged.munitions;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JMenuItem;

import characters.Actor;
import items.Item;
import items.weapons.meles.MeleWeapon;

public class Munition extends Item{
	
	private int damage;
	
	public Munition(String name, int damage, double weight, double value) {
		super(name, weight, value, false);
		this.damage = damage;
	}
	
	public Munition (Munition munition) {
		super(munition.getName(), munition.getWeight(), munition.getValue(), false);
		this.damage = munition.damage;
	}
	
	@Override
	public Color getItemColor() {
		return Color.ORANGE;
	}

	@Override
	public void use(Actor characterTarget) {
		System.out.println("You can't use this " + this.getName() +
", use it on an appropriate ranged weapon.");
	}

	public int getDamage() {
		return damage;
	}
	
	@Override
	public ArrayList<JMenuItem> getListMenuItems() {
		this.listMenuItems = new ArrayList<JMenuItem>();
		
		JMenuItem menuAddTo = new JMenuItem("Ajouter à");
		this.listMenuItems.add(menuAddTo);
		final Munition mThis = this;
		menuAddTo.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
            	//RangedWeapon.addMunition(this);
            }
        }); 
		
		JMenuItem menuDrop = new JMenuItem("Lâcher");
		this.listMenuItems.add(menuDrop);
		menuDrop.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
            	getOwner().dropItem(mThis);
            }
        }); 
		return this.listMenuItems;
	}
}
