package fr.uha.ensisa.Jump.framework;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	
	public static final Sound gamerunningSound = new Sound("gamerunningSound.wav");


	private Clip clip;

	private Sound(String name) {
		try {
			clip = AudioSystem.getClip();
	        AudioInputStream inputStream = AudioSystem.getAudioInputStream(
	        		this.getClass().getClassLoader().getResource("sounds/" + name));
	        clip.open(inputStream);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public void play() {
		try {
			new Thread() {
				public void run() {
					clip.start();
				}
			}.start();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
