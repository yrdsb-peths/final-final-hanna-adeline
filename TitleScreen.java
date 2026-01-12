import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TitleScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleScreen extends World
{

    /**
     * Constructor for objects of class TitleScreen.
     * 
     */
    public TitleScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        prepare();
        
        // Set background
        GreenfootImage bg = new GreenfootImage("images/background/Battleground2.png");
        bg.scale(getWidth(), getHeight());
        setBackground(bg);
        
        // Create the witch object
        WitchTitleScreen witch = new WitchTitleScreen();
        addObject(witch, 330, 230);
        
        // Add image for the title
        GreenfootImage img = new GreenfootImage("images/background/Startgame_Title.png");
        TitleImage title = new TitleImage(img, 470, 45);
        addObject(title, 300, 125);
    }

    /**
     * The main world act loop
     */
    public void act()
    {
        // Begin the Story if the user presses the space bar
        if(Greenfoot.isKeyDown("space"))
        {
            StoryWorld storyWorld = new StoryWorld();
            Greenfoot.setWorld(storyWorld);
        }
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        
    }
}
