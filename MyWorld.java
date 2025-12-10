import greenfoot.*;

public class MyWorld extends World {
    public MyWorld() {
        super(600, 400, 1);
        
        // Set background
        GreenfootImage bg = new GreenfootImage("images/background.gif");
        bg.scale(getWidth(), getHeight());
        setBackground(bg);
    }
}
