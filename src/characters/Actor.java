package characters;

import java.util.ArrayList;
import java.util.Observable;

import abilities.Ability;
import ihm.GroundInventory;
import items.Item;
import items.weapons.Weapon;
import items.wearables.Wearable;
import utils.ButtonsInventoryManagement;

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
	private ArrayList<Wearable> armorSet = new ArrayList<Wearable>(); 	//les habits/armures du personnage

	private ArrayList<Item> inventory = new ArrayList<Item>();			//repr�sente l'inventaire du perspnnage
	private ArrayList<Ability> abilities = new ArrayList<Ability>();	//repr�sente les capacit�s du personnage
	
	public Actor(String name, int maxLife, int strength, int maxPower, double maxWeight) {
		super();
		this.name      = name;
		this.maxLife   = maxLife;
		this.life      = maxLife;
		this.strength  = strength;
		this.maxPower  = maxPower;
		this.power     = maxPower;
		this.maxWeight = maxWeight;
	}
	
	public Actor (Actor actor) {
		super();
		this.name      = actor.getName();
		this.maxLife   = actor.getMaxLife();
		this.life      = actor.getLife();
		this.strength  = actor.getStrength();
		this.maxPower  = actor.getMaxPower();
		this.power     = actor.getPower();
		this.maxWeight = actor.getMaxWeight();
		this.isDead    = actor.isDead();
		this.inventory = actor.getInventory();
		for (Item i : this.inventory) {//On doit changer l'owner des items une fois que l'on a recopi� l'inventaire
			i.setOwner(this);
		}
		this.weaponEquiped = actor.getWeapon();
	}
	
	public double arrondir(double value){
		return Math.round(value * 100) / 100;
	}
	
	public void pickUpItem(Item item){
		if (weight + item.getWeight() <= maxWeight){
			weight += arrondir(item.getWeight());
			this.inventory.add(item);
			item.setOwner(this);
			ButtonsInventoryManagement.initialiserListButtonItem(item);
			setChanged();
			notifyObservers(item);
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
		weight -= arrondir(item.getWeight());
		inventory.remove(item);
		GroundInventory.addItemToGround(item);
		setChanged();
		notifyObservers(item);
		System.out.println(this.getName() + " l�che " + item.getName());
		for (Item i: inventory)
			System.out.println(i.getName());
	}

	public void attack(Actor characterAttacked){
		if (weaponEquiped == null)
			characterAttacked.looseLife(strength);
		else
			this.getWeapon().attack(characterAttacked);
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
			System.out.println(this.getName() + " re�oit " + life + " d�g�ts.");
			this.setLife(0);
			isDead = true;		
			System.out.println(this.getName() + " est mort");
		}
		else {
			this.setLife(life - points);
			System.out.println(this.getName() + " reçoit " + points + " dégâts.");
		}
	}
	
	public void addWearable (Wearable wearable) {
		this.armorSet.add(wearable);
		setChanged();
		notifyObservers(wearable);
		System.out.println(this.getName() + " s'équipe avec " + wearable.getName());
	}
	
	public void removeWearable (Wearable wearable) {
		this.armorSet.remove(wearable);
		setChanged();
		notifyObservers(wearable);
		System.out.println(this.getName() + " se déséquipe de " + wearable.getName());
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
		if (this.weaponEquiped != null) {
			this.weaponEquiped.setName(
					weaponEquiped.getName().substring(0, weaponEquiped.getName().length() - 4));
		}
		this.weaponEquiped = weapon;
		this.weaponEquiped.setName(weaponEquiped.getName() + " (E)");
		setChanged();
		notifyObservers();
	}
	
	public ArrayList<Wearable> getArmorSet() {
		return armorSet;
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
