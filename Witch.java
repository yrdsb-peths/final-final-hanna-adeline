import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Witch here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Witch extends Actor
{
    // Image idles of witch
    GreenfootImage[] defaultIdle = new GreenfootImage[37];
    GreenfootImage[] attack1 = new GreenfootImage[15];
    
    boolean isAttacking = false;
    
    // SimplerTimer variables
    SimpleTimer defaultTimer = new SimpleTimer();
    SimpleTimer attackTimer1 = new SimpleTimer();
    
    /**
     * Constructor - the code that gets run one time when the object is created.
     */
    public Witch()
    {
        for(int i = 0; i < defaultIdle.length; i++)
        {
            defaultIdle[i] = new GreenfootImage("witch_default_idle/default" + i + ".png");
            defaultIdle[i].scale(300, 300);
        }
        
        for(int i = 0; i < attack1.length; i++)
        {
            attack1[i] = new GreenfootImage("witch_attack1_idle/attack1_" + i + ".png");
            attack1[i].scale(300, 300);
        }
        
        defaultTimer.mark();
        
        // Initial witch image
        setImage(defaultIdle[0]);
    }
    
    /**
     * Animate the witch
     */
    int imageIndex = 0;
    public void animateWitch()
    {
        if(defaultTimer.millisElapsed() < 50)
        {
            return;
        }
        defaultTimer.mark();
        
        setImage(defaultIdle[imageIndex]);
        imageIndex = (imageIndex + 1) % defaultIdle.length;
    }
    
    /**
     * Animate the attack 1 of witch
     */
    int attackIndex1 = 0;
    public void animateAttackOne()
    {
        if(attackTimer1.millisElapsed() < 50)
        {
            return;
        }
        attackTimer1.mark();
        
        setImage(attack1[attackIndex1]);
        attackIndex1 = attackIndex1 + 1;
    }
    
    
    public void act()
    {
        if(Greenfoot.isKeyDown("left"))
        {
            move(-2);
        }
        else if(Greenfoot.isKeyDown("right"))
        {
            move(2);
        }
        
        // Start attack 1
        if(Greenfoot.isKeyDown("shift"))
        {
            isAttacking = true;
            attackIndex1 = 0; // restart animation
            attackTimer1.mark();
        }
        
        /*
         * Animate the witch at default state
         * If currently attacking, play attack animation only
         */ 
        if(isAttacking)
        {
            animateAttackOne();
            if(attackIndex1 >= attack1.length)
            {
                isAttacking = false;
                attackIndex1 = 0;
            }
        }
        else
        {
            animateWitch();
        }
    }
}
