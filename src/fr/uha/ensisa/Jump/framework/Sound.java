package fr.uha.ensisa.Jump.framework;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	
	public static final Sound gamerunningSound = new Sound("gamerunningSound.wav");
	public static final Sound playerDeath = new Sound("PlayerDeath.wav");


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
					clip.setFramePosition(0);
					clip.start();
				}
			}.start();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public void playLoop() {
		try {
			new Thread() {
				public void run() {
					clip.loop(Clip.LOOP_CONTINUOUSLY);
				}
			}.start();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
