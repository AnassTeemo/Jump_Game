package fr.uha.ensisa.Jump.game;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import fr.uha.ensisa.Jump.framework.Animation;
import fr.uha.ensisa.Jump.framework.Canvas;

public class Avatar {

	// private final int WIDTH = 18;
	// private final int HEIGHT = 18;

	/**
	 * X coordinate of the avatar.
	 */
	private float x;

	private static enum Mouvement_State {
		STOPED, RIGHT, LEFT, JUMP
	}

	private Mouvement_State mvState;
	private String previous_state = "R";

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
	private BufferedImage avatar_image_L;
	private BufferedImage avatar_image_J_R;
	private BufferedImage avatar_image_J_L;
	private BufferedImage annimation_image_right;
	private BufferedImage annimation_image_left;

	private Animation avatar_annim_R;
	private Animation avatar_annim_L;

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
		mvState = Mouvement_State.STOPED;

	}

	private void loadContent() {
		try {
			avatar_image = ImageIO.read(new File("resources/img/avatar_S.png"));
			avatar_image_L = ImageIO.read(new File(
					"resources/img/avatar_S_L.png"));
			avatar_image_J_R = ImageIO.read(new File(
					"resources/img/avatar_J_R.png"));
			avatar_image_J_L = ImageIO.read(new File(
					"resources/img/avatar_J_L.png"));
			annimation_image_right = ImageIO.read(new File(
					"resources/img/annimation_image_right.png"));
			annimation_image_left = ImageIO.read(new File(
					"resources/img/annimation_image_left.png"));
			avatar_annim_R = new Animation(annimation_image_right, 18, 18, 30,
					25, true, (int) x, (int) y, 0);
			avatar_annim_L = new Animation(annimation_image_left, 18, 18, 30,
					25, true, (int) x, (int) y, 0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void draw(Graphics2D g2d) {
		switch (mvState) {
		case STOPED:
			if (previous_state == "R")
				g2d.drawImage(avatar_image, (int) x, (int) (y), null);
			else
				g2d.drawImage(avatar_image_L, (int) x, (int) (y), null);
			break;
		case RIGHT:
			if (isDead) {
				Map.setChookBlad(x + 18, y);
			}
			avatar_annim_R.Draw(g2d);
			break;
		case LEFT:
			if (isDead) {
				Map.setChookBlad(x, y);
			}
			avatar_annim_L.Draw(g2d);
			break;
		case JUMP:
			if (isDead) {
				Map.setChookBlad(x, y);
			}
			if (previous_state == "R")
				g2d.drawImage(avatar_image_J_R, (int) x, (int) (y), null);
			else
				g2d.drawImage(avatar_image_J_L, (int) x, (int) (y), null);
			break;

		}
		if (isDead) {
			Map.setChookBlad(x, y + 18);
		}

	}

	/**
	 * Here we move the avatar.
	 */
	public void update(Map map) {
		speedX = 0;
		// speedY = 0;
		mvState = Mouvement_State.STOPED;

		if (Canvas.keyboardKeyState(KeyEvent.VK_RIGHT)) {
			mvState = Mouvement_State.RIGHT;
			int uptile = map.getrightUptiletype(x, y + 3);
			int downtile = map.getrightDowntiletype(x, y - 2);

			if (uptile == 0 && downtile == 0)
				speedX = 1.8f;
			// TODO 7eyd else
			else if (uptile == 2 || downtile == 2 || uptile == 3
					|| downtile == 3 || uptile == 4 || downtile == 4
					|| uptile == 5 || downtile == 5 || uptile == 6
					|| downtile == 6 || uptile == 7 || downtile == 7
					|| uptile == 8 || downtile == 8 || uptile == 9
					|| downtile == 9)
				isDead = true;
			previous_state = "R";

		}

		if (Canvas.keyboardKeyState(KeyEvent.VK_LEFT)) {
			mvState = Mouvement_State.LEFT;
			int uptile = map.getleftUptiletype(x, y + 3);
			int downtile = map.getleftDowntiletype(x, y - 2);

			if (uptile == 0 && downtile == 0)
				speedX = -1.8f;
			// TODO 7eyd else
			else if (uptile == 2 || downtile == 2 || uptile == 3
					|| downtile == 3 || uptile == 4 || downtile == 4
					|| uptile == 5 || downtile == 5 || uptile == 6
					|| downtile == 6 || uptile == 7 || downtile == 7
					|| uptile == 8 || downtile == 8 || uptile == 9
					|| downtile == 9)
				isDead = true;
			previous_state = "L";
		}

		int ldowntile = map.getleftDowntiletype(x + 2, y);
		int rdowntile = map.getrightDowntiletype(x - 2, y);

		if (ldowntile == 0 && rdowntile == 0) {
			speedY += 0.5f;
			if (speedY > 2.0f)
				speedY = 2.0f;
		} else if (ldowntile == 1 || rdowntile == 1 || ldowntile == 10
				|| rdowntile == 10) {
			speedY = 0;
			jumpcount = 0;
		}
		// TODO 7eyd else
		else if (ldowntile == 2 || rdowntile == 2 || ldowntile == 3
				|| rdowntile == 3 || ldowntile == 4 || rdowntile == 4
				|| ldowntile == 5 || rdowntile == 5 || ldowntile == 6
				|| rdowntile == 6 || ldowntile == 7 || rdowntile == 7
				|| ldowntile == 8 || rdowntile == 8 || ldowntile == 9
				|| rdowntile == 9)
			isDead = true;

		if (Canvas.keyboardKeyState(KeyEvent.VK_UP)) {
			mvState = Mouvement_State.JUMP;

			if (canjump && jumpcount == 0) {
				speedY = -6.0f;
				canjump = false;
				jumpcount++;
			}

			if (canjump && jumpcount == 1) {
				speedY = -5.0f;
				jumpcount++;
				canjump = false;
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
			if (luptile == 1 || ruptile == 1 || luptile == 10 || ruptile == 10) {
				speedY = i - y;
				break;
			}
			// TODO 7eyd else
			else if (luptile == 2 || ruptile == 2 || luptile == 3
					|| ruptile == 3 || luptile == 4 || ruptile == 4
					|| luptile == 5 || ruptile == 5 || luptile == 6
					|| ruptile == 6 || luptile == 7 || ruptile == 7
					|| luptile == 8 || ruptile == 8 || luptile == 9
					|| ruptile == 9) {
				speedY = i - y;
				isDead = true;
				break;
			}
		}

		avatar_annim_R.changeCoordinates((int) x, (int) y);
		avatar_annim_L.changeCoordinates((int) x, (int) y);

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
