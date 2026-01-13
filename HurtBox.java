import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HurtBox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HurtBox extends Actor
{
    // access the witch class instance
    private Witch witch;
    private int hitCountWitch = 0;
    private int invincibleTimer = 0;
    
    // Constructor - method called once 
    public HurtBox(Witch witch, int w, int h)
    {
        // Access the witch object
        this.witch = witch;
        
        GreenfootImage img = new GreenfootImage(w, h);
        img.setColor(new Color(255, 0, 0, 0));
        img.fillRect(0, 0, w, h);
        
        setImage(img);
    }
    
    // Getter method for HealingPotion class
    public Witch getWitch()
    {
        return witch;
    }

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
    
        // Reaper
        if(isTouching(Level3Reaper.class) || isTouching(Level3Skeleton.class))
        {
            dealDamage();
        }
    }
    
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
     * Act - do whatever the HurtBox wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
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
