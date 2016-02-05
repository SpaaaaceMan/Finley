package maps;

import java.util.ArrayList;

import maps.places.Place;

public class Map {

	private ArrayList<ArrayList<Place>> map;
	
	public Map (int size) {
		map = new ArrayList<ArrayList<Place>>(size);
		
		for (int i = 0; i < size; ++i) {
			ArrayList<Place> rows = new ArrayList<Place>(size);
			
			for (int j = 0; j < size; ++j) {
				rows.add(new Place("Nowhere", "Wood", true));
			}
			
			map.add(rows);
		}
	}

	public ArrayList<ArrayList<Place>> getMap() {
		return map;
	}



	@Override
	public String toString() {
		String strToReturn = "Map : \n";
		
		for (int i = 0; i < map.size() + 4; ++i) {
			strToReturn += "-";
		}
		strToReturn += "\n";
		
		for (int i = 0; i < map.size(); ++i) {
			strToReturn += "| ";
			for (int j = 0; j < map.get(i).size(); ++j) {
				strToReturn += map.get(i).get(j).getType().charAt(0);
			}
			strToReturn += " |\n";
		}
		
		for (int i = 0; i < map.size() + 4; ++i) {
			strToReturn += "-";
		}
		
		return strToReturn;
	}
	
	
}
