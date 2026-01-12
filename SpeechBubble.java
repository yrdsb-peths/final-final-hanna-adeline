import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SpeechBubble here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SpeechBubble extends Actor
{
    private GreenfootImage baseImage;
    
    public SpeechBubble(String imagePath)
    {
        baseImage = new GreenfootImage(imagePath);
        setImage(new GreenfootImage(1, 1)); // hidden at start
    }

    public void show(String text)
    {
        GreenfootImage img = new GreenfootImage(baseImage);
        img.setFont(new Font("Courier New", 16));
        img.setColor(Color.BLACK);

        String[] lines = text.split("\n");

        int y = 40;
        for (String line : lines)
        {
            img.drawString(line, 20, y);
            y += 22;
        }

        setImage(img);
    }

    public void hide()
    {
        setImage(new GreenfootImage(1, 1));
    }
    
    /**
     * Act - do whatever the SpeechBubble wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
}
