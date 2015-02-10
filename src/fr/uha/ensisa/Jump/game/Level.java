package fr.uha.ensisa.Jump.game;

import java.awt.Graphics2D;

import fr.uha.ensisa.Jump.framework.Sound;

public class Level {

	private Avatar avatar;
	private Map map;
	private Cannon cannon;
	private Door exitDoor;
	private MovingSquares movingSquares;
	
	private boolean completed;

	public Level(String lvl) {
		completed = false;
		avatar = new Avatar();
		map = new Map(lvl);
		cannon = new Cannon();
		exitDoor = new Door(742, 312);
		movingSquares = new MovingSquares(map.getMovingSqaures());
	}

	public void upDate(long gameTime) {
		if (Avatar.isDead) {
			avatar.setX(180);
			avatar.setY(324);
			Sound.playerDeath.play();
		
			try {
				Thread.sleep(130);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.restart();
		}
		avatar.update(map);
		avatar.move();
		exitDoor.update(this, avatar);
		cannon.update(avatar, map);
		movingSquares.update(avatar, map ,gameTime);
	}

	public void draw(Graphics2D g2d) {
		map.draw(g2d);
		exitDoor.draw(g2d);
		cannon.draw(g2d);
		movingSquares.draw(g2d);
		avatar.draw(g2d);
	}

	public void restart(){
		Avatar.isDead = false;
		avatar.setX(180);
		avatar.setY(324);
		movingSquares.initialize(map);
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	
}
