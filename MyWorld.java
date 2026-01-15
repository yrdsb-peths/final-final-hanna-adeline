import greenfoot.*;

/**
 * The first MyWorld class represents Level 1 of the game.
 * 
 * This world manages enemy spawning, level completion logic,
 * potion spawning, and transitions to the game over screen and next level.
 * 
 * @author Hanna & Adeline
 * version December 2025
 */
public class MyWorld extends World {
    public static boolean level1Complete = false;
    public static boolean potion1Collected = false;
    
    public static GreenfootSound potionSpawnedSound = new GreenfootSound("potionBubblingSound.wav");
    public static GreenfootSound level1Sound = new GreenfootSound("level1Sound.wav");
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
        
        //Play background music
        GameOver.gameOverSound.stop();
        StoryWorld.introSound.stop();
        EndScreen.endingSound.stop();
        level1Sound.playLoop();
        
        // Restart the healing potion spawn at start of the level
        healingPotionSpawned = false;
        
        // Add image for the Attack1
        GreenfootImage img1 = new GreenfootImage("images/Fonts/Attack1_Simple.png");
        TitleImage attack1 = new TitleImage(img1, 50, 18);
        addObject(attack1, 160, 30);
        
        // Add image for the Attack1
        GreenfootImage img2 = new GreenfootImage("images/Fonts/Attack2_Simple.png");
        TitleImage attack2 = new TitleImage(img2, 50, 18);
        addObject(attack2, 160, 60);
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
     * Spawns the healing potion and plays the sound.
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
    public void gameOver() 
    {
        removeObjects(getObjects(null));
        Greenfoot.setWorld(new GameOver());
        if(potionSpawnedSound.isPlaying()) {
            potionSpawnedSound.stop();
        }
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
        if(StoryWorld.introSound.isPlaying())
        {
            StoryWorld.introSound.stop();
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
        if (MyWorld3.potionSpawnedSound.isPlaying())
        {
            MyWorld3.potionSpawnedSound.stop();
        }
        if (EndScreen.endingSound.isPlaying())
        {
             EndScreen.endingSound.stop();
        }
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
        // Determines whether to spawn the potion
        checkLevelComplete();
        if(level1Complete && !potion1Collected && getObjects(Potion1.class).isEmpty())
        {
            spawnPotion1();
        }
        if(level1Complete)
        {
            level1Sound.stop();
        }
        if(potion1Collected && potionSpawnedSound.isPlaying()) 
        {
            potionSpawnedSound.stop();
        }
        
        // Spawn healing potion if Witch HP is low
        Witch witch = getObjects(Witch.class).get(0);
        if (witch.getHP() <= 2 && getObjects(HealingPotion.class).isEmpty() && !healingPotionSpawned)
        {
            spawnHealingPotion();
            healingPotionSpawned = true; // make sure it only spawns once
        }
    }
}
