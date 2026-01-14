import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Represents a Level 2 Golem enemy.
 * 
 * The golem moves toward the witch, attacks when in contact,
 * plays animations and sounds, and displays a health bar above itself.
 * It has higher health and larger animations compared to Level 1 enemies.
 * 
 * @author Adeline & Hanna
 * @version December 2025
 */
public class Level2Golem extends Actor
{
    //Idle images of Golem
    GreenfootImage[] walkRightImage = new GreenfootImage[12];
    GreenfootImage[] attackRightImage = new GreenfootImage[12];
    GreenfootImage[] walkLeftImage = new GreenfootImage[12];
    GreenfootImage[] attackLeftImage = new GreenfootImage[12];
    GreenfootImage[] deadRightImage = new GreenfootImage[15];
    GreenfootImage[] deadLeftImage = new GreenfootImage[15];
    
    //Sounds for Golem
    GreenfootSound golemAttackSound = new GreenfootSound("whackSound.mp3");
    GreenfootSound golemDeadSound = new GreenfootSound("slimeDeadSound.mp3");
    
    //Direction Golem is facing
    String direction = "left";
    
    //Direction Golem needs to travel
    Boolean moveRight = false;
    Boolean moveLeft = false;
    
    //Variable for attacks
    boolean isAttacking = false;
    
    // Indicates whether the golem is alive.
    public boolean isAlive = true;
    
    //SimpleTimer variables
    SimpleTimer attackTimer = new SimpleTimer();
    SimpleTimer walkTimer = new SimpleTimer();
    SimpleTimer deadTimer = new SimpleTimer();
    
    // Tracks total damage dealt by the golem.
    public static int level2GolemDamage = 0;
    
    // Health bar images for the golem.
    public GreenfootImage[] level2GolemHP = new GreenfootImage[6];
    // Health bar displayed above the golem.
    public HPBar golem2HPBar;
    private int golem2CurrentHP = 5;
    private int golem2MaxHP = 5;
    private int invincibleTimer = 0;
    
    /**
     * The constructor which constructs a Level 2 Golem and 
     * initializes animations and health.
     */
    public Level2Golem()
    {
        //Set idle image for walk of SlimeRed
        
        for(int i=0; i<walkRightImage.length; i++)
        {
            walkRightImage[i] = new GreenfootImage("images/Monsters/Level2/Level2Golem/walkRight/walkRight"+ i + ".png");
            walkRightImage[i].scale(150, 150);
        }
        
        for(int i=0; i<walkLeftImage.length; i++)
        {
            walkLeftImage[i] = new GreenfootImage("images/Monsters/Level2/Level2Golem/walkLeft/walkLeft"+ i + ".png");
            walkLeftImage[i].scale(150, 150);
        }
        
        //Set idle image for attack of Golem
        for(int i=0; i<attackRightImage.length; i++)
        {
            attackRightImage[i] = new GreenfootImage("images/Monsters/Level2/Level2Golem/attackRight/attackRight"+ i + ".png");
            attackRightImage[i].scale(150,150);
        }
        
        for(int i=0; i<attackLeftImage.length; i++)
        {
            attackLeftImage[i] = new GreenfootImage("images/Monsters/Level2/Level2Golem/attackLeft/attackLeft"+ i + ".png");
            attackLeftImage[i].scale(150,150);
        }
        
        //Set idle image for death of Golem
        for(int i=0; i<deadLeftImage.length; i++)
        {
            deadLeftImage[i] = new GreenfootImage("images/Monsters/Level2/Level2Golem/deadLeft/deadLeft"+ i + ".png");
            deadLeftImage[i].scale(150, 150);
        }
        
        //Set idle image for death of Golem
        for(int i=0; i<deadRightImage.length; i++)
        {
            deadRightImage[i] = new GreenfootImage("images/Monsters/Level2/Level2Golem/deadRight/deadRight"+ i + ".png");
            deadRightImage[i].scale(150, 150);
        }
        
        walkTimer.mark();
        
        //Initial Golem image
        setImage(walkLeftImage[0]);
        
        // Set image for hp of Golem
        for(int i = 0; i < level2GolemHP.length; i++)
        {
            level2GolemHP[i] = new GreenfootImage("hp_bar/monster2_hp/monster2_hp_" + i + ".png");
            level2GolemHP[i].scale(70, 30);
        }
        golem2CurrentHP = 5;
    }
    
    int attackImageIndex = 0;
    /**
     * Animates the golem's attack sequence.
     */
    public void animateAttack()
    {
        if(attackTimer.millisElapsed() < 100)
        {
            return;
        }
        
        attackTimer.mark();
        
        if(attackImageIndex == 1)
        {
            golemAttackSound.play();
        }
        
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
    
    //Animate death of Golem
    int deadImageIndex = 0;
    /**
     * Animates the golem's death sequence and removes it from the world.
     */
    public void animateDeath()
    {
        if(deadTimer.millisElapsed() < 100)
        {
            return;
        }
        
        deadTimer.mark();
        if(direction.equals("right"))
        {
          setImage(deadLeftImage[deadImageIndex]);
            deadImageIndex = (deadImageIndex+1);
            golemDeadSound.play();  
        }
        else
        {
          setImage(deadRightImage[deadImageIndex]);
            deadImageIndex = (deadImageIndex+1);
            golemDeadSound.play();  
        }
        
        if (deadImageIndex >= deadLeftImage.length) 
        {
            getWorld().removeObject(golem2HPBar);
            getWorld().removeObject(this);
        }
    }
    
    // Getter method to check if the Golem is alive
    /**
     * Returns whether the golem is alive.
     * 
     * @return true if alive, false otherwise
     */
    public boolean isAlive()
    {   
        return isAlive;
    }

    //Animate walk of Golem
    int walkImageIndex = 0;
    /**
     * Animates the golem's walking movement.
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
     * Moves the golem toward the witch.
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
     * @return Total damage dealt by the golem
     */
    public int attack()
    {
        animateAttack();
        level2GolemDamage += 1;
        return level2GolemDamage;
    }
    
    /**
     * Applies damage to the golem and updates the health bar.
     * 
     * @param damage Amount of damage taken
     */
    public void takeDamage(int damage)
    {
        if(invincibleTimer > 0)
        {
            return;
        }
        golem2CurrentHP -= damage;
        
        if(golem2CurrentHP < 0)
        {
            golem2CurrentHP = 0;
        }
        
        golem2HPBar.setHP(golem2CurrentHP);
        invincibleTimer = 70;
    }
    
    /**
     * Adds the golem's health bar when it is added to the world.
     * 
     * @param w World that the golem hp bar is added to
     */
    public void addedToWorld(World w)
    {   
        // HPBar
        golem2HPBar = new HPBar(golem2CurrentHP, level2GolemHP);
        w.addObject(golem2HPBar, getX(), getY() - 60);
    }
    
    /**
     * The act method that updates the golem's movement, attack 
     * behavior, animations,and health each frame.
     */
    public void act()
    {
        if(golem2CurrentHP<=0)
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
              attack();
          }
        }

        // Move the HPBar with the witch
        if(isAlive && level2GolemHP != null)
        {
          golem2HPBar.setLocation(getX(), getY() - 60);
        }
          
        //invicibleTimer decrease
        if(invincibleTimer > 0)
        {
            invincibleTimer--;
        }
    }
}
