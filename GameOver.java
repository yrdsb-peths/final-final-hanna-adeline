import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameOver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOver extends World
{
    // Instances of labels
    Label gameOverLabel;
    Label replayLabel;
    
    /**
     * Constructor for objects of class GameOver.
     * 
     */
    public GameOver()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        
        // Create the gameOverLabel
        gameOverLabel = new Label("Game Over", 70);
        addObject(gameOverLabel, 300, 150);
        
        // Create the replay label
        replayLabel = new Label("Press Space to Replay", 40);
        addObject(replayLabel, 300, 240);
    }
}
