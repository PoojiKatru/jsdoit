import greenfoot.*;

/**
 * TextInputActor is a clickable text input field for Greenfoot.
 * When clicked, it prompts the user to type text via Greenfoot.ask().
 * The text is displayed inside a rectangular box with customizable
 * dimensions, font size, background color, and text color.
 */
public class TextInputActor extends Actor {
    private String text = "";              // Current text in the box
    private int width, height, fontSize;   // Dimensions and font size
    private greenfoot.Color bgColor;       // Background color
    private greenfoot.Color textColor;     // Text color

    /**
     * Constructs a TextInputActor with specified size, font, and colors.
     *
     * @param width     Width of the text box
     * @param height    Height of the text box
     * @param fontSize  Font size for displayed text
     * @param bgColor   Background color of the box
     * @param textColor Color of the text
     */
    public TextInputActor(int width, int height, int fontSize, greenfoot.Color bgColor, greenfoot.Color textColor) {
        this.width = width;
        this.height = height;
        this.fontSize = fontSize;
        this.bgColor = bgColor;
        this.textColor = textColor;
        updateImage();
    }

    /**
     * Act method called each frame.
     * Detects clicks on this actor to prompt user input.
     */
    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            String input = Greenfoot.ask("Type here:");  // Prompt user
            if (input != null) {
                text = input;  // Update text
                updateImage();
            }
        }
    }

    /**
     * Sets the current text programmatically.
     *
     * @param t The new text to display
     */
    public void setText(String t) {
        text = t;
        updateImage();
    }

    /**
     * Returns the current text in the box.
     *
     * @return The text string
     */
    public String getText() {
        return text;
    }

    /**
     * Updates the visual representation of the TextInputActor.
     * Draws background, border, and text centered vertically with padding.
     */
    private void updateImage() {
        GreenfootImage img = new GreenfootImage(width, height);

        // Draw background
        img.setColor(bgColor);
        img.fillRect(0, 0, width, height);

        // Draw border
        img.setColor(new greenfoot.Color(0, 0, 0));
        img.drawRect(0, 0, width - 1, height - 1);

        // Draw text with left padding
        GreenfootImage textImg = new GreenfootImage(text, fontSize, textColor, new greenfoot.Color(0, 0, 0, 0));
        int x = 5; // small padding from left
        int y = (height - textImg.getHeight()) / 2; // vertically centered
        img.drawImage(textImg, x, y);

        setImage(img);
    }
}
