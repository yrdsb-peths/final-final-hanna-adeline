import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The PotionFinal class represents the final potion in the game.
 * This potion displays a looping animation and serves as a visual
 * or gameplay element in the final stage.
 * 
 * @author Adeline
 * @version December 2025
 */
public class PotionFinal extends Actor
{
    /** Array of images used for the potion's animation */
    GreenfootImage[] potionImage = new GreenfootImage[24];
    
    /** Timer used to control animation speed */
    SimpleTimer animationTimer = new SimpleTimer();
    
    /**
     * Constructs a PotionFinal object.
     * Loads, scales, and initializes all animation frames.
     */
    public PotionFinal()
    {   
        for(int i=0; i<potionImage.length; i++)
        {
            potionImage[i] = new GreenfootImage("images/Potions/PotionFinal/potionfinal-"+ i + ".png");
            potionImage[i].scale(29, 50);
        }
        
        //Initial potion 1 image
        setImage(potionImage[0]);
        
        animationTimer.mark();
    }
    
    /** Index used to cycle through potion animation frames */
    int imageIndex = 0;
    /**
     * Animates the potion by cycling through images
     * at short time intervals to create a smooth animation.
     */
    public void animatePotion()
    {
        if(animationTimer.millisElapsed() < 50)
        {
            return;
        }
        animationTimer.mark();
        
        setImage(potionImage[imageIndex]);
        imageIndex = (imageIndex + 1) % potionImage.length;
    }
    
    /**
     * Act method for PotionFinal.
     * Continuously animates the potion each frame.
     */
    public void act()
    {
        animatePotion();
    }
}
