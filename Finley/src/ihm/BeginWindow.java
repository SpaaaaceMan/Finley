package ihm;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class BeginWindow extends JFrame {
	
	public BeginWindow() {
		this.setLayout(new FlowLayout());
		JTextField textName = new JTextField();
		textName.setPreferredSize(new Dimension(80, 30));
		String[] classes = {"Guerrier","Mage","Archer"};
		JComboBox<String> classChoice = new JComboBox<String>(classes);
		this.add(textName);
		this.add(classChoice);
		
		this.setTitle("Accueil");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(100, 100));
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}

}
