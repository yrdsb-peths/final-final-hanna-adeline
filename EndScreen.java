import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The EndScreen class displays the win screen of the game.
 * 
 * This world is used to present the win screen if the
 * player-controlled witch defeats all the monsters and collect
 * all the potions.
 * 
 * @author Hanna & Adeline
 * version January 2026
 */
public class EndScreen extends World
{
    // Sound for the ending screen
    public static GreenfootSound endingSound = new GreenfootSound("endingSound.wav");
    // Tracks if the final potion is spawned
    private boolean finalPotionSpawned = false;
    // Track if the final potion has been collected
    private boolean finalPotionCollected = false;
    
    // Mission complete image
    private TitleImage missionCompleteImage;
    
    // SimpleTimer for the finalpotion
    private SimpleTimer finalPotionTimer = new SimpleTimer();
    
    private SpeechBubble bubble;
    private SimpleTimer bubbleTimer = new SimpleTimer();
    private int bubbleStep = 0; // Tracks which sentence to show
    /**
     * Constructor for objects of class StoryWorld.
     * 
     * Initializes the background and title images.
     */
    public EndScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        
        // Set background
        GreenfootImage bg = new GreenfootImage("images/background/Win.png");
        bg.scale(getWidth(), getHeight());
        setBackground(bg);
        
        // Create the witch object
        Witch witch = new Witch();
        addObject(witch, 120, 230);
        
        MyWorld3.level3Sound.stop();
        StoryWorld.introSound.stop();
        endingSound.play();
        
        finalPotionTimer.mark();
        
        // Create the speech bubble
        bubble = new SpeechBubble("images/speechbubble/speech_bubble_right.png");
        bubble.resize(180, 100);
        addObject(bubble, 450, 100);
        
        // Start timer for bubble messages
        bubbleTimer.mark();
        bubbleStep = 0;
        bubble.show("Congratulations!");
    }
    
    /**
     * Spawns the potion after all level is completed.
     */
    private void spawnPotionFinal()
    {
        if (!finalPotionSpawned && finalPotionTimer.millisElapsed() >= 8000)
        {    
            PotionFinal finalPotion = new PotionFinal();
            addObject(finalPotion, 300, 215);
            finalPotionSpawned = true;
            
            bubble.show("Collect the \nmedicine for Lucy!");
        }
    }
    
    // Checks whether the potion have been collected
    private void checkPotionCollected()
    {
        // Only check if the potion was spawned and not yet collected
        if(finalPotionSpawned && !finalPotionCollected)
        {
            // PotionFinal removes itself from the world when collected
            if(getObjects(PotionFinal.class).isEmpty())
            {
                finalPotionCollected = true;
    
                // Remove everything else except the background
                for (Object obj : getObjects(null)) {
                    if(obj instanceof Actor) {
                        removeObject((Actor)obj);
                    }
                }
    
                // Show mission complete title
                GreenfootImage img1 = new GreenfootImage("images/background/EndScreen_Title1.png");
                TitleImage title1 = new TitleImage(img1, 480, 45);
                addObject(title1, 300, 130);
                
                GreenfootImage img2 = new GreenfootImage("images/background/EndScreen_Title2.png");
                TitleImage title2 = new TitleImage(img2, 500, 45);
                addObject(title2, 300, 170);
            }
        }
    }
    
    /**
     * Act method for EndScreen.
     * Spawns one final potion after 10 seconds and dialogue.
     */
    public void act()
    {
        // Cycle through speech bubble messages every ~2 seconds
        if (bubbleStep < 3 && bubbleTimer.millisElapsed() > 2000)
        {
            bubbleTimer.mark(); // Reset timer
    
            bubbleStep++; // Advance to next message
    
            if (bubbleStep == 1)
            {
                bubble.show("You've collected \nall potions.");
            }
            else if (bubbleStep == 2)
            {
                bubble.show("Mixing potions...");
            }
        }
        
        // Spawn last potion
        spawnPotionFinal();
        
        // Check if player collected the potion
        checkPotionCollected();
    }
}
