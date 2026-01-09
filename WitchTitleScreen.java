import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WitchTitleScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WitchTitleScreen extends Actor
{
    // Image idles of witch
    GreenfootImage[] defaultIdleRight = new GreenfootImage[37];
    
    // SimplerTimer variables
    SimpleTimer defaultTimer = new SimpleTimer();
    
    public WitchTitleScreen()
    {
        // Set idle image for default witch
        for(int i = 0; i < defaultIdleRight.length; i++)
        {
            defaultIdleRight[i] = new GreenfootImage("witch_default_idle/default" + i + ".png");
            defaultIdleRight[i].scale(400, 400);
        }
        
        // Initial witch image
        setImage(defaultIdleRight[0]);
    }
    
    /**
     * Animate the witch at default state
     */
    int imageIndex = 0;
    public void animateWitch()
    {
        if(defaultTimer.millisElapsed() < 50)
        {
            return;
        }
        defaultTimer.mark();
        
        // Set image for witch
        setImage(defaultIdleRight[imageIndex]);
        imageIndex = (imageIndex + 1) % defaultIdleRight.length;
    }
    
    /**
     * Act - do whatever the WitchTitleScreen wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        animateWitch();
    }
}
