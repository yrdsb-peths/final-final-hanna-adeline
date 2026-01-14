import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level3Reaper here.
 * 
 * @author Adeline
 * @version December 2025
 */
public class Level3Reaper extends Actor
{
    //Idle images of Reaper
    GreenfootImage[] walkRightImage = new GreenfootImage[12];
    GreenfootImage[] walkLeftImage = new GreenfootImage[12];
    GreenfootImage[] slashRightImage = new GreenfootImage[12];
    GreenfootImage[] slashLeftImage = new GreenfootImage[12];
    GreenfootImage[] kickRightImage = new GreenfootImage[12];
    GreenfootImage[] kickLeftImage = new GreenfootImage[12];
    GreenfootImage[] deadRightImage = new GreenfootImage[15];
    GreenfootImage[] deadLeftImage = new GreenfootImage[15];
    
    //Sounds for Reaper
    GreenfootSound reaperSlashAttackSound = new GreenfootSound("slashSound.mp3");
    GreenfootSound reaperKickAttackSound = new GreenfootSound("kickSound.mp3");
    GreenfootSound reaperDeadSound = new GreenfootSound("slimeDeadSound.mp3");
    
    //Direction Reaper is facing
    String direction = "left";
    
    //Direction Reaper needs to travel
    Boolean moveRight = false;
    Boolean moveLeft = false;
    
    //Variable for attacks
    boolean isAttacking = false;
    private String attackType = "";
    boolean attackChosen = false;
    boolean attackSoundIsPlaying = false;
    
    //Boolean for whether Reaper is alive
    boolean isAlive = true;
    
    //SimpleTimer variables
    SimpleTimer slashTimer = new SimpleTimer();
    SimpleTimer kickTimer = new SimpleTimer();
    SimpleTimer walkTimer = new SimpleTimer();
    SimpleTimer deadTimer = new SimpleTimer();
    
    public static int level3ReaperDamage = 0;
    
    // Image idles of hpbar of Reaper
    public GreenfootImage[] level3ReaperHP = new GreenfootImage[6];
    public HPBar reaper3HPBar;
    private int reaper3CurrentHP = 5;
    private int reaper3MaxHP = 5;
    private int invincibleTimer = 0;
    
    
    public Level3Reaper()
    {
        //Set idle image for walk of Reaper
        
        for(int i=0; i<walkRightImage.length; i++)
        {
            walkRightImage[i] = new GreenfootImage("images/Monsters/Level3/Level3GrimReaper/Reaper/walkRight/walkRight"+ i + ".png");
            walkRightImage[i].scale(150, 150);
        }
        
        for(int i=0; i<walkLeftImage.length; i++)
        {
            walkLeftImage[i] = new GreenfootImage("images/Monsters/Level3/Level3GrimReaper/Reaper/walkLeft/walkLeft"+ i + ".png");
            walkLeftImage[i].scale(150, 150);
        }
        
        //Set idle image for slash attack of Reaper
        for(int i=0; i<slashRightImage.length; i++)
        {
            slashRightImage[i] = new GreenfootImage("images/Monsters/Level3/Level3GrimReaper/Reaper/slashRight/slashRight"+ i + ".png");
            slashRightImage[i].scale(150,150);
        }
        
        for(int i=0; i<slashLeftImage.length; i++)
        {
            slashLeftImage[i] = new GreenfootImage("images/Monsters/Level3/Level3GrimReaper/Reaper/slashLeft/slashLeft"+ i + ".png");
            slashLeftImage[i].scale(150,150);
        }
        
        //Set idle image for kick attack of Reaper
        for(int i=0; i<kickRightImage.length; i++)
        {
            kickRightImage[i] = new GreenfootImage("images/Monsters/Level3/Level3GrimReaper/Reaper/kickRight/kickRight"+ i + ".png");
            kickRightImage[i].scale(150,150);
        }
        
        for(int i=0; i<kickLeftImage.length; i++)
        {
            kickLeftImage[i] = new GreenfootImage("images/Monsters/Level3/Level3GrimReaper/Reaper/kickLeft/kickLeft"+ i + ".png");
            kickLeftImage[i].scale(150,150);
        }
        
        //Set idle image for death of Reaper
        for(int i=0; i<deadLeftImage.length; i++)
        {
            deadLeftImage[i] = new GreenfootImage("images/Monsters/Level3/Level3GrimReaper/Reaper/deadLeft/deadLeft"+ i + ".png");
            deadLeftImage[i].scale(150, 150);
        }
        
        //Set idle image for death of Reaper
        for(int i=0; i<deadRightImage.length; i++)
        {
            deadRightImage[i] = new GreenfootImage("images/Monsters/Level3/Level3GrimReaper/Reaper/deadRight/deadRight"+ i + ".png");
            deadRightImage[i].scale(150, 150);
        }
        
        walkTimer.mark();
        
        //Initial Reaper image
        setImage(walkLeftImage[0]);
        
        // Set image for hp of Reaper
        for(int i = 0; i < level3ReaperHP.length; i++)
        {
            level3ReaperHP[i] = new GreenfootImage("hp_bar/monster1_hp/monster1_hp_" + i + ".png");
            level3ReaperHP[i].scale(70, 30);
        }
        reaper3CurrentHP = 5;
    }
    
    int slashImageIndex = 0;
    /**
     * Animate slash attack of Reaper
     */
    
    public void animateSlashAttack()
    {
        if(slashTimer.millisElapsed() < 75)
        {
            return;
        }
        
        slashTimer.mark();
        
        if(slashImageIndex == 1)
        {
            reaperSlashAttackSound.play();
        }
        
        if(direction.equals("right"))
        {
            setImage(slashRightImage[slashImageIndex]);
            slashImageIndex = (slashImageIndex+1);
        }
        else
        {
            setImage(slashLeftImage[slashImageIndex]);
            slashImageIndex = (slashImageIndex+1);
        }
        if(slashImageIndex >= slashRightImage.length)
        {
            slashImageIndex = 0;
            attackChosen = false;
            isAttacking = false;
            attackType = "";
        }
    }
    
    int kickImageIndex = 0;
    
    /**
     * Animate kick attack of Reaper
     */
    
    public void animateKickAttack()
    {
        if(kickTimer.millisElapsed() < 75)
        {
            return;
        }
        
        kickTimer.mark();
        
        if(kickImageIndex == 1)
        {
            reaperKickAttackSound.play();
        }
        
        if(direction.equals("right"))
        {
            setImage(kickRightImage[kickImageIndex]);
            kickImageIndex = (kickImageIndex+1);
        }
        else
        {
            setImage(kickLeftImage[kickImageIndex]);
            kickImageIndex = (kickImageIndex+1);
        }
        
         if(kickImageIndex >= kickRightImage.length)
        {
            kickImageIndex = 0;
            attackChosen = false;
            isAttacking = false;
            attackType = "";
        }
    }
    
    //Animate death of Reaper
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
            reaperDeadSound.play();  
        }
        else
        {
          setImage(deadRightImage[deadImageIndex]);
            deadImageIndex = (deadImageIndex+1);
            reaperDeadSound.play();  
        }
        
        if(deadImageIndex == 14)
        {
            getWorld().removeObject(reaper3HPBar);
            getWorld().removeObject(this);
        }
    }
    
    //Animate walk of Reaper
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
        if(!attackChosen)
        {
            int attackNumber = Greenfoot.getRandomNumber(2);
            if(attackNumber == 0)
            {
                attackType = "slash";
                slashImageIndex = 0;
                slashTimer.mark();
            }
            else if(attackNumber == 1)
            {
                attackType = "kick";
                kickImageIndex = 0;
                kickTimer.mark();
                
            }
            attackSoundIsPlaying = false;
            attackChosen = true;
        }
        if(attackType.equals("slash"))
        {
            animateSlashAttack();
            if(slashImageIndex==6)
            {
                level3ReaperDamage += 2;
            }
        }
        else if(attackType.equals("kick"))
        {
            animateKickAttack();
            if(kickImageIndex==6)
            {
                level3ReaperDamage += 2;
            }
        }
        return level3ReaperDamage;
    }
    
    // Damage to change hp method
    public void takeDamage(int damage)
    {
        if(invincibleTimer > 0)
        {
            return;
        }
        reaper3CurrentHP -= damage;
        
        if(reaper3CurrentHP < 0)
        {
            reaper3CurrentHP = 0;
        }
        
        reaper3HPBar.setHP(reaper3CurrentHP);
        invincibleTimer = 70;
    }
    
    // HPBar addedToWorld method
    public void addedToWorld(World w)
    {   
        // HPBar
        reaper3HPBar = new HPBar(reaper3CurrentHP, level3ReaperHP);
        w.addObject(reaper3HPBar, getX(), getY() - 45);
    }
    
    /**
     * Act - do whatever the Level3Reaper wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(reaper3CurrentHP<=0)
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
            if(!attackSoundIsPlaying)
                if(attackType.equals("slash"))
                {
                    reaperSlashAttackSound.play();
                }
                else
                {
                    reaperKickAttackSound.play();
                }
                attackSoundIsPlaying = true;
            attack();
          }
        }

        // Move the HPBar with the witch
        if(isAlive && level3ReaperHP != null)
        {
          reaper3HPBar.setLocation(getX(), getY() - 45);
        }
          
        //invicibleTimer decrease
        if(invincibleTimer > 0)
        {
            invincibleTimer--;
        }
    }
}
