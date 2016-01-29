package ihm;

import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import characters.Actor;
import items.Item;

@SuppressWarnings("serial")
public class InventoryWindow extends JFrame implements Observer{
	public InventoryWindow(final Actor character) {
		this.setLayout(new FlowLayout());
		for (final Item i: character.getInventory()){
			JLabel labelItem = new JLabel(i.getName());
			this.add(labelItem);
			JPopupMenu popupItem = new JPopupMenu();
			for(JMenuItem menu: i.getListMenuItems()){
				popupItem.add(menu);
			}
			PopupListener popupListener = new PopupListener(popupItem);
			labelItem.addMouseListener(popupListener); 
			
		}
		this.setTitle("Inventaire " + character.getName());
		//this.setMinimumSize(new Dimension(300, 300));
		this.pack();
		this.setVisible(true);
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}
