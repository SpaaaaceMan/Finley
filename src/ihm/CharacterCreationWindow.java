package ihm;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import characters.Actor;
import characters.Hero;

@SuppressWarnings("serial")
public class CharacterCreationWindow extends JFrame {
	
	private JTextField name;
	
	private JSpinner strength;
	private JSpinner endurance;
	private JSpinner perception;
	private JSpinner agility;
	private JSpinner luck;
	
	private JLabel labelRemainingPoints;
	
	private int remainingPoints = 0;	//la limite est de 25 points 
	
	private int currentStrengthValue   = 5;
	private int currentEnduranceValue  = 5;
	private int currentPerceptionValue = 5;
	private int currentAgilityValue    = 5;
	private int currentLuckValue       = 5;
	public CharacterCreationWindow() {

		/*===INITIALISATION DES LABELS===*/
		JLabel labelName 	  = new JLabel("Nommez votre personnage :");
		JLabel labelStrength  = new JLabel("FORCE :");
		JLabel labelEndurance = new JLabel("ENDURANCE :");
		JLabel labelPrecision = new JLabel("PERCEPTION :");
		JLabel labelAgility   = new JLabel("AGILITÉ :");
		JLabel labelLuck      = new JLabel("CHANCE :");
		
		/*===INITIALISATION DES ATTRIBUTS===*/
		
		name 	   = new JTextField();
		strength   = new StatisticSpinner();
		endurance  = new StatisticSpinner();
		perception = new StatisticSpinner();
		agility    = new StatisticSpinner();
		luck       = new StatisticSpinner();
		
		strength.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				int value = (int) strength.getValue();
				/* si on augmente la valeur du spinner */
				if (value > currentStrengthValue && remainingPoints > 0){
					actualizeRemainingPoints(--remainingPoints);
					currentStrengthValue = value;
				}
				/* si on baisse la valeur */
				else if (value < currentStrengthValue){
					actualizeRemainingPoints(++remainingPoints);
					currentStrengthValue = value;
				}
				/*si il n'y a plus de points à répartir*/
				if (remainingPoints == 0)
					strength.getModel().setValue(currentStrengthValue);
			}
		});

		endurance.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				int value = (int) endurance.getValue();
				/* si on augmente la valeur du spinner */
				if (value > currentEnduranceValue && remainingPoints > 0){
					actualizeRemainingPoints(--remainingPoints);
					currentEnduranceValue = value;
				}
				/* si on baisse la valeur */
				else if (value < currentEnduranceValue){
					actualizeRemainingPoints(++remainingPoints);
					currentEnduranceValue = value;
				}
				/*si il n'y a plus de points à répartir*/
				if (remainingPoints == 0)
					endurance.getModel().setValue(currentEnduranceValue);
			}
		});
		
		perception.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				int value = (int) perception.getValue();
				/* si on augmente la valeur du spinner */
				if (value > currentPerceptionValue && remainingPoints > 0){
					actualizeRemainingPoints(--remainingPoints);
					currentPerceptionValue = value;
				}
				/* si on baisse la valeur */
				else if (value < currentPerceptionValue){
					actualizeRemainingPoints(++remainingPoints);
					currentPerceptionValue = value;
				}
				/*si il n'y a plus de points à répartir*/
				if (remainingPoints == 0)
					perception.getModel().setValue(currentPerceptionValue);
			}
		});
		
		agility.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				int value = (int) agility.getValue();
				/* si on augmente la valeur du spinner */
				if (value > currentAgilityValue && remainingPoints > 0){
					actualizeRemainingPoints(--remainingPoints);
					currentAgilityValue = value;
				}
				/* si on baisse la valeur */
				else if (value < currentAgilityValue){
					actualizeRemainingPoints(++remainingPoints);
					currentAgilityValue = value;
				}
				/*si il n'y a plus de points à répartir*/
				if (remainingPoints == 0)
					agility.getModel().setValue(currentAgilityValue);
			}
		});
		
		luck.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				int value = (int) luck.getValue();
				/* si on augmente la valeur du spinner */
				if (value > currentLuckValue && remainingPoints > 0){
					actualizeRemainingPoints(--remainingPoints);
					currentLuckValue = value;
				}
				/* si on baisse la valeur */
				else if (value < currentLuckValue){
					actualizeRemainingPoints(++remainingPoints);
					currentLuckValue = value;
				}
				/*si il n'y a plus de points à répartir*/
				if (remainingPoints == 0)
					luck.getModel().setValue(currentLuckValue);
			}
		});
		
		/*===LAYOUT DE LA FENETRE===*/
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		this.add(mainPanel);
		
		/*===POINTS A DISTRIBUER===*/
		JPanel panelPointsToDistribute = new JPanel();
		panelPointsToDistribute.setLayout(new GridLayout(2, 1));
		
		JLabel instructions = new JLabel("Vous devez répartir 25 points dans les 5 statistiques de base de "
				+ "votre nouveau personnage. Ils détermineront ses capacités.", JLabel.CENTER);
		
		labelRemainingPoints = new JLabel("Il reste " + remainingPoints + " à répartir.", JLabel.CENTER);
		
		panelPointsToDistribute.add(instructions);
		panelPointsToDistribute.add(labelRemainingPoints);
		
		mainPanel.add(panelPointsToDistribute);
		
		/*===ESPACE ENTRE LES COMPOSANTS DU LAYOUT===*/
		mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		
		/*===STATISTIQUES DU PERSONNAGE===*/
		
		JPanel statistics = new JPanel();
		{
			statistics.setLayout(new GridLayout(5, 2));
			statistics.add(labelStrength);
			statistics.add(strength);
			statistics.add(labelEndurance);
			statistics.add(endurance);
			statistics.add(labelPrecision);
			statistics.add(perception);
			statistics.add(labelAgility);
			statistics.add(agility);
			statistics.add(labelLuck);
			statistics.add(luck);
		}
		mainPanel.add(statistics);
		
		/*===ESPACE ENTRE LES COMPOSANTS DU LAYOUT===*/
		mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		
		/*===CHOIX DE LA CLASSE===*/
		JPanel panelClass = new JPanel();
		panelClass.setLayout(new FlowLayout());
		
		JLabel labelClass = new JLabel("Classe : ", JLabel.LEFT);
		String[] classes = {"Guerrier","Mage","Archer"};
		JComboBox<String> classChoice = new JComboBox<String>(classes);
		
		panelClass.add(labelClass);
		panelClass.add(classChoice);
		
		mainPanel.add(panelClass);
		
		/*===ESPACE ENTRE LES COMPOSANTS DU LAYOUT===*/
		mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		
		/*===AFFICHAGE DU NOM===*/
		JPanel panelName = new JPanel();
		{
			panelName.setLayout(new BoxLayout(panelName, BoxLayout.Y_AXIS));
			panelName.add(labelName);
			panelName.add(name);
		}
		mainPanel.add(panelName);
		
		/*===BOUTONS===*/
		JPanel panelButtons = new JPanel();
		panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.X_AXIS));
		
		JButton validate = new JButton("Donner la vie");
		JButton cancel 	 = new JButton("Avorter");
		
		validate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Actor hero = new Hero(name.getText(), currentStrengthValue, currentEnduranceValue, currentPerceptionValue, currentAgilityValue, currentLuckValue);
				System.out.println(hero.toString());
				System.out.println(hero.getPrecision());
				hero.attack(hero);
				hero.attack(hero);
				hero.attack(hero);
				hero.attack(hero);
				hero.attack(hero);
				hero.attack(hero);
				hero.attack(hero);
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0); 
			}
		});
		
		panelButtons.add(Box.createHorizontalGlue());
		panelButtons.add(validate);
		panelButtons.add(cancel);
		
		mainPanel.add(panelButtons);
		
		/*===OPTIONS DE LA FENETRE===*/
		setTitle("Création de personnage");
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	public void actualizeRemainingPoints(int remainingPoints){
		labelRemainingPoints.setText("Il reste " + remainingPoints + " à répartir.");
	}

	public static void main(String[] args) {
		CharacterCreationWindow test = new CharacterCreationWindow();
	}
}
