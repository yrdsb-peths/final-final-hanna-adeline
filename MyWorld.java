import greenfoot.*;

public class MyWorld extends World {
    public static boolean level1Complete = false;
    public static boolean level2Complete = false;
    public static boolean level3Complete = false;
    
    public static boolean potion1Collected = false;
    public static boolean potion2Collected = false;
    public static boolean potion3Collected = false;
    
    public MyWorld() {
        super(600, 400, 1);
        
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
    }
    
    private void spawnPotion2() {
        Potion2 potion2 = new Potion2();
        addObject(potion2, 500, 300);
    }
    
    private void spawnPotion3() {
        Potion3 potion3 = new Potion3();
        addObject(potion3, 500, 300);
    }
    
    //Removes all monsters, hp boxes and the witch when the game is over
    public void gameOver() {
        removeObjects(getObjects(null));
    }
    public void act() {
        if(level1Complete && !potion1Collected)
        {
            spawnPotion1();
        }
        if(level2Complete && !potion2Collected)
        {
            spawnPotion2();
        }
        if(level3Complete && !potion3Collected)
        {
            spawnPotion3();
        }
    }
}
