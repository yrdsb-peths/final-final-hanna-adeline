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
    private SpeechBubble bubble;
    private int step = 0;
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
        addObject(witch, 170, 250);

        //Add practice target
        TutorialTarget slimered = new TutorialTarget();
        addObject(slimered, 450, 300);
        
        // Tutorial bubble
        bubble = new SpeechBubble("images/speechbubble/speech_bubble_right.png");
        bubble.resize(220, 90);
        addObject(bubble, 450, 150);
    
        showStep();

        // Add image for the title
        GreenfootImage img = new GreenfootImage("images/Fonts/Tutorial.png");
        TitleImage tip = new TitleImage(img, 500, 24);
        addObject(tip, 300, 380);
    }
    
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
            bubble.show("RED bar = Attack 1\nPress SHIFT to attack.");
        }
        else if (step == 3)
        {
            bubble.show("GREEN bar = Attack 2\nPress SPACE to attack.");
        }
        else
        {
            bubble.show("Tutorial complete!\nGood luck.");
            Greenfoot.delay(60);
            Greenfoot.setWorld(new MyWorld());
        }
    }

    public void act()
    {
        if(Greenfoot.isKeyDown("enter"))
        {
            MyWorld gameWorld = new MyWorld();
            Greenfoot.setWorld(gameWorld);
        }
        
        if (step == 0)
        {
            if (Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("right"))
            {
                step++;
                showStep();
            }
        }
        else if (step == 1)
        {
            if (Greenfoot.isKeyDown("up"))
            {
                step++;
                showStep();
            }
        }
        else if (step == 2)
        {
            if (Greenfoot.isKeyDown("shift"))
            {
                step++;
                showStep();
            }
        }
        else if (step == 3)
        {
            if (Greenfoot.isKeyDown("space"))
            {
                step++;
                showStep();
            }
        }
    }
}
