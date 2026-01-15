import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The HPBar class represents a visual health bar for a character.
 * 
 * It displays the character’s current health using a set of images,
 * where each image corresponds to a specific HP value and the HP bar 
 * updates automatically when the health value changes.
 * 
 * @author Hanna
 * @version December 2022
 */
public class HPBar extends Actor
{
    // The maximum health value of the character.
    private int maxHP;
    
    // The current health value of the character.
    private int currentHP;
    
    // An array of images representing each health level.
    public GreenfootImage[] hpImages;
    
    /**
     * The constructor which constructs an HPBar with a given
     * maximum HP and image set.
     * 
     * @param maxHP the maximum health value
     * @param hpImages an array of images representing each HP level
     */
    public HPBar(int maxHP, GreenfootImage[] hpImages)
    {
        this.maxHP = maxHP;
        this.currentHP = maxHP;
        this.hpImages = hpImages;
        
        // Inital witch hp image
        setImage(hpImages[currentHP]);
    }
    
    /**
     * Updates the HP bar to reflect a new health value.
     * 
     * The HP value is between 0 and the maximum HP
     * to prevent invalid values.
     * 
     * @param hp the new health value
     */
    public void setHP(int hp)
    {
        if(hp <= 0)
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
     * Act method for the HPBar.
     * 
     * This class does not require continuous updates,
     * as the HP bar only changes when setHP is called.
     */
    public void act()
    {
        // Add your action code here.
    }
}
