package fr.uha.ensisa.Jump.game;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Level {

	private Avatar avatar;
	private Map map;
	private Cannon cannon;
	private Door exitDoor;
	
	private boolean completed;

	public Level(String lvl) {
		completed = false;
		avatar = new Avatar();
		map = new Map(lvl);
		cannon = new Cannon();
		exitDoor = new Door(742, 312);
	}

	public void upDate() {
		avatar.update(map);
		avatar.move();
		exitDoor.update(this, avatar);
		cannon.update(avatar, map);
	}

	public void draw(Graphics2D g2d) {
		map.draw(g2d);
		exitDoor.draw(g2d);
		avatar.draw(g2d);
		cannon.draw(g2d);
		
	}

	public void restart(){
		Avatar.isDead = false;
		avatar.setX(180);
		avatar.setY(324);
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	
}
