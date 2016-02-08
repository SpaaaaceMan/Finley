package ihm;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.swing.table.AbstractTableModel;

import items.Item;
import items.weapons.ranged.munitions.Munition;
import utils.ButtonsInventoryManagement;

@SuppressWarnings("serial")
public class ModeleDynamiqueObjet extends AbstractTableModel {
    private final List<Item> items = new ArrayList<Item>();
 
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
            	return ButtonsInventoryManagement.quantityOfItem.get(items.get(rowIndex).getName());
            default:
                return null; //Ne devrait jamais arriver
        }
    }
 
    public void addItem(Item item) {
    	/*si l'item est déjà présent dans l'inventaire*/
        if (ButtonsInventoryManagement.quantityOfItem.containsKey(item.getName())){
        	int previousQuantity = ButtonsInventoryManagement.quantityOfItem.get(item.getName());
        	ButtonsInventoryManagement.quantityOfItem.put(item.getName(), previousQuantity + 1);
        }
        else{
        	items.add(item);
        	if (item instanceof Munition)
            	ButtonsInventoryManagement.quantityOfItem.put(item.getName(), ((Munition) item).getNumber());
        	else
            	ButtonsInventoryManagement.quantityOfItem.put(item.getName(), 1);
        }
 
        fireTableRowsInserted(items.size() -1, items.size() -1);
    }
 
    public void removeItem(int rowIndex) {
        if (ButtonsInventoryManagement.quantityOfItem.get(items.get(rowIndex).getName()) == 1){
        	ButtonsInventoryManagement.quantityOfItem.remove(items.get(rowIndex).getName());
        	items.remove(rowIndex);
        }
        else{
        	int previousQuantity = ButtonsInventoryManagement.quantityOfItem.get(items.get(rowIndex).getName());
        	ButtonsInventoryManagement.quantityOfItem.put(items.get(rowIndex).getName(), previousQuantity - 1);
        }
 
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (columnIndex == 5)
			ButtonsInventoryManagement.quantityOfItem.put(items.get(rowIndex).getName(), (Integer) aValue);
		else
			super.setValueAt(aValue, rowIndex, columnIndex);
	}

	public List<Item> getItems() {
		return items;
	} 
}

