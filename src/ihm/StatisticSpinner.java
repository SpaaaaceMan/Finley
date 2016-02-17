package ihm;

import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class StatisticSpinner extends JSpinner {

	public StatisticSpinner() {
		super();
		SpinnerModel sm = new SpinnerNumberModel(5, 0, 10, 1); 
		this.setModel(sm);
	}
}
