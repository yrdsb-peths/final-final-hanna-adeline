import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Witch here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Witch extends Actor
{
    GreenfootImage[] defaultIdle = new GreenfootImage[37];
    
    /**
     * Constructor - the code that gets run one time when the object is created.
     */
    public Witch()
    {
        for(int i = 0; i < defaultIdle.length; i++)
        {
            defaultIdle[i] = new GreenfootImage("images/witchdefault_idle/default" + i + ".png");
            defaultIdle[i].scale(85, 85);
        }
        
        
        // Initial witch image
        setImage(defaultIdle[0]);
    }
    
    public void act()
    {
        if(Greenfoot.isKeyDown("left"))
        {
            move(-4);
        }
        else if(Greenfoot.isKeyDown("right"))
        {
            move(4);
        }
    }
}
