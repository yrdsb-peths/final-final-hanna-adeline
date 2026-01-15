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
    // Label displaying the "Game Over" title
    Label gameOverLabel;
    // Label prompting the player to replay the game
    Label replayLabel;
    // Sound for gameover
    public static GreenfootSound gameOverSound = new GreenfootSound("gameOverSound.mp3");
    
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
        
        // Play background music
        MyWorld.level1Sound.stop();
        MyWorld2.level2Sound.stop();
        MyWorld3.level3Sound.stop();
        MyWorld.potionSpawnedSound.stop();
        MyWorld2.potionSpawnedSound.stop();
        MyWorld3.potionSpawnedSound.stop();

        gameOverSound.playLoop();
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
            gameOverSound.stop();
            MyWorld.resetGame();
            MyWorld2.resetGame();
            MyWorld3.resetGame();
            Greenfoot.setWorld(new MyWorld());
        }
    }
}
