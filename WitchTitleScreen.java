import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The WitchTitleScreen class represents the animated witch
 * that appears on the title screen. 
 * 
 * It displays the witch in her default idle animation and loops 
 * the animation continuously while on the titlescreen and storyworld.
 * 
 * 
 * @author Hanna
 * @version January 2026
 */
public class WitchTitleScreen extends Actor
{
    // Image idles of witch
    GreenfootImage[] defaultIdleRight = new GreenfootImage[37];
    
    // SimplerTimer variables
    SimpleTimer defaultTimer = new SimpleTimer();
    
    /** 
     * Constructs the WitchTitleScreen object.
     * Loads and scales the witch's idle animation images
     * and sets the initial image. 
     */
    public WitchTitleScreen()
    {
        // Set idle image for default witch
        for(int i = 0; i < defaultIdleRight.length; i++)
        {
            defaultIdleRight[i] = new GreenfootImage("witch_default_idle/default" + i + ".png");
            defaultIdleRight[i].scale(320, 320);
        }
        
        // Initial witch image
        setImage(defaultIdleRight[0]);
    }
    
    int imageIndex = 0;
    /**
     * Animates the witch's idle animation.
     * Loops through the defaultIdleRight images at a consistent speed.
     */
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
     * Act method called by Greenfoot on every frame.
     * Continuously animates the witch by calling animateWitch().
     */
    /**
     * Act - do whatever the WitchTitleScreen wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        animateWitch();
    }
}
