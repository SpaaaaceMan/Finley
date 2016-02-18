package utils;

import java.util.Random;

import characters.Actor;

public abstract class RollTheDice {
	
	private static Random rand = new Random();

	public static boolean perceptionRoll(Actor launcher){
		return (rand.nextInt(101) < launcher.getPrecision());
	}
	
	public static int strengthRoll(Actor launcher){
		return rand.nextInt(launcher.getDamages()) + 1;
	}
}
