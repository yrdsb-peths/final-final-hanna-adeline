import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StoryWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StoryWorld extends World
{
    public SpeechBubble leftBubble;
    public SpeechBubble rightBubble;
    /**
     * Constructor for objects of class StoryWorld.
     * 
     */
    public StoryWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        
        // Set background
        GreenfootImage bg = new GreenfootImage("images/background/Battleground2.png");
        bg.scale(getWidth(), getHeight());
        setBackground(bg);
        
        // Create the witch object
        WitchTitleScreen witch = new WitchTitleScreen();
        addObject(witch, 130, 230);
        
        // Create speech bubbles
        leftBubble = new SpeechBubble();
        rightBubble = new SpeechBubble();
        
        addObject(leftBubble, 220, 250);
        addObject(rightBubble, 400, 250);
        
        // Hide the bubbles first
        leftBubble.hide();
        rightBubble.hide();
        
        // Create dialogue manager and pass the bubbles
        DialogueManager dm = new DialogueManager(leftBubble, rightBubble);
        addObject(dm, 300, 250);
    }
    
    public void act()
    {
        // Add text
    }
}
