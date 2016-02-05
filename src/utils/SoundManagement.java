package utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import sounds.Sound;

public abstract class SoundManagement {
	public static void playSound(String path){
		Sound player = new Sound(path);
		InputStream stream = new ByteArrayInputStream(player.getSamples());
		player.play(stream);
	}
}
