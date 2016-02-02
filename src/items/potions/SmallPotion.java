package items.potions;

import javax.swing.ImageIcon;

public class SmallPotion extends Potion {

	public SmallPotion(double weight, double value) {
		super("Petite potion", weight, value, 10, new ImageIcon("icons/P_Red02.png", "a small healing potion"));
	}
}
