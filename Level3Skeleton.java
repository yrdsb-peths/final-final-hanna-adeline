import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Level3Skeleton class represents a skeleton enemy in Level 3.
 * 
 * The skeleton moves toward the witch, performs slash attacks,
 * takes damage, displays a health bar, and plays a death animation
 * when defeated.
 * 
 * This class manages movement, animation, sound effects,
 * combat behavior, and HP tracking.
 * 
 * @author Adeline
 * @version December 2025
 */
public class Level3Skeleton extends Actor
{
    //Idle images of Skeleton
    GreenfootImage[] walkRightImage = new GreenfootImage[12];
    GreenfootImage[] walkLeftImage = new GreenfootImage[12];
    GreenfootImage[] slashRightImage = new GreenfootImage[12];
    GreenfootImage[] slashLeftImage = new GreenfootImage[12];
    GreenfootImage[] kickRightImage = new GreenfootImage[12];
    GreenfootImage[] kickLeftImage = new GreenfootImage[12];
    GreenfootImage[] deadRightImage = new GreenfootImage[15];
    GreenfootImage[] deadLeftImage = new GreenfootImage[15];
    
    //Sounds for Skeleton
    GreenfootSound skeletonAttackSound = new GreenfootSound("swordSound.mp3");
    GreenfootSound skeletonDeadSound = new GreenfootSound("slimeDeadSound.mp3");
    
    //Direction Skeleton is facing
    String direction = "left";
    
    //Direction Skeleton needs to travel
    Boolean moveRight = false;
    Boolean moveLeft = false;
    
    //Variable for attacks
    boolean isAttacking = false;
    
    //Boolean for whether Skeleton is alive
    public boolean isAlive = true;
    
    //SimpleTimer variables
    SimpleTimer slashTimer = new SimpleTimer();
    SimpleTimer walkTimer = new SimpleTimer();
    SimpleTimer deadTimer = new SimpleTimer();
    
    public static int level3SkeletonDamage = 0;
    
    // Image idles of hpbar of Skeleton
    public GreenfootImage[] level3SkeletonHP = new GreenfootImage[6];
    public HPBar skeleton3HPBar;
    private int skeleton3CurrentHP = 5;
    private int skeleton3MaxHP = 5;
    private int invincibleTimer = 0;
    
    /**
     * Constructs a Level3Skeleton object.
     * Loads animation images, initializes health,
     * and sets the starting image.
     */
    public Level3Skeleton()
    {
        //Set idle image for walk of Skeleton
        
        for(int i=0; i<walkRightImage.length; i++)
        {
            walkRightImage[i] = new GreenfootImage("images/Monsters/Level3/Level3Skeleton/walkRight/walkRight"+ i + ".png");
            walkRightImage[i].scale(150, 150);
        }
        
        for(int i=0; i<walkLeftImage.length; i++)
        {
            walkLeftImage[i] = new GreenfootImage("images/Monsters/Level3/Level3Skeleton/walkLeft/walkLeft"+ i + ".png");
            walkLeftImage[i].scale(150, 150);
        }
        
        //Set idle image for slash attack of Skeleton
        for(int i=0; i<slashRightImage.length; i++)
        {
            slashRightImage[i] = new GreenfootImage("images/Monsters/Level3/Level3Skeleton/slashRight/slashRight"+ i + ".png");
            slashRightImage[i].scale(150,150);
        }
        
        for(int i=0; i<slashLeftImage.length; i++)
        {
            slashLeftImage[i] = new GreenfootImage("images/Monsters/Level3/Level3Skeleton/slashLeft/slashLeft"+ i + ".png");
            slashLeftImage[i].scale(150,150);
        }
        
        //Set idle image for death of Skeleton
        for(int i=0; i<deadLeftImage.length; i++)
        {
            deadLeftImage[i] = new GreenfootImage("images/Monsters/Level3/Level3Skeleton/deadLeft/deadLeft"+ i + ".png");
            deadLeftImage[i].scale(150, 150);
        }
        
        //Set idle image for death of Skeleton
        for(int i=0; i<deadRightImage.length; i++)
        {
            deadRightImage[i] = new GreenfootImage("images/Monsters/Level3/Level3Skeleton/deadRight/deadRight"+ i + ".png");
            deadRightImage[i].scale(150, 150);
        }
        
        walkTimer.mark();
        
        //Initial Skeleton image
        setImage(walkLeftImage[0]);
        
        // Set image for hp of Skeleton
        for(int i = 0; i < level3SkeletonHP.length; i++)
        {
            level3SkeletonHP[i] = new GreenfootImage("hp_bar/monster2_hp/monster2_hp_" + i + ".png");
            level3SkeletonHP[i].scale(70, 30);
        }
        skeleton3CurrentHP = 5;
    }
    
    int slashImageIndex = 0;
    /**
     * Animate slash attack of Skeleton
     */
    public void animateSlashAttack()
    {
        if(slashTimer.millisElapsed() < 100)
        {
            return;
        }
        
        slashTimer.mark();
        if(slashImageIndex == 1)
        {
            skeletonAttackSound.play();
        }
        
        if(direction.equals("right"))
        {
            setImage(slashRightImage[slashImageIndex]);
            slashImageIndex = (slashImageIndex+1) % slashRightImage.length;
        }
        else
        {
            setImage(slashLeftImage[slashImageIndex]);
            slashImageIndex = (slashImageIndex+1) % slashLeftImage.length;
        }
        if(slashImageIndex == 11)
        {
            skeletonAttackSound.play();
        }
    }
    
    int deadImageIndex = 0;
     /**
     * Animates the skeleton's death sequence
     * and removes it from the world.
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
            skeletonDeadSound.play();  
        }
        else
        {
          setImage(deadRightImage[deadImageIndex]);
            deadImageIndex = (deadImageIndex+1);
            skeletonDeadSound.play();  
        }
        
        if(deadImageIndex >= deadLeftImage.length)
        {
            getWorld().removeObject(skeleton3HPBar);
            getWorld().removeObject(this);
        }
    }
    
    /**
     * Returns whether the skeleton is alive.
     * 
     * @return true if alive, false otherwise
     */
    /**
     * Returns whether the skeleton is alive.
     * 
     * @return true if alive, false otherwise
     */
    public boolean isAlive()
    {   
        return isAlive;
    }
    
    int walkImageIndex = 0;
    /**
     * Animates the skeleton walking.
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
     * Moves to the witch's location
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
     * Performs a slash attack.
     * 
     * @return damage dealt by the skeleton
     */
    public int attack()
    {
        animateSlashAttack();
        level3SkeletonDamage += 1;
        return level3SkeletonDamage;
    }
    
    /**
     * Reduces the skeleton's HP when damaged.
     * 
     * @param damage amount of damage taken
     */
    public void takeDamage(int damage)
    {
        if(invincibleTimer > 0)
        {
            return;
        }
        skeleton3CurrentHP -= damage;
        
        if(skeleton3CurrentHP < 0)
        {
            skeleton3CurrentHP = 0;
        }
        
        skeleton3HPBar.setHP(skeleton3CurrentHP);
        invincibleTimer = 70;
    }
    
    /**
     * Adds the HP bar when the skeleton is added to the world.
     * 
     * @param w the world the skeleton is added to
     */
    public void addedToWorld(World w)
    {   
        // HPBar
        skeleton3HPBar = new HPBar(skeleton3CurrentHP, level3SkeletonHP);
        w.addObject(skeleton3HPBar, getX(), getY() - 60);
    }
    
    /**
     * Act method called every frame.
     * Handles movement, attacking, death,
     * HP bar updates, and invincibility timing.
     */
    public void act()
    {
        if(skeleton3CurrentHP<=0)
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
        if(isAlive && level3SkeletonHP != null)
        {
          skeleton3HPBar.setLocation(getX(), getY() - 60);
        }
          
        //invicibleTimer decrease
        if(invincibleTimer > 0)
        {
            invincibleTimer--;
        }
    }
}
