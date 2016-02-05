package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import characters.Actor;
import items.Item;
import utils.ButtonsInventoryManagement;
import utils.InventoryActionButton;
import utils.SoundManagement;

@SuppressWarnings("serial")
public class InventoryWindow extends JFrame implements Observer{
	
	private Actor ownerOfInventory;	//le personnage dont l'inventaire s'affiche
	
	private JPanel panelWeight;		//le panel contenant les infos relatives au poids
	private JPanel panelInventory;	//le panel contentant la liste des items du personnage
	private JPanel panelActions;	//le panel contenant les boutons d'actions relatifs à l'item sélectionné
	
	private JLabel labelWeight;		//affiche le poids actuel du personnage / sa capacité max
	
	private ModeleDynamiqueObjet DLMInventory = new ModeleDynamiqueObjet();
	private JTable listItems = new JTable(DLMInventory);
	
	private int currentRowSelected = -1;
	
	public InventoryWindow(final Actor character) {
		ownerOfInventory = character;
		ownerOfInventory.addObserver(this);
		
		//affichage des infos sur le poids
		panelWeight = new JPanel();
		{
			JLabel labelCapacity = new JLabel("Capacité : ");
			labelCapacity.setFont(new Font("Courier New Gras", Font.BOLD, 16));
			labelCapacity.setForeground(new Color(37, 248, 131));
			panelWeight.setBackground(new Color(29, 82, 42));
			panelWeight.setLayout(new FlowLayout());
			panelWeight.add(labelCapacity);
			labelWeight = new JLabel(ownerOfInventory.getWeight() + "/" + ownerOfInventory.getMaxWeight() + " kg");
			labelWeight.setFont(new Font("Courier New", Font.PLAIN, 16));
			labelWeight.setForeground(new Color(37, 248, 131));
			panelWeight.add(labelWeight);
		}
		
		//affichage des items en eux-même
		panelInventory = new JPanel();
		{
			panelInventory.setLayout(new GridLayout());
			panelInventory.add(new JScrollPane(listItems));
		}
			
		panelActions = new JPanel();
		{
			panelActions.setBackground(new Color(29, 82, 42));
			panelActions.setLayout(new FlowLayout());
		}
		actualizeInventory();
		settingsTable();
		
		this.setLayout(new BorderLayout());
		this.add(panelWeight, BorderLayout.NORTH);
		this.add(panelInventory, BorderLayout.CENTER);
		this.add(panelActions, BorderLayout.SOUTH);
		this.setTitle("Inventaire de " + ownerOfInventory.getName());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private void settingsTable() {
		JTableHeader header = listItems.getTableHeader();
        header.setBackground(new Color(37, 248, 131));
        header.setForeground(new Color(29, 82, 42));
        header.setFont(new Font("Courier New", Font.BOLD, 16));
		DefaultTableCellRenderer custom = new DefaultTableCellRenderer(); 
		custom.setHorizontalAlignment(JLabel.CENTER); // centre les données du tableau
		for (int i = 1; i < listItems.getColumnCount(); i++) // centre chaque cellule du tableau sauf les icônes
			listItems.getColumnModel().getColumn(i).setCellRenderer(custom); 
		
		/*===FONT===*/
		listItems.setFont(new Font("Courier New", Font.PLAIN, 12));
		
		/*===COULEURS===*/
		listItems.setBackground(new Color(29, 82, 42));
		listItems.setForeground(new Color(37, 248, 131));
		listItems.setGridColor(new Color(37, 248, 131));
		listItems.setSelectionBackground(new Color(37, 248, 131));
		listItems.setSelectionForeground(new Color(29, 82, 42));
		
		/*===TAILLE===*/
		listItems.setPreferredScrollableViewportSize(new Dimension(800, 250));
		
		/*===MODE DE SELECTION===*/
		listItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		/*===HAUTEUR DES LIGNES===*/
		listItems.setRowHeight(50);
		
		/*===CENTRER LES DONNEES===*/
		((JLabel)listItems.getDefaultRenderer(String.class)).setHorizontalTextPosition(JLabel.CENTER); 
		
		/*===TRI PAR COLONNE===*/
		listItems.setAutoCreateRowSorter(true);
		
		/*===AFFICHAGE BOUTONS SELON ITEM SELECTIONNE===*/
		listItems.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				final int row = listItems.getSelectedRow();
				panelActions.removeAll();
				panelActions.invalidate();
    			for(final InventoryActionButton b: DLMInventory.getItems().get(row).getListButtonsItems()){
    				panelActions.add(b);  				
	    			panelActions.validate();
    				panelActions.repaint();
    				displayInventory();
				}
			}
		});
		listItems.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				int row = listItems.rowAtPoint(e.getPoint());
				if (row != currentRowSelected){
					listItems.clearSelection();
					listItems.setRowSelectionInterval(row, row);
				}
				currentRowSelected = row;			
			}

			@Override
			public void mouseDragged(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
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
					
					//Ajout d'une indication si équipé 
					//(ne fonctionne pas mais aucunes idées de pourquoi)
					if (ownerOfInventory.getWeapon() != null &&
							ownerOfInventory.getWeapon() == item) {
						DLMInventory.setValueAt(item.getName() + " (E)", i, 1);
						listItems.setValueAt(item.getName() + " (E)", i, 1);
						DLMInventory.fireTableRowsInserted(i - 1, i);
					}
					break;
				}
			}
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
			for (int i = 0; i < ownerOfInventory.getInventory().size(); ++i){
				if (arg1 == ownerOfInventory.getInventory().get(i)){
					for (int j = 0; j <= DLMInventory.getRowCount(); ++j){
						if (j != DLMInventory.getRowCount() && ((Item) arg1).getName() == listItems.getValueAt(j, 1)){
							int n = (int) listItems.getValueAt(j, 5);
							listItems.setValueAt(++n, j, 5);
							break;
						}
						else if (j == DLMInventory.getRowCount()){
							DLMInventory.addItem((Item) arg1);
							break;
						}
					}
					break;
				}	
				else if (i == ownerOfInventory.getInventory().size() - 1){
					for (int j = 0; j <= DLMInventory.getRowCount(); ++j){
						if (j != DLMInventory.getRowCount() && ((Item) arg1).getName() == listItems.getValueAt(j, 1)){
							DLMInventory.removeItem(j);
							listItems.getSelectionModel().setSelectionInterval(j, j);
						}
					}
				}
			}//boucle for
			labelWeight.setText(ownerOfInventory.getWeight() + "/" + ownerOfInventory.getMaxWeight() + " kg");	
		}//if
	}//update()
}
