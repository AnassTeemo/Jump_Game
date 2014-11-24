package fr.uha.ensisa.Jump.game;

import java.awt.Graphics2D;

public class Level {
	
	private Avatar avatar;
	private Map map = new Map();;

	public Level(){
    	avatar = new Avatar();
    	map.loadMapFile("resources/map/map.txt");
	}

	public void upDate() {
		avatar.update();
	}
	
	public void draw(Graphics2D g2d)
	{
		map.draw(g2d);
		avatar.draw(g2d);
	}
	
}
