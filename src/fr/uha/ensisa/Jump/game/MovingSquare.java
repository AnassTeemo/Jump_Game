package fr.uha.ensisa.Jump.game;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import fr.uha.ensisa.Jump.framework.Framework;

public class MovingSquare {
	private Point tilePositon;
	private float x;
	private float y;
	private float speedY;
	private float acclerationY;
	private long time;

	private BufferedImage img_movingSquare;
	private boolean isOutOfScreen;
	private boolean isFalling;
	private boolean canFall;

	public MovingSquare(Point p) {
		tilePositon = new Point(p);
		x = (float) (p.getX() * 18);
		y = (float) (p.getY() * 18);
		speedY = 0;
		acclerationY = 0.1f;
		isFalling = false;
		isOutOfScreen = false;
		canFall = false;
		time = 0;
		loadContent();
	}

	public void initialize(Map map) {
		map.initalizeTile(tilePositon);
		x = (float) (tilePositon.getX() * 18);
		y = (float) (tilePositon.getY() * 18);
		isFalling = false;
		isOutOfScreen = false;
		canFall = false;
		speedY = 0;
		time = 0;
	}

	public void loadContent() {
		try {
			img_movingSquare = ImageIO.read(new File(
					"resources/img/movingFloor.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update(Avatar player, Map map, long gameTime) {
		if (!isOutOfScreen) {
			if (!canFall) {
				Rectangle2D rectplayer = new Rectangle2D.Float(player.getX(),
						player.getY(), 18, 18);
				Rectangle2D thisrect = new Rectangle2D.Float(x - 1, y - 1, 19,
						19);
				if (rectplayer.intersects(thisrect)) {
					canFall = true;
					time = gameTime;
				}
			} else if (gameTime - time > (0.3 * Framework.secInNanosec)) {
				if (!isFalling) {
					isFalling = true;
					map.removeTile(tilePositon);
				} else
					fall();
			}

			if (y > 340)
				isOutOfScreen = true;

		}
	}

	public void fall() {
		speedY += acclerationY;
		y += speedY;
	}

	public void draw(Graphics2D g2d) {
		if (!isOutOfScreen) {
			g2d.drawImage(img_movingSquare, (int) x, (int) y, null);
		}
	}

	public boolean isOutOfScreen() {
		return isOutOfScreen;
	}

}
