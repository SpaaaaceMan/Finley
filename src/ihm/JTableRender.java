package ihm;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import utils.ColorManagement;

@SuppressWarnings("serial")
public class JTableRender extends DefaultTableCellRenderer {
	 
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        System.out.println("yolo");
    	Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (row == InventoryWindow.selectedRow){
        	component.setFont(new Font("Courier New", Font.BOLD, 16));
        }
        else{
        	component.setFont(new Font("Courier New", Font.PLAIN, 12));
        }
        return component;
    }
}
