import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The HurtBox class represents a hurt box attached to the witch character.
 * 
 * The HurtBox detects collisions with enemies and applies damage
 * to the witch while managing invincibility frames.
 * 
 * @author Hanna & Adeline
 * @version January 2025
 */
public class HurtBox extends Actor
{
    // Access the witch class instance
    private Witch witch;
    // Instance that counts consecutive enemy hits before damage is applied
    private int hitCountWitch = 0;
    // Timer used to provide temporary invincibility after taking damage.
    private int invincibleTimer = 0;
    
    /**
     * Constructs a HurtBox linked to a witch with a specified size.
     * 
     * @param witch The witch instance this hurt box belongs to
     * @param w The width of the hurt box
     * @param h The height of the hurt box
     */
    public HurtBox(Witch witch, int w, int h)
    {
        // Access the witch object
        this.witch = witch;
        
        GreenfootImage img = new GreenfootImage(w, h);
        img.setColor(new Color(255, 0, 0, 0));
        img.fillRect(0, 0, w, h);
        
        setImage(img);
    }
    
    /**
     * Returns the witch associated with this hurt box.
     * 
     * @return The witch instance
     */
    public Witch getWitch()
    {
        return witch;
    }
    
    /**
     * Checks for collisions with enemies and applies damage
     * to the witch if invincibility is not active.
     */
    public void checkDamageWitch()
    {
        if(invincibleTimer > 0) 
        {
           return; 
        }

        // Slime Red
        if(isTouching(Level1SlimeRed.class) || isTouching(Level2SlimeBlue.class))
        {
            dealDamage();
            return;
        }
    
        // Golem (only if alive)
        Level2Golem golem = (Level2Golem) getOneIntersectingObject(Level2Golem.class);
        if(golem != null && golem.isAlive())
        {
            dealDamage();
            return;
        }
    
        // Reaper (only if alive)
        Level3Reaper reaper = (Level3Reaper) getOneIntersectingObject(Level3Reaper.class);
        if(reaper != null && reaper.isAlive())
        {
            reaperDamage();
            return;
        }
        
        // Skeleton (only if alive)
        Level3Skeleton skeleton = (Level3Skeleton) getOneIntersectingObject(Level3Skeleton.class);
        if(skeleton != null && skeleton.isAlive())
        {
            dealDamage();
            return;
        }
    }
    
    /**
     * Applies damage to the witch and activates invincibility frames.
     */
    private void dealDamage()
    {
        hitCountWitch++;
        if(hitCountWitch >= 6)
        {
            witch.takeDamage(1);
            hitCountWitch = 0;
        }
        invincibleTimer = 20;
    }
    
    /**
     * Applies damage to the witch and activates invincibility frames.
     */
    private void reaperDamage()
    {
        hitCountWitch++;
        if(hitCountWitch >= 4)
        {
            witch.takeDamage(1);
            hitCountWitch = 0;
        }
        invincibleTimer = 20;
    }
    
    /**
     * The act method updates the hurt box each frame.
     * 
     * Handles collision checks and manages the invincibility timer.
     */
    public void act()
    {
        if(witch instanceof Witch)
        {
            checkDamageWitch();
        }
        
        if(invincibleTimer > 0)
        {
            invincibleTimer--;
        }
    }
}
