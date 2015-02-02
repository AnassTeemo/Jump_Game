package fr.uha.ensisa.Jump.game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Cannon {
	
	Missile missile;
	
	private int x;
	private int y;	
	private BufferedImage img_cannon;

	private boolean canShoot;
	
	public Cannon(){
		this.initialize();
		this.loadContent();
	}

	private void loadContent() {
		try {
			img_cannon = ImageIO.read(new File("resources/img/Cannon.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void initialize() {
		x = 450;
		y = 20;
		missile = new Missile(x, y);
		canShoot = false;
		
	}
	
	public void update(Avatar player,Map map){
		float a = (player.getY() - this.y)/(player.getX() - this.x);
		
		if(canShoot)
			missile.update(player.getX(),player.getY());
		else
			missile.update();
		
	}
	
	public void draw(Graphics2D g2d){
		g2d.drawImage(img_cannon, x, y, null);
		missile.draw(g2d);
	}
}
