package ihm;

import java.util.ArrayList;
import java.util.List;
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
        	/*on incrémente la quantité de cet item de 1*/
        	ButtonsInventoryManagement.quantityOfItem.put(item.getName(), previousQuantity + 1);
        }
        /*si l'item n'était pas déjà dans l'inventaire*/
        else{
        	/*on l'ajoute à la liste des items*/
        	items.add(item);
        	/*si il s'agit de munitions on associe la quantité à son attribut nombre*/
        	if (item instanceof Munition)
            	ButtonsInventoryManagement.quantityOfItem.put(item.getName(), ((Munition) item).getNumber());
        	/*sinon on initialise sa quantité à 1*/
        	else
            	ButtonsInventoryManagement.quantityOfItem.put(item.getName(), 1);
        }
 
        fireTableRowsInserted(items.size() -1, items.size() -1);
    }
 
    public void removeItem(int rowIndex) {
        /*si il reste plusieurs exemplaires de cet item dans l'inventaire*/
        if (ButtonsInventoryManagement.quantityOfItem.get(items.get(rowIndex).getName()) != 1){
        	int previousQuantity = ButtonsInventoryManagement.quantityOfItem.get(items.get(rowIndex).getName());
        	/*on décrémente sa quantité de 1*/
        	ButtonsInventoryManagement.quantityOfItem.put(items.get(rowIndex).getName(), previousQuantity - 1);
        }
        /*si il ne restait plus qu'un exemplaire de cet item dans l'inventaire*/
        else{
        	/*on supprime sa quantité relative dans le treemap*/
        	ButtonsInventoryManagement.quantityOfItem.remove(items.get(rowIndex).getName());
        	/*puis on le supprime de la liste des items*/
        	items.remove(rowIndex);
        }
 
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		/*si il s'agit de la quantité*/
		if (columnIndex == 5)
			ButtonsInventoryManagement.quantityOfItem.put(items.get(rowIndex).getName(), (Integer) aValue);
		else
			super.setValueAt(aValue, rowIndex, columnIndex);
	}

	public List<Item> getItems() {
		return items;
	} 
}

