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
    
    GreenfootSound gameOverSound = new GreenfootSound("gameOverSound.mp3");
    
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
        
        // Play background music
        gameOverSound.playLoop();

    }
    
    public void act()
    {
        //Start the game if the user presses the space bar
        if(Greenfoot.isKeyDown("space"))
        {
            gameOverSound.stop();
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
        if(MyWorld.level1Sound.isPlaying())
        {
            MyWorld.level1Sound.stop();
        }
        if(MyWorld2.level2Sound.isPlaying())
        {
            MyWorld2.level2Sound.stop();
        }
        if(MyWorld3.level3Sound.isPlaying())
        {
            MyWorld3.level3Sound.stop();
        }
    }
}
