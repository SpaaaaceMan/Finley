package ihm;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import characters.Actor;
import characters.ListActors;

@SuppressWarnings("serial")
public class BeginWindow extends JFrame {
	
	public BeginWindow() {
		this.setLayout(new FlowLayout());
		
		JLabel labelNomPerso = new JLabel("Nom : ");
		JTextField textName = new JTextField();
		textName.setPreferredSize(new Dimension(120, 30));
		
		JLabel labelClasse = new JLabel("Classe : ");
		String[] classes = {"Guerrier","Mage","Archer"};
		JComboBox<String> classChoice = new JComboBox<String>(classes);
		
		JButton boutonJouer = new JButton("Jouer");
		boutonJouer.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
            	Actor hero = new Actor("Julien", 100, 5, 5, 100);
            	Actor monstre = ListActors.getActor(5);
                TestWindow fenetre = new TestWindow(hero, monstre);
            }
        });      
		
		this.add(labelNomPerso);
		this.add(textName);
		this.add(labelClasse);
		this.add(classChoice);
		this.add(boutonJouer);
		
		this.setTitle("Choix du personnage");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}

}
