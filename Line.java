import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Line class represents a single line of dialogue in the game.
 * Each Line stores the speaker's name and the dialogue text.
 * 
 * This class can be used for cutscenes, conversations, or story narration.
 * 
 * @author Hanna & Adeline
 * @version January 2026
 */
public class Line extends Actor
{
    /** The name of the character speaking the line */
    public String speaker;
    /** The dialogue text spoken by the character */
    public String text;
    
    /**
     * Constructs a Line object with a specified speaker and dialogue text.
     *
     * @param speaker the name of the character speaking
     * @param text the dialogue text to be displayed
     */
    public Line(String speaker, String text)
    {
        this.speaker = speaker;
        this.text = text;
    }
    
    /**
     * Act - do whatever the Line wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
}
