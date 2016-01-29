package ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JSpinner.ListEditor;

import characters.Actor;
import items.Item;
import items.potions.LargePotion;

@SuppressWarnings("serial")
public class InventoryWindow extends JFrame implements Observer{
	
	private ArrayList<JLabel> labelsInventory = new ArrayList<JLabel>();
	private Actor ownerOfInventory;
	
	public InventoryWindow(final Actor character) {
		ownerOfInventory = character;
		ownerOfInventory.addObserver(this);
		actualizeInventory(ownerOfInventory);
		this.setLayout(new FlowLayout());
		this.setTitle("Inventaire " + ownerOfInventory.getName());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(300, 30));
		this.pack();
		this.setVisible(true);
		
	}
	
	public void actualizeInventory(Actor character){
		labelsInventory = new ArrayList<JLabel>();
		for (final Item i: character.getInventory()){
			JLabel labelItem = new JLabel(i.getName());
			labelItem.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			labelsInventory.add(labelItem);
			JPopupMenu popupItem = new JPopupMenu();
			for(JMenuItem menu: i.getListMenuItems()){
				popupItem.add(menu);
			}
			labelItem.addMouseListener(new PopupListener(popupItem)); 
		}
		displayInventory();
	}
	
	public void displayInventory(){
		if (labelsInventory.isEmpty()){
			this.getContentPane().removeAll();
		}
		else
		{
			for (JLabel label: labelsInventory){
				this.add(label);
			}
		}
		this.getContentPane().repaint();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		for (int i = 0; i < ownerOfInventory.getInventory().size(); ++i)
		{
			if (arg1 == ownerOfInventory.getInventory().get(i)){
				this.remove(labelsInventory.get(i));
				ownerOfInventory.getInventory().remove(i);
			}
		}
		actualizeInventory(ownerOfInventory);
	}
}
