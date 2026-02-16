import greenfoot.*;

/**
 * RoundedButton is an abstract class representing a customizable button
 * with a colored background and centered text. It is clickable, and the
 * specific action on click is defined by subclasses via the onClick() method.
 */
public abstract class RoundedButton extends Actor {

    private String label;      // Text displayed on the button
    private int width;         // Button width
    private int height;        // Button height
    private Color bg;          // Background color
    private Color text;        // Text color

    /**
     * Constructor for RoundedButton.
     *
     * @param label    The text displayed on the button.
     * @param width    Width of the button.
     * @param height   Height of the button.
     * @param bgColor  Background color of the button.
     * @param textColor Text color of the label.
     */
    public RoundedButton(String label, int width, int height, Color bgColor, Color textColor) {
        this.label = label;
        this.width = width;
        this.height = height;
        this.bg = bgColor;
        this.text = textColor;

        updateImage();
    }

    /**
     * Draws the button image with background and centered text.
     */
    private void updateImage() {
        GreenfootImage img = new GreenfootImage(width, height);

        // Set button background
        img.setColor(bg);
        img.fill(); // Fill the entire rectangle with bg color

        // Draw centered text
        GreenfootImage textImg = new GreenfootImage(label, 24, text, new Color(0,0,0,0));
        img.drawImage(textImg, (width - textImg.getWidth()) / 2, (height - textImg.getHeight()) / 2);

        setImage(img);
    }

    /**
     * Act method called every frame.
     * Detects mouse clicks on the button and calls the abstract onClick() method.
     */
    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            onClick();
        }
    }

    /**
     * Abstract method defining what happens when the button is clicked.
     * Must be implemented by subclasses (e.g., in DashboardWorld).
     */
    public abstract void onClick();
}

