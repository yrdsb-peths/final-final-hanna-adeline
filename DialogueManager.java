import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DialogueManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
    
    public DialogueManager(SpeechBubble left, SpeechBubble right)
    {
        this.left = left;
        this.right = right;
        showLine();
    }
    
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
     * Act - do whatever the DialogueManager wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
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
                Greenfoot.setWorld(new MyWorld());
                return;
            }
            showLine();
        }
        
        // Enter to skip story
        if(enter && !enterLast)
        {
            Greenfoot.setWorld(new MyWorld());
            return;
        }
        
        spaceLast = space;
        enterLast = enter;
    }
}
