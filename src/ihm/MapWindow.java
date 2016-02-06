package ihm;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.StrokeBorder;

import characters.Actor;
import maps.Map;
import maps.places.Place;

public class MapWindow extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**La {@link maps.Map} de cette fenetre.
	 */
	private Map map;
	
	/**Le {@link JPanel} representant la {@link maps.Map}.
	 */
	private JPanel mapPanel;
	
	/**
	 * les coordonnées en X de l'{@link #hero}.
	 */
	private int heroX = 0;
	
	/**
	 * les coordonnées en Y de l'{@link #hero}.
	 */
	private int heroY = 0;
	
	/**Vous! (Votre {@link Actor}).
	 * 
	 */
	private Actor hero;
	
	public MapWindow (Actor hero, Map map) {
		//Création de la JFrame
		super("Map");
		
		//Initialisation des attributs
		this.hero 	   = hero;
		this.map 	   = map;
		this.mapPanel  = new JPanel(new GridLayout(16, 16));
		
		//initialisation des composants static
		
		//Ajout en tant qu'observer
		this.hero.addObserver(this);
		
		//Gestion du mapPanel
		//Création d'une map simple.
		for (ArrayList<Place> row : map.getMap()) {
			for (Place column : row) {
				JLabel mapLabel = new JLabel ("" + column.getType().charAt(0));
				this.mapPanel.add(mapLabel);
			}
		}
		
		//Indication de la position du héro
		JLabel mapLabel = (JLabel) mapPanel.getComponent(10 * heroX + heroY);
		mapLabel.setBackground(Color.RED);
		mapLabel.setOpaque(true);
		
		//Ajout d'une Border au mapPanel
		mapPanel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3)));
		
		//Ajout des composants et du Layout
		this.setLayout(new BorderLayout());
		this.add(mapPanel, BorderLayout.CENTER);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
	}

}
