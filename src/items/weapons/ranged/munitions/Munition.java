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
	
	private String realName;
	
	private int damage;
	
	private int number = 0;
	
	public Munition(String name, int damage, double weight, double value) {
		super(name, weight, value, true);
		this.realName = name;
		this.setName(realName + "[" + this.number + "]");
		this.damage = damage;
	}
	
	public Munition (Munition munition) {
		super(munition.getRealName(), munition.getWeight(), munition.getValue(), true);
		this.realName = munition.getRealName();
		this.damage   = munition.getDamage();
		this.number   = munition.getNumber();
		this.setName(realName + "[" + this.number + "]");
	}
	
	public void addMunition (int number) {
		this.number += number;
		this.setName(realName + "[" + this.number + "]");
	}
	
	public String getRealName() {
		return realName;
	}

	@Override
	public Color getItemColor() {
		return Color.ORANGE;
	}

	@Override
	public void use(Actor characterTarget) {
		this.number -= 1;
	}

	public int getDamage() {
		return damage;
	}
	
	public int getNumber() {
		return number;
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
