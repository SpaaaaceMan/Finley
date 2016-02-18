package ihm.inventory;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import characters.Actor;
import characters.Hero;
import items.Item;
import items.weapons.ranged.RangedWeapon;
import utils.ButtonsInventoryManagement;
import utils.ColorManagement;
import utils.InventoryActionButton;
import utils.ItemManagement;

@SuppressWarnings("serial")
public class InventoryWindow extends JFrame implements Observer{
	
	private Hero ownerOfInventory;	//le personnage dont l'inventaire s'affiche
	
	private JPanel panelWeight;		//le panel contenant les infos relatives au poids
	private JPanel panelInventory;	//le panel contentant la liste des items du personnage
	private JPanel panelActions;	//le panel contenant les boutons d'actions relatifs à l'item sélectionné
	private JPanel panelEquipment;	//la panel contenant les items équipés sur le personnage
	private JPanel Armor;
	private JPanel Weapon;
	private JPanel Munition;
	private JPanel Artifact;
	
	private JLabel labelActualMoney;
	private JLabel labelActualPlace;
	private JLabel labelActualWeight;		//affiche le poids actuel du personnage / sa capacité max
	
	private ModeleDynamiqueObjet DLMInventory = new ModeleDynamiqueObjet();
	private JTable listItems = new JTable(DLMInventory);
	
	public static int selectedRow;
	
	public InventoryWindow(final Hero character) {
		/*===LIAISON AVEC LE PERSONNAGE===*/
		ownerOfInventory = character;
		ownerOfInventory.addObserver(this);
		
		/*===INFOS SUR LE POIDS===*/
		panelWeight = new JPanel();
		{
			/*les panneaux contiennent les labels correspondants pour ne pas être séparés par le margin*/
			JPanel panelActualWeight = new JPanel();
			JPanel panelActualPlace  = new JPanel();
			JPanel panelActualMoney  = new JPanel();
			
			/*on leur applique la couleur de fond sinon blanc*/
			panelActualWeight.setBackground(ColorManagement.DARK_GREEN);
			panelActualPlace.setBackground(ColorManagement.DARK_GREEN);
			panelActualMoney.setBackground(ColorManagement.DARK_GREEN);

			/*Ce sont les labels fixes*/
			JLabel labelWeight = new JLabel("Poids: ");
			JLabel labelPlace  = new JLabel("Place: ");
			JLabel labelMoney  = new JLabel("Argent: ");
			
			/*ce sont les labels qui vont s'actualiser*/
			labelActualWeight = new JLabel(ownerOfInventory.getWeight() + "/" + ownerOfInventory.getMaxWeight() + " kg");
			labelActualPlace  = new JLabel(ownerOfInventory.getPlace() + "/" + ownerOfInventory.getMaxPlace() + " emplacements");
			labelActualMoney  = new JLabel(ownerOfInventory.getGold() + " Finlays");
			
			/*on applique le thème + mise en page des textes*/
			personalizeComponent(labelWeight);
			personalizeComponent(labelPlace);
			personalizeComponent(labelMoney);
			personalizeComponent(labelActualWeight);
			personalizeComponent(labelActualPlace);
			personalizeComponent(labelActualMoney);
			
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
		
		/*===LES ITEMS===*/
		panelInventory = new JPanel();
		{
			panelInventory.setLayout(new GridLayout());
			JScrollPane scrollItems = new JScrollPane(listItems);
			scrollItems.getViewport().setOpaque(false);
			scrollItems.setOpaque(false);
			panelInventory.add(scrollItems);
		}
			
		panelActions = new JPanel();
		{
			panelActions.setBackground(ColorManagement.DARK_GREEN);
			panelActions.setLayout(new FlowLayout());
		}
		
		panelEquipment = new JPanel();
		{
			panelEquipment.setLayout(new GridLayout(10, 1));
			panelEquipment.setBackground(ColorManagement.DARK_GREEN);
			panelEquipment.setPreferredSize(new Dimension(90, 100));
			
			JLabel labelArmor    = new JLabel("Armure", JLabel.CENTER);
			JLabel labelWeapon   = new JLabel("Arme", JLabel.CENTER);
			JLabel labelMunition = new JLabel("Munition", JLabel.CENTER);
			JLabel labelArtifact = new JLabel("Artefact", JLabel.CENTER);
			
			personalizeComponent(labelArmor);
			personalizeComponent(labelWeapon);
			personalizeComponent(labelMunition);
			personalizeComponent(labelArtifact);
			
			Armor    = new JPanel();
			Weapon   = new JPanel();
			Munition = new JPanel();
			Artifact = new JPanel();
			
			Armor.setLayout(new GridLayout());
			Weapon.setLayout(new GridLayout());
			
			personalizeComponent(Armor);
			personalizeComponent(Weapon);
			personalizeComponent(Munition);
			personalizeComponent(Artifact);
			
			Armor.setBorder(BorderFactory.createEtchedBorder());
			Weapon.setBorder(BorderFactory.createEtchedBorder());
			Munition.setBorder(BorderFactory.createEtchedBorder());
			Artifact.setBorder(BorderFactory.createEtchedBorder());
			
			panelEquipment.add(labelArmor);
			panelEquipment.add(Armor);
			panelEquipment.add(labelWeapon);
			panelEquipment.add(Weapon);
			panelEquipment.add(labelMunition);
			panelEquipment.add(Munition);
			panelEquipment.add(labelArtifact);
			panelEquipment.add(Artifact);
		}
		initializeInventory();
		settingsTable();
		
		/*===PARAMETRES DE LA FENETRE===*/
		panelInventory.setBackground(ColorManagement.DARK_GREEN);
		this.setBackground(ColorManagement.DARK_GREEN);
		this.setLayout(new BorderLayout());
		this.add(panelWeight, BorderLayout.NORTH);
		this.add(panelInventory, BorderLayout.CENTER);
		this.add(panelActions, BorderLayout.SOUTH);
		this.add(panelEquipment, BorderLayout.EAST);
		this.setTitle("Inventaire de " + ownerOfInventory.getName());
		this.setMinimumSize(new Dimension(700, 300));
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}//InventoryWindow()
	
	private void personalizeComponent(JComponent label){
		if (label instanceof JLabel){
		if (((JLabel) label).getText().contains(":"))
			label.setFont(new Font("Courier New", Font.BOLD, 16));
		else
			label.setFont(new Font("Courier New", Font.PLAIN, 16));
		}
		label.setBackground(ColorManagement.DARK_GREEN);
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
		});
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
	
	public void removeItem(Item item, int nbToRemove){
		/*pour chaque ligne de l'inventaire graphique*/
		for (int j = 0; j <= DLMInventory.getRowCount(); ++j){
			/*à la ligne où se trouve cet item*/
			if (j != DLMInventory.getRowCount() && item.getName() == listItems.getValueAt(j, 1)){
				selectedRow = listItems.getSelectedRow();
				
				DLMInventory.removeItem(j, nbToRemove);	
				/*si l'inventaire n'est pas vide*/
				if (DLMInventory.getRowCount() != 0){
					//si l'inventaire ne contient plus qu'un item
					if (DLMInventory.getRowCount() == 1)
						listItems.getSelectionModel().setSelectionInterval(0, 0);
					/*sinon  si l'item selectionné est le dernier de la liste*/
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
				}//if 
				else
					panelActions.removeAll();
				displayInventory();
			}//if
		}//boucle for
	}
	
	

	public void changeEquipedWeapon (){
		Weapon.removeAll();
		ImageIcon iconWeapon = (ImageIcon) listItems.getValueAt(listItems.getSelectedRow(), 0);
		Weapon.add(new JLabel(iconWeapon));
		if (ownerOfInventory.getWeaponEquiped() instanceof RangedWeapon){
			Munition.setLayout(new GridLayout());
			JLabel label = new JLabel(((RangedWeapon) ownerOfInventory.getWeaponEquiped()).getMunitions().getIcon());
			Munition.add(label);
		}
		displayInventory();
	}
	
	public void changeEquipedArmor (){
		Armor.removeAll();
		ImageIcon icone = (ImageIcon) listItems.getValueAt(listItems.getSelectedRow(), 0);
		Armor.add(new JLabel(icone));
		displayInventory();
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg1 == "pickUp")
			addItem(ItemManagement.itemToMove);
		else if (arg1 instanceof Integer){
			if (ItemManagement.itemToMove == ownerOfInventory.getWeaponEquiped())
				Weapon.removeAll();
			else if (ownerOfInventory.getArmorSet().contains(ItemManagement.itemToMove))
				Armor.removeAll();
			removeItem(ItemManagement.itemToMove, (int) arg1);
		}
		else if (arg1 == "arme")
			changeEquipedWeapon();
		else if (arg1 == "armure")
			changeEquipedArmor();
		labelActualWeight.setText(ownerOfInventory.getWeight() + "/" + ownerOfInventory.getMaxWeight() + " kg");	
		labelActualPlace.setText(ownerOfInventory.getPlace() + "/" + ownerOfInventory.getMaxPlace() + " emplacements");
	}//update()
}
