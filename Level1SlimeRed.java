import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level1SlimeRed here.
 * 
 * @author Adeline
 * @version December 2025
 */
public class Level1SlimeRed extends Actor
{
    //Idle images of SlimeRed
    GreenfootImage[] walkRightImage = new GreenfootImage[7];
    GreenfootImage[] attackRightImage = new GreenfootImage[5];
    GreenfootImage[] walkLeftImage = new GreenfootImage[7];
    GreenfootImage[] attackLeftImage = new GreenfootImage[5];
    GreenfootImage[] deadImage = new GreenfootImage[3];
    
    //Sounds for SlimeRed
    GreenfootSound slimeAttackSound = new GreenfootSound("slimeAttackSound.mp3");
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
    
    public static int level1SlimeRedDamage = 0;
    
    // Image idles of hpbar of SlimeRed
    public GreenfootImage[] level1SlimeHP = new GreenfootImage[6];
    public HPBar slime1RedHPBar;
    private int slime1CurrentHP = 5;
    private int slime1MaxHP = 5;
    private int invincibleTimer = 0;
    
    public Level1SlimeRed()
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
        
        //Set idle image for attack of SlimeRed
        for(int i=0; i<attackRightImage.length; i++)
        {
            attackRightImage[i] = new GreenfootImage("images/Monsters/Level1/Level1SlimeRed/attackRight/attackRight"+ i + ".png");
            attackRightImage[i].scale(59,45);
        }
        
        for(int i=0; i<attackLeftImage.length; i++)
        {
            attackLeftImage[i] = new GreenfootImage("images/Monsters/Level1/Level1SlimeRed/attackLeft/attackLeft"+ i + ".png");
            attackLeftImage[i].scale(59,45);
        }
        
        //Set idle image for death of SlimeRed
        for(int i=0; i<deadImage.length; i++)
        {
            deadImage[i] = new GreenfootImage("images/Monsters/Level1/Level1SlimeRed/dead/dead"+ i + ".png");
            deadImage[i].scale(128, 128);
        }
        
        walkTimer.mark();
        
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
    
    int attackImageIndex = 0;
    /**
     * Animate attack of the SlimeRed
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
    
    //Animate walk of SlimeRed
    int walkImageIndex = 0;
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
    
    public int attack()
    {
        animateAttack();
        level1SlimeRedDamage += 1;
        return level1SlimeRedDamage;
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
        invincibleTimer = 50;
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
          getWorld().removeObject(slime1RedHPBar);
          getWorld().removeObject(this);
          ((MyWorld)getWorld()).level1Complete = true;
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
