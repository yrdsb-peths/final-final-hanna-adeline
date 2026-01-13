import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HealingPotion here.
 * 
 * @author Adeline
 * @version December 2025
 */
public class HealingPotion extends Actor
{
    GreenfootImage[] potionImage = new GreenfootImage[8];
    SimpleTimer animationTimer = new SimpleTimer();
    public static GreenfootSound potionCollectSound = new GreenfootSound("potionCollectSound.mp3");
    public static boolean healingPotionCollected = false;
    
    public HealingPotion()
    {
        for(int i=0; i<potionImage.length; i++)
        {
            potionImage[i] = new GreenfootImage("images/Potions/HealingPotion/healingPotion" + i + ".png");
            potionImage[i].scale(22, 37);
        }
        
        //Initial potion 1 image
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
    
    /**
     * Act - do whatever the HealingPotion wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        animatePotion();

        // Potion disappears after it's collected by the user
        HurtBox hb = (HurtBox)getOneIntersectingObject(HurtBox.class);
        if (hb != null)
        {
            Witch witch = hb.getWitch();
            witch.heal(2);
    
            potionCollectSound.play();
            getWorld().removeObject(this);
        }
    }
}
