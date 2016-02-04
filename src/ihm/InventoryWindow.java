package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.TransferHandler;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.DefaultMenuLayout;
import javax.swing.plaf.metal.MetalIconFactory;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import characters.Actor;
import characters.ActorFactory;
import items.Item;

@SuppressWarnings("serial")
public class InventoryWindow extends JFrame implements Observer{
	
	private Actor ownerOfInventory;
	private JPanel panelWeight;
	private JPanel panelActions;
	private JLabel labelWeight;
	private JPanel panelInventory;
	private ModeleDynamiqueObjet DLMInventory = new ModeleDynamiqueObjet();
	private JTable listItems = new JTable(DLMInventory);
	private MyGlassPane glass = new MyGlassPane();
	
	private int previous = -1;
	
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
		panelInventory.setLayout(new GridLayout());
		
		JPanel panelListItems = new JPanel();
		panelListItems.setLayout(new GridLayout());
		JScrollPane scrollPaneInventory = new JScrollPane(listItems);
		panelListItems.add(scrollPaneInventory);
		listItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listItems.setRowHeight(50);
		//listItems.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		listItems.setAutoCreateRowSorter(true);
		listItems.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				final int row = listItems.getSelectedRow();
				if (row != previous){
    				panelActions.removeAll();
    				panelActions.invalidate();
	    			for(JButton b: DLMInventory.getItems().get(row).getListButtonsItem())
	    				panelActions.add(b);
	    			panelActions.validate();
    				panelActions.repaint();
    				displayInventory();
				}
				previous = row;
			}
		});
		panelInventory.add(panelListItems);
			
		panelActions = new JPanel();
		panelActions.setLayout(new FlowLayout());
		actualizeInventory();
		
		this.setLayout(new BorderLayout());
		this.add(panelWeight, BorderLayout.NORTH);
		this.add(panelInventory, BorderLayout.CENTER);
		this.add(panelActions, BorderLayout.SOUTH);
		this.setTitle("Inventaire " + ownerOfInventory.getName());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public void actualizeInventory(){
		for (Item item: ownerOfInventory.getInventory()){
			for (int i = 0; i <= DLMInventory.getRowCount(); ++i){
				if (i != DLMInventory.getRowCount() && item.getName() == listItems.getValueAt(i, 1)){
					int n = (int) listItems.getValueAt(i, 5);
					listItems.setValueAt(++n, i, 5);
					break;
				}
				else if (i == DLMInventory.getRowCount()){
					DLMInventory.addItem(item);
					break;
				}
			}
			/*
			if (ownerOfInventory.getArmorSet().contains(i) || ownerOfInventory.getWeapon() == i) {
				labelItem.setText(i.getName() + " [equipé]");
			}
			else {
				labelItem.setText(i.getName());
			}*/
		}
		displayInventory();
	}
	
	public void displayInventory(){
		this.validate();
		this.repaint();
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
