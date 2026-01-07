import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PotionFinal here.
 * 
 * @author Adeline
 * @version December 2025
 */
public class PotionFinal extends Actor
{
    GreenfootImage[] potionImage = new GreenfootImage[24];
    SimpleTimer animationTimer = new SimpleTimer();
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
    /**
     * Act - do whatever the PotionFinal wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        animatePotion();
    }
}
