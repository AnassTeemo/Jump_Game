package fr.uha.ensisa.Jump.game;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;

import fr.uha.ensisa.Jump.framework.LineIterator;

public class Cannon {

	Missile missile;

	private int x;
	private int y;
	private BufferedImage img_cannon;
	Point2D direcVect;

	public Cannon(Point p) {
		x = p.x;
		y = p.y;
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
		if (x != -1 && y != -1) {
			x = 450;
			y = 18;
			missile = new Missile(x, y);
			direcVect = null;
		}
	}

	public void update(Avatar player, Map map) {
		if (x != -1 && y != -1) {
			if (missile.canbeShoot()) {

				if (playerIsTargetable(player, map))
					direcVect = new Point2D.Float(player.getX() - this.x,
							player.getY() - this.y);
				else
					direcVect = null;

				missile.shoot(direcVect);
				missile.setCanbeShoot(false);

			}

			missile.update(map);

			missileKillsPlayer(player, missile);

		}
	}

	public void draw(Graphics2D g2d) {
		if (x != -1 && y != -1) {
			g2d.drawImage(img_cannon, x, y, null);
			missile.draw(g2d);
		}
	}

	public boolean playerIsTargetable(Avatar player, Map map) {
		Line2D line = new Line2D.Float(this.x, this.y, player.getX(),
				player.getY());
		Point2D current;
		for (Iterator<Point2D> iter = new LineIterator(line); iter.hasNext();) {
			current = iter.next();
			if (map.getleftUptiletype((float) current.getX(),
					(float) current.getY()) != 0)
				return false;
		}

		return true;
	}

	private void missileKillsPlayer(Avatar player, Missile missile) {
		Rectangle2D rectPlayer = new Rectangle2D.Float(player.getX(),
				player.getY(), 18, 18);
		Rectangle2D rectMissile = new Rectangle2D.Float(missile.getX(),
				missile.getY(), 9, 9);

		if (rectPlayer.intersects(rectMissile)) {
			Avatar.isDead = true;
			missile.setCanbeShoot(true);
			missile.restartShooting();
		}

	}

}
