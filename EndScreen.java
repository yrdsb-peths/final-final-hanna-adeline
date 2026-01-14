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
    // Sound for the ending screen
    public static GreenfootSound endingSound = new GreenfootSound("endingSound.wav");
    // Tracks if the final potion is spawned
    private boolean finalPotionSpawned = false;
    private SimpleTimer finalPotionTimer = new SimpleTimer();
    
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
        
        // Create the witch object
        Witch witch = new Witch();
        addObject(witch, 120, 250);
        
        MyWorld3.level3Sound.stop();
        StoryWorld.introSound.stop();
        endingSound.play();
        
        finalPotionTimer.mark();
    }
    
    /**
     * Spawns the potion after all level is completed.
     */
    private void spawnPotionFinal()
    {
        if (!finalPotionSpawned && finalPotionTimer.millisElapsed() >= 8000)
        {    
            PotionFinal finalPotion = new PotionFinal();
            addObject(finalPotion, 491, 244);
            finalPotionSpawned = true;
        }
    }
    
    /**
     * Act method for EndScreen.
     * Spawns one final potion after 10 seconds and dialogue.
     */
    public void act()
    {
        if(MyWorld3.level3Complete && MyWorld3.potion3Collected)
        {
            spawnPotionFinal();
        }
    }
}
