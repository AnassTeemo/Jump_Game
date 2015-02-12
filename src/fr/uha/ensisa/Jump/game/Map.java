package fr.uha.ensisa.Jump.game;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Map {

	private final int tileWidth = 18;
	private final int tileHeight = 18;
	private BufferedImage tileSet;
	private BufferedImage img_chouk_down;
	private BufferedImage img_chouk_up;
	private BufferedImage img_chouk_left;
	private BufferedImage img_chouk_right;
	private BufferedImage img_chouk_up_blad;
	private BufferedImage img_chouk_down_blad;
	private BufferedImage img_chouk_left_blad;
	private BufferedImage img_chouk_right_blad;
	private BufferedImage img_door;
	private int nbTilesX;
	private int nbTilesY;
	private int canonCorX;
	private int canonCorY;


	private static int[][] map;

	// public Point getleftDowntile(float x, float y){
	// return new Point(((int)(x/nbTilesX))*18, ((int)((y+18)/nbTilesY))*18);
	// }
	// public Point getleftUptile(float x, float y){
	// return new Point(((int)(x/nbTilesX))*18, ((int)(y/nbTilesY))*18);
	// }
	//

	public Map(String filename) {
		this.loadMapFile(filename);
	}

	public void loadMapFile(String filename) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(filename));

			String line = null;
			String[] parts = null;

			tileSet = ImageIO.read(new File("resources/img/"
					+ reader.readLine()));
			img_chouk_down = ImageIO.read(new File("resources/img/"
					+ reader.readLine()));
			img_chouk_up = ImageIO.read(new File("resources/img/"
					+ reader.readLine()));
			img_chouk_left = ImageIO.read(new File("resources/img/"
					+ reader.readLine()));
			img_chouk_right = ImageIO.read(new File("resources/img/"
					+ reader.readLine()));
			img_chouk_down_blad = ImageIO.read(new File("resources/img/"
					+ reader.readLine()));
			img_chouk_up_blad = ImageIO.read(new File("resources/img/"
					+ reader.readLine()));
			img_chouk_left_blad = ImageIO.read(new File("resources/img/"
					+ reader.readLine()));
			img_chouk_right_blad = ImageIO.read(new File("resources/img/"
					+ reader.readLine()));
			img_door = ImageIO.read(new File("resources/img/"
					+ reader.readLine()));
			parts = reader.readLine().split(" ");
			this.nbTilesX = Integer.valueOf(parts[0]);
			this.nbTilesY = Integer.valueOf(parts[1]);
			parts = reader.readLine().split(" ");
			this.canonCorX = Integer.valueOf(parts[0]);
			this.canonCorY = Integer.valueOf(parts[1]);
			
			map = new int[nbTilesY][nbTilesX];
			int i = 0;
			while ((line = reader.readLine()) != null) {
				parts = line.split(" ");
				for (int j = 0; j < nbTilesX; j++)
					map[i][j] = Integer.valueOf(parts[j]);
				i++;

			}
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void draw(Graphics2D g2d) {

		int drawpositionX;
		int drawpositionY = 0;

		for (int i = 0; i < nbTilesY; i++) {
			drawpositionX = 0;
			for (int j = 0; j < nbTilesX; j++) {

				if (map[i][j] == 1)
					g2d.drawImage(tileSet, drawpositionX, drawpositionY,
							tileWidth, tileHeight, null);
				else if (map[i][j] == 2)
					g2d.drawImage(img_chouk_down, drawpositionX, drawpositionY,
							tileWidth, tileHeight, null);
				else if (map[i][j] == 3)
					g2d.drawImage(img_chouk_up, drawpositionX, drawpositionY,
							tileWidth, tileHeight, null);
				else if (map[i][j] == 4)
					g2d.drawImage(img_chouk_left, drawpositionX, drawpositionY,
							tileWidth, tileHeight, null);
				else if (map[i][j] == 5)
					g2d.drawImage(img_chouk_right, drawpositionX,
							drawpositionY, tileWidth, tileHeight, null);
				else if (map[i][j] == 6)
					g2d.drawImage(img_chouk_down_blad, drawpositionX,
							drawpositionY, tileWidth, tileHeight, null);
				else if (map[i][j] == 7)
					g2d.drawImage(img_chouk_up_blad, drawpositionX,
							drawpositionY, tileWidth, tileHeight, null);
				else if (map[i][j] == 8)
					g2d.drawImage(img_chouk_left_blad, drawpositionX,
							drawpositionY, tileWidth, tileHeight, null);
				else if (map[i][j] == 9)
					g2d.drawImage(img_chouk_right_blad, drawpositionX,
							drawpositionY, tileWidth, tileHeight, null);
				else if (map[i][j] == 11)
					g2d.drawImage(img_door, drawpositionX,
							drawpositionY-18, 36, 36, null);

				drawpositionX += tileWidth;
			}

			drawpositionY += tileHeight;

		}
	}

	public ArrayList<Point> getMovingSqaures() {
		ArrayList<Point> tmp = new ArrayList<Point>();
		for (int i = 0; i < nbTilesY; i++)
			for (int j = 0; j < nbTilesX; j++)
				if (map[i][j] == 10)
					tmp.add(new Point(j, i));
		return tmp;
	}

	public void removeTile(Point p) {
		map[(int) p.getY()][(int) p.getX()] = 0;
	}

	public void initalizeTile(Point p) {
		map[(int) p.getY()][(int) p.getX()] = 10;
	}

	public int getleftUptiletype(float x, float y) {
		return map[(int) (y / 18)][(int) (x / 18)];
	}

	public static void setChookBlad(float x, float y) {
		switch(map[(int) (y / 18)][(int) (x / 18)]){
		case 2:
			System.out.println("Chook DOWN");
			map[(int) (y / 18)][(int) (x / 18)] = 6;
			break;
		case 3:
			System.out.println("Chook UP");
			map[(int) (y / 18)][(int) (x / 18)] = 7;
			break;
		case 4:
			System.out.println("Chook LEFT");
			map[(int) (y / 18)][(int) (x / 18)] = 8;
			break;
		case 5:
			System.out.println("Chook RIGHT");
			map[(int) (y / 18)][(int) (x / 18)] = 9;
			break;
		}
		
	}

	public int getleftDowntiletype(float x, float y) {
		return map[(int) ((y + 18) / 18)][(int) (x / 18)];
	}

	public int getrightUptiletype(float x, float y) {
		return map[(int) (y / 18)][(int) ((x + 18) / 18)];
	}

	public int getrightDowntiletype(float x, float y) {
		return map[(int) ((y + 18) / 18)][(int) ((x + 18) / 18)];
	}

	public int[][] getMap() {
		return map;
	}

	public int getNbTilesX() {
		return nbTilesX;
	}

	public int getNbTilesY() {
		return nbTilesY;
	}
	
	public Point getCanonCor(){
		return new Point(this.canonCorX,this.canonCorY);
	}

}
