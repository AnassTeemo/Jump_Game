package fr.uha.ensisa.Jump.game;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Level {

	private Avatar avatar;
	private Map map = new Map();

	public Level() {
		avatar = new Avatar();
		map.loadMapFile("resources/map/map.txt");
	}

	public void upDate() {
		avatar.updateInput();
		if (tryToMove())
			avatar.move();
	}

	public void draw(Graphics2D g2d) {
		map.draw(g2d);
		avatar.draw(g2d);
	}

	public boolean isCollision(Rectangle r1, Rectangle r2) {

		// if (r1.intersects(r2))
		if ((r1.getX() >= r2.getX() + r2.getWidth())
				|| (r1.getX() + r1.getWidth() <= r2.getX())
				|| (r1.getY() >= r2.getY() + r2.getHeight())
				|| (r1.getY() + r1.getHeight() <= r2.getY())

		)
			return true;
		return false;
	}

	public boolean tryToMove() {
		Rectangle ravatar = avatar.getNextRectangle();

		int[][] farid = map.getMap();
		for (int i = 0; i < map.getNbTilesY(); i++) {

			for (int j = 0; j < map.getNbTilesY(); j++) {

				if (farid[i][j] == 1) {
					Rectangle rbrique = new Rectangle(i * 18, j * 18, 18, 18);
					if (isCollision(ravatar, rbrique)) {
						System.out.println("Collision");
						return false;
					}
				}
			}

		}

		return true;
	}

}
