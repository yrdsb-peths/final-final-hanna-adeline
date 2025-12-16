import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level1SlimeRed here.
 * 
 * @author Adeline
 * @version December 2025
 */
public class Level1SlimeRed extends Actor
{
    GreenfootImage[] walkRightImage = new GreenfootImage[8];
    GreenfootImage[] attackRightImage = new GreenfootImage[5];
    GreenfootImage[] walkLeftImage = new GreenfootImage[8];
    GreenfootImage[] attackLeftImage = new GreenfootImage[5];
    GreenfootImage[] deadImage = new GreenfootImage[8];
    
    SimpleTimer attackTimer = new SimpleTimer();
    
    public Level1SlimeRed()
    {
        //Initial potion 1 image
        setImage(potionImage[0]);
        
        for(int i=0; i<potionImage.length; i++)
        {
            potionImage[i] = new GreenfootImage
            potionImage[i].scale(22, 37);
        }
        
        animationTimer.mark();
    }
    
    int imageIndex = 0;
    
    public void attackRight()
    {
        setImage(attackRightImage[0]);
        
        for(int i=0; i<attackRightImage.length; i++)
        {
            attackRightImage[i] = new GreenfootImage("images/Monsters/Level1/Level1SlimeRed/attackRight/attackRight" + i + ".png");
            attackRightImage[i].scale(50,50);
        }
        
        attackTimer.mark();
        
    }
    
    public void animateLevel1RedSlime()
    {
        if(attackTimer.millsisElapsed() < 100)
        {
            return;
        }
        attackTimer.mark();
        
        setImage(attackRightImage[imageIndex]);
        imageIndex = (imageIndex + 1) % attackRightImage.length;
    }
    /**
     * Act - do whatever the Level1SlimeRed wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
      attackRight();  
    }
}
