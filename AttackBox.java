import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AttackBox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AttackBox extends Actor
{
    public AttackBox(int w, int h)
    {
        GreenfootImage img = new GreenfootImage(w, h);
        img.setColor(new Color(0, 255, 0, 120));
        img.fillRect(0, 0, w, h);
        
        setImage(img);
    }
    
    /**
     * Act - do whatever the AttackBox wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
}
