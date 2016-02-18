package characters;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import abilities.Ability;
import items.Item;
import items.weapons.Weapon;
import items.wearables.Wearable;
import utils.RollTheDice;

public class Actor extends Observable{

	private String name;				//nom du personnage
	private int level = 0;				//le niveau du personnage
	private int gold = 0;				//l'or que possède le personnage
	
	private int life;					//vie du personnage
	private int maxLife;				//vie maximum du personnage
		
	private int power;					//mana du personnage
	private int maxPower;				//mana max du personnage
	
	private int precision;				//la précision du personnage
	private int damages; 				//dégats du personnage (sans arme)
	private boolean isDead = false;		//le personnage est-il mort (true) ou toujours en vie (false)
	private Weapon weaponEquiped = null;//l'arme dont est �quip� le personnage
	
	private ArrayList<Wearable> armorSet = new ArrayList<Wearable>(); 	//les habits/armures du personnage
	private ArrayList<Item> inventory    = new ArrayList<Item>();		//repr�sente l'inventaire du perspnnage
	private ArrayList<Ability> abilities = new ArrayList<Ability>();	//repr�sente les capacit�s du personnage
	
	public Actor(String name, int maxLife, int maxPower) {
		super();
		this.name      = name;
		this.maxLife   = maxLife;
		this.life      = maxLife;
		this.maxPower  = maxPower;
		this.power     = maxPower;
	}
	
	public Actor (Actor actor) {
		super();
		this.name      = actor.getName();
		this.maxLife   = actor.getMaxLife();
		this.life      = actor.getLife();
		this.damages   = actor.getDamages();
		this.maxPower  = actor.getMaxPower();
		this.power     = actor.getPower();
		this.isDead    = actor.isDead();
		this.inventory = actor.getInventory();
		for (Item i : this.inventory) {//On doit changer l'owner des items une fois que l'on a recopi� l'inventaire
			i.setOwner(this);
		}
		this.weaponEquiped = actor.getWeaponEquiped();
	}
	
	public double arrondir(double value){
		return Math.round((value * 100) / 100);
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
	
	public void pickUpItem(Item item){
		
	}
	
	public void dropItem(Item item, int nbToRemove){
		
	}
	
	public void attack(Actor characterAttacked){
		if (RollTheDice.perceptionRoll(this)) {
			if (weaponEquiped == null)
				characterAttacked.looseLife(RollTheDice.strengthRoll(this));
			else
				this.getWeaponEquiped().attack(characterAttacked);
		}
		else
			System.out.println("raté");
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
			System.out.println(this.getName() + " est déjà mort, on ne peut plus le tuer");
		else if (life - points <= 0){
			System.out.println(this.getName() + " reçoit " + life + " dégâts.");
			this.setLife(0);
			isDead = true;		
			System.out.println(this.getName() + " est mort");
		}
		else {
			this.setLife(life - points);
			System.out.println(this.getName() + " reçoit " + points + " dégâts.");
		}
	}
	
	public void earnMoney(int money){
		this.gold += money;
	}
	
	public void looseMoney(int money){
		if (this.gold - money < 0)
			this.gold = 0;
		else
			this.gold -= money;
	}
	
	public void addWearable (Wearable wearable) {
		this.armorSet.add(wearable);
		setChanged();
		notifyObservers("armure");
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

	public int getDamages() {
		return damages;
	}

	public void setDamages(int damages) {
		this.damages = damages;
		setChanged();
		notifyObservers();
	}

	public void setWeaponEquiped(Weapon weapon) {
		this.weaponEquiped = weapon;
		setChanged();
		notifyObservers("arme");
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

	public int getGold() {
		return gold;
	}

	public int getLevel() {
		return level;
	}

	public Weapon getWeaponEquiped() {
		return weaponEquiped;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public void setMaxLife(int maxLife) {
		this.maxLife = maxLife;
	}

	public void setMaxPower(int maxPower) {
		this.maxPower = maxPower;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	public void setArmorSet(ArrayList<Wearable> armorSet) {
		this.armorSet = armorSet;
	}

	public void setInventory(ArrayList<Item> inventory) {
		this.inventory = inventory;
	}

	public void setAbilities(ArrayList<Ability> abilities) {
		this.abilities = abilities;
	}
	

	public int getPrecision() {
		return precision;
	}
	

	public void setPrecision(int precision) {
		this.precision = precision;
	}
}
