import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The SpeechBubble class displays a speech bubble image
 * that can show or hide text during gameplay.
 * It supports resizing and multi-line text rendering.
 * 
 * @author Hanna
 * @version December 2025
 */
public class SpeechBubble extends Actor
{
    /** Base image used as the speech bubble background */
    private GreenfootImage baseImage;
    
    /**
     * Constructs a SpeechBubble object using the given image path.
     * The speech bubble starts hidden with a transparent image.
     *
     * @param imagePath the file path of the speech bubble image
     */
    public SpeechBubble(String imagePath)
    {
        baseImage = new GreenfootImage(imagePath);
        
        // Start hidden with transparent image
        GreenfootImage transparent = new GreenfootImage(baseImage.getWidth(), baseImage.getHeight());
        transparent.setTransparency(0); // fully transparent
        setImage(transparent);
    }

    /**
     * Resizes the speech bubble image.
     *
     * @param width the new width of the speech bubble
     * @param height the new height of the speech bubble
     */
    public void resize(int width, int height)
    {
        baseImage.scale(width, height); // scale the original image
    }

    /**
     * Displays the speech bubble with the specified text.
     * Supports multi-line text separated by newline characters.
     *
     * @param text the text to display inside the speech bubble
     */
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

    /**
     * Hides the speech bubble by replacing it
     * with a fully transparent image.
     */
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
