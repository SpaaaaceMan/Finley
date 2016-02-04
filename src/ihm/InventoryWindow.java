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
	private JLabel labelWeight;
	private JPanel panelInventory;
	private ModeleDynamiqueObjet DLMInventory = new ModeleDynamiqueObjet();
	private JTable listItems = new JTable(DLMInventory);
	private MyGlassPane glass = new MyGlassPane();
	private Map<Object, Icon> icons = new HashMap<Object, Icon>();
	private ArrayList<Item> itemsOwned = new ArrayList<Item>();
	
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
		listItems.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		listItems.setAutoCreateRowSorter(true);
		actualizeInventory();
		//listItems.setCell(new IconListRenderer(icons));
		panelInventory.add(panelListItems);
			
			JPanel panelActions = new JPanel();
			panelActions.setLayout(new FlowLayout());
			JButton buttonDrop = new JButton("Lâcher");
			JButton buttonUse = new JButton("Utiliser");
			panelActions.add(buttonUse);
			panelActions.add(buttonDrop);
			
			/*listItems.addListSelectionListener(new ListSelectionListener() {
				
				@Override
				public void valueChanged(ListSelectionEvent e) {
					selectedItem = ownerOfInventory.getInventory().get(listItems.getSelectedIndex());
					itemName.setText(selectedItem.getName());
					itemWeight.setText(String.valueOf(selectedItem.getWeight()));
				}
			}); */
		/*creation de la grille d'inventaire vide avec des bordures*/
		/*for (int i = 0; i < 25; ++i){
			JLabel label = new JLabel("", JLabel.CENTER);
			label.setBorder(BorderFactory.createLineBorder(Color.black, 1));
			label.addMouseListener(new MouseGlassListener(glass));
		    label.addMouseMotionListener(new MouseGlassMotionListener(glass));
		    label.setTransferHandler(new TransferHandler("icon"));
			labelsInventory.add(label);
			panelInventory.add(label);
		}*/
		
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
			//DLMInventory.addItem(item);
			/*
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
			labelItem.addMouseListener(new PopupListener(popupItem)); */
		}
		displayInventory();
	}
	
	public void displayInventory(){
		/*panelInventory.removeAll();
		panelInventory.invalidate();
		for (JLabel label: labelsInventory)
			panelInventory.add(label);
		
		panelInventory.validate();*/
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
