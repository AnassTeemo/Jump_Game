package fr.uha.ensisa.Jump.framework;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import fr.uha.ensisa.Jump.game.Avatar;
import fr.uha.ensisa.Jump.game.Level;

public class Game {

	BufferedImage background_image;
	private Level level;
	
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
    	level = new Level();
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
    	level.upDate();
    	//Sound.gamerunningSound.play();
    	if(Avatar.isDead){
    		Sound.playerDeath.play();
    		try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		level.restart();
    	}
    	
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
    	level.draw(g2d);
    }
}
