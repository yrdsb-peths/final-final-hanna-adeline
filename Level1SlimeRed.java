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
    
    // Images for SlimeRed hp bar
    public GreenfootImage[] slimeHP = new GreenfootImage[6];
    public HPBar slimeRedHP;
    private int slimeCurrentHP = 5;
    private int slimeMaxHP = 5;
    private int invincibleTimer = 0;
    
    public Level1SlimeRed()
    {
        //Set idle image for walk of SlimeRed
        
        for(int i=0; i<walkRightImage.length; i++)
        {
            walkRightImage[i] = new GreenfootImage("images/Monsters/Level1/Level1SlimeRed/walkRight/walkRight"+ i + ".png");
            walkRightImage[i].scale(128, 128);
        }
        
        for(int i=0; i<walkLeftImage.length; i++)
        {
            walkLeftImage[i] = new GreenfootImage("images/Monsters/Level1/Level1SlimeRed/walkLeft/walkLeft"+ i + ".png");
            walkLeftImage[i].scale(128, 128);
        }
        
        //Set idle image for attack of SlimeRed
        for(int i=0; i<attackRightImage.length; i++)
        {
            attackRightImage[i] = new GreenfootImage("images/Monsters/Level1/Level1SlimeRed/attackRight/attackRight"+ i + ".png");
            attackRightImage[i].scale(128, 128);
        }
        
        for(int i=0; i<attackLeftImage.length; i++)
        {
            attackLeftImage[i] = new GreenfootImage("images/Monsters/Level1/Level1SlimeRed/attackLeft/attackLeft"+ i + ".png");
            attackLeftImage[i].scale(128, 128);
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
        
        // Set image for hp of witch
        for(int i = 0; i < slimeHP.length; i++)
        {
            slimeHP[i] = new GreenfootImage("hp_bar/monster1_hp/monster1_hp_" + i + ".png");
            slimeHP[i].scale(70, 30);
        }
        slimeCurrentHP = 5;
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
    
    public void addedToWorld(World w)
    {
        // HPBar
        slimeRedHP = new HPBar(slimeCurrentHP, slimeHP);
        w.addObject(slimeRedHP, getX() + 5, getY() - 15);
    }
    
    /**
     * Act - do whatever the Level1SlimeRed wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        moveToWitch();
        if(slimeCurrentHP <= 0)
        {
            isAlive = false;
        }
          
        // Move the HPBar with the witch
        if(slimeRedHP != null)
        {
            slimeRedHP.setLocation(getX() + 5, getY() - 15);
        }
    }
}
