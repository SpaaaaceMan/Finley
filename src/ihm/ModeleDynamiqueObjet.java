package ihm;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import items.Item;

@SuppressWarnings("serial")
public class ModeleDynamiqueObjet extends AbstractTableModel {
    private final List<Item> items = new ArrayList<Item>();
    private final List<Integer> quantities = new ArrayList<Integer>();
 
    private final String[] entetes = {"Icône", "Nom", "Poids", "Valeur", "Place", "Quantité"};
 
    public ModeleDynamiqueObjet() {
        super();
    }
 
    public int getRowCount() {
        return items.size();
    }
 
    public int getColumnCount() {
        return entetes.length;
    }
 
    public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }
    
    public Class<?> getColumnClass(int column)
    {
    	if (items.isEmpty())
            return Object.class;
        return getValueAt(0, column).getClass();
    }
 
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
        	case 0:
        		return items.get(rowIndex).getIcon();
            case 1:
                return items.get(rowIndex).getName();
            case 2:
                return items.get(rowIndex).getWeight();
            case 3:
                return items.get(rowIndex).getValue();
            case 4:
                return items.get(rowIndex).getPlaceOccupiedInventory();
            case 5:
            	return quantities.get(rowIndex);
            default:
                return null; //Ne devrait jamais arriver
        }
    }
 
    public void addItem(Item item) {
        items.add(item);
        quantities.add(1);
 
        fireTableRowsInserted(items.size() -1, items.size() -1);
    }
 
    public void removeItem(int rowIndex) {
        if (quantities.get(rowIndex) == 1){
        	items.remove(rowIndex);
        	quantities.remove(rowIndex);
        }
        else
        	quantities.set(rowIndex, quantities.get(rowIndex) - 1);
 
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (columnIndex == 5)
			quantities.set(rowIndex, (Integer) aValue);
		else
			super.setValueAt(aValue, rowIndex, columnIndex);
	}

	public List<Item> getItems() {
		return items;
	} 
}

