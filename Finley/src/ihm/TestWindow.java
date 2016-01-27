package ihm;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import characters.Actor;

@SuppressWarnings("serial")
public class TestWindow extends JFrame {
	
	private JTextArea textAreaActions;
	private Character monHero;
	private Character monEnnemi;
	
	public TestWindow(Character hero, Character ennemi){
		this.monHero = hero;
		this.monEnnemi = ennemi;
		this.setLayout(new GridLayout(2, 2));
		
		//en bas a gauche
		textAreaActions = new JTextArea();
		
		//en haut a gauche
		JPanel panelHero = new JPanel();
		panelHero.setLayout(new FlowLayout());
		JLabel labelNomHero = new JLabel(monHero.toString(), JLabel.CENTER);
		panelHero.add(labelNomHero);
		
		//en haut a droite
		JPanel panelEnnemi = new JPanel();
		panelEnnemi.setLayout(new FlowLayout());
		JLabel labelNomEnnemi = new JLabel(monEnnemi.toString(), JLabel.CENTER);
		panelEnnemi.add(labelNomEnnemi);
		
		//en bas a droite
		JPanel panelActions = new JPanel();
		panelActions.setLayout(new GridLayout());
		JButton boutonAttaquer = new JButton("Attaquer");
		JButton boutonRamasser = new JButton("Ramasser");
		JButton boutonUtiliser = new JButton("Utiliser");
		panelActions.add(boutonAttaquer);
		panelActions.add(boutonRamasser);
		panelActions.add(boutonUtiliser);
		
		this.add(panelHero);
		this.add(panelEnnemi);
		this.add(textAreaActions);
		this.add(panelActions);
		
		this.setTitle("Test du jeu");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(300, 300));
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
	
	public JTextArea getTextArea() {
		return textAreaActions;
	}
}
