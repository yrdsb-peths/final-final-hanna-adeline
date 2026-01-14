import greenfoot.*;

public class MyWorld extends World {
    public static boolean level1Complete = false;
    public static boolean potion1Collected = false;
    
    public static GreenfootSound potionSpawnedSound = new GreenfootSound("potionBubblingSound.mp3");
    public static GreenfootSound level1Sound = new GreenfootSound("level1Sound.mp3");
    public static boolean healingPotionSpawned = false;
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
        level1Sound.playLoop();
        
        // Restart the healing potion spawn at start of the level
        healingPotionSpawned = false;
    }
    
    // Method to spawn the potion when the monster is dead
    private void spawnPotion1() {
        Potion1 potion1 = new Potion1();
        addObject(potion1, 500, 300);
        potionSpawnedSound.playLoop();
    }
    
    private void spawnHealingPotion()
    {
        HealingPotion healingPotion = new HealingPotion();
        addObject(healingPotion, 450, 90); 
        potionSpawnedSound.play();
    }
    // Removes all monsters, hp boxes and the witch when the game is over
    public void gameOver() {
        removeObjects(getObjects(null));
        Greenfoot.setWorld(new GameOver());
        if(potionSpawnedSound.isPlaying()) {
            potionSpawnedSound.stop();
        }
    }
    
    // Reset the variable when starting a new game
    public static void resetGame()
    {
        level1Complete = false;
        potion1Collected = false;
        healingPotionSpawned = false;
    }
    
    // Method to check if the level is complete
    public void checkLevelComplete()
    {
        if(getObjects(Level1SlimeRed.class).isEmpty())
        {
            level1Complete = true;
        }
    }
    
    public void act() {
        checkLevelComplete();
        
        if(level1Complete && !potion1Collected && getObjects(Potion1.class).isEmpty())
        {
            level1Sound.stop();
            spawnPotion1();
        }
        if(potion1Collected && potionSpawnedSound.isPlaying()) 
        {
            potionSpawnedSound.stop();
        }
        Witch witch = getObjects(Witch.class).get(0);
        if (witch.getHP() <= 2 && getObjects(HealingPotion.class).isEmpty() && !healingPotionSpawned)
        {
            spawnHealingPotion();
            healingPotionSpawned = true; // make sure it only spawns once
        }
    }
}
