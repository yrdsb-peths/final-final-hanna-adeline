import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The EndScreen class displays the win screen of the game.
 * 
 * This world is used to present the win screen if the
 * player-controlled witch defeats all the monsters and collect
 * all the potions.
 * 
 * @author Hanna & Adeline
 * version January 2026
 */
public class EndScreen extends World
{

    /**
     * Constructor for objects of class StoryWorld.
     * 
     * Initializes the background and title images.
     */
    public EndScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        
        // Set background
        GreenfootImage bg = new GreenfootImage("images/background/Win.png");
        bg.scale(getWidth(), getHeight());
        setBackground(bg);
    }
}
