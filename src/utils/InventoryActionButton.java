package utils;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class InventoryActionButton extends JButton{

	public InventoryActionButton(String text) {
		super(text);
		this.setBackground(new Color(121, 62, 30));
		this.setForeground(new Color(183, 180, 98));
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				setBackground(new Color(121, 62, 30));
				setForeground(new Color(183, 180, 98));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				setBackground(new Color(241, 204, 55));
				setForeground(Color.gray);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		});
	}
}
