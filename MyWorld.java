import greenfoot.*;
/**
 * The first MyWorld class represents Level 1 of the game.
 * 
 * This world manages enemy spawning, level completion logic,
 * potion spawning, and transitions to the game over screen and next level.
 * 
 * @author Hanna & Adeline
 * version January 2026
 */
public class MyWorld extends World {
    /**
     * Track whether Level 1 have been completed.
     */
    public static boolean level1Complete = false;
    /**
     * Tracks whether the potion1 have been collected.
     */
    public static boolean potion1Collected = false;
    /**
     * Sound played when the potion spawns.
     */
    public static GreenfootSound potionSpawnedSound = new GreenfootSound("potionBubblingSound.mp3");
    
    public static boolean healingPotionSpawned = false;
    /**
     * Constructor for objects of class MyWorld.
     * 
     * Initializes the background, player character, enemy, labels,
     * and resets level state variables.
     */
    public MyWorld() {
        super(600, 400, 1);
        
        //Set booleans
        level1Complete = false;
        potion1Collected = false;
        
        // Set background
        GreenfootImage bg = new GreenfootImage("images/background/Battleground1.png");
        bg.scale(getWidth(), getHeight());
        setBackground(bg);
        
        // Create the witch object
        Witch witch = new Witch();
        addObject(witch, 120, 250);
        
        // Create the slime object
        Level1SlimeRed slimered = new Level1SlimeRed();
        addObject(slimered, 500, 300);
        
        // Create the label of stage 1
        Label level1Label = new Label("Level 1", 40);
        addObject(level1Label, 300, 35);
        
        // Restart the healing potion spawn at start of the level
        healingPotionSpawned = false;
    }
    
    /**
     * Spawns the potion after the enemy is defeated.
     * 
     * Plays a looping sound effect when the potion appears.
     */
    private void spawnPotion1() {
        Potion1 potion1 = new Potion1();
        addObject(potion1, 500, 290);
        potionSpawnedSound.playLoop();
    }
    
    /**
     * Spawns the healing potion for the witch to increase hp.
     */
    private void spawnHealingPotion()
    {
        HealingPotion healingPotion = new HealingPotion();
        addObject(healingPotion, 450, 90); 
        potionSpawnedSound.play();
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
     * Resets Level 1 progress variables.
     * 
     * Called when starting a new game.
     */
    public static void resetGame()
    {
        level1Complete = false;
        potion1Collected = false;
        healingPotionSpawned = false;
    }
    
    /**
     * Checks whether all enemies in Level 1 have been defeated.
     * 
     * If no enemies remain, the level is marked as complete.
     */
    public void checkLevelComplete()
    {
        if(getObjects(Level1SlimeRed.class).isEmpty())
        {
            level1Complete = true;
        }
    }
    
    /**
     * Main game loop for Level 1.
     * 
     * Checks level completion and spawns the potion when appropriate.
     */
    public void act() 
    {
        checkLevelComplete();
        
        if(level1Complete && !potion1Collected && getObjects(Potion1.class).isEmpty())
        {
            spawnPotion1();
        }
        
        // Spawn the healing potion when the hp of witch is <=2
        Witch witch = getObjects(Witch.class).get(0);
        if (witch.getHP() <= 2 && getObjects(HealingPotion.class).isEmpty() && !healingPotionSpawned)
        {
            spawnHealingPotion();
            healingPotionSpawned = true; // make sure it only spawns once
        }
    }
}
