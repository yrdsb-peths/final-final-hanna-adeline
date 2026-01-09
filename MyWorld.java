import greenfoot.*;

public class MyWorld extends World {
    public static boolean level1Complete = false;
    public static boolean potion1Collected = false;
    
    public static GreenfootSound potionSpawnedSound = new GreenfootSound("potionBubblingSound.mp3");
    
    public MyWorld() {
        super(600, 400, 1);
        
        //Set booleans
        level1Complete = false;
        potion1Collected = false;
        
        // Set background
        GreenfootImage bg = new GreenfootImage("images/background/Battleground2.png");
        bg.scale(getWidth(), getHeight());
        setBackground(bg);
        
        // Create the witch object
        Witch witch = new Witch();
        addObject(witch, 120, 250);
        
        Level1SlimeRed slimered = new Level1SlimeRed();
        addObject(slimered, 500,300);
    }
    
    private void spawnPotion1() {
        Potion1 potion1 = new Potion1();
        addObject(potion1, 500, 300);
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
        level1Complete = false;
        potion1Collected = false;
    }
    
    public void act() {
        if(level1Complete && !potion1Collected && getObjects(Potion1.class).isEmpty())
        {
            spawnPotion1();
        }
    }
}
