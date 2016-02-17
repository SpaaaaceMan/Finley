package ihm.inventory;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JList;

import items.weapons.ranged.RangedWeapon;
import items.weapons.ranged.munitions.Munition;
import utils.ColorManagement;

@SuppressWarnings("serial")
public class MunitionsWindow extends JFrame {
	
	private JList<String> listMunition = new JList<String>();
	
	private ArrayList<Munition> munitions = new ArrayList<Munition>();
	
	public MunitionsWindow(RangedWeapon rangedWeapon) {
		this.setBackground(ColorManagement.DARK_GREEN);
		this.setLayout(new BorderLayout());
		this.setTitle("Munitions pour " + rangedWeapon.getName());
		this.setPreferredSize(new Dimension(300, 300));
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}//MunitionWindow()

	public JList<String> getListMunition() {
		return listMunition;
	}

	public void setListMunition(JList<String> listMunition) {
		this.listMunition = listMunition;
	}
}
