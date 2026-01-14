import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The DialogueManager controls the display and progression of story dialogue.
 * 
 * It manages a sequence of dialogue lines, determines which character is
 * speaking, and displays the dialogue in the appropriate speech bubble.
 * 
 * The player can advance the dialogue using the space key or skip the
 * dialogue entirely using the enter key.
 * 
 * @author Hanna & Adeline 
 * @version January 2026
 */
public class DialogueManager extends Actor
{
    // Main dialogue instance
    private Line[] dialogue = 
    {
        new Line("witch", "...Where am I?"),
        new Line("messenger", "You have crossed\ninto the\nforbidden realm."),
        new Line("witch", "Who are you?"),
        new Line("messenger", "A guide.\nOr a warning."),
        new Line("witch", "Then teach me\nhow to survive.")
    };
    
    // Index of dialogue
    private int index = 0;
    
    // Mark for last space and enter
    private boolean spaceLast = false;
    private boolean enterLast = false;
    
    // Speech bubble
    private SpeechBubble left;
    private SpeechBubble right;
    
    /**
     * The constructor which constructs a DialogueManager and 
     * initializes the dialogue display.
     * 
     * @param left The speech bubble used for left-side characters
     * @param right The speech bubble used for right-side characters
     */
    public DialogueManager(SpeechBubble left, SpeechBubble right)
    {
        this.left = left;
        this.right = right;
        showLine();
    }
    
    /**
     * Displays the current dialogue line in the appropriate speech bubble
     * based on the speaking character.
     */
    private void showLine()
    {
        Line line = dialogue[index];
    
        left.hide();
        right.hide();
    
        if ("witch".equals(line.speaker))
        {
            left.show(line.text);
        }
        else
        {
            right.show(line.text);
        }
    }
    
    /**
     * Handles player input to progress or skip the dialogue.
     * 
     * Pressing the space key advances to the next dialogue line.
     * Pressing the enter key skips the dialogue and transitions
     * directly to the tutorial world.
     */
    public void act()
    {
        boolean space = Greenfoot.isKeyDown("space");
        boolean enter = Greenfoot.isKeyDown("enter");
        
        // Space for next dialogue
        if(space && !spaceLast)
        {
            index++;
            if(index >= dialogue.length)
            {
                Greenfoot.setWorld(new TutorialWorld());
                return;
            }
            showLine();
        }
        
        // Enter to skip story
        if(enter && !enterLast)
        {
            Greenfoot.setWorld(new TutorialWorld());
            return;
        }
        
        spaceLast = space;
        enterLast = enter;
    }
}
