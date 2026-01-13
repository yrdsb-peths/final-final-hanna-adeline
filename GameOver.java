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
        
        // Set background
        GreenfootImage bg = new GreenfootImage("images/background/GameOver.png");
        bg.scale(getWidth(), getHeight());
        setBackground(bg);
        
        // Add image for the title
        GreenfootImage img = new GreenfootImage("images/Fonts/GameOverTitle.png");
        TitleImage gameOver = new TitleImage(img, 450, 35);
        addObject(gameOver, 300, 330);
    }
    
    public void act()
    {
        //Start the game if the user presses the space bar
        if(Greenfoot.isKeyDown("space"))
        {
            MyWorld.resetGame();
            MyWorld2.resetGame();
            MyWorld3.resetGame();
            Greenfoot.setWorld(new MyWorld());
        }
        if(MyWorld.potionSpawnedSound.isPlaying())
        {
            MyWorld.potionSpawnedSound.stop();
        }
        if(MyWorld2.potionSpawnedSound.isPlaying())
        { 
            MyWorld2.potionSpawnedSound.stop();
        }
        if(MyWorld3.potionSpawnedSound.isPlaying())
        {
            MyWorld3.potionSpawnedSound.stop();
        }
    }
}
