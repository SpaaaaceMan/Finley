package characters;

import java.util.ArrayList;

import abilities.Ability;
import items.Item;
import items.weapons.Weapon;

public class Actor {

	private String name;				//nom du personnage
	private int life;					//vie du personnage
	private int maxLife;				//vie maximum du personnage
	private int strength; 				//force physique du personnage (sans arme)
	private int power;					//mana du personnage
	private int maxPower;				//mana max du personnage
	private boolean isDead = false;		//le personnage est-il mort (true) ou toujours en vie (false)
	private double maxWeight = 10;		//le poids maximum que le personage est capable de porter
	private double weight = 0;			//le poids que porte actuellement le personnage
	private Weapon weapon;				//l'arme dont est équipé le personnage
	private ArrayList<Item> inventory = new ArrayList<Item>();
	private ArrayList<Ability> abilities = new ArrayList<Ability>();
	
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
			System.out.println(this.getName() + " ramasse " + item.getName());
		}
		else 
			System.out.println("Vous ne pouvez pas porter plus de poids");
	}
	
	public void addAbility(Ability newAbility){
		abilities.add(newAbility);
	}
	
	public void removeAbility(Ability abilityToRemove){
		abilities.remove(abilityToRemove);
	}
	
	public void useAbility(Ability ability, Actor target){
		ability.activate(this, target);
	}
	
	public void dropItem(Item item){
		this.inventory.remove(item);
		weight -= item.getWeight();
		System.out.println(this.getName() + " lâche " + item.getName());
	}

	public void attack(Actor characterAttacked){
		if (weapon == null)
			characterAttacked.looseLife(strength);
		else
			characterAttacked.looseLife(weapon.getDamage());
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
			System.out.println(this.getName() + " est déjà mort, on ne peut plus le tuer");
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
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	@Override
	public String toString() {
		return "Character [name=" + name + ", life=" + life + ", maxLife=" + maxLife + ", strength=" + strength
				+ ", isDead=" + isDead + ", maxWeight=" + maxWeight + ", weight=" + weight + ", weapon=" + weapon + "]";
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
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
	}

	public int getMaxPower() {
		return maxPower;
	}
}
