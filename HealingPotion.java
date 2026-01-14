import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The HealingPotion class displays a healing potion that animates 
 * and restores health when collected by the player.
 * 
 * The potion cycles through animation frames and disappears after
 * healing the witch.
 * 
 * @author Hanna & Adeline
 * @version December 2025
 */
public class HealingPotion extends Actor
{
    // Animation frames for the healing potion.
    GreenfootImage[] potionImage = new GreenfootImage[8];
    
    // Timer used to control the animation speed.
    SimpleTimer animationTimer = new SimpleTimer();
    
    // Sound effect played when the potion is collected.
    public static GreenfootSound potionCollectSound = new GreenfootSound("potionCollectSound.mp3");
    
    // Indicates whether a healing potion has been collected.
    public static boolean healingPotionCollected = false;
    
    /**
     * Constructs a HealingPotion object, loads animation images,
     * and initializes the animation timer.
     */
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
    /**
     * Animates the potion by cycling through images at a fixed interval.
     */
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
     * The act method updates the potion each frame.
     * 
     * Animates the potion and checks for collision with the player's
     * hurt box. If collected, heals the witch, plays a sound effect,
     * and removes the potion from the world.
     */
    public void act()
    {
        animatePotion();
        
        HurtBox hb = (HurtBox)getOneIntersectingObject(HurtBox.class);
        
        // Potion disappears after it's collected by the user
        if (hb != null)
        {
            Witch witch = hb.getWitch();
            witch.heal(2);
            
            // Stop the healing potion spawn sound when collected
            if (getWorld() instanceof MyWorld)
            {
                MyWorld.potionSpawnedSound.stop();
            }
            else if (getWorld() instanceof MyWorld2)
            {
                MyWorld2.potionSpawnedSound.stop();
            }
            else if (getWorld() instanceof MyWorld3)
            {
                MyWorld3.potionSpawnedSound.stop();
            }
            
            potionCollectSound.play();
            getWorld().removeObject(this);
        }
    }
}
