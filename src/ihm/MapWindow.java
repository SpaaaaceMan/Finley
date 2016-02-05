package ihm;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
	@SuppressWarnings("unused")
	private Map map;
	
	/**Le {@link JPanel} representant la {@link maps.Map}.
	 */
	private JPanel mapPanel;
	
	/**Vous! (Votre {@link Actor}).
	 * 
	 */
	private Actor hero;
	
	public MapWindow (Actor hero, Map map) {
		//Création de la JFrame
		super("Map");
		
		//Initialisation des attributs
		this.hero 	  = hero;
		this.map 	  = map;
		this.mapPanel = new JPanel(new GridLayout(16, 16));
		
		//Ajout en tant qu'observer
		this.hero.addObserver(this);
		
		//Gestion du mapPanel
		//Création d'une map simple.
		for (ArrayList<Place> row : map.getMap()) {
			for (Place column : row) {
				this.mapPanel.add(new JLabel ("" + column.getType().charAt(0)));
			}
		}
		
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
		// TODO Auto-generated method stub

	}

}
