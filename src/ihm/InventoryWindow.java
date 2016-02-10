package ihm;

import items.Item;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import utils.ButtonsInventoryManagement;
import utils.ColorManagement;
import utils.InventoryActionButton;
import utils.ItemManagement;
import characters.Actor;

@SuppressWarnings("serial")
public class InventoryWindow extends JFrame implements Observer{
	
	private Actor ownerOfInventory;	//le personnage dont l'inventaire s'affiche
	
	private JPanel panelWeight;		//le panel contenant les infos relatives au poids
	private JPanel panelInventory;	//le panel contentant la liste des items du personnage
	private JPanel panelActions;	//le panel contenant les boutons d'actions relatifs à l'item sélectionné
	
	private JLabel labelActualMoney;
	private JLabel labelActualPlace;
	private JLabel labelActualWeight;		//affiche le poids actuel du personnage / sa capacité max
	
	private ModeleDynamiqueObjet DLMInventory = new ModeleDynamiqueObjet();
	private JTable listItems = new JTable(DLMInventory);
	
	private int currentRowSelected = -1;
	@SuppressWarnings("unused")
	private boolean hasRemove = false;
	
	public static int selectedRow;
	
	public InventoryWindow(final Actor character) {
		/*===LIAISON AVEC LE PERSONNAGE===*/
		ownerOfInventory = character;
		ownerOfInventory.addObserver(this);
		
		/*===INFOS SUR LE POIDS===*/
		panelWeight = new JPanel();
		{
			/*les panneaux contiennent les labels correspondants pour ne pas être séparés par le margin*/
			JPanel panelActualWeight = new JPanel();
			JPanel panelActualPlace = new JPanel();
			JPanel panelActualMoney = new JPanel();
			
			/*on leur applique la couleur de fond sinon blanc*/
			panelActualWeight.setBackground(ColorManagement.DARK_GREEN);
			panelActualPlace.setBackground(ColorManagement.DARK_GREEN);
			panelActualMoney.setBackground(ColorManagement.DARK_GREEN);

			/*Ce sont les labels fixes*/
			JLabel labelWeight = new JLabel("Poids: ");
			JLabel labelPlace = new JLabel("Place: ");
			JLabel labelMoney = new JLabel("Argent: ");
			
			/*ce sont les labels qui vont s'actualiser*/
			labelActualWeight = new JLabel(ownerOfInventory.getWeight() + "/" + ownerOfInventory.getMaxWeight() + " kg");
			labelActualPlace = new JLabel(ownerOfInventory.getPlace() + "/" + ownerOfInventory.getMaxPlace() + " emplacements");
			labelActualMoney = new JLabel(ownerOfInventory.getGold() + " Finlays");
			
			/*on applique le thème + mise en page des textes*/
			personalizeLabel(labelWeight);
			personalizeLabel(labelPlace);
			personalizeLabel(labelMoney);
			personalizeLabel(labelActualWeight);
			personalizeLabel(labelActualPlace);
			personalizeLabel(labelActualMoney);
			
			/*on assigne chaque duos de labels à leur panel*/
			panelActualWeight.add(labelWeight);
			panelActualWeight.add(labelActualWeight);
			panelActualPlace.add(labelPlace);
			panelActualPlace.add(labelActualPlace);
			panelActualMoney.add(labelMoney);
			panelActualMoney.add(labelActualMoney);
			
			panelWeight.setBackground(ColorManagement.DARK_GREEN);
			panelWeight.setLayout(new BorderLayout());
			panelWeight.add(panelActualWeight, BorderLayout.WEST);
			panelWeight.add(panelActualPlace, BorderLayout.CENTER);
			panelWeight.add(panelActualMoney, BorderLayout.EAST);
		}
		
		/*===INFOS SUR LES ITEMS===*/
		panelInventory = new JPanel();
		{
			panelInventory.setLayout(new GridLayout());
			panelInventory.add(new JScrollPane(listItems));
		}
			
		panelActions = new JPanel();
		{
			panelActions.setBackground(ColorManagement.DARK_GREEN);
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
		this.setMinimumSize(new Dimension(700, 300));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}//InventoryWindow()
	
	private void personalizeLabel(JLabel label){
		if (label.getText().contains(":"))
			label.setFont(new Font("Courier New", Font.BOLD, 16));
		else
			label.setFont(new Font("Courier New", Font.PLAIN, 16));
		label.setForeground(ColorManagement.LIGHT_GREEN);
	}
	
	private void settingsTable() {
		JTableHeader header = listItems.getTableHeader();
        header.setBackground(ColorManagement.LIGHT_GREEN);
        header.setForeground(ColorManagement.DARK_GREEN);
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
				panelActions.removeAll();
				panelActions.invalidate();
    			for(final InventoryActionButton b: DLMInventory.getItems().get(listItems.getSelectedRow()).getListButtonsItems()){
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
				selectedRow = listItems.getSelectedRow();
				DLMInventory.removeItem(j);	
				System.out.println(DLMInventory.getRowCount() + " j = " + selectedRow);
				/*si l'inventaire n'est pas vide*/
				if (DLMInventory.getRowCount() != 0){
					//si l'inventaire ne contient plus qu'un item
					if (DLMInventory.getRowCount() == 1)
						listItems.getSelectionModel().setSelectionInterval(0, 0);
					else if (selectedRow == DLMInventory.getRowCount())
						listItems.getSelectionModel().setSelectionInterval(j - 1, j - 1);
					else
						listItems.getSelectionModel().setSelectionInterval(j, j);
					//on obtient la ligne nouvellement sélectionné
					//puis on actualise les boutons d'actions selon l'item concern�
					panelActions.removeAll();
					panelActions.invalidate();
	    			for(final InventoryActionButton b: DLMInventory.getItems().get(listItems.getSelectedRow()).getListButtonsItems()){
	    				panelActions.add(b);  				
		    			panelActions.validate();
	    				panelActions.repaint();
					}//boucle for
	    			displayInventory();
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
		labelActualWeight.setText(ownerOfInventory.getWeight() + "/" + ownerOfInventory.getMaxWeight() + " kg");	
	}//update()
}
