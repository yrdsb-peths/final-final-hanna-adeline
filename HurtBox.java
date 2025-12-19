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
    private Witch owner;
    private int hitCount = 0;
    private int invincibleTimer = 0;
    
    // Constructor - method called once 
    public HurtBox(Witch owner, int w, int h)
    {
        // Access the witch object
        this.owner = owner;
        
        GreenfootImage img = new GreenfootImage(w, h);
        img.setColor(new Color(255, 0, 0, 0));
        img.fillRect(0, 0, w, h);
        
        setImage(img);
    }
    
    public void checkDamage()
    {
        if(isTouching(Level1SlimeRed.class))
        {
            if(invincibleTimer > 0)
            {
                return;
            }
            hitCount++;
            if(hitCount >= 5)
            {
                owner.takeDamage(1);
                hitCount = 0;
            }
            invincibleTimer = 20;
        }
    }
    
    /**
     * Act - do whatever the HurtBox wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        checkDamage();
        
        if(invincibleTimer > 0)
        {
            invincibleTimer--;
        }
    }
}
