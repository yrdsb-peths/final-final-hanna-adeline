import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The TitleScreen class represents the starting site of the game.
 * 
 * This world displays the game title and main character
 * and waits for player input to begin the story.
 * 
 * @author Hanna & Adeline
 * @version January 2026
 */
public class TitleScreen extends World
{
    // BackgroundSound for titlescreen
    public static GreenfootSound titleScreenSound;
    public static boolean firstPlayDone = false;
    
    /**
     * Constructor for objects of class TitleScreen.
     * 
     * Initializes the background, title image, and
     * title-screen character.
     */
    public TitleScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        
        // Set background
        GreenfootImage bg = new GreenfootImage("images/background/Battleground2.png");
        bg.scale(getWidth(), getHeight());
        setBackground(bg);
        
        // Create the witch object
        WitchTitleScreen witch = new WitchTitleScreen();
        addObject(witch, 330, 230);
        
        // Add image for the title
        GreenfootImage img = new GreenfootImage("images/background/Startgame_Title.png");
        TitleImage title = new TitleImage(img, 480, 45);
        addObject(title, 300, 135);
        
        //Play background music for beginning title screen
        if(titleScreenSound != null && titleScreenSound.isPlaying())
        {
            titleScreenSound.stop();
        }
        titleScreenSound = new GreenfootSound("titleScreenSound.mp3");
        titleScreenSound.play();
        firstPlayDone = false;
    }
    
    /**
     * Executes the main loop of the TitleScreen.
     * 
     * Transition to the StoryWorld when the player
     * presses the space bar.
     */
    public void act()
    {
        //Loops through titleScreenSound once first play is done
        if(!firstPlayDone && !titleScreenSound.isPlaying())
        {
            firstPlayDone = true;
            titleScreenSound.playLoop();
        }
        
        // Begin the Story if the user presses the space bar
        if(Greenfoot.isKeyDown("space"))
        {
            StoryWorld storyWorld = new StoryWorld();
            Greenfoot.setWorld(storyWorld);
            titleScreenSound.stop();
        }
    }
}
