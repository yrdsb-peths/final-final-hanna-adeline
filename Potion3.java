import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Potion3 here.
 * 
 * @author Adeline
 * @version December 2025
 */
public class Potion3 extends Actor
{
    GreenfootImage[] potionImage = new GreenfootImage[12];
    SimpleTimer animationTimer = new SimpleTimer();
    public static GreenfootSound potionCollectSound = new GreenfootSound("potionCollectSound.mp3");

    public Potion3()
    {
        for(int i=0; i<potionImage.length; i++)
        {
            potionImage[i] = new GreenfootImage("images/Potions/Potion3/potion3-" + i + ".png");
            potionImage[i].scale(24, 39);
        }
        
        //Initial potion 1 image
        setImage(potionImage[0]);
        
        animationTimer.mark();
    }
    int imageIndex = 0;
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
    
    // Potion disappears after it's collected by the user
    
    public boolean potion3Collected()
    {
        getWorld().removeObject(this);
        MyWorld3.potionSpawnedSound.stop();
        Potion1.potionCollectSound.play();
        ((MyWorld3)getWorld()).potion3Collected = true;
        return ((MyWorld3)getWorld()).potion3Collected;
    }
    
    /**
     * Act - do whatever the Potion3 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        animatePotion();
    }
}
