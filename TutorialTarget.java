import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TutorialTarget here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TutorialTarget extends Actor
{
    private int hp = 3;

    public TutorialTarget() {
        GreenfootImage img = new GreenfootImage(50, 50);
        img.setColor(Color.RED);
        img.fillRect(0, 0, 50, 50);
        setImage(img);
    }

    /**
     * Act - do whatever the TutorialTarget wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Check collision with attack boxes
        Actor attack = getOneIntersectingObject(AttackBox.class);
        if (attack != null) {
            hp--;
            if (hp <= 0) {
                getWorld().removeObject(this);
            }
        }
    }
}
