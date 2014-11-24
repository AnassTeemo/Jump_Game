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
    public int x;
    
    /**
     * Y coordinate of the avatar.
     */
    public int y;

    /**
     * image of the Avatar.
     */
    private BufferedImage avatar_image;;
    
    public Avatar()
    {
    	this.Initialize();
    	this.LoadContent();
    }
    
    private void Initialize()
    {
        avatar_image = null;
        x = 300;
        y = 300;
    }
    
    private void LoadContent()
    {
    	try {
			avatar_image = ImageIO.read(new File("resources/img/avatar.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void Draw(Graphics2D g2d)
    {
    	g2d.drawImage(avatar_image, x, y, null);
        System.out.println("Avatar coordinates: " + x + " : " + y);
    }
    
    /**
     * Here we move the avatar.
     */
    public void Update()
    {
        // moving right or left.
        if(Canvas.keyboardKeyState(KeyEvent.VK_RIGHT))  x++;
        
        if(Canvas.keyboardKeyState(KeyEvent.VK_LEFT))	x--;
        
        if(Canvas.keyboardKeyState(KeyEvent.VK_UP))
            y = y - 3;
        else
            y = y + 2;
        
      //Checks if the avatar still in the area
        
        if( this.x > 784 ) this.x = 784;
        if( this.x < 0) this.x = 0;
        if( this.y > 300) this.y = 300;
        if( this.y < 0)	this.y = 0;
        
    }
    
}
