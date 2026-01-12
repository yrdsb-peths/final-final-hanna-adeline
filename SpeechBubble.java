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
        
        // Start hidden with transparent image
        GreenfootImage transparent = new GreenfootImage(baseImage.getWidth(), baseImage.getHeight());
        transparent.setTransparency(0); // fully transparent
        setImage(transparent);
    }

    public void resize(int width, int height)
    {
        baseImage.scale(width, height); // scale the original image
    }

    public void show(String text)
    {
        GreenfootImage img = new GreenfootImage(baseImage);
        img.setFont(new Font("Courier New", 13));
        img.setColor(Color.BLACK);

        String[] lines = text.split("\n");

        int y = 28;
        for (String line : lines)
        {
            img.drawString(line, 20, y);
            y += 12;
        }

        setImage(img);
    }

    public void hide()
    {
        GreenfootImage transparent = new GreenfootImage(baseImage.getWidth(), baseImage.getHeight());
        transparent.setTransparency(0); // fully transparent
        setImage(transparent);
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
