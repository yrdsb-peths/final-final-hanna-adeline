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
    GreenfootImage[] attack1 = new GreenfootImage[13];
    GreenfootImage[] attack2 = new GreenfootImage[26];
    
    boolean isAttacking1 = false;
    boolean isAttacking2 = false;
    
    // SimplerTimer variables
    SimpleTimer defaultTimer = new SimpleTimer();
    SimpleTimer attackTimer1 = new SimpleTimer();
    SimpleTimer attackTimer2 = new SimpleTimer();
    
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
        
        for(int i = 0; i < attack2.length; i++)
        {
            attack2[i] = new GreenfootImage("witch_attack2_idle/attack2_" + i + ".png");
            attack2[i].scale(300, 300);
        }
        defaultTimer.mark();
        
        // Initial witch image
        setImage(defaultIdle[0]);
    }
    
    /**
     * Animate the witch at default state
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
     * Animate the level 1 attack of witch
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
    
    /**
     * Animate the level 2 attack of witch
     */   
    int attackIndex2 = 0;
    public void animateAttackTwo()
    {
        if(attackTimer2.millisElapsed() < 50)
        {
            return;
        }
        attackTimer2.mark();
        
        setImage(attack2[attackIndex2]);
        attackIndex2 = attackIndex2 + 1;
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
            isAttacking1 = true;
            attackIndex1 = 0; // restart animation
            attackTimer1.mark();
        }
        
        // Start attack 2
        if(Greenfoot.isKeyDown("space"))
        {
            isAttacking2 = true;
            attackIndex2 = 0; //restart animation
            attackTimer2.mark();
        }
        
        /*
         * Animate the witch at default state
         * If currently attacking, play attack animation only
         */ 
        if(isAttacking1)
        {
            animateAttackOne();
            if(attackIndex1 >= attack1.length)
            {
                isAttacking1 = false;
                attackIndex1 = 0;
            }
        }
        else if(isAttacking2)
        {
            animateAttackTwo();
            if(attackIndex2 >= attack2.length)
            {
                isAttacking2 = false;
                attackIndex2 = 0;
            }
        }
        else
        {
            animateWitch();
        }
    }
}
