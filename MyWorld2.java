import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld2 extends World
{
    public static boolean level2Complete = false;
    public static boolean potion2Collected = false;
    public static boolean golemSpawned = false;
    
    public static GreenfootSound potionSpawnedSound = new GreenfootSound("potionBubblingSound.mp3");

    /**
     * Constructor for objects of class MyWorld2.
     * 
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
        
    }
    
    private void spawnPotion2() {
        Potion2 potion2 = new Potion2();
        addObject(potion2, 500, 300);
        potionSpawnedSound.playLoop();
    }
    
    private void spawnHealingPotion()
    {
        HealingPotion healingPotion = new HealingPotion();
        HealingPotion.healingPotionCollected = false;
        //addObject(healingPotion);
        potionSpawnedSound.play();
        
    }
    
    //Spawn Golem after SlimeBlue is defeated
    private void spawnGolem()
    {
        Level2Golem golem2 = new Level2Golem();
        addObject(golem2, 500, 300);
        golemSpawned = true;
    }
    
    //Removes all monsters, hp boxes and the witch when the game is over
    public void gameOver() {
        removeObjects(getObjects(null));
        Greenfoot.setWorld(new GameOver());
    }
    
    // Reset the variable when starting a new game
    public static void resetGame()
    {
        level2Complete = false;
        potion2Collected = false;
        golemSpawned = false;
    }
    
    // Method to check if the level is complete
    public void checkLevelComplete()
    {
        if(getObjects(Level1SlimeRed.class).isEmpty() && getObjects(Level2SlimeBlue.class).isEmpty() && getObjects(Level2Golem.class).isEmpty() && golemSpawned)
        {
            level2Complete = true;
        }
    }
    
    public void act() {
        checkLevelComplete();
        
        if(level2Complete && !potion2Collected && getObjects(Potion2.class).isEmpty())
        {
            spawnPotion2();
        }
        if(getObjects(Level2SlimeBlue.class).isEmpty() && !golemSpawned)
        {
            spawnGolem();
        }
    }
}
