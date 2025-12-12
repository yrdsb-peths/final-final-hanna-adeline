import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Potion1 here.
 * 
 * @author Adeline
 * @version December 2025
 */
public class Potion1 extends Actor
{
    GreenfootImage[] potionImage = new GreenfootImage[8];
    SimpleTimer animationTimer = new SimpleTimer();
    public Potion1()
    {
        //Initial potion 1 image
        setImage(potionImage[0]);
        
        for(int i=0; i<potionImage.length; i++)
        {
            potionImage[i] = new GreenfootImage("images/Potion1/potion1-" + i + ".png");
            potionImage[i].scale(22, 37);
        }
        
        animationTimer.mark();
    }
    int imageIndex = 0;
    public void animatePotion()
    {
        if(animationTimer.millisElapsed() < 300)
        {
            return;
        }
        animationTimer.mark();
        
        setImage(potionImage[imageIndex]);
        imageIndex = (imageIndex + 1) % potionImage.length;
    }
    /**
     * Act - do whatever the Potion1 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        animatePotion();
    }
}
