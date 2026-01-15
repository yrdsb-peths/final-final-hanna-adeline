import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * An AttackBox represents an invisible hitbox used to deal damage
 * to enemies when an attack occurs.
 * 
 * The AttackBox detects collisions with enemy objects and applies
 * a specified amount of damage to any enemy it intersects.
 * 
 * @author Hanna & Adeline
 * @version January 2026
 */
public class AttackBox extends Actor
{
    // The amount of damage this attack box inflicts on enemies.
    private int damage;
    
    /**
     * The constuctor which constructs an AttackBox with 
     * a specified size and damage value.
     * 
     * The attack box is created as a transparent image and is used
     * only for collision detection.
     * 
     * @param w The width of the attack box
     * @param h The height of the attack box
     * @param damage The amount of damage dealt to enemies
     */
    public AttackBox(int w, int h, int damage)
    {
        this.damage = damage;
        
        GreenfootImage img = new GreenfootImage(w, h);
        img.setColor(new Color(255, 0, 0, 0));
        img.fillRect(0, 0, w, h);
        
        setImage(img);
    }
    
    /**
     * The act method which checks for collisions with enemy objects 
     * and applies damage to any enemy that intersects with this attack box.
     */
    public void act()
    {
        TutorialTarget targetSlime = (TutorialTarget) getOneIntersectingObject(TutorialTarget.class);
        if(targetSlime != null)
        {
            targetSlime.takeDamage(damage);
        }
        
        Level1SlimeRed redSlime = (Level1SlimeRed) getOneIntersectingObject(Level1SlimeRed.class);
        if(redSlime != null)
        {
            redSlime.takeDamage(damage);
        }
        
        Level2SlimeBlue blueSlime = (Level2SlimeBlue) getOneIntersectingObject(Level2SlimeBlue.class);
        if(blueSlime != null)
        {
            blueSlime.takeDamage(damage);
        }
        
        Level2Golem golem = (Level2Golem) getOneIntersectingObject(Level2Golem.class);
        if(golem != null)
        {
            golem.takeDamage(damage);
        }
        
        Level3Reaper reaper = (Level3Reaper) getOneIntersectingObject(Level3Reaper.class);
        if(reaper != null)
        {
            reaper.takeDamage(damage);
        }
        
        Level3Skeleton skeleton = (Level3Skeleton) getOneIntersectingObject(Level3Skeleton.class);
        if(skeleton != null)
        {
            skeleton.takeDamage(damage);
        }
    }
}
