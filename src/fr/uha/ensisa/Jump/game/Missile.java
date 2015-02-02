package fr.uha.ensisa.Jump.game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Missile {

	private float x;
	private float y;
	private float startx;
	private float starty;
	private float speedX;
	private float speedY;
	private BufferedImage img_missile;
	
	public Missile(float x,float y){
		this.initialize(x, y);
		this.loadContent();
	}

	private void loadContent() {
		try {
			img_missile = ImageIO.read(new File("resources/img/missile.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void initialize(float x, float y) {
		this.x = startx = x;
		this.y = starty = y;
		this.speedX = 0;
		this.speedY = 4;
	}
	
	public void update(float x, float y){
		
	}
	
	public void update(){
		x += speedX;
		y += speedY;
		if(y>324)
			this.restartShooting();
	}
	
	public void draw(Graphics2D g2d){
		g2d.drawImage(img_missile, (int)x,(int)y, null);
	}
	
	private void restartShooting(){
		this.x = startx;
		this.y = starty;
	}
}
