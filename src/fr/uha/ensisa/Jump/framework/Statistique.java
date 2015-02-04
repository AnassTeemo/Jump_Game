package fr.uha.ensisa.Jump.framework;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import fr.uha.ensisa.Jump.game.Avatar;
import fr.uha.ensisa.Jump.game.Level;

public class Statistique {
	private static long gametime;
	private static int numberOfDeath;
	private static int currentLvl;
	private static Font font;
	
	public static void initialize(){
		font = new Font("monospaced", Font.BOLD, 18);
		gametime = 0;
		numberOfDeath = 0;
		currentLvl = 1;
	}
	
	public static void update(Level lvl,long gametime){
		Statistique.gametime = gametime;
		if(Avatar.isDead)
			Statistique.numberOfDeath++;
		if(lvl.isCompleted())
			Statistique.currentLvl++;
		
	}
	
	public static void draw(Graphics2D g2d){
        g2d.setFont(font);
        g2d.setColor(Color.green);
		g2d.drawString("Time: " + formatTime(gametime), 40, 40);
		g2d.drawString("Deaths: " + numberOfDeath, 40, 55);
		g2d.drawString("Level: " + currentLvl, 40, 65);		
	}
	
    private static String formatTime(long time){
        // Given time in seconds.
        int sec = (int)(time / Framework.milisecInNanosec / 1000);

        // Given time in minutes and seconds.
        int min = sec / 60;
        sec = sec - (min * 60);

        String minString, secString;

        if(min <= 9)
            minString = "0" + Integer.toString(min);
        else
            minString = "" + Integer.toString(min);

        if(sec <= 9)
            secString = "0" + Integer.toString(sec);
        else
            secString = "" + Integer.toString(sec);

        return minString + ":" + secString;
}

}
