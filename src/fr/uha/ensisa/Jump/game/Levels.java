package fr.uha.ensisa.Jump.game;

import java.io.File;

import fr.uha.ensisa.Jump.framework.Framework;


public class Levels {

	private File[] levels;
	private int currentlevel;
	private int maxlevel;

	public Levels() {
		this.levels = (new File("resources/map")).listFiles();
		this.currentlevel = 0;
		this.maxlevel = this.levels.length;
	}

	public Level NextLevel() {
		if (currentlevel < maxlevel) {
			Level lvl = new Level(levels[currentlevel].toString());
			currentlevel++;
			return lvl;
		} else {
			Framework.gameState = Framework.GameState.COMPLETED;
			return null;
		}
	}

}
