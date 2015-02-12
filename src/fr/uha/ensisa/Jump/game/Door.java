package fr.uha.ensisa.Jump.game;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Door {
	
	private float x;
	private float y;
	private BufferedImage door_img;
	
	
	public Door(float x, float y) {
		this.initialize(x, y);
		this.loadContent();
	}

	public void initialize(float x, float y){
		this.x = x;
		this.y = y;
	}

	public void loadContent(){
		try {
			door_img = ImageIO.read(this.getClass().getClassLoader().getResource("img/door.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void update(Level lvl, Avatar player){
		Rectangle2D rectPlayer = new Rectangle2D.Float(player.getX(), player.getY(), 18, 18);
		Rectangle2D rectDoor = new Rectangle2D.Float(this.x+10, this.y+10, 10, 10);
		
		if(rectPlayer.intersects(rectDoor)){
			lvl.setCompleted(true);
		}
	}
	
	public void draw(Graphics2D g2d){
		g2d.drawImage(door_img, (int)x, (int)y, null);
	}
}
