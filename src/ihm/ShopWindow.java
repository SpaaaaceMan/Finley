package ihm;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class ShopWindow extends JFrame {

	public ShopWindow(){
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Boutique");
		this.pack();
		this.setVisible(true);
	}
}
