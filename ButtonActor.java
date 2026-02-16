import greenfoot.*;

/**
 * ButtonActor represents a customizable rectangular button with text for the Greenfoot world.
 * The button supports custom text color, background color, width, and height.
 * It responds to mouse clicks and plays a click sound when pressed.
 * Subclasses must implement the {@link #onClick()} method to define the button's behavior.
 */
public abstract class ButtonActor extends Actor {

    private String text;
    private int width, height;
    private int bgRed, bgGreen, bgBlue;
    private int textRed, textGreen, textBlue;

    /**
     * Constructor for ButtonActor.
     *
     * @param text       The label to display on the button
     * @param textRed    Red component of text color (0-255)
     * @param textGreen  Green component of text color (0-255)
     * @param textBlue   Blue component of text color (0-255)
     * @param bgRed      Red component of background color (0-255)
     * @param bgGreen    Green component of background color (0-255)
     * @param bgBlue     Blue component of background color (0-255)
     * @param width      Width of the button in pixels
     * @param height     Height of the button in pixels
     */
    public ButtonActor(String text, int textRed, int textGreen, int textBlue,
                       int bgRed, int bgGreen, int bgBlue,
                       int width, int height) {
        this.text = text;
        this.textRed = textRed;
        this.textGreen = textGreen;
        this.textBlue = textBlue;
        this.bgRed = bgRed;
        this.bgGreen = bgGreen;
        this.bgBlue = bgBlue;
        this.width = width;
        this.height = height;
        createImage();
    }

    /**
     * Creates the button image with the specified background color and text.
     */
    private void createImage() {
        GreenfootImage img = new GreenfootImage(width, height);

        // Fill background
        img.setColor(new greenfoot.Color(bgRed, bgGreen, bgBlue));
        img.fill();

        // Draw text label
        img.setColor(new greenfoot.Color(textRed, textGreen, textBlue));
        img.drawString(text, 10, height / 2 + 5); // slightly offset vertically
        setImage(img);
    }

    /**
     * Act method checks for mouse clicks and triggers the onClick behavior.
     * Plays a click sound when pressed.
     */
    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            playSound("button-1.wav");
            onClick();
        }
    }

    /**
     * Plays a sound when the button is clicked.
     * Silently ignores errors if the sound file is missing.
     *
     * @param soundFile The filename of the sound to play
     */
    private void playSound(String soundFile) {
        try {
            Greenfoot.playSound(soundFile);
        } catch (Exception e) {
            // Sound file not found, continue silently
        }
    }

    /**
     * Abstract method to define the behavior when the button is clicked.
     * Subclasses must implement this method.
     */
    public abstract void onClick();
}

