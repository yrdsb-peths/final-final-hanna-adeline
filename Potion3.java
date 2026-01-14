import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Potion3 class represents a collectible potion in the game.
 * The potion plays an animation, plays a sound when collected,
 * and triggers a world transition when picked up by the player.
 * 
 * @author Adeline
 * @version December 2025
 */
public class Potion3 extends Actor
{
    /** Array of images used to animate the potion */
    GreenfootImage[] potionImage = new GreenfootImage[12];
    
    /** Timer used to control the animation speed */
    SimpleTimer animationTimer = new SimpleTimer();
    
    /** Sound played when the potion is collected*/
    public static GreenfootSound potionCollectSound = new GreenfootSound("potionCollectSound.mp3");

    /**
     * Constructs a Potion3 object and loads all animation frames.
     * The potion image is scaled and the animation timer is initialized.
     */
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
    /**
     * Animates the potion by cycling through images
     * at a fixed time interval.
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
     * Handles potion collection logic.
     * Removes the potion from the world, stops the spawn sound,
     * plays the collection sound, and updates the world state.
     *
     * @return true if the potion has been collected
     */
    public boolean potion3Collected()
    {
        getWorld().removeObject(this);
        MyWorld3.potionSpawnedSound.stop();
        potionCollectSound.play();
        ((MyWorld3)getWorld()).potion3Collected = true;
        ((MyWorld3)getWorld()).level3Complete = true;
        return ((MyWorld3)getWorld()).potion3Collected;
    }
    
    /**
     * Act method for Potion3.
     * Animates the potion and checks for collision with the player.
     * When collected, the world transitions to the next level.
     */
    public void act()
    {
        animatePotion();
        if(isTouching(HurtBox.class))
        {
            potion3Collected();
            Greenfoot.setWorld(new EndScreen());
        }
    }
}
