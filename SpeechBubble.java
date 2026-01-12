import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SpeechBubble here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SpeechBubble extends Actor
{
    private String text = "";
    private boolean visible = false;
    
    public void show(String text)
    {
        this.text = text;
        visible = true;
        redraw();
    }
    
    public void hide()
    {
        visible = false;
        setImage(new GreenfootImage(1, 1));
    }
    
    private void redraw()
    {
        if(!visible) 
        {
            return;
        }
        GreenfootImage img = new GreenfootImage(150, 70);
        img.setColor(new Color(255, 255, 255, 220));
        img.fillRect(0, 0, 260, 100);

        img.setColor(Color.BLACK);
        img.setFont(new Font("Courier New", 13));
        img.drawString(text, 10, 25);

        setImage(img);
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
