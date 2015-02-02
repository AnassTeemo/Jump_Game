package fr.uha.ensisa.Jump.game;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Level {

	private Avatar avatar;
	private Map map;
	private Cannon cannon;

	public Level() {
		avatar = new Avatar();
		map = new Map("resources/map/map.txt");
		cannon = new Cannon();
	}

	public void upDate() {
		avatar.update(map);
		avatar.move();
		cannon.update(avatar, map);
	}

	public void draw(Graphics2D g2d) {
		map.draw(g2d);
		avatar.draw(g2d);
		cannon.draw(g2d);
	}

	public void restart(){
		Avatar.isDead = false;
		avatar.setX(180);
		avatar.setY(324);
	}

	
}
