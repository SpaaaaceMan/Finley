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
import javax.swing.text.TableView.TableRow;

import characters.Actor;
import items.Item;
import items.weapons.ranged.munitions.Munition;
import utils.ButtonsInventoryManagement;
import utils.ColorManagement;
import utils.InventoryActionButton;
import utils.ItemManagement;
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
	private boolean hasRemove = false;
	
	public static int selectedRow;
	
	public InventoryWindow(final Actor character) {
		/*===LIAISON AVEC LE PERSONNAGE===*/
		ownerOfInventory = character;
		ownerOfInventory.addObserver(this);
		
		/*===INFOS SUR LE POIDS===*/
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
		
		/*===INFOS SUR LES ITEMS===*/
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
		initializeInventory();
		settingsTable();
		
		/*===PARAMETRES DE LA FENETRE===*/
		this.setLayout(new BorderLayout());
		this.add(panelWeight, BorderLayout.NORTH);
		this.add(panelInventory, BorderLayout.CENTER);
		this.add(panelActions, BorderLayout.SOUTH);
		this.setTitle("Inventaire de " + ownerOfInventory.getName());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}//InventoryWindow()
	
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
		listItems.setBackground(ColorManagement.DARK_GREEN);
		listItems.setForeground(ColorManagement.LIGHT_GREEN);
		listItems.setGridColor(ColorManagement.LIGHT_GREEN);
		listItems.setSelectionBackground(ColorManagement.LIGHT_GREEN);
		listItems.setSelectionForeground(ColorManagement.DARK_GREEN);
		
		/*===TAILLE===*/
		listItems.setPreferredScrollableViewportSize(new Dimension(800, 250));
		
		/*===MODE DE SELECTION===*/
		listItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		/*===HAUTEUR DES LIGNES===*/
		listItems.setRowHeight(50);
		
		/*===CENTRER LES DONNEES===*/
		//((JLabel)listItems.getDefaultRenderer(String.class)).setHorizontalTextPosition(JLabel.CENTER); 
		listItems.setDefaultRenderer(String.class, new JTableRender());
		
		/*===TRI PAR COLONNE===*/
		listItems.setAutoCreateRowSorter(true);
		
		/*===SUPPRESSION DE LA GRILLE===*/
		listItems.setShowGrid(false);
		
		/*===AFFICHAGE BOUTONS SELON ITEM SELECTIONNE===*/
		listItems.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectedRow = listItems.getSelectedRow();
				JTableRender custom = new JTableRender(); 
				custom.setHorizontalAlignment(JLabel.CENTER); // centre les données du tableau
					listItems.getColumnModel().getColumn(1).setCellRenderer(custom); 
				panelActions.removeAll();
				panelActions.invalidate();
    			for(final InventoryActionButton b: DLMInventory.getItems().get(selectedRow).getListButtonsItems()){
    				panelActions.add(b);  				
	    			panelActions.validate();
    				panelActions.repaint();
    				displayInventory();
				}
			}
		});//listItems.MouseAdapter()
		/*listItems.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (hasRemove){
					hasRemove = false;
				}
			}
		});*/
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
		});//listItems.MouseMotionListener()
 	}//settingsTable()
	
	public void initializeInventory(){
		/*pour chaque item présent dans l'inventaire du personnage*/
		for (Item item: ownerOfInventory.getInventory()){
			DLMInventory.addItem(item);	//ajout du nouvel item dans l'inventaire graphique		
		}
		displayInventory();
	}//actualizeInventory()
	
	public void displayInventory(){
		this.validate();
		this.repaint();
	}
	
	public void addItem(Item item){
		/*pour chaque ligne de l'inventaire graphique*/
		for (int j = 0; j <= DLMInventory.getRowCount(); ++j){
			/*à la ligne où se trouve cet item*/
			if (j != DLMInventory.getRowCount() && item.getName() == listItems.getValueAt(j, 1)){
				DLMInventory.addItem(item);
			}
		}//boucle for
	}
	
	public void removeItem(Item item){
		/*pour chaque ligne de l'inventaire graphique*/
		for (int j = 0; j <= DLMInventory.getRowCount(); ++j){
			/*à la ligne où se trouve cet item*/
			if (j != DLMInventory.getRowCount() && item.getName() == listItems.getValueAt(j, 1)){
				DLMInventory.removeItem(j);	//on supprime l'item de l'inventaire graphique
				/*si l'inventaire n'est pas vide*/
				if (DLMInventory.getRowCount() != 0){
					/*si l'inventaire ne contient plus qu'un item*/
					/*final int row = listItems.getSelectedRow();
					//on obtient la ligne nouvellement sélectionné
					//puis on actualise les boutons d'actions selon l'item concerné
					panelActions.removeAll();
					panelActions.invalidate();
	    			for(final InventoryActionButton b: DLMInventory.getItems().get(row).getListButtonsItems()){
	    				panelActions.add(b);  				
		    			panelActions.validate();
	    				panelActions.repaint();
	    				displayInventory();
					}//boucle for*/
				}//if 
			}//if
		}//boucle for
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg1 == "pickUp")
			addItem(ItemManagement.itemToMove);
		else if (arg1 == "drop")
			removeItem(ItemManagement.itemToMove);
		labelWeight.setText(ownerOfInventory.getWeight() + "/" + ownerOfInventory.getMaxWeight() + " kg");	
		
	}//update()
}
