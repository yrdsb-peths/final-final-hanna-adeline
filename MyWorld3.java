import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld3 extends World
{
    public static boolean level3Complete = false;
    public static boolean potion3Collected = false;
    public static boolean reaperSpawned = false;
    
    public static GreenfootSound potionSpawnedSound = new GreenfootSound("potionBubblingSound.mp3");
    private GreenfootSound level3Sound = new GreenfootSound("level3Sound.mp3");
    
    /**
     * Constructor for objects of class MyWorld3.
     * 
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
        addObject(skeleton, 500, 300);
        
        // Create the label of stage 3
        Label level3Label = new Label("Level 3", 40);
        addObject(level3Label, 300, 35);
        
        //Play background music
        level3Sound.playLoop();
        }

    private void spawnReaper()
    {
        Level3Reaper reaper = new Level3Reaper();
        addObject(reaper, 500, 300);
        reaperSpawned = true;
    }
    
    private void spawnHealingPotion()
    {
        HealingPotion healingPotion = new HealingPotion();
        HealingPotion.healingPotionCollected = false;
        //addObject(healingPotion);
        potionSpawnedSound.play();
        
    }
    
    private void spawnPotion3() {
        Potion3 potion3 = new Potion3();
        addObject(potion3, 500, 300);
        potionSpawnedSound.playLoop(); 
    }
    
    //Removes all monsters, hp boxes and the witch when the game is over
    public void gameOver() {
        removeObjects(getObjects(null));
        Greenfoot.setWorld(new GameOver());
    }
    
    // Reset the variable when starting a new game
    public static void resetGame()
    {
        level3Complete = false;
        potion3Collected = false;
        reaperSpawned = false;
        if(potionSpawnedSound.isPlaying())
        {
            potionSpawnedSound.stop();
        }
        Greenfoot.setWorld(new MyWorld3());
    }
    
    // Method to check if the level is complete
    public void checkLevelComplete()
    {
        if(getObjects(Level1SlimeRed.class).isEmpty() && getObjects(Level2SlimeBlue.class).isEmpty() && getObjects(Level2Golem.class).isEmpty() && MyWorld2.golemSpawned && getObjects(Level3Skeleton.class).isEmpty() && getObjects(Level3Reaper.class).isEmpty() && reaperSpawned)
        {
            level3Complete = true;
        }
    }
    
    public void act() {
        checkLevelComplete();
        if(level3Complete && !potion3Collected && getObjects(Potion3.class).isEmpty())
        {
            level3Sound.stop();
            spawnPotion3();
        }
        if(getObjects(Level3Skeleton.class).isEmpty() && !reaperSpawned)
        {
            spawnReaper();
        }
        if(potion3Collected && potionSpawnedSound.isPlaying())
        {
            potionSpawnedSound.stop();
        }
    }
}
