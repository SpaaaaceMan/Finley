package characters;

import ihm.inventory.GroundInventory;
import items.Item;
import items.weapons.ranged.munitions.Munition;
import utils.ButtonsInventoryManagement;
import utils.ItemManagement;

public class Hero extends Actor {

	/*Les statistiques de base du héro*/
	private int strength;			//la force influence sur les dégats de base + poids porté
	private int endurance;			//l'endurance influence sur la résistance aux dégats + points de vie
	private int perception;			//la perception influence sur les chances de toucher + champ de vision
	private int agility;			//l'agilité influence sur les chances d'esquiver + vitesse de déplacement + points d'actions
	private int luck;				//la chance influence sur les chances de faire des coups critiques + loot rare
	
	private double weight = 0;		//le poids que porte actuellement le personnage
	private double maxWeight;		//le poids maximum que le personage est capable de porter
	
	private int place = 0;			//le nombre d'emplacements occupés dans l'inventaire du personnage
	private int maxPlace = 100;		//le nombre d'emplacements maximum dans l'inventaire du personnage

	
	public Hero(String name, int strength, int endurance, int perception, int agility, int luck) {
		super(name, 0, 0);
		this.strength   = strength;
		this.endurance  = endurance;
		this.perception = perception;
		this.agility    = agility;
		this.luck       = luck;
		actualizeStatistics();
	}
	
	public void actualizeStatistics(){
		/*===FORCE===*/
		this.maxWeight = 50 + this.strength * 10;
		this.setDamages(strength * 2);
		
		/*===ENDURANCE===*/
		this.setMaxLife(50 + this.endurance * 10);
		this.setLife(this.getMaxLife());
		
		/*===AGILITE===*/
		this.setMaxPower(50 + this.agility * 10);
		this.setPower(this.getMaxPower());
		
		/*===PERCEPTION===*/
		this.setPrecision(this.perception * 10);
	}

	public void pickUpItem(Item item){
		/*si l'item peut être porté par le personnage*/
		if (weight + item.getWeight() <= maxWeight && place + item.getPlaceOccupiedInventory() <= maxPlace){
			if (!ButtonsInventoryManagement.quantityOfItem.containsKey(item.getName())){
				this.getInventory().add(item);
				item.setOwner(this);
			}
			weight += arrondir(item.getWeight());
			if (item instanceof Munition)
				place += item.getPlaceOccupiedInventory() * ((Munition) item).getNumber();
			else
				place += item.getPlaceOccupiedInventory();
			ButtonsInventoryManagement.initialiserListButtonItem(item);
			ItemManagement.itemToMove = item;
			setChanged();
			notifyObservers("pickUp");
			System.out.println(this.getName() + " ramasse " + item.getName());
		}
		else {
			if (weight + item.getWeight() > maxWeight)
				System.out.println("Vous ne pouvez pas porter plus de poids!");
			else if (place +item.getPlaceOccupiedInventory() > maxPlace)
				System.out.println("Vous n'avez pas assez de place sur vous!");
		}
	}
	
	public void dropItem(Item item, int nbToRemove){
		if (ButtonsInventoryManagement.quantityOfItem.get(item.getName()) == 1){
			item.setOwner(null);
			getInventory().remove(item);
		}	
		int saveNbToRemove = nbToRemove;
		double weightToRemove = arrondir(item.getWeight());
		while(nbToRemove > 0){
			weight -= weightToRemove;
			place -= item.getPlaceOccupiedInventory();
			--nbToRemove;
		}
		GroundInventory.addItemToGround(item);
		ItemManagement.itemToMove = item;
		setChanged();
		notifyObservers(saveNbToRemove);
		System.out.println(this.getName() + " lâche " + item.getName());
	}

	public int getStrength() {
		return strength;
	}

	public int getEndurance() {
		return endurance;
	}

	public int getAgility() {
		return agility;
	}

	public int getLuck() {
		return luck;
	}

	public String getWeight() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPlace() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getMaxWeight() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getMaxPlace() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setStrength(int strength) {
		this.strength = strength;
		actualizeStatistics();
	}

	public void setEndurance(int endurance) {
		this.endurance = endurance;
		actualizeStatistics();
	}

	public void setPerception(int perception) {
		this.perception = perception;
		actualizeStatistics();
	}

	public void setAgility(int agility) {
		this.agility = agility;
		actualizeStatistics();
	}

	public void setLuck(int luck) {
		this.luck = luck;
		actualizeStatistics();
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public void setMaxWeight(double maxWeight) {
		this.maxWeight = maxWeight;
	}

	public void setPlace(int place) {
		this.place = place;
	}

	public void setMaxPlace(int maxPlace) {
		this.maxPlace = maxPlace;
	}

	@Override
	public String toString() {
		return "Hero [strength=" + strength + ", endurance=" + endurance + ", perception=" + perception + ", agility="
				+ agility + ", luck=" + luck + ", weight=" + weight + ", maxWeight=" + maxWeight + ", place=" + place
				+ ", maxPlace=" + maxPlace + "]";
	}
}
