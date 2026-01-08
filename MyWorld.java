import greenfoot.*;

public class MyWorld extends World {
<<<<<<< Updated upstream
=======
    public static boolean level1Complete = false;
    public static boolean level2Complete = false;
    public static boolean level3Complete = false;
    
    public static boolean potion1Collected = false;
    public static boolean potion2Collected = false;
    public static boolean potion3Collected = false;
    
    public static boolean level1Start = false;
    public static boolean level2Start = false;
    public static boolean level3Start = false;
    
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
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
        addObject(slimered, 500,250);
    }
}
