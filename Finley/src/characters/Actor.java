package characters;

import java.util.ArrayList;
import java.util.Observable;

import abilities.Ability;
import ihm.InventoryWindow;
import items.Item;
import items.weapons.Weapon;

public class Actor extends Observable{

	private String name;				//nom du personnage
	private int life;					//vie du personnage
	private int maxLife;				//vie maximum du personnage
	private int strength; 				//force physique du personnage (sans arme)
	private int power;					//mana du personnage
	private int maxPower;				//mana max du personnage
	private boolean isDead = false;		//le personnage est-il mort (true) ou toujours en vie (false)
	private double maxWeight = 10;		//le poids maximum que le personage est capable de porter
	private double weight = 0;			//le poids que porte actuellement le personnage
	private Weapon weaponEquiped = null;		//l'arme dont est �quip� le personnage
	private ArrayList<Item> inventory = new ArrayList<Item>();			//repr�sente l'inventaire du perspnnage
	private ArrayList<Ability> abilities = new ArrayList<Ability>();	//repr�sente les capacit�s du personnage
	
	public Actor(String name, int maxLife, int strength, int maxPower, double maxWeight) {
		super();
		this.name = name;
		this.maxLife = maxLife;
		this.life = maxLife;
		this.strength = strength;
		this.maxPower = maxPower;
		this.power = maxPower;
		this.maxWeight = maxWeight;
	}
	
	public void pickUpItem(Item item){
		if (weight + item.getWeight() <= maxWeight){
			weight += item.getWeight();
			this.inventory.add(item);
			item.setOwner(this);
			setChanged();
			notifyObservers();
			System.out.println(this.getName() + " ramasse " + item.getName());
		}
		else 
			System.out.println("Vous ne pouvez pas porter plus de poids");
	}
	
	public void addAbility(Ability newAbility){
		abilities.add(newAbility);
		setChanged();
		notifyObservers();
	}
	
	public void removeAbility(Ability abilityToRemove){
		abilities.remove(abilityToRemove);
		setChanged();
		notifyObservers();
	}
	
	public void useAbility(Ability ability, Actor target){
		ability.activate(this, target);
	}
	
	public void dropItem(Item item){
		item.setOwner(null);
		weight -= item.getWeight();
		setChanged();
		notifyObservers(item);
		System.out.println(this.getName() + " l�che " + item.getName());
	}

	public void attack(Actor characterAttacked){
		if (weaponEquiped == null)
			characterAttacked.looseLife(strength);
		else
			this.getWeapon().attack(characterAttacked);
		System.out.println(this.getName() + " attaque " + characterAttacked.getName());
	}
	
	public void heal(Actor characterToHeal, int points){
		int lifeBeforeHeal = characterToHeal.getLife();
		characterToHeal.earnLife(points);
		if (characterToHeal == this)
			System.out.println(this.getName() + " se soigne de " + 
		(this.getLife() - lifeBeforeHeal) + " points de vie");
		else
			System.out.println(this.getName() + " soigne " + characterToHeal.getName() + " de " + 
		(characterToHeal.getLife() - lifeBeforeHeal) + " points de vie");
	}

	public void earnLife(int points){
		if (life + points > maxLife)
			this.setLife(maxLife);
		else
			this.setLife(life + points);
	}
	
	public void looseLife(int points){
		if (this.isDead())
			System.out.println(this.getName() + " est d�j� mort, on ne peut plus le tuer");
		else if (life - points <= 0){
			this.setLife(0);
			isDead = true;
			System.out.println(this.getName() + " est mort");
		}
		else
			this.setLife(life - points);
	}
	
	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
		setChanged();
		notifyObservers(this);
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
		setChanged();
		notifyObservers();
	}

	@Override
	public String toString() {
		return "Character [name=" + name + ", life=" + life + ", maxLife=" + maxLife + ", strength=" + strength
				+ ", isDead=" + isDead + ", maxWeight=" + maxWeight + ", weight=" + weight + ", weapon=" + weaponEquiped + "]";
	}

	public Weapon getWeapon() {
		return weaponEquiped;
	}

	public void setWeapon(Weapon weapon) {
		if (weapon == this.weaponEquiped)
			weaponEquiped = null;
		else
			this.weaponEquiped = weapon;
		setChanged();
		notifyObservers();
	}

	public String getName() {
		return name;
	}

	public boolean isDead() {
		return isDead;
	}

	public ArrayList<Item> getInventory() {
		return inventory;
	}

	public ArrayList<Ability> getAbilities() {
		return abilities;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
		setChanged();
		notifyObservers();
	}

	public int getMaxPower() {
		return maxPower;
	}

	public int getMaxLife() {
		return maxLife;
	}

	public double getMaxWeight() {
		return maxWeight;
	}

	public double getWeight() {
		return weight;
	}
}
