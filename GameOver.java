import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The GameOver World displays the GameOver screen when the 
 * player-controlled character dies.
 * 
 * @author Hanna & Adeline
 * @version January 2026
 */
public class GameOver extends World
{
    /** 
     * Label displaying the "Game Over" title. 
     */
    Label gameOverLabel;
    /** 
     * Label prompting the player to replay the game. 
     */
    Label replayLabel;
    
    /**
     * Constructs the Game Over world.
     * 
     * Initializes the background and displays the
     * Game Over title image.
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
    
    /**
     * Executes the main loop of the Game Over world.
     * 
     * Restarts the game and resets all level data
     * when the player presses the space bar.
     */
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
