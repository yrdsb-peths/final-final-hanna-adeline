import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TutorialTarget here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
    
    // Damage to change hp method
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
    
    // HPBar addedToWorld method
    public void addedToWorld(World w)
    {   
        // HPBar
        slime1RedHPBar = new HPBar(slime1CurrentHP, level1SlimeHP);
        w.addObject(slime1RedHPBar, getX(), getY() - 45);
    }
    
    /**
     * Act - do whatever the Level1SlimeRed wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
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
