import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level2Golem here.
 * 
 * @author Adeline
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
    GreenfootSound golemAttackSound = new GreenfootSound("slimeAttackSound.mp3");
    GreenfootSound golemDeadSound = new GreenfootSound("slimeDeadSound.mp3");
    
    //Direction Golem is facing
    String direction = "left";
    
    //Direction Golem needs to travel
    Boolean moveRight = false;
    Boolean moveLeft = false;
    
    //Variable for attacks
    boolean isAttacking = false;
    
    //Boolean for whether Golem is alive
    public boolean isAlive = true;
    
    //SimpleTimer variables
    SimpleTimer attackTimer = new SimpleTimer();
    SimpleTimer walkTimer = new SimpleTimer();
    SimpleTimer deadTimer = new SimpleTimer();
    
    public static int level2GolemDamage = 0;
    
    // Image idles of hpbar of Golem
    public GreenfootImage[] level2GolemHP = new GreenfootImage[6];
    public HPBar golem2HPBar;
    private int golem2CurrentHP = 5;
    private int golem2MaxHP = 5;
    private int invincibleTimer = 0;
    
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
            level2GolemHP[i] = new GreenfootImage("hp_bar/monster1_hp/monster1_hp_" + i + ".png");
            level2GolemHP[i].scale(70, 30);
        }
        golem2CurrentHP = 5;
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
    
    //Animate death of Golem
    int deadImageIndex = 0;
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
        
        if(deadImageIndex == 14)
        {
            getWorld().removeObject(golem2HPBar);
            getWorld().removeObject(this);
        }
    }
    
    // Getter method to check if the Golem is alive
    public boolean isAlive()
    {   
        return isAlive;
    }

    //Animate walk of Golem
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
        level2GolemDamage += 1;
        return level2GolemDamage;
    }
    
    // Damage to change hp method
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
    
    // HPBar addedToWorld method
    public void addedToWorld(World w)
    {   
        // HPBar
        golem2HPBar = new HPBar(golem2CurrentHP, level2GolemHP);
        w.addObject(golem2HPBar, getX(), getY() - 45);
    }
    
    /**
     * Act - do whatever the Level2Golem wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
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
              golemAttackSound.play();
              attack();
          }
        }

        // Move the HPBar with the witch
        if(isAlive && level2GolemHP != null)
        {
          golem2HPBar.setLocation(getX(), getY() - 45);
        }
          
        //invicibleTimer decrease
        if(invincibleTimer > 0)
        {
            invincibleTimer--;
        }
    }
}
