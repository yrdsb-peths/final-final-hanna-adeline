import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TutorialWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TutorialWorld extends World
{
    private Witch witch;
    private SpeechBubble tutorialBubble;
    /**
     * Constructor for objects of class TutorialWorld.
     * 
     */
    public TutorialWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        
        // Set background
        GreenfootImage bg = new GreenfootImage("images/background/Battleground2.png");
        bg.scale(getWidth(), getHeight());
        setBackground(bg);
        
        // Add the witch
        witch = new Witch();
        addObject(witch, 120, 250);

        // Add tutorial speech bubble
        tutorialBubble = new SpeechBubble("images/speechbubble/speech_bubble_left.png");
        tutorialBubble.show("Use arrow keys to move\nSHIFT for Attack1\nSPACE for Attack2\nUP to fly!");
        addObject(tutorialBubble, 200, 100);

        //Add practice target
        addObject(new TutorialTarget(), 500, 300);
    }
    
    public void act()
    {
        if(Greenfoot.isKeyDown("enter"))
        {
            MyWorld gameWorld = new MyWorld();
            Greenfoot.setWorld(gameWorld);
        }
    }
}
