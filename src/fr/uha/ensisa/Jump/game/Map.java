package fr.uha.ensisa.Jump.game;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

public class Map {

	private final int tileWidth = 18;
	private final int tileHeight = 18;
	private BufferedImage tileSet;
	private int nbTilesX;
	private int nbTilesY;

	private int[][] map;
//		public Point getleftDowntile(float x, float y){
//		return new Point(((int)(x/nbTilesX))*18, ((int)((y+18)/nbTilesY))*18);
//	}
//	public Point getleftUptile(float x, float y){
//		return new Point(((int)(x/nbTilesX))*18, ((int)(y/nbTilesY))*18);
//	}
//	
	public int getleftUptiletype(float x, float y){
		return map[(int)(y/18)][(int)(x/18)];
	}
	
	public int getleftDowntiletype(float x, float y){
		return map[(int)((y+18)/18)][(int)(x/18)];
	}
	
	public int getrightUptiletype(float x, float y){
		return map[(int)(y/18)][(int)((x+18)/18)];
	}
	
	public int getrightDowntiletype(float x, float y){
		return map[(int)((y+18)/18)][(int)((x+18)/18)];
	}

	public void loadMapFile(String filename)  {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(filename));
		
		String line = null;
		String[] parts = null;

		tileSet = ImageIO.read(new File("resources/img/" + reader.readLine()));
		parts = reader.readLine().split(" ");
		this.nbTilesX = Integer.valueOf(parts[0]);
		this.nbTilesY = Integer.valueOf(parts[1]);

		map = new int[nbTilesY][nbTilesX];
		int i = 0;
		while ((line = reader.readLine()) != null) {
			parts = line.split(" ");
			for (int j = 0; j < nbTilesX; j++)
				map[i][j] = Integer.valueOf(parts[j]);
			i++;

		}
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
					g2d.drawImage(tileSet, drawpositionX, drawpositionY, tileWidth, tileHeight,null);

				drawpositionX += tileWidth;
			}

			drawpositionY += tileHeight;

		}
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

}
