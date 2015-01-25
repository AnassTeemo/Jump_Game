package fr.uha.ensisa.Jump.game;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import fr.uha.ensisa.Jump.framework.Canvas;
import fr.uha.ensisa.Jump.framework.Framework;

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
        x = 20;
        y = 322;
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
    public void updateInput()
    {
    	speedX = 0;
    	speedY = 0;
        // moving right or left.
        if(Canvas.keyboardKeyState(KeyEvent.VK_RIGHT))  speedX = 1.8f;
        
        if(Canvas.keyboardKeyState(KeyEvent.VK_LEFT))	speedX = -1.8f;
        
        if(Canvas.keyboardKeyState(KeyEvent.VK_UP))  speedY = -3f;
        else speedY = 1.8f;
        
    }

	public void move() {
		x += speedX;
		y += speedY;
        
        if( this.x > 784 ) this.x = 784;
        if( this.x < 0) this.x = 0;
        if( this.y > 322){ this.y = 322; }
        if( this.y < 0)	this.y = 0;
		
	}

	public Rectangle getNextRectangle() {
		return new Rectangle((int)(x+speedX), (int)(y+speedY), this.WIDTH, this.HEIGHT);
	}
    
}
