import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level1SlimeRed here.
 * 
 * @author Adeline
 * @version December 2025
 */
public class Level1SlimeRed extends Actor
{
    GreenfootImage[] walkRightImage = new GreenfootImage[8];
    GreenfootImage[] attackRightImage = new GreenfootImage[8];
    GreenfootImage[] walkLeftImage = new GreenfootImage[8];
    GreenfootImage[] attackLeftImage = new GreenfootImage[8];
    GreenfootImage[] deadLeftImage = new GreenfootImage[8];
    GreenfootImage[] deadRightImage = new GreenfootImage[8];
    
    SimpleTimer animationTimer = new SimpleTimer();
    public Level1SlimeRed()
    {
        //Initial potion 1 image
        setImage(potionImage[0]);
        
        for(int i=0; i<potionImage.length; i++)
        {
            potionImage[i] = new GreenfootImage("images/Potions/Potion1/potion1-" + i + ".png");
            potionImage[i].scale(22, 37);
        }
        
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
    /**
     * Act - do whatever the Level1SlimeRed wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        animatePotion();
    }
}
