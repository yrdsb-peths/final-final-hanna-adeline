import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The TutorialTarget class represents a stationary enemy used
 * for tutorial or practice purposes. It allows the player to
 * learn combat mechanics such as attacking and dealing damage.
 * 
 * The target has health, a health bar, damage invincibility frames,
 * and a death animation.
 * 
 * @author Hanna
 * @version December 2025
 */
public class TutorialTarget extends Actor
{
    //Idle images of SlimeRed
    GreenfootImage[] walkRightImage = new GreenfootImage[7];
    GreenfootImage[] attackRightImage = new GreenfootImage[5];
    GreenfootImage[] walkLeftImage = new GreenfootImage[7];
    GreenfootImage[] attackLeftImage = new GreenfootImage[5];
    GreenfootImage[] deadImage = new GreenfootImage[3];
    
    //Sounds for SlimeRed
    GreenfootSound slimeAttackSound = new GreenfootSound("slimeAttackSound.mp3");
    GreenfootSound slimeDeadSound = new GreenfootSound("slimeDeadSound.mp3");
    
    //Direction SlimeRed is facing
    String direction = "left";
    
    //Direction SlimeRed needs to travel
    Boolean moveRight = false;
    Boolean moveLeft = false;
    
    //Boolean for whether SlimeRed is alive
    boolean isAlive = true;
    
    //SimpleTimer variables
    SimpleTimer attackTimer = new SimpleTimer();
    SimpleTimer walkTimer = new SimpleTimer();
    SimpleTimer deadTimer = new SimpleTimer();
    
    public static int level1SlimeRedDamage = 0;
    
    // Image idles of hpbar of SlimeRed
    public GreenfootImage[] level1SlimeHP = new GreenfootImage[6];
    public HPBar slime1RedHPBar;
    private int slime1CurrentHP = 5;
    private int slime1MaxHP = 5;
    private int invincibleTimer = 0;
    
    /**
     * Constructs a TutorialTarget and initializes its animations,
     * health values, and default image.
     */
    public TutorialTarget()
    {
        //Set idle image for walk of SlimeRed
        for(int i=0; i<walkRightImage.length; i++)
        {
            walkRightImage[i] = new GreenfootImage("images/Monsters/Level1/Level1SlimeRed/walkRight/walkRight"+ i + ".png");
            walkRightImage[i].scale(58, 30);
        }
        
        for(int i=0; i<walkLeftImage.length; i++)
        {
            walkLeftImage[i] = new GreenfootImage("images/Monsters/Level1/Level1SlimeRed/walkLeft/walkLeft"+ i + ".png");
            walkLeftImage[i].scale(58, 30);
        }
        
        //Set idle image for death of SlimeRed
        for(int i=0; i<deadImage.length; i++)
        {
            deadImage[i] = new GreenfootImage("images/Monsters/Level1/Level1SlimeRed/dead/dead"+ i + ".png");
            deadImage[i].scale(59, 30);
        }
        
        //Initial SlimeRed image
        setImage(walkLeftImage[0]);
        
        // Set image for hp of Slime1Red
        for(int i = 0; i < level1SlimeHP.length; i++)
        {
            level1SlimeHP[i] = new GreenfootImage("hp_bar/monster2_hp/monster2_hp_" + i + ".png");
            level1SlimeHP[i].scale(70, 30);
        }
        slime1CurrentHP = 5;
    }
    
    //Animate death of SlimeRed
    int deadImageIndex = 0;
    /**
     * Plays the death animation and removes the target
     * and its HP bar from the world once complete.
     */
    public void animateDeath()
    {
        if(deadTimer.millisElapsed() < 425)
        {
            return;
        }
        
        deadTimer.mark();
        
        setImage(deadImage[deadImageIndex]);
        deadImageIndex = (deadImageIndex+1);
        slimeDeadSound.play();
        if(deadImageIndex == 3)
        {
            getWorld().removeObject(slime1RedHPBar);
            getWorld().removeObject(this);
        }
    }
    
    /**
     * Reduces the target's health when damaged.
     * Damage is ignored if the invincibility timer is active.
     *
     * @param damage the amount of damage taken
     */
    public void takeDamage(int damage)
    {
        if(invincibleTimer > 0)
        {
            return;
        }
        slime1CurrentHP -= damage;
        
        if(slime1CurrentHP < 0)
        {
            slime1CurrentHP = 0;
        }
        
        slime1RedHPBar.setHP(slime1CurrentHP);
        invincibleTimer = 70;
    }
    
    /**
     * Adds the HP bar to the world when the target
     * is added to the game world.
     *
     * @param w the world the target is added to
     */
    public void addedToWorld(World w)
    {   
        // HPBar
        slime1RedHPBar = new HPBar(slime1CurrentHP, level1SlimeHP);
        w.addObject(slime1RedHPBar, getX(), getY() - 45);
    }
    
    /**
     * Act method called each game cycle.
     * Handles death, HP bar positioning, and invincibility timing.
     */
    public void act()
    {
        if(slime1CurrentHP<=0)
        {
          isAlive = false;
          animateDeath();
        }

        // Move the HPBar with the witch
        if(isAlive && level1SlimeHP != null)
        {
          slime1RedHPBar.setLocation(getX(), getY() - 45);
        }
          
        //invicibleTimer decrease
        if(invincibleTimer > 0)
        {
            invincibleTimer--;
        }
    }
}
