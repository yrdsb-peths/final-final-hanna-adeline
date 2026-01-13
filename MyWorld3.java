import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The third MyWorld class represents Level 3 of the game.
 * 
 * This world manages enemy spawning, level completion logic,
 * potion spawning, and transitions to the game over screen.
 * 
 * @author Adeline & Hanna
 * version January 2025
 */
public class MyWorld3 extends World
{
    /**
     * Track whether Level 3 have been completed.
     */
    public static boolean level3Complete = false;
    /**
     * Tracks whether the potion3 have been collected.
     */
    public static boolean potion3Collected = false;
    /**
     * Sound played when the potion spawns.
     */
    public static GreenfootSound potionSpawnedSound = new GreenfootSound("potionBubblingSound.mp3");

    /**
     * Constructor for objects of class MyWorld3.
     * 
     * Initializes the background, player character, enemies,
     * labels, and resets level state variables.
     */
    public MyWorld3()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        
        // Set booleans
        level3Complete = false;
        potion3Collected = false;
        
        // Set background
        GreenfootImage bg = new GreenfootImage("images/background/Battleground4.png");
        bg.scale(getWidth(), getHeight());
        setBackground(bg);
        
        // Create the witch object
        Witch witch = new Witch();
        addObject(witch, 120, 250);
        
        // Create the label of stage 3
        Label level3Label = new Label("Level 3", 40);
        addObject(level3Label, 300, 35);
    }
    
    private void spawnHealingPotion()
    {
        HealingPotion healingPotion = new HealingPotion();
        HealingPotion.healingPotionCollected = false;
        //addObject(healingPotion);
        potionSpawnedSound.play();
        
    }
    
    /**
     * Spawns the potion after the enemy is defeated.
     * 
     * Plays a looping sound effect when the potion appears.
     */
    private void spawnPotion3() {
        Potion3 potion3 = new Potion3();
        addObject(potion3, 500, 300);
        potionSpawnedSound.playLoop(); 
    }
    
    /**
     * Ends the game and switches to the GameOver screen.
     * 
     * Removes all objects from the world before transitioning.
     */
    public void gameOver() {
        removeObjects(getObjects(null));
        Greenfoot.setWorld(new GameOver());
    }
    
    /**
     * Resets Level 3 progress variables.
     * 
     * Called when starting a new game.
     */
    public static void resetGame()
    {
        level3Complete = false;
        potion3Collected = false;
    }
    
    /**
     * Main game loop for Level 3.
     * 
     * Checks level completion and spawns the potion when appropriate.
     */
    public void act() {
        if(level3Complete && !potion3Collected && getObjects(Potion3.class).isEmpty())
        {
            spawnPotion3();
        }
    }
}
