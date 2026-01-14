import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The TutorialWorld displays the tutorial of the game.
 * 
 * This world teaches the player basic controls such as movement,
 * flying, and attacks through step-by-step instructions displayed
 * using a speech bubble.
 * 
 * @author Hanna & Adeline
 * @version January 2026
 */
public class TutorialWorld extends World
{
    /** The player-controlled witch character */
    private Witch witch;
    
    /** Speech bubble used to display tutorial instructions */
    private SpeechBubble bubble;
    
    /** Tracks the current step of the tutorial */
    private int step = 0;
    
    /**
     * Tracks whether the space key has been released.
     * (Reserved for preventing repeated input if needed.)
     */
    private boolean spaceReleased = false;
    /**
     * Constructor for objects of class TutorialWorld.
     * 
     * Initializes the background, TutorialWorld-characters, 
     * speech bubble, and tutorial title.
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
        addObject(witch, 170, 250);

        //Add practice target
        TutorialTarget slimered = new TutorialTarget();
        addObject(slimered, 450, 300);
        
        // Tutorial bubble
        bubble = new SpeechBubble("images/speechbubble/speech_bubble_right.png");
        bubble.resize(220, 100);
        addObject(bubble, 450, 150);
    
        showStep();

        // Add image for the title
        GreenfootImage img = new GreenfootImage("images/Fonts/Tutorial.png");
        TitleImage tip = new TitleImage(img, 365, 22);
        addObject(tip, 300, 380);
    }
    
    /**
     * Displays the instruction text for the current tutorial step.
     * 
     * Each step introduces a new control or mechanic.
     * When all steps are completed, the game transitions
     * to the main world.
     */
    private void showStep()
    {
        if (step == 0)
        {
            bubble.show("Use LEFT and RIGHT\narrows to move.");
        }
        else if (step == 1)
        {
            bubble.show("BLUE bar = Flying\nHold UP arrow to fly.");
        }
        else if (step == 2)
        {
            bubble.show("RED bar = Attack 1\nPress SHIFT to \nattack me.");
        }
        else if (step == 3)
        {
            bubble.show("GREEN bar = Attack 2\nPress SPACE to \nattack me.");
        }
        else if (step == 4)
        {
            bubble.show("Press E to end\nthe tutorial ");
        }
        else
        {
            bubble.show("Tutorial complete!\nGood luck.");
            Greenfoot.delay(60);
            Greenfoot.setWorld(new MyWorld());
        }
    }

    /**
     * Executes the main loop of the TutorialWorld.
     * 
     * Advances the tutorial when the player performs
     * the required action for each step.
     */
    public void act()
    {
        if(Greenfoot.isKeyDown("enter"))
        {
            MyWorld gameWorld = new MyWorld();
            Greenfoot.setWorld(gameWorld);
        }
        
        if(step == 0)
        {
            if(Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("right"))
            {
                step++;
                showStep();
            }
        }
        else if(step == 1)
        {
            if(Greenfoot.isKeyDown("up"))
            {
                step++;
                showStep();
            }
        }
        else if(step == 2)
        {
            if(Greenfoot.isKeyDown("shift"))
            {
                step++;
                showStep();
            }
        }
        else if(step == 3)
        {
            if (Greenfoot.isKeyDown("space"))
            {
                step++;
                showStep();
            }
        }
        else if(step == 4)
        {
            if(Greenfoot.isKeyDown("E"))
            {
                step++;
                showStep();
            }
        }
    }
}
