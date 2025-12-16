import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HPBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HPBar extends Actor
{
    private int maxHP;
    private int currentHP;
    
    // Store images for different hp levels
    public GreenfootImage[] hpImages;
    
    public HPBar(int maxHP, GreenfootImage[] hpImages)
    {
        this.maxHP = maxHP;
        this.currentHP = maxHP;
        this.hpImages = hpImages;
        
        // Inital witch hp image
        setImage(hpImages[currentHP]);
    }
    
    public void setHP(int hp)
    {
        if(hp < 0)
        {
            hp = 0;
        }
        if(hp > maxHP)
        {
            hp = maxHP;
        }
        currentHP = hp;
        setImage(hpImages[hp]);
    }
    
    /**
     * Act - do whatever the HPBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
}
