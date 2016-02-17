package ihm.inventory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import ihm.PopupListener;
import items.Item;

@SuppressWarnings("serial")
public class GroundInventory extends JFrame implements Observer{
	
	@SuppressWarnings("unused")
	private static GroundInventory instance; //Singleton
	private static ArrayList<Item> inventory = new ArrayList<Item>(); //repr�sente les Item au sol
	private static ArrayList<JLabel> labelsInventory = new ArrayList<JLabel>();
	private static Actor actorToPlay; //L'Actor qui pourra ramass� (celui qui joue).
	private JPanel panelInventory;
	
	//Attention, il faudra ajouter cet observer � tous les Actor.
	private GroundInventory(final Actor character) {
		GroundInventory.actorToPlay = character;
		
		//affichage des items en eux-m�me
		panelInventory = new JPanel();
		actualizeInventory();
		
		this.setLayout(new GridLayout(2, 1));
		this.add(panelInventory);
		this.setTitle("Sol");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(500, 30));
		this.pack();
		this.setVisible(true);
	}
	
	public void actualizeInventory(){
		labelsInventory = new ArrayList<JLabel>();
		for (final Item i: inventory){
			JLabel labelItem = new JLabel(i.getName());
			labelItem.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			labelItem.setBackground(i.getItemColor());
			labelItem.setOpaque(true);
			labelsInventory.add(labelItem);
			JPopupMenu popupItem = new JPopupMenu();
			JMenuItem menuPickup = new JMenuItem("Ramasser");			
			menuPickup.addActionListener(new ActionListener() {
				 
	            public void actionPerformed(ActionEvent e)
	            {
	            	inventory.remove(i);
	            	actorToPlay.pickUpItem(i);    	
	            }
	        });
			popupItem.add(menuPickup);
			labelItem.addMouseListener(new PopupListener(popupItem)); 
		}
		displayInventory();
	}
	
	private void displayInventory(){
		panelInventory.removeAll();
		panelInventory.invalidate();
		for (JLabel label: labelsInventory)
			panelInventory.add(label);
		panelInventory.validate();
		panelInventory.repaint();
	}
	
	public static GroundInventory getInstance(Actor actorToPlay) {
		return instance = new GroundInventory(actorToPlay);
	}
	
	public void setActorToPlay(Actor actorToPlay) {
		GroundInventory.actorToPlay = actorToPlay;
	}
	
	public static void addItemToGround (Item item) {
		inventory.add(item);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		actualizeInventory();
	}//update()
}
