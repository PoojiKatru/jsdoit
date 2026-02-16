import greenfoot.*;

/**
 * SidebarButton represents a clickable button in a sidebar menu.
 * It displays a label and responds to mouse clicks by calling the
 * abstract {@link #onClick()} method, which must be implemented
 * by subclasses to define specific behavior.
 */
public abstract class SidebarButton extends Actor {

    /** The text displayed on the button */
    private String text;

    /**
     * Constructs a SidebarButton with the specified label text.
     *
     * @param t The text to display on the button
     */
    public SidebarButton(String t) {
        this.text = t;
        updateImage();
    }

    /**
     * Draws the visual representation of the button.
     * Background is navy, text is white.
     */
    private void updateImage() {
        GreenfootImage img = new GreenfootImage(160, 50);

        // Background color: Navy
        img.setColor(new Color(0, 0, 128));
        img.fill();

        // Text color: White
        img.setColor(Color.WHITE);
        img.drawString(text, 10, 30);

        setImage(img);
    }

    /**
     * Act method called by Greenfoot on each frame.
     * Detects mouse clicks on this button and triggers the onClick behavior.
     */
    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            onClick();
        }
    }

    /**
     * Abstract method to define the action that occurs when the button is clicked.
     * Subclasses must implement this method to provide functionality.
     */
    public abstract void onClick();
}

