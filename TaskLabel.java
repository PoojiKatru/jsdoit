import greenfoot.*;

/**
 * TaskLabel represents a simple label for displaying text on a colored background.
 * It can be used in task panels or dashboards to show task names, categories, or status.
 */
public class TaskLabel extends Actor {

    /**
     * Constructs a TaskLabel with custom text and colors.
     *
     * @param text      The string to display on the label.
     * @param bgRed     Red component of the background color (0-255).
     * @param bgGreen   Green component of the background color (0-255).
     * @param bgBlue    Blue component of the background color (0-255).
     * @param textRed   Red component of the text color (0-255).
     * @param textGreen Green component of the text color (0-255).
     * @param textBlue  Blue component of the text color (0-255).
     */
    public TaskLabel(String text, int bgRed, int bgGreen, int bgBlue,
                     int textRed, int textGreen, int textBlue) {
        // Create the image for the label
        GreenfootImage img = new GreenfootImage(200, 30);

        // Fill the background
        img.setColor(new greenfoot.Color(bgRed, bgGreen, bgBlue));
        img.fill();

        // Draw the text
        img.setColor(new greenfoot.Color(textRed, textGreen, textBlue));
        img.drawString(text, 5, 20);

        // Set the image to this Actor
        setImage(img);
    }
}

