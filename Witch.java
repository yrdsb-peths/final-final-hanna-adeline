import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Witch class represents the main playable character.
 * The witch can move, fly, attack enemies using two attack types,
 * take damage, heal, and display cooldown and health bars.
 * 
 * This class controls animations, combat logic, cooldown systems,
 * flying mechanics, and game-over behavior.
 * 
 * @author Hanna & Adeline
 * @version December 2025
 */
public class Witch extends Actor
{   
    // Declare hurtBox
    public HurtBox hurtBox;
    public AttackBox attackBox1;
    public AttackBox attackBox2;
    
    // hurtBox offsets
    int hurtOffsetXRight = -25;
    int hurtOffsetXLeft = 30;
    // hitBox offsets
    int hitOffsetXRight1 = 40;
    int hitOffsetXLeft1 = -40;
    int hitOffsetXRight2 = 40;
    int hitOffsetXLeft2 = -40;
    // Flying coolDown bar offsets
    int flyingCoolOffsetXRight = -25;
    int flyingCoolOffsetXLeft = 30;
    
    // Image idles of hpbar of witch
    public GreenfootImage[] witchHP = new GreenfootImage[6];
    public HPBar witchHPBar;
    private int witchCurrentHP = 5;
    private int witchMaxHP = 5;
    private int invincibleTimer = 0;
    
    // HP bar displayed above the witch
    public static boolean witchAlive;
    
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
    
    // Set sounds for witch
    GreenfootSound witchAttack1Sound = new GreenfootSound("attack1Sound.mp3");
    GreenfootSound witchAttack2Sound = new GreenfootSound("c6aa3b4b.mp3");
    GreenfootSound witchDeadSound = new GreenfootSound("witchDeadSound.mp3");

    // Image idles for attack cooldown bar
    GreenfootImage[] attack1CooldownImg = new GreenfootImage[5];
    GreenfootImage[] attack2CooldownImg = new GreenfootImage[5];
    
    // Current level of cooldown bar
    private int attack1CooldownLevel = 0;
    private int attack2CooldownLevel = 0;

    // Timer for cooling down
    private int attack1CooldownTimer = 0;
    private int attack2CooldownTimer = 0;
    
    // The time it takes to cool one block
    private int attack1CooldownSpeed = 18;
    private int attack2CooldownSpeed = 55;
    
    // Icon for the cooldown Actor
    private Actor attack1CooldownIcon;
    private Actor attack2CooldownIcon;
    
    // Flying System
    private boolean isFlying = false;
    
    // Flying cooldown icon sprite images
    GreenfootImage[] flyingCooldownImg = new GreenfootImage[5];
    
    // Fly cooldown bar state
    private int flyCooldownLevel = 4;
    private int flyCooldownTimer = 0;
    private int flyCooldownSpeed = 40;
    private int flyBarCooldownSpeed = 70;

    // Fly cooldown icon
    private Actor flyCooldownIcon;

    // Gravity
    private int groundY;
    private int gravitySpeed = 2;
    private int flySpeed = 2;   
    
    // Time for Game Over at 0 hp
    private boolean isDying = false;
    private int deathTimer = 0;
    
    /**
     * Constructs the Witch object.
     * Initializes animations, sounds, health, cooldown bars,
     * and sets default states.
     */
    public Witch()
    {
        // Set idle image for default witch
        for(int i = 0; i < defaultIdleRight.length; i++)
        {
            defaultIdleRight[i] = new GreenfootImage("witch_default_idle/default" + i + ".png");
            defaultIdleRight[i].scale(270, 270);
        }
        for(int i = 0; i < defaultIdleLeft.length; i++)
        {
            defaultIdleLeft[i] = new GreenfootImage("witch_default_idle/default" + i + ".png");
            defaultIdleLeft[i].mirrorHorizontally();
            defaultIdleLeft[i].scale(270, 270);
        }
        
        // Set idle image for attack 1 of witch
        for(int i = 0; i < attack1Right.length; i++)
        {
            attack1Right[i] = new GreenfootImage("witch_attack1_idle/attack1_" + i + ".png");
            attack1Right[i].scale(270, 270);
        }
        for(int i = 0; i < attack1Left.length; i++)
        {
            attack1Left[i] = new GreenfootImage("witch_attack1_idle/attack1_" + i + ".png");
            attack1Left[i].mirrorHorizontally();
            attack1Left[i].scale(270, 270);
        }
        
        // Set idle image for attack 2 of witch
        for(int i = 0; i < attack2Right.length; i++)
        {
            attack2Right[i] = new GreenfootImage("witch_attack2_idle/attack2_" + i + ".png");
            attack2Right[i].scale(270, 270);
        }
        for(int i = 0; i < attack2Left.length; i++)
        {
            attack2Left[i] = new GreenfootImage("witch_attack2_idle/attack2_" + i + ".png");
            attack2Left[i].mirrorHorizontally();
            attack2Left[i].scale(270, 270);
        }
        defaultTimer.mark();
        
        // Set witchAlive for every new game
        witchAlive = true;
        
        // Initial witch image
        setImage(defaultIdleRight[0]);
        
        // Set image for hp of witch
        for(int i = 0; i < witchHP.length; i++)
        {
            witchHP[i] = new GreenfootImage("hp_bar/witch_hp/witch_hp_" + i + ".png");
            witchHP[i].scale(70, 30);
        }
        witchCurrentHP = 5;
        
        // Set image for the attack cooldown bar
        for(int i = 0; i < 5; i++)
        {
            // Image idle for attack1
            attack1CooldownImg[i] = new GreenfootImage("cool_bar/coolbar_red/red_coolbar" + i + ".png");
            attack1CooldownImg[i].scale(125, 20);
            
            // Image idle for attack2
            attack2CooldownImg[i] = new GreenfootImage("cool_bar/coolbar_green/green_coolbar" + i + ".png");
            attack2CooldownImg[i].scale(125, 20);
        }
        
        // Set image for the flying cooldown bar
        for(int i = 0; i < 5; i++)
        {
            // Image idle for flying Cooldown bar
            flyingCooldownImg[i] = new GreenfootImage("cool_bar/flycoolbar_blue/blue_coolbar" + i + ".png");
            flyingCooldownImg[i].scale(60, 10);
        }
    }
    
    int imageIndex = 0;
    /**
     * Animates the witch's default idle animation
     * based on the direction she is facing.
     */
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
    
    int attackIndex1 = 0;
    /**
     * Animates the witch's level 1 attack.
     * Plays sound at the start of the attack.
     */
    public void animateAttackOne()
    {
        if(attackTimer1.millisElapsed() < 50)
        {
            return;
        }
        attackTimer1.mark();
        if(attackIndex1 == 0)
        {
            witchAttack1Sound.play();
        }
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
      
    int attackIndex2 = 0;
    /**
     * Animates the witch's level 2 attack.
     * Plays sound at the start of the attack.
     */
    public void animateAttackTwo()
    {
        if(attackTimer2.millisElapsed() < 50)
        {
            return;
        }
        attackTimer2.mark();
        if(attackIndex2 == 0)
        {
            witchAttack2Sound.play();
        }
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
    
    /**
     * Adds the witch's HurtBox, HP bar, and cooldown bars
     * when the witch is added to the world.
     * 
     * @param w the world the witch is added to
     */
    public void addedToWorld(World w)
    {
        // HurtBox
        hurtBox = new HurtBox(this, 50, 130);
        w.addObject(hurtBox, getX() - 25, getY());
        
        // HPBar
        witchHPBar = new HPBar(witchCurrentHP, witchHP);
        w.addObject(witchHPBar, getX() - 25, getY() - 80);
        
        // Cooldown Bar for Attack1
        attack1CooldownIcon = new Actor(){};
        attack1CooldownIcon.setImage(attack1CooldownImg[attack1CooldownLevel]);
        w.addObject(attack1CooldownIcon, 70, 30);
        
        // Cooldown Bar for Attack2
        attack2CooldownIcon = new Actor(){};
        attack2CooldownIcon.setImage(attack2CooldownImg[attack2CooldownLevel]);
        w.addObject(attack2CooldownIcon, 70, 60);
        
        // Save ground position
        groundY = getY();
        
        // Fly cooldown bar
        flyCooldownIcon = new Actor(){};
        flyCooldownIcon.setImage(flyingCooldownImg[flyCooldownLevel]);
        w.addObject(flyCooldownIcon, getX() - 25, getY() - 60);
    }
    
    /**
     * Applies damage to the witch.
     * Damage is ignored if invincibility or death is active.
     * 
     * @param damage the amount of damage taken
     */
    public void takeDamage(int damage)
    {
        if(invincibleTimer > 0 || isDying)
        {
            return;
        }
        witchCurrentHP -= damage;
        if(witchCurrentHP <= 0)
        {
            witchCurrentHP = 0;
            isDying = true;
            deathTimer = 0;
            // witchAlive = false;
        }
        witchHPBar.setHP(witchCurrentHP);
        invincibleTimer = 30;
    }
    
    /**
     * Heals the witch by the specified amount.
     * Healing is capped at maximum HP.
     * 
     * @param amount the amount of HP restored
     */
    public void heal(int amount)
    {
        if (witchCurrentHP <= 0) 
        {
           return; // can't heal the witch if dead
        } 
    
        witchCurrentHP += amount;
        if (witchCurrentHP > witchMaxHP)
        {
            witchCurrentHP = witchMaxHP;
        }
    
        witchHPBar.setHP(witchCurrentHP);
    }
    
    /**
     * Getter method that returns the witch's current HP.
     * 
     * @return current HP value
     */
    public int getHP()
    {
        return witchCurrentHP;
    }
    
    /**
     * Main act method which handles movement, flying, attacks, cooldowns,
     * animations, collision boxes, and game-over logic.
     */
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
        
        if(Greenfoot.isKeyDown("up") && flyCooldownLevel == 4 && !isFlying)
        {
            isFlying = true;
        }
        
        if(isFlying)
        {
            if(Greenfoot.isKeyDown("up"))
            {
                setLocation(getX(), getY() - flySpeed);
            }
            else if(Greenfoot.isKeyDown("down") && getY() < groundY)
            {
                setLocation(getX(), getY() + flySpeed);
            }
        }
        
        if(isFlying)
        {
            flyCooldownTimer++;
        
            if(flyCooldownTimer >= flyCooldownSpeed)
            {
                flyCooldownLevel--;
                flyCooldownTimer = 0;
                flyCooldownIcon.setImage(flyingCooldownImg[flyCooldownLevel]);
                if(flyCooldownLevel <= 0)
                {
                    flyCooldownLevel = 0;
                    isFlying = false;
                }
            }
        }
        
        // Gravity pulls witch back down
        if(!isFlying && getY() < groundY)
        {
            setLocation(getX(), getY() + gravitySpeed);
        }
        
        // Recharge flybar
        if(!isFlying && getY() >= groundY && flyCooldownLevel < 4)
        {
            flyCooldownTimer++;
        
            if(flyCooldownTimer >= flyBarCooldownSpeed)
            {
                flyCooldownLevel++;
                flyCooldownTimer = 0;
                flyCooldownIcon.setImage(flyingCooldownImg[flyCooldownLevel]);
            }
        }
        
        // Start attack 1
        if(Greenfoot.isKeyDown("shift") && !isAttacking1 && !isAttacking2 && attack1CooldownLevel == 4)
        {
            isAttacking1 = true;
            attackIndex1 = 0; // restart animation
            attackTimer1.mark();
        }
        
        // Start attack 2
        if(Greenfoot.isKeyDown("space") && !isAttacking2 && !isAttacking1 && attack2CooldownLevel == 4)
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
                
                // Set attack cool down to initial
                attack1CooldownLevel = 0;
                attack1CooldownTimer = 0;
                attack1CooldownIcon.setImage(attack1CooldownImg[attack1CooldownLevel]);
            }
        }
        else if(isAttacking2)
        {
            animateAttackTwo();
            if(attackIndex2 >= attack2Right.length)
            {
                isAttacking2 = false;
                attackIndex2 = 0;
                
                // Set attack cool down to initial
                attack2CooldownLevel = 0;
                attack2CooldownTimer = 0;
                attack2CooldownIcon.setImage(attack2CooldownImg[attack2CooldownLevel]);
            }
        }
        else
        {
            animateWitch();
        }
        
        // hurtBox setLocation
        if(hurtBox != null)
        {
            int offSetX;
            int flyingSetX;
            int hitBox1;
            int hitBox2;
            
            if(facing.equals("right"))
            {
                offSetX = hurtOffsetXRight;
                flyingSetX = flyingCoolOffsetXRight;
                hitBox1 = hitOffsetXRight1;
                hitBox2 = hitOffsetXRight2;
            }
            else
            {
                offSetX = hurtOffsetXLeft;
                flyingSetX = flyingCoolOffsetXLeft;
                hitBox1 = hitOffsetXLeft1;
                hitBox2 = hitOffsetXLeft2;
            }
            
            hurtBox.setLocation(getX() + offSetX, getY() + 6);
            flyCooldownIcon.setLocation(getX() + flyingSetX, getY() - 60);
            
            // Set attackBox1 add and remove
            if(isAttacking1 && attackIndex1 == 3 && attackBox1 == null)
            {
                attackBox1 = new AttackBox(130, 130, 1);
                getWorld().addObject(attackBox1, getX() + hitBox1, getY() + 6);
            }
            if(!isAttacking1 && attackBox1 != null)
            {
                getWorld().removeObject(attackBox1);
                attackBox1 = null;
            }
            
            // Set attackbox2 add and remove
            if(isAttacking2 && attackIndex2 == 3 && attackBox2 == null)
            {
                attackBox2 = new AttackBox(140, 130, 2);
                getWorld().addObject(attackBox2, getX() + hitBox2, getY() + 6);
            }
            if(!isAttacking2 && attackBox2 != null)
            {
                getWorld().removeObject(attackBox2);
                attackBox2 = null;
            }
        }
        
        // Move the HPBar with the witch
        if(witchHP != null)
        {
            if(facing.equals("right"))
            {
                witchHPBar.setLocation(getX() - 25, getY() - 80);
            }
            else
            {
                witchHPBar.setLocation(getX() + 30, getY() - 80);
            }
        }
        
        // Attack 1 cooldown recovery
        if (attack1CooldownLevel < 4)
        {
            attack1CooldownTimer++;
            if (attack1CooldownTimer >= attack1CooldownSpeed)
            {
                attack1CooldownLevel++;
                attack1CooldownTimer = 0;
                // Set image for cool down
                attack1CooldownIcon.setImage(attack1CooldownImg[attack1CooldownLevel]);
            }
        }
        
        // Attack 2 cooldown recovery
        if (attack2CooldownLevel < 4)
        {
            attack2CooldownTimer++;
            if (attack2CooldownTimer >= attack2CooldownSpeed)
            {
                attack2CooldownLevel++;
                attack2CooldownTimer = 0;
                // Set image for cool down
                attack2CooldownIcon.setImage(attack2CooldownImg[attack2CooldownLevel]);
            }
        }
        
        //invicibleTimer decrease
        if(invincibleTimer > 0)
        {
            invincibleTimer--;
        }
        
        //check if witch is alive
        if(isDying)
        {
            deathTimer++;
            
            if(deathTimer >= 60)
            {
                witchDeadSound.play();
                World w = getWorld();
    
                if(w instanceof MyWorld)
                {
                    ((MyWorld)w).gameOver();
                }
                else if(w instanceof MyWorld2)
                {
                    ((MyWorld2)w).gameOver();
                }
                else if(w instanceof MyWorld3)
                {
                    ((MyWorld3)w).gameOver();
                }
            }
        }
    }
}
