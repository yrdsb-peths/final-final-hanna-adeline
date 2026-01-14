import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The second MyWorld class represents Level 2 of the game.
 * 
 * This world manages enemy spawning, level completion logic,
 * potion spawning, and transitions to the game over screen and next level.
 * 
 * @author Adeline & Hanna
 * version January 2026
 */
public class MyWorld2 extends World
{
    /** Track whether Level 2 have been completed */
    public static boolean level2Complete = false;
    
    /** Tracks whether the potion2 have been collected */
    public static boolean potion2Collected = false;
    
    /** Indicates whether the Level 2 golem has already been spawned */
    private boolean golemSpawned = false;
    
    /** Sound played when the potion spawns */
    public static GreenfootSound potionSpawnedSound = new GreenfootSound("potionBubblingSound.mp3");
    
    /** Track if the healing potion have been spawned */
    public static boolean healingPotionSpawned = false;
    
    /**
     * Constructor for objects of class MyWorld2.
     * 
     * Initializes the background, player character, enemies,
     * labels, and resets level state variables.
     */
    public MyWorld2()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        
        //Set booleans
        level2Complete = false;
        potion2Collected = false;
        
        // Set background
        GreenfootImage bg = new GreenfootImage("images/background/Battleground3.png");
        bg.scale(getWidth(), getHeight());
        setBackground(bg);
        
        // Create the witch object
        Witch witch = new Witch();
        addObject(witch, 120, 250);
        
        // Create the label of stage 2
        Label level2Label = new Label("Level 2", 40);
        addObject(level2Label, 300, 35);
                
        // Create the slime 2 object
        Level2SlimeBlue slimered1 = new Level2SlimeBlue();
        addObject(slimered1, 500, 300);
        
        // Restart the healing potion spawn at start of the level
        healingPotionSpawned = false;
    }
    
    /**
     * Spawns the potion after the enemy is defeated.
     * 
     * Plays a looping sound effect when the potion appears.
     */
    private void spawnPotion2() {
        Potion2 potion2 = new Potion2();
        addObject(potion2, 500, 290);
        potionSpawnedSound.playLoop();
    }
    
    /**
     * Spawns the healing potion for the witch to increase hp.
     */
    private void spawnHealingPotion()
    {
        HealingPotion healingPotion = new HealingPotion();
        addObject(healingPotion, 280, 90); 
        potionSpawnedSound.play();
        healingPotionSpawned = true;
    }
    
    /**
     * Spawns the Level 2 golem after the blue slime is defeated.
     */
    private void spawnGolem()
    {
        Level2Golem golem2 = new Level2Golem();
        addObject(golem2, 500, 250);
        golemSpawned = true;
    }
    
    /**
     * Ends the game and switches to the GameOver screen.
     * 
     * Removes all objects from the world before transitioning.
     */    
    public void gameOver() 
    {
        removeObjects(getObjects(null));
        Greenfoot.setWorld(new GameOver());
    }
    
    /**
     * Resets Level 2 progress variables.
     * 
     * Called when starting a new game.
     */
    public static void resetGame()
    {
        level2Complete = false;
        potion2Collected = false;
        healingPotionSpawned = false;
    }
    
    /**
     * Checks whether all enemies in Level 2 have been defeated.
     * 
     * If no enemies remain, the level is marked as complete.
     */
    public void checkLevelComplete()
    {
        if(getObjects(Level1SlimeRed.class).isEmpty() && getObjects(Level2SlimeBlue.class).isEmpty() && getObjects(Level2Golem.class).isEmpty() && golemSpawned)
        {
            level2Complete = true;
        }
    }
    
    /**
     * Main game loop for Level 2.
     * 
     * Checks level completion and spawns the potion when appropriate.
     */
    public void act() 
    {
        checkLevelComplete();
        
        if(level2Complete && !potion2Collected && getObjects(Potion2.class).isEmpty())
        {
            spawnPotion2();
        }
        
        if(getObjects(Level2SlimeBlue.class).isEmpty() && !golemSpawned)
        {
            spawnGolem();
        }
        
        // Spawn the healing potion when the hp of witch is <=2
        if(!healingPotionSpawned) 
        {
            Witch witch = getObjects(Witch.class).get(0);
            if(witch.getHP() <= 2 && getObjects(HealingPotion.class).isEmpty()) 
            {
                spawnHealingPotion();
            }
        }
    }
}
