import greenfoot.*;

/**
 * TextBox is an interactive text input field for Greenfoot.
 * Users can click to activate it and type text, supporting
 * backspace for deletion. Only one TextBox can be active at a time.
 */
public class TextBox extends Actor {
    private String text = "";      // Current text in the box
    private int width, height;     // Dimensions of the box
    private boolean active = false; // Whether this box is currently active

    /**
     * Constructs a TextBox of specified dimensions.
     * 
     * @param width  Width of the text box in pixels
     * @param height Height of the text box in pixels
     */
    public TextBox(int width, int height) {
        this.width = width;
        this.height = height;
        updateImage();
    }

    /**
     * Act method is called on each frame.
     * Handles activation on click and keyboard input when active.
     */
    public void act() {
        // Activate this TextBox if clicked
        if (Greenfoot.mouseClicked(this)) {
            // Deactivate all other TextBoxes in the world
            for (TextBox tb : getWorld().getObjects(TextBox.class)) {
                tb.active = false;
            }
            active = true;
        }

        // Capture key input if this box is active
        if (active) {
            String key = Greenfoot.getKey();
            if (key != null) {
                if (key.equals("backspace") && text.length() > 0) {
                    // Remove last character
                    text = text.substring(0, text.length() - 1);
                } else if (!key.equals("enter") && key.length() == 1) {
                    // Append normal characters
                    text += key;
                }
                updateImage(); // Refresh display
            }
        }
    }

    /**
     * Updates the visual representation of the TextBox.
     * Active boxes are light blue; inactive are white.
     * Draws current text inside the box.
     */
    private void updateImage() {
        GreenfootImage img = new GreenfootImage(width, height);

        // Background color based on active state
        img.setColor(active ? new greenfoot.Color(200, 200, 255) : new greenfoot.Color(255, 255, 255));
        img.fill();

        // Border
        img.setColor(new greenfoot.Color(0, 0, 128));
        img.drawRect(0, 0, width - 1, height - 1);

        // Draw text
        img.drawString(text, 5, height / 2 + 5);

        setImage(img);
    }

    /**
     * Returns the current text inside the TextBox.
     * 
     * @return Current text string
     */
    public String getText() {
        return text;
    }
}

