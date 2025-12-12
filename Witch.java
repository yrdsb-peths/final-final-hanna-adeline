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
    
    SimpleTimer defaultTimer = new SimpleTimer();
    
    /**
     * Constructor - the code that gets run one time when the object is created.
     */
    public Witch()
    {
        for(int i = 0; i < defaultIdle.length; i++)
        {
            defaultIdle[i] = new GreenfootImage("witch_default_idle/default" + i + ".png");
            defaultIdle[i].scale(300, 300);
        }
        
        defaultTimer.mark();
        
        // Initial witch image
        setImage(defaultIdle[0]);
    }
    
    /**
     * Animate the witch
     */
    int imageIndex = 0;
    public void animateWitch()
    {
        if(defaultTimer.millisElapsed() < 50)
        {
            return;
        }
        defaultTimer.mark();
        
        setImage(defaultIdle[imageIndex]);
        imageIndex = (imageIndex + 1) % defaultIdle.length;
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
        
        // Animate the witch at default state
        animateWitch();
    }
}
