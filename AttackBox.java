import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AttackBox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AttackBox extends Actor
{
    private int damage;
    
    public AttackBox(int w, int h, int damage)
    {
        this.damage = damage;
        
        GreenfootImage img = new GreenfootImage(w, h);
        img.setColor(new Color(255, 0, 0, 0));
        img.fillRect(0, 0, w, h);
        
        setImage(img);
    }
    
    /**
     * Act - do whatever the AttackBox wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        Level1SlimeRed redSlime = (Level1SlimeRed) getOneIntersectingObject(Level1SlimeRed.class);
        if(redSlime != null)
        {
            redSlime.takeDamage(damage);
        }
        
        Level2SlimeBlue blueSlime = (Level2SlimeBlue) getOneIntersectingObject(Level2SlimeBlue.class);
        if(blueSlime != null)
        {
            blueSlime.takeDamage(damage);
        }
        
        Level2Golem golem = (Level2Golem) getOneIntersectingObject(Level2Golem.class);
        if(golem != null)
        {
            golem.takeDamage(damage);
        }
        
        Level3Monster monster = (Level3Monster) getOneIntersectingObject(Level3Monster.class);
        if(monster != null)
        {
            monster.takeDamage(damage);
        }
    }
}
