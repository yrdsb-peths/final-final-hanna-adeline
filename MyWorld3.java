import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The third MyWorld class represents Level 3 of the game.
 * 
 * This world manages enemy spawning, level completion logic,
 * potion spawning, and transitions to the game over screen and next level.
 * 
 * @author Hanna & Adeline
 * version November 2025
 */
public class MyWorld3 extends World
{
    public static boolean level3Complete = false;
    public static boolean potion3Collected = false;
    public static boolean reaperSpawned = false;
    
    public static GreenfootSound potionSpawnedSound = new GreenfootSound("potionBubblingSound.wav");
    public static GreenfootSound level3Sound = new GreenfootSound("level3Sound.wav");
    
    public static boolean healingPotionSpawned = false;
    
    /**
     * Constructor for objects of class MyWorld3.
     * 
     * Initializes the background, player character, enemy, labels,
     * and resets level state variables.
     */
    public MyWorld3()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        
        // Set booleans
        level3Complete = false;
        potion3Collected = false;
        reaperSpawned = false; 
        
        // Set background
        GreenfootImage bg = new GreenfootImage("images/background/Battleground4.png");
        bg.scale(getWidth(), getHeight());
        setBackground(bg);
        
        // Create the witch object
        Witch witch = new Witch();
        addObject(witch, 120, 250);
        
        //  Create the skeleton object
        Level3Skeleton skeleton = new Level3Skeleton();
        addObject(skeleton, 500, 270);
        
        // Create the label of stage 3
        Label level3Label = new Label("Level 3", 40);
        addObject(level3Label, 300, 35);
        
        //Play background music
        if(!level3Complete && MyWorld2.potion2Collected && MyWorld.potion1Collected)
        {
            level3Sound.playLoop();
            level3Sound.setVolume(100);
        }
        
        // Restart the healing potion spawn at start of the level
        healingPotionSpawned = false;
    }
    
    /**
     * Spawn the reaper when the skeleton is defeated.
     */
    private void spawnReaper()
    {
        Level3Reaper reaper = new Level3Reaper();
        addObject(reaper, 500, 270);
        reaperSpawned = true;
    }
    
    /** 
     * Spawns the healing potion and plays the sound.
     */
    private void spawnHealingPotion()
    {
        HealingPotion healingPotion = new HealingPotion();
        addObject(healingPotion, 100, 100); 
        potionSpawnedSound.play();
        healingPotionSpawned = true;
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
    public void gameOver() 
    {
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
        reaperSpawned = false;
        healingPotionSpawned = false;
        if(potionSpawnedSound.isPlaying())
        {
            potionSpawnedSound.stop();
        }
        Greenfoot.setWorld(new MyWorld3());
    }
    
    /**
     * Checks whether all enemies in Level 3 have been defeated.
     * 
     * If no enemies remain, the level is marked as complete.
     */
    public void checkLevelComplete()
    {
        if(getObjects(Level3Skeleton.class).isEmpty() && getObjects(Level3Reaper.class).isEmpty() && reaperSpawned)
        {
            level3Complete = true;
        }
    }
    
    /**
     * Main game loop for Level 3.
     * 
     * Checks level completion and spawns the potion when appropriate.
     */
    public void act() 
    {
        // Determines whether to spawn the potion
        checkLevelComplete();
        
        if(level3Complete && !potion3Collected && getObjects(Potion3.class).isEmpty())
        {
            spawnPotion3();
        }
        if(getObjects(Level3Skeleton.class).isEmpty() && !reaperSpawned)
        {
            spawnReaper();
        }
        if(potion3Collected)
        {
            potionSpawnedSound.stop();
            Greenfoot.setWorld(new EndScreen());
        }
        
        // Spawn healing potion if Witch HP is low
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
