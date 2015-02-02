package fr.uha.ensisa.Jump.game;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import fr.uha.ensisa.Jump.framework.Canvas;

public class Avatar {

	private final int WIDTH = 18;
	private final int HEIGHT = 18;

	/**
	 * X coordinate of the avatar.
	 */
	private float x;

	/**
	 * Y coordinate of the avatar.
	 */
	private float y;

	private float speedX;
	private float speedY;

	private boolean canjump;
	private int jumpcount;
	
	public static boolean isDead;

	/**
	 * image of the Avatar.
	 */
	private BufferedImage avatar_image;

	public Avatar() {
		this.initialize();
		this.loadContent();
	}

	private void initialize() {
		avatar_image = null;
		x = 180;
		y = 320;
		speedX = 0;
		speedY = 0;
		jumpcount = 0;
		canjump = true;
		isDead = false;

	}

	private void loadContent() {
		try {
			avatar_image = ImageIO.read(new File("resources/img/avatar.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void draw(Graphics2D g2d) {
		g2d.drawImage(avatar_image, (int) x, (int) (y), null);
		// System.out.println("Avatar coordinates: " + x + " : " + y);
	}

	/**
	 * Here we move the avatar.
	 */
	public void update(Map map) {
		speedX = 0;
		// speedY = 0;

		if (Canvas.keyboardKeyState(KeyEvent.VK_RIGHT)) {
			int uptile = map.getrightUptiletype(x, y + 3);
			int downtile = map.getrightDowntiletype(x, y - 2);

			if (uptile == 0 && downtile == 0)
				speedX = 1.8f;
			//TODO 7eyd else
			else if(uptile == 2 || downtile == 2)
				isDead = true;

		}

		if (Canvas.keyboardKeyState(KeyEvent.VK_LEFT)) {
			int uptile = map.getleftUptiletype(x, y + 3);
			int downtile = map.getleftDowntiletype(x, y - 2);

			if (uptile == 0 && downtile == 0)
				speedX = -1.8f;
			//TODO 7eyd else
			else if(uptile == 2 || downtile == 2)
				isDead = true;
		}

		int ldowntile = map.getleftDowntiletype(x + 2, y);
		int rdowntile = map.getrightDowntiletype(x - 2, y);

		if (ldowntile == 0 && rdowntile == 0) {
			speedY += 0.5f;
			if (speedY > 2.0f)
				speedY = 2.0f;
		} else if (ldowntile == 1 || rdowntile == 1){
			speedY = 0;
			jumpcount = 0;
		}
		//TODO 7eyd else
		else if(ldowntile == 2 || rdowntile == 2)
			isDead = true;

		if (Canvas.keyboardKeyState(KeyEvent.VK_UP)) {

			if (canjump && jumpcount == 0) {
				speedY = -6.0f;
				canjump = false;
				jumpcount++;
				System.out.println("here1");
			}

			if (canjump && jumpcount == 1) {
				speedY = -5.0f;
				jumpcount++;
				canjump = false;
				System.out.println("here2");
			}
			canjump = false;

		} else {
			canjump = true;
		}
		
		int luptile;
		int ruptile;
		float i;
		for (i = y; i > y + speedY; i--) {
			luptile = map.getleftUptiletype(x + 2, i);
			ruptile = map.getrightUptiletype(x - 2, i);
			if (luptile == 1 || ruptile == 1) {
				speedY = i - y;
				break;
			}
			//TODO 7eyd else
			else if (luptile == 2 || ruptile == 2) {
				speedY = i - y;
				isDead = true;
				break;
			}
		}

	}

	public void move() {
		x += speedX;
		y += speedY;

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
