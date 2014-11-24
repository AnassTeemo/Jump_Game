package fr.uha.ensisa.Jump.framework;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.prefs.BackingStoreException;

import javax.imageio.ImageIO;

import fr.uha.ensisa.Jump.game.Avatar;

/**
 * Actual game.
 * 
 * @author www.gametutorial.net
 */

public class Game {

	BufferedImage background_image;
	private Avatar avatar;
	
    public Game()
    {
        Framework.gameState = Framework.GameState.GAME_CONTENT_LOADING;
        
        Thread threadForInitGame = new Thread() {
            @Override
            public void run(){
                // Sets variables and objects for the game.
                Initialize();
                // Load game files (images, sounds, ...)
                LoadContent();
                
                Framework.gameState = Framework.GameState.PLAYING;
            }
        };
        threadForInitGame.start();
    }
    
    
   /**
     * Set variables and objects for the game.
     */
    private void Initialize()
    {
    	avatar = new Avatar();
    }
    
    /**
     * Load game files - images, sounds, ...
     */
    private void LoadContent()
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
    public void RestartGame()
    {
        
    }
    
    
    /**
     * Update game logic.
     * 
     * @param gameTime gameTime of the game.
     * @param mousePosition current mouse position.
     */
    public void UpdateGame(long gameTime, Point mousePosition)
    {
        avatar.Update();
    }
    
    /**
     * Draw the game to the screen.
     * 
     * @param g2d Graphics2D
     * @param mousePosition current mouse position.
     */
    public void Draw(Graphics2D g2d, Point mousePosition)
    {
    	g2d.drawImage(background_image, 0, 0, Framework.frameWidth, Framework.frameHeight, null);
    	avatar.Draw(g2d);
    }
}
