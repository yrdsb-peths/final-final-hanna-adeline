import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Witch, our main character.
 * 
 * @author Hanna & Adeline 
 * @version December 2025
 */
public class Witch extends Actor
{
    // Image idles of witch
    GreenfootImage[] defaultIdleRight = new GreenfootImage[37];
    GreenfootImage[] defaultIdleLeft = new GreenfootImage[37];
    GreenfootImage[] attack1Right = new GreenfootImage[13];
    GreenfootImage[] attack1Left = new GreenfootImage[13];
    GreenfootImage[] attack2Right = new GreenfootImage[26];
    GreenfootImage[] attack2Left = new GreenfootImage[26];
    
    // Direction the witch is facing
    String facing = "right";
    
    // Variables of attacks
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
        // Set idle image for default witch
        for(int i = 0; i < defaultIdleRight.length; i++)
        {
            defaultIdleRight[i] = new GreenfootImage("witch_default_idle/default" + i + ".png");
            defaultIdleRight[i].scale(300, 300);
        }
        for(int i = 0; i < defaultIdleLeft.length; i++)
        {
            defaultIdleLeft[i] = new GreenfootImage("witch_default_idle/default" + i + ".png");
            defaultIdleLeft[i].mirrorHorizontally();
            defaultIdleLeft[i].scale(300, 300);
        }
        
        // Set idle image for attack 1 of witch
        for(int i = 0; i < attack1Right.length; i++)
        {
            attack1Right[i] = new GreenfootImage("witch_attack1_idle/attack1_" + i + ".png");
            attack1Right[i].scale(300, 300);
        }
        for(int i = 0; i < attack1Left.length; i++)
        {
            attack1Left[i] = new GreenfootImage("witch_attack1_idle/attack1_" + i + ".png");
            attack1Left[i].mirrorHorizontally();
            attack1Left[i].scale(300, 300);
        }
        
        // Set idle image for attack 2 of witch
        for(int i = 0; i < attack2Right.length; i++)
        {
            attack2Right[i] = new GreenfootImage("witch_attack2_idle/attack2_" + i + ".png");
            attack2Right[i].scale(300, 300);
        }
        for(int i = 0; i < attack2Left.length; i++)
        {
            attack2Left[i] = new GreenfootImage("witch_attack2_idle/attack2_" + i + ".png");
            attack2Left[i].mirrorHorizontally();
            attack2Left[i].scale(300, 300);
        }
        defaultTimer.mark();
        
        // Initial witch image
        setImage(defaultIdleRight[0]);
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
        
        if(facing.equals("right"))
        {
            setImage(defaultIdleRight[imageIndex]);
            imageIndex = (imageIndex + 1) % defaultIdleRight.length;
        }
        else
        {
            setImage(defaultIdleLeft[imageIndex]);
            imageIndex = (imageIndex + 1) % defaultIdleLeft.length;
        }
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
        
        if(facing.equals("right"))
        {
            setImage(attack1Right[attackIndex1]);
            attackIndex1 = attackIndex1 + 1;
        }
        else
        {
            setImage(attack1Left[attackIndex1]);
            attackIndex1 = attackIndex1 + 1;
        }
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
        
        if(facing.equals("right"))
        {
            setImage(attack2Right[attackIndex2]);
            attackIndex2 = attackIndex2 + 1;
        }
        else
        {
            setImage(attack2Left[attackIndex2]);
            attackIndex2 = attackIndex2 + 1;
        }
    }
    
    public void act()
    {
        if(Greenfoot.isKeyDown("left"))
        {
            move(-2);
            facing = "left";
        }
        else if(Greenfoot.isKeyDown("right"))
        {
            move(2);
            facing = "right";
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
            if(attackIndex1 >= attack1Right.length)
            {
                isAttacking1 = false;
                attackIndex1 = 0;
            }
        }
        else if(isAttacking2)
        {
            animateAttackTwo();
            if(attackIndex2 >= attack2Right.length)
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
