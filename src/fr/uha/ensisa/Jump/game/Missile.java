package fr.uha.ensisa.Jump.game;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Missile {
	
	private final float DEFAULT_SPEEDY = 4.5f;
	private final float DEFAULT_SPEEDX = 0;
	private final double SPEED_NORME = Math.sqrt(Math.pow(DEFAULT_SPEEDY, 2) + Math.pow(DEFAULT_SPEEDX, 2));
	
	private float x;
	private float y;
	private float startx;
	private float starty;
	private float speedX;
	private float speedY;
	private double adjustVect;
	private BufferedImage img_missile;
	
	private boolean canbeShoot;
	
	public Missile(float x,float y){
		this.initialize(x, y);
		this.loadContent();
	}

	private void loadContent() {
		try {
			img_missile = ImageIO.read(this.getClass().getClassLoader().getResource("img/missile.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void initialize(float x, float y) {
		this.x = startx = x;
		this.y = starty = y;
		this.speedX = DEFAULT_SPEEDX;
		this.speedY = DEFAULT_SPEEDY;
		canbeShoot = true;
	}
	
	public void update(Map map){
		x += speedX;
		y += speedY;
		if(IsCollision(map)){
			this.canbeShoot = true;
			this.restartShooting();
			}
	}
	
	public void shoot(Point2D direcVect) {
		if(direcVect != null){
			adjustVect = SPEED_NORME / (Math.sqrt(Math.pow(direcVect.getX(), 2) + Math.pow(direcVect.getY(), 2)));
			speedX = (float) (direcVect.getX() * adjustVect);
			speedY = (float) (direcVect.getY() * adjustVect);
		}
		else{
			speedX = DEFAULT_SPEEDX;
			speedY = DEFAULT_SPEEDY;
		}
		
	}
	
	public void draw(Graphics2D g2d){
		g2d.drawImage(img_missile, (int)x,(int)y, null);
	}
	
	public void restartShooting(){
		this.x = startx;
		this.y = starty;
	}
	
	private boolean IsCollision(Map map){
		int ruptile = map.getrightUptiletype(x - 9, y);
		int rdowntile = map.getrightDowntiletype(x - 9, y - 9);
		int luptile = map.getleftUptiletype(x, y);
		int ldowntile = map.getleftDowntiletype(x, y - 9);
		
		if(ruptile != 0 || rdowntile != 0 || luptile != 0 || ldowntile != 0)
			return true;
		return false;
	}

	public boolean canbeShoot() {
		return canbeShoot;
	}

	public void setCanbeShoot(boolean canbeShoot) {
		this.canbeShoot = canbeShoot;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}


}
