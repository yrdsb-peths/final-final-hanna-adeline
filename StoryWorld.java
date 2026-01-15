import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The StoryWorld class represents the story world of the game.
 * 
 * This world is used to present story dialogue and visuals
 * before gameplay begins. It displays characters, speech
 * bubbles, manages dialogue progression and transitions to level 1 game.
 * 
 * @author Hanna & Adeline
 * version January 2026
 */
public class StoryWorld extends World
{
    // Speech bubble displayed on the left side of the screen
    public SpeechBubble leftBubble;
    // Speech bubble displayed on the right side of the screen
    public SpeechBubble rightBubble;
    // Background for the story world
    public static GreenfootSound introSound = new GreenfootSound("introSound.wav");
    
    /**
     * Constructor for objects of class StoryWorld.
     * 
     * Initializes the background, characters, title images,
     * speech bubbles, and dialogue manager.
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
        leftBubble.resize(176, 104);
        rightBubble.resize(176, 104);
        
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
        
        //Play sound
        playIntroSound();
        if(EndScreen.endingSound.isPlaying())
        {
            EndScreen.endingSound.stop();
        }
    }
    
    /**
     * Ensures the the introSound audio is playing.
     */
    public static void playIntroSound()
    {
        if(!introSound.isPlaying())
        {   
            introSound.play();
        }
        if(introSound.isPlaying())
        {
            return;
        }
    }
        
    /**
     * Executes the main loop for the story world.
     * 
     * Dialogue updates and story interactions are handled
     * by the DialogueManager.
     */
    public void act()
    {
        //
    }
}
