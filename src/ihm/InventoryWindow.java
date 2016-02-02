package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.TransferHandler;

import characters.Actor;
import items.Item;

@SuppressWarnings("serial")
public class InventoryWindow extends JFrame implements Observer{
	
	private ArrayList<JLabel> labelsInventory = new ArrayList<JLabel>();
	private Actor ownerOfInventory;
	private JPanel panelWeight;
	private JLabel labelWeight;
	private JPanel panelInventory;
	private MyGlassPane glass = new MyGlassPane();
	
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
		panelInventory.setLayout(new GridLayout(5, 5));
		
		/*creation de la grille d'inventaire vide avec des bordures*/
		for (int i = 0; i < 25; ++i){
			JLabel label = new JLabel("", JLabel.CENTER);
			label.setBorder(BorderFactory.createLineBorder(Color.black, 1));
			label.addMouseListener(new MouseGlassListener(glass));
		    label.addMouseMotionListener(new MouseGlassMotionListener(glass));
		    label.setTransferHandler(new TransferHandler("icon"));
			labelsInventory.add(label);
			panelInventory.add(label);
		}
		actualizeInventory();
		
		this.setLayout(new BorderLayout());
		this.add(panelWeight, BorderLayout.NORTH);
		this.add(panelInventory, BorderLayout.CENTER);
		this.setTitle("Inventaire " + ownerOfInventory.getName());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(400, 400));
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public boolean locationIsFree(JLabel labelToCheck){
		return (labelToCheck.getText() == new JLabel().getText());
	}
	
	public void actualizeInventory(){
		for (int i = 0; i < ownerOfInventory.getInventory().size(); ++i){
			if (ownerOfInventory.getInventory().get(i).getIcon() != null)
				labelsInventory.get(i).setIcon(ownerOfInventory.getInventory().get(i).getIcon());
			else
				labelsInventory.get(i).setText(ownerOfInventory.getInventory().get(i).getName());
			}
			/*//On vérifie si l'Item est équipé
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
		}*/
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
