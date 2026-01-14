import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Potion2 class represents a collectible potion in the game.
 * The potion plays an animation, plays a sound when collected,
 * and triggers a world transition when picked up by the player.
 * 
 * @author Adeline
 * @version December 2025
 */
public class Potion2 extends Actor
{
    /** Array of images used to animate the potion */
    GreenfootImage[] potionImage = new GreenfootImage[14];
    
    /** Timer used to control the animation speed */
    SimpleTimer animationTimer = new SimpleTimer();
    
    /** Sound played when the potion is collected */
    public static GreenfootSound potionCollectSound = new GreenfootSound("potionCollectSound.mp3");
    
    /**
     * Constructs a Potion2 object and loads all animation frames.
     * The potion image is scaled and the animation timer is initialized.
     */
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
    /**
     * Animates the potion by cycling through images
     * at a fixed time interval.
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
     * Handles potion collection logic.
     * Removes the potion from the world, stops the spawn sound,
     * plays the collection sound, and updates the world state.
     *
     * @return true if the potion has been collected
     */
    public boolean potion2Collected()
    {
        getWorld().removeObject(this);
        MyWorld2.potionSpawnedSound.stop();
        Potion1.potionCollectSound.play();
        ((MyWorld2)getWorld()).potion2Collected = true;
        return ((MyWorld2)getWorld()).potion2Collected;
    }
    
    /**
     * Act method for Potion2.
     * Animates the potion and checks for collision with the player.
     * When collected, the world transitions to the next level.
     */
    public void act()
    {
        animatePotion();
        if(isTouching(HurtBox.class))
        {
            potion2Collected();
            Greenfoot.setWorld(new MyWorld3());
        }
    }
}
