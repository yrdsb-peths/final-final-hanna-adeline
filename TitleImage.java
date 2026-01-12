import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TitleImage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleImage extends Actor
{
    GreenfootImage img;
    
    public TitleImage(GreenfootImage img, int x, int y)
    {
        img.scale(x, y);
        setImage(img);
    }
    
    /**
     * Act - do whatever the TitleImage wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
}
