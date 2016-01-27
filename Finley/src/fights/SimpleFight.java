package fights;
import characters.Actor;

public abstract class SimpleFight {
	
	public static void fight(Actor character1, Actor character2){
		if (character1.isDead())
			System.out.println(character2.getName() + " a gagné le combat contre " + character1.getName());
		else if(character2.isDead())
			System.out.println(character1.getName() + " a gagné le combat contre " + character2.getName());
		else
		{
			character1.attack(character2);
			System.out.println(character1.toString());
			System.out.println(character2.toString());
			fight(character2, character1);
		}
	}
}
