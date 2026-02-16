import greenfoot.*;

/**
 * Label is a simple text display actor used to show static text in the world.
 * It supports custom text, font size, and color.
 */
public class Label extends Actor {

    /**
     * Constructor for Label.
     *
     * @param text     The text to display
     * @param fontSize The font size of the text
     * @param red      Red component of text color (0-255)
     * @param green    Green component of text color (0-255)
     * @param blue     Blue component of text color (0-255)
     */
    public Label(String text, int fontSize, int red, int green, int blue) {
        // Create temporary image to set font
        GreenfootImage img = new GreenfootImage(1, 1);
        Font font = new Font("Times New Roman", false, false, fontSize);
        img.setFont(font);

        // Create the actual image with text
        GreenfootImage temp = new GreenfootImage(
            text, fontSize, 
            new greenfoot.Color(red, green, blue),    // text color
            new greenfoot.Color(0, 0, 0, 0)          // transparent background
        );

        setImage(temp);
    }
}
