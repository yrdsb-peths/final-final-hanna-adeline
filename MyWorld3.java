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
    
    public static GreenfootSound potionSpawnedSound = new GreenfootSound("potionBubblingSound.mp3");

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
        
        // Set background
        GreenfootImage bg = new GreenfootImage("images/background/Battleground4.png");
        bg.scale(getWidth(), getHeight());
        setBackground(bg);
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
    }
    
    public void act() {
        if(level3Complete && !potion3Collected && getObjects(Potion3.class).isEmpty())
        {
            spawnPotion3();
        }
    }
}
