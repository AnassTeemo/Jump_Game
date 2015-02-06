package fr.uha.ensisa.Jump.game;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Vector;

public class MovingSquares {

	private Vector<MovingSquare> movingSquares;
	
	public MovingSquares(ArrayList<Point> ms) {
		movingSquares = new Vector<MovingSquare>();
		for (Point point : ms) {
			movingSquares.add(new MovingSquare(point));
		}
		

	}

	public void initialize(Map map) {
		for (MovingSquare movingSquare : movingSquares) {
			movingSquare.initialize(map);
		}
	}

	public void loadContent() {

	}

	public void update(Avatar player,Map map,long gameTime) {
		for (MovingSquare movingSquare : movingSquares) {
			movingSquare.update(player,map,gameTime);
		}
	}

	public void draw(Graphics2D g2d) {
		for (MovingSquare movingSquare : movingSquares) {
			movingSquare.draw(g2d);
		}
	}

}
