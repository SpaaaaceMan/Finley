package ihm;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import characters.Actor;
import characters.Monster;
import characters.Warrior;
import fights.SimpleFight;

@SuppressWarnings("serial")
public class TestWindow extends JFrame implements Observer{
	
	private JTextArea textAreaActions;
	private Actor monHero;
	private Actor monEnnemi;
	private JLabel labelNom = new JLabel();
	private JLabel labelVie = new JLabel();
	private JLabel labelEnergie = new JLabel();
	private JLabel labelNomEnnemi = new JLabel();
	private JLabel labelVieEnnemi = new JLabel();
	private JLabel labelEnergieEnnemi = new JLabel();
	
	public TestWindow(Actor hero, Actor ennemi){
		this.monHero = hero;
		monHero.addObserver(this);
		this.monEnnemi = ennemi;
		monEnnemi.addObserver(this);
		this.setLayout(new GridLayout(2, 2));
		
		//en bas a gauche
		textAreaActions = new JTextArea();
		JScrollPane scrollActions = new JScrollPane(textAreaActions);
		
		//en haut a gauche
		JPanel panelHero = new JPanel();
		actualizeInformations(monHero);
		panelHero.add(labelNom);
		panelHero.add(labelVie);
		panelHero.add(labelEnergie);
		panelHero.setBorder(BorderFactory.createTitledBorder("Héro"));
		
		//en haut a droite
		JPanel panelEnnemi = new JPanel();
		actualizeInformations(monEnnemi);
		panelEnnemi.add(labelNomEnnemi);
		panelEnnemi.add(labelVieEnnemi);
		panelEnnemi.add(labelEnergieEnnemi);
		panelEnnemi.setBorder(BorderFactory.createTitledBorder("Ennemi"));
		
		//en bas a droite
		JPanel panelActions = new JPanel();
		panelActions.setLayout(new GridLayout());
		JButton boutonAttaquer = new JButton("Attaquer");
		boutonAttaquer.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
            	SimpleFight.fight(monHero, monEnnemi);
            }
        });      
		JButton boutonRamasser = new JButton("Ramasser");
		JButton boutonUtiliser = new JButton("Utiliser");
		panelActions.add(boutonAttaquer);
		panelActions.add(boutonRamasser);
		panelActions.add(boutonUtiliser);
		
		this.add(panelHero);
		this.add(panelEnnemi);
		this.add(scrollActions);
		this.add(panelActions);
		
		this.setTitle("Finley");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(400, 300));
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
	
	public void actualizeInformations(Actor character){
		if (character.getClass() == monEnnemi.getClass())
		{
			labelNomEnnemi.setText(character.getName());
			labelVieEnnemi.setText(character.getLife() + "/" + character.getMaxLife() + " PV");
			labelEnergieEnnemi.setText(character.getPower() + "/" + character.getMaxPower() + " POWER");
		}
		else if (character.getClass() == monHero.getClass())
		{
			labelNom.setText(character.getName());
			labelVie.setText(character.getLife() + "/" + character.getMaxLife() + " PV");
			labelEnergie.setText(character.getPower() + "/" + character.getMaxPower() + " POWER");
		}
	}
	
	public JTextArea getTextArea() {
		return textAreaActions;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o.getClass() == monEnnemi.getClass())
		{
			actualizeInformations(monEnnemi);
		}
		else if (o.getClass() == monHero.getClass())
			actualizeInformations(monHero);
		
	}
}
