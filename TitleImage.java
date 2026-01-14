import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The TitleImage class represents a static image used
 * for titles, screens, or UI elements in the game.
 * The image can be scaled to a specified size.
 * 
 * @author Hanna
 * @version January 2026
 */
public class TitleImage extends Actor
{
    /** The image displayed by this TitleImage */
    GreenfootImage img;
    
    /**
     * Constructs a TitleImage object with a specified image
     * and scales it to the given width and height.
     *
     * @param img the image to be displayed
     * @param x the width to scale the image to
     * @param y the height to scale the image to
     */
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
