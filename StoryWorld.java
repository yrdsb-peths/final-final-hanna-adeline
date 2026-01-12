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
        addObject(witch, 170, 230);
        
        // Add image for the slime
        GreenfootImage slimeImg = new GreenfootImage("images/Monsters/Level1/Level1SlimeRed/attackLeft/attackLeft1.png");
        TitleImage slime = new TitleImage(slimeImg, 59, 45);
        addObject(slime, 500, 280);
        
        // Create speech bubbles
        leftBubble = new SpeechBubble("images/speechbubble/speech_bubble_left.png");
        rightBubble = new SpeechBubble("images/speechbubble/speech_bubble_right.png");
        
        // Resize the images of speech bubble
        leftBubble.resize(180, 100);
        rightBubble.resize(180, 100);
        
        // Add the object into the world
        addObject(leftBubble, 210, 130);
        addObject(rightBubble, 420, 130);
        
        // Hide the bubbles first
        leftBubble.hide();
        rightBubble.hide();
        
        // Create dialogue manager and pass the bubbles
        DialogueManager dm = new DialogueManager(leftBubble, rightBubble);
        addObject(dm, 300, 250);
        
        // Add image for the title
        GreenfootImage img = new GreenfootImage("images/Fonts/Story.png");
        TitleImage tip = new TitleImage(img, 500, 27);
        addObject(tip, 300, 380);
    }
    
    public void act()
    {
        // Add text
    }
}
