package fr.uha.ensisa.Jump.game;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import fr.uha.ensisa.Jump.framework.Canvas;

public class Avatar {

	private final int WIDTH = 18;
    private final int HEIGHT = 18;
	
	 /**
	  * X coordinate of the avatar.
	  */
    public float x;
    
    /**
     * Y coordinate of the avatar.
     */
    public float y;
    
    private float speedX;
    private float speedY;
    
    /**
     * image of the Avatar.
     */
    private BufferedImage avatar_image;

    public Avatar()
    {
    	this.initialize();
    	this.loadContent();
    }
    
    private void initialize()
    {
        avatar_image = null;
        x = 180;
        y = 324;
        speedX = 0;
        speedY = 0;
    }
    
    private void loadContent()
    {
    	try {
			avatar_image = ImageIO.read(new File("resources/img/avatar.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void draw(Graphics2D g2d)
    {
    	g2d.drawImage(avatar_image,(int) x,(int)(y), null);
        //System.out.println("Avatar coordinates: " + x + " : " + y);
    }
    
    /**
     * Here we move the avatar.
     */
    public void update(Map map)
    {
    	speedX = 0;
    	speedY = 0;
        
        if(Canvas.keyboardKeyState(KeyEvent.VK_RIGHT)) {
        	int uptile = map.getrightUptiletype(x, y+3);
        	int downtile = map.getrightDowntiletype(x, y-2);
        	
        	if(uptile != 1 && downtile != 1)
        		speedX = 1.8f;
 	
        	}
        
        if(Canvas.keyboardKeyState(KeyEvent.VK_LEFT)){
        	int uptile = map.getleftUptiletype(x, y+3);
        	int downtile = map.getleftDowntiletype(x, y-2);

        	if(uptile != 1 && downtile != 1)
        		speedX = -1.8f;
        	
        	
        }
        
        if(Canvas.keyboardKeyState(KeyEvent.VK_UP))  {
        	int luptile = map.getleftUptiletype(x+2, y);
        	int ruptile = map.getrightUptiletype(x-2, y);
        	
        	if(luptile != 1 && ruptile != 1)
        		speedY = -3f;
        	
        }
        else{
        	int ldowntile = map.getleftDowntiletype(x+2, y);
        	int rdowntile = map.getrightDowntiletype(x-2, y);
        	
        	if(ldowntile != 1 && rdowntile != 1)
        		speedY = 1.8f;
        }
        
        
        

        
    }

	public void move() {
		x += speedX;
		y += speedY;
        
	}

    
}
