package fr.uha.ensisa.Jump.game;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import fr.uha.ensisa.Jump.framework.Canvas;
import fr.uha.ensisa.Jump.framework.Framework;

public class Avatar {

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
    
    private boolean jumping;
    private boolean jumped;
    private boolean canJump;
    private int jumpCount;
    private int maxJump;

    
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
        jumping = false;
        jumped = false;
        canJump = true;
        jumpCount = 0;
        maxJump = 2;
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
        System.out.println("Avatar coordinates: " + x + " : " + y);
    }
    
    /**
     * Here we move the avatar.
     */
    public void update()
    {
        // moving right or left.
        if(Canvas.keyboardKeyState(KeyEvent.VK_RIGHT))  speedX = 1.8f;
        else speedX = 0;
        
        if(Canvas.keyboardKeyState(KeyEvent.VK_LEFT))	speedX = -1.8f;
        
        if(Canvas.keyboardKeyState(KeyEvent.VK_UP)){
        	if( jumpCount < maxJump && y > 280 && canJump ){
        		speedY = -5;
        		jumping = true;
        		jumped = true;
        	}
        	else if( y>230 && canJump && jumpCount == 1){
        		speedY = -5;
        		jumping = true;
        		jumped = true;
        	}
        	else{
        		canJump = false;
        		//speedY += 0.5f;
        	}
        	}
        else{
        	if(jumped)
        		jumpCount++;
        	jumped = false;
        	canJump = true;
        	//speedY += 0.5f;
        }
      
        if(jumping){
        	speedY += 0.7f;
        }
        
        x+=speedX;
        y+=speedY;
        
        if( this.x > 784 ) this.x = 784;
        if( this.x < 0) this.x = 0;
        if( this.y > 322){ this.y = 322; jumping = false; jumpCount = 0; }
        if( this.y < 0)	this.y = 0;
        
    }
    
}
