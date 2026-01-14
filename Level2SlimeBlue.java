import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Level2SlimeBlue class displays a Level 2 blue slime enemy.
 * 
 * The slime automatically moves toward the witch, attacks on contact,
 * plays animations and sounds, and displays a health bar above itself.
 * 
 * @author Adeline & Hanna
 * @version December 2025
 */
public class Level2SlimeBlue extends Actor
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
    
    //Variable for attacks
    boolean isAttacking = false;
    
    //Boolean for whether SlimeRed is alive
    boolean isAlive = true;
    
    //SimpleTimer variables
    SimpleTimer attackTimer = new SimpleTimer();
    SimpleTimer walkTimer = new SimpleTimer();
    SimpleTimer deadTimer = new SimpleTimer();
    
    /**
     * Tracks total damage dealt by the slime.
     */
    public static int level2SlimeBlueDamage = 0;
    
    /**
     * Health bar images for the slime.
     */
    public GreenfootImage[] level2SlimeHP = new GreenfootImage[6];
    /**
     * Health bar displayed above the slime.
     */
    public HPBar slime2BlueHPBar;
    // Instances for slime hp modifying
    private int slime2CurrentHP = 5;
    private int slime2MaxHP = 5;
    private int invincibleTimer = 0;
    
    /**
     * The constructor that constructs a Level 2 blue slime 
     * and initializes animations and health.
     */
    public Level2SlimeBlue()
    {
        //Set idle image for walk of SlimeRed
        
        for(int i=0; i<walkRightImage.length; i++)
        {
            walkRightImage[i] = new GreenfootImage("images/Monsters/Level2/Level2SlimeBlue/walkRight/walkRight"+ i + ".png");
            walkRightImage[i].scale(58, 30);
        }
        
        for(int i=0; i<walkLeftImage.length; i++)
        {
            walkLeftImage[i] = new GreenfootImage("images/Monsters/Level2/Level2SlimeBlue/walkLeft/walkLeft"+ i + ".png");
            walkLeftImage[i].scale(58, 30);
        }
        
        //Set idle image for attack of SlimeRed
        for(int i=0; i<attackRightImage.length; i++)
        {
            attackRightImage[i] = new GreenfootImage("images/Monsters/Level2/Level2SlimeBlue/attackRight/attackRight"+ i + ".png");
            attackRightImage[i].scale(59,45);
        }
        
        for(int i=0; i<attackLeftImage.length; i++)
        {
            attackLeftImage[i] = new GreenfootImage("images/Monsters/Level2/Level2SlimeBlue/attackLeft/attackLeft"+ i + ".png");
            attackLeftImage[i].scale(59,45);
        }
        
        //Set idle image for death of SlimeRed
        for(int i=0; i<deadImage.length; i++)
        {
            deadImage[i] = new GreenfootImage("images/Monsters/Level2/Level2SlimeBlue/dead/dead"+ i + ".png");
            deadImage[i].scale(59, 30);
        }
        
        walkTimer.mark();
        
        //Initial SlimeRed image
        setImage(walkLeftImage[0]);
        
        // Set image for hp of Slime1Red
        for(int i = 0; i < level2SlimeHP.length; i++)
        {
            level2SlimeHP[i] = new GreenfootImage("hp_bar/monster1_hp/monster1_hp_" + i + ".png");
            level2SlimeHP[i].scale(70, 30);
        }
        slime2CurrentHP = 5;
    }
    
    int attackImageIndex = 0;
    /**
     * Animates the slime's attack sequence.
     */
    public void animateAttack()
    {
        if(attackTimer.millisElapsed() < 100)
        {
            return;
        }
        
        attackTimer.mark();
        
        if(direction.equals("right"))
        {
            setImage(attackRightImage[attackImageIndex]);
            attackImageIndex = (attackImageIndex+1) % attackRightImage.length;
        }
        else
        {
            setImage(attackLeftImage[attackImageIndex]);
            attackImageIndex = (attackImageIndex+1) % attackLeftImage.length;
        }
    }
    
    //Animate death of SlimeRed
    int deadImageIndex = 0;
    /**
     * Animates the slime's death sequence and removes it from the world.
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
            getWorld().removeObject(slime2BlueHPBar);
            getWorld().removeObject(this);
        }
    }
    
    //Animate walk of SlimeRed
    int walkImageIndex = 0;
    /**
     * Animates the slime's walking movement.
     */
    public void animateWalk()
    {
        if(walkTimer.millisElapsed() < 100)
        {
            return;
        }
        
        walkTimer.mark();
        
        if(direction.equals("right"))
        {
            setImage(walkRightImage[walkImageIndex]);
            walkImageIndex = (walkImageIndex+1) % walkRightImage.length;
        }
        else
        {
            setImage(walkLeftImage[walkImageIndex]);
            walkImageIndex = (walkImageIndex+1) % walkLeftImage.length;
        }
    }
    
    /**
     * Moves the slime toward the witch.
     */
    public void moveToWitch()
    {
        if(!isAttacking)
        {
            Witch witch = (Witch) getWorld().getObjects(Witch.class).get(0);
            int targetX = witch.getX();
            int distanceX = this.getX() - targetX;
            if(distanceX<0)
            {
                move(1);
                direction = "right";
            }
            else if(distanceX>0)
            {
                move(-1);
                direction = "left";
            }
            
            animateWalk();
        }
    } 
    
    /**
     * Performs an attack and returns the total damage dealt.
     * 
     * @return Total damage dealt by the slime
     */
    public int attack()
    {
        animateAttack();
        level2SlimeBlueDamage += 1;
        return level2SlimeBlueDamage;
    }
    
    /**
     * Applies damage to the slime and updates the health bar.
     * 
     * @param damage Amount of damage taken
     */
    public void takeDamage(int damage)
    {
        if(invincibleTimer > 0)
        {
            return;
        }
        slime2CurrentHP -= damage;
        
        if(slime2CurrentHP < 0)
        {
            slime2CurrentHP = 0;
        }
        
        slime2BlueHPBar.setHP(slime2CurrentHP);
        invincibleTimer = 70;
    }
    
    /**
     * Adds the slime's health bar when it is added to the world.
     * 
     * @param w The World that the object is added to
     */
    public void addedToWorld(World w)
    {   
        // HPBar
        slime2BlueHPBar = new HPBar(slime2CurrentHP, level2SlimeHP);
        w.addObject(slime2BlueHPBar, getX(), getY() - 45);
    }
    
    /**
     * The act method that updates the slime's movement, attack 
     * behavior, animations,and health each frame.
     */
    public void act()
    {
        if(slime2CurrentHP<=0)
        {
          isAlive = false;
          animateDeath();
        }
        
        if(isAlive)
        {
          moveToWitch();

          if(isTouching(HurtBox.class))
          {
              isAttacking = true;
          }
          else if(isTouching(HurtBox.class) == false)
          {
              isAttacking = false;
          }
          
          if(isAttacking)
          {
              slimeAttackSound.play();
              attack();
          }
        }

        // Move the HPBar with the witch
        if(isAlive && level2SlimeHP != null)
        {
          slime2BlueHPBar.setLocation(getX(), getY() - 45);
        }
          
        //invicibleTimer decrease
        if(invincibleTimer > 0)
        {
            invincibleTimer--;
        }
    }
}
