package fr.uha.ensisa.Jump.framework;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import fr.uha.ensisa.Jump.game.Avatar;
import fr.uha.ensisa.Jump.game.Level;

public class Game {

	BufferedImage background_image;
	private ArrayList<Level> levels;
	private Level currentLevel;
	
    public Game()
    {
        Framework.gameState = Framework.GameState.GAME_CONTENT_LOADING;
        
        Thread threadForInitGame = new Thread() {
            @Override
            public void run(){
                // Sets variables and objects for the game.
                initialize();
                // Load game files (images, sounds, ...)
                loadContent();
                
                Framework.gameState = Framework.GameState.PLAYING;
            }
        };
        threadForInitGame.start();
    }
    
    
   /**
     * Set variables and objects for the game.
     */
    private void initialize()
    {
    	levels = new ArrayList<Level>();
    	levels.add(new Level("resources/map/lvl1.txt"));
    	levels.add(new Level("resources/map/lvl2.txt"));
    	currentLevel = levels.get(0);
    	Statistique.initialize();
    }
    
    /**
     * Load game files - images, sounds, ...
     */
    private void loadContent()
    {
    	try {
			background_image = ImageIO.read(new File("resources/img/Backgound.PNG"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	

    }    
    
    
    /**
     * Restart game - reset some variables.
     */
    public void restartGame()
    {
        
    }
    
    
    /**
     * Update game logic.
     * 
     * @param gameTime gameTime of the game.
     * @param mousePosition current mouse position.
     */
    public void updateGame(long gameTime, Point mousePosition)
    {
    	currentLevel.upDate();
    	Statistique.update(currentLevel,gameTime);
    	//Sound.gamerunningSound.play();
    	if(Avatar.isDead){
    		Sound.playerDeath.play();
    		try { Thread.sleep(100); } catch (InterruptedException e) {e.printStackTrace();}
    		currentLevel.restart();
    	}
    	
    	if(currentLevel.isCompleted())
    		currentLevel = levels.get(1);
    	
    }
    
    /**
     * Draw the game to the screen.
     * 
     * @param g2d Graphics2D
     * @param mousePosition current mouse position.
     */
    public void draw(Graphics2D g2d, Point mousePosition)
    {
    	g2d.drawImage(background_image, 0, 0, Framework.frameWidth, Framework.frameHeight, null);
    	currentLevel.draw(g2d);
    	Statistique.draw(g2d);
    }
}
