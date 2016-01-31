package ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import characters.Actor;
import items.Item;
import items.wearables.Wearable;

@SuppressWarnings("serial")
public class InventoryWindow extends JFrame implements Observer{
	
	private ArrayList<JLabel> labelsInventory = new ArrayList<JLabel>();
	private Actor ownerOfInventory;
	private JPanel panelWeight;
	private JLabel labelWeight;
	private JPanel panelInventory;
	
	public InventoryWindow(final Actor character) {
		ownerOfInventory = character;
		ownerOfInventory.addObserver(this);
		
		//affichage des infos sur le poids
		panelWeight = new JPanel();
		panelWeight.setLayout(new FlowLayout());
		panelWeight.add(new JLabel("Capacité : "));
		labelWeight = new JLabel(ownerOfInventory.getWeight() + "/" + ownerOfInventory.getMaxWeight() + " kg");
		panelWeight.add(labelWeight);
		
		//affichage des items en eux-même
		panelInventory = new JPanel();
		actualizeInventory();
		
		this.setLayout(new GridLayout(2, 1));
		this.add(panelWeight);
		this.add(panelInventory);
		this.setTitle("Inventaire " + ownerOfInventory.getName());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(500, 30));
		this.pack();
		this.setVisible(true);
		
	}
	
	public void actualizeInventory(){
		labelsInventory = new ArrayList<JLabel>();
		for (Item i: ownerOfInventory.getInventory()){
			JLabel labelItem = new JLabel();
			
			//On vérifie si l'Item est équipé
			if (ownerOfInventory.getArmorSet().contains(i) || ownerOfInventory.getWeapon() == i) {
				labelItem.setText(i.getName() + " [equipé]");
			}
			else {
				labelItem.setText(i.getName());
			}
			
			labelItem.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			labelItem.setBackground(i.getItemColor());
			labelItem.setOpaque(true);
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
		panelInventory.removeAll();
		panelInventory.invalidate();
		for (JLabel label: labelsInventory)
			panelInventory.add(label);
		
		panelInventory.validate();
		panelInventory.repaint();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		/*si le notify provient de l'action ramasser/lâcher*/
		if (arg1 instanceof Item)
		{
			labelWeight.setText(ownerOfInventory.getWeight() + "/" + ownerOfInventory.getMaxWeight() + " kg");	
		}
		actualizeInventory();
	}//update()
}
