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
    private int hitCounter = 0;
    
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
    
    /**
     * Act - do whatever the HurtBox wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Use the takeDamage method to decrease the HP points
        Level1SlimeRed m = (Level1SlimeRed)getOneIntersectingObject(Level1SlimeRed.class);
        if(m != null)
        {
            owner.takeDamage(1);
        }
    }
}
