import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Potion2 here.
 * 
 * @author Adeline
 * @version December 2025
 */
public class Potion2 extends Actor
{
    GreenfootImage[] potionImage = new GreenfootImage[14];
    SimpleTimer animationTimer = new SimpleTimer();
    public static GreenfootSound potionCollectSound = new GreenfootSound("potionCollectSound.mp3");

    public Potion2()
    {
        for(int i=0; i<potionImage.length; i++)
        {
            potionImage[i] = new GreenfootImage("images/Potions/Potion2/potion2-" + i + ".png");
            potionImage[i].scale(16, 51);
        }
        
        //Initial potion 2 image
        setImage(potionImage[0]);
        
        animationTimer.mark();
    }
    int imageIndex = 0;
    public void animatePotion()
    {
        if(animationTimer.millisElapsed() < 100)
        {
            return;
        }
        animationTimer.mark();
        
        setImage(potionImage[imageIndex]);
        imageIndex = (imageIndex + 1) % potionImage.length;
    }
    
    // Potion disappears after it's collected by the user
    
    public boolean potion2Collected()
    {
        getWorld().removeObject(this);
        MyWorld.potionSpawnedSound.stop();
        Potion1.potionCollectSound.play();
        ((MyWorld2)getWorld()).potion2Collected = true;
        return ((MyWorld2)getWorld()).potion2Collected;
    }
    
    /**
     * Act - do whatever the Potion2 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        animatePotion();
    }
}
