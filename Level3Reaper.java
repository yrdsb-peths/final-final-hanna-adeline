import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level3Reaper here.
 * 
 * @author Adeline
 * @version December 2025
 */
public class Level3Reaper extends Actor
{
    //Idle images of Monster
    GreenfootImage[] walkRightImage = new GreenfootImage[12];
    GreenfootImage[] attackRightImage = new GreenfootImage[12];
    GreenfootImage[] walkLeftImage = new GreenfootImage[12];
    GreenfootImage[] attackLeftImage = new GreenfootImage[12];
    GreenfootImage[] deadRightImage = new GreenfootImage[15];
    GreenfootImage[] deadLeftImage = new GreenfootImage[15];
    
    //Sounds for Monster
    GreenfootSound monsterAttackSound = new GreenfootSound("slimeAttackSound.mp3");
    GreenfootSound monsterDeadSound = new GreenfootSound("slimeDeadSound.mp3");
    
    //Direction Monster is facing
    String direction = "left";
    
    //Direction Monster needs to travel
    Boolean moveRight = false;
    Boolean moveLeft = false;
    
    //Variable for attacks
    boolean isAttacking = false;
    
    //Boolean for whether Monster is alive
    boolean isAlive = true;
    
    //SimpleTimer variables
    SimpleTimer attackTimer = new SimpleTimer();
    SimpleTimer walkTimer = new SimpleTimer();
    SimpleTimer deadTimer = new SimpleTimer();
    
    public static int level3MonsterDamage = 0;
    
    // Image idles of hpbar of Monster
    public GreenfootImage[] level3MonsterHP = new GreenfootImage[6];
    public HPBar monster3HPBar;
    private int monster3CurrentHP = 5;
    private int monster3MaxHP = 5;
    private int invincibleTimer = 0;
    
    public Level3Reaper()
    {
        //Set idle image for walk of Monster
        
        for(int i=0; i<walkRightImage.length; i++)
        {
            walkRightImage[i] = new GreenfootImage("images/Monsters/Level3/Level3Monster/walkRight/walkRight"+ i + ".png");
            walkRightImage[i].scale(150, 150);
        }
        
        for(int i=0; i<walkLeftImage.length; i++)
        {
            walkLeftImage[i] = new GreenfootImage("images/Monsters/Level3/Level3Monster/walkLeft/walkLeft"+ i + ".png");
            walkLeftImage[i].scale(150, 150);
        }
        
        //Set idle image for attack of Monster
        for(int i=0; i<attackRightImage.length; i++)
        {
            attackRightImage[i] = new GreenfootImage("images/Monsters/Level3/Level3Monster/attackRight/attackRight"+ i + ".png");
            attackRightImage[i].scale(150,150);
        }
        
        for(int i=0; i<attackLeftImage.length; i++)
        {
            attackLeftImage[i] = new GreenfootImage("images/Monsters/Level3/Level3Monster/attackLeft/attackLeft"+ i + ".png");
            attackLeftImage[i].scale(150,150);
        }
        
        //Set idle image for death of Monster
        for(int i=0; i<deadLeftImage.length; i++)
        {
            deadLeftImage[i] = new GreenfootImage("images/Monsters/Level3/Level3Monster/deadLeft/deadLeft"+ i + ".png");
            deadLeftImage[i].scale(150, 150);
        }
        
        //Set idle image for death of Monster
        for(int i=0; i<deadRightImage.length; i++)
        {
            deadRightImage[i] = new GreenfootImage("images/Monsters/Level3/Level3Monster/deadRight/deadRight"+ i + ".png");
            deadRightImage[i].scale(150, 150);
        }
        
        walkTimer.mark();
        
        //Initial Golem image
        setImage(walkLeftImage[0]);
        
        // Set image for hp of Golem
        for(int i = 0; i < level3MonsterHP.length; i++)
        {
            level3MonsterHP[i] = new GreenfootImage("hp_bar/monster1_hp/monster1_hp_" + i + ".png");
            level3MonsterHP[i].scale(70, 30);
        }
        monster3CurrentHP = 5;
    }
    
    int attackImageIndex = 0;
    /**
     * Animate attack of the Monster
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
    
    //Animate death of Monster
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
            monsterDeadSound.play();  
        }
        else
        {
          setImage(deadRightImage[deadImageIndex]);
            deadImageIndex = (deadImageIndex+1);
            monsterDeadSound.play();  
        }
        
        if(deadImageIndex == 14)
        {
            getWorld().removeObject(monster3HPBar);
            getWorld().removeObject(this);
        }
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
        level3MonsterDamage += 1;
        return level3MonsterDamage;
    }
    
    // Damage to change hp method
    public void takeDamage(int damage)
    {
        if(invincibleTimer > 0)
        {
            return;
        }
        monster3CurrentHP -= damage;
        
        if(monster3CurrentHP < 0)
        {
            monster3CurrentHP = 0;
        }
        
        monster3HPBar.setHP(monster3CurrentHP);
        invincibleTimer = 70;
    }
    
    // HPBar addedToWorld method
    public void addedToWorld(World w)
    {   
        // HPBar
        monster3HPBar = new HPBar(monster3CurrentHP, level3MonsterHP);
        w.addObject(monster3HPBar, getX(), getY() - 45);
    }
    
    /**
     * Act - do whatever the Level3Reaper wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(monster3CurrentHP<=0)
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
              monsterAttackSound.play();
              attack();
          }
        }

        // Move the HPBar with the witch
        if(isAlive && level3MonsterHP != null)
        {
          monster3HPBar.setLocation(getX(), getY() - 45);
        }
          
        //invicibleTimer decrease
        if(invincibleTimer > 0)
        {
            invincibleTimer--;
        }
    }
}
