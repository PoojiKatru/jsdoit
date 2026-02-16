import greenfoot.*;

/**
 * AestheticButton represents a soft, rounded, cream-colored button for UI elements,
 * typically used in sidebars. It displays a text label and responds to mouse clicks.
 * Subclasses must implement the {@link #onClick()} method to define the button's behavior.
 */
public abstract class AestheticButton extends Actor {

    private String label;

    /**
     * Constructor for AestheticButton.
     *
     * @param text The text to display on the button.
     */
    public AestheticButton(String text) {
        this.label = text;
        updateImage();
    }

    /**
     * Updates the button image.
     * Draws a rounded rectangle with a fill color and border,
     * then draws the button label.
     */
    private void updateImage() {
        int w = 160, h = 45, arc = 18;
        GreenfootImage img = new GreenfootImage(w, h);

        // Draw the rounded rectangle
        drawRoundedRect(img, 
                        new greenfoot.Color(252, 248, 243), // fill color (cream)
                        new greenfoot.Color(206, 193, 181), // border color
                        0, 0, w, h, arc);

        // Draw the text label
        img.setColor(new greenfoot.Color(50, 50, 50)); // dark gray text
        img.drawString(label, 20, 28); // x=20, y=28 roughly centered vertically

        setImage(img);
    }

    /**
     * Act method checks for mouse clicks and triggers onClick behavior.
     */
    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            onClick();
        }
    }

    /**
     * Abstract method to define what happens when the button is clicked.
     * Subclasses must implement this method.
     */
    public abstract void onClick();

    /**
     * Draws a rounded rectangle on a GreenfootImage.
     * Fills the center rectangle and corner ovals with the fill color,
     * then draws the border around it using the border color.
     *
     * @param img         The GreenfootImage to draw on
     * @param fillColor   Color for the rectangle fill
     * @param borderColor Color for the border
     * @param x           X-coordinate of the rectangle
     * @param y           Y-coordinate of the rectangle
     * @param w           Width of the rectangle
     * @param h           Height of the rectangle
     * @param arc         Diameter of the corner arcs
     */
    private void drawRoundedRect(GreenfootImage img, greenfoot.Color fillColor, greenfoot.Color borderColor,
                                 int x, int y, int w, int h, int arc) {
        // Fill main area (center rectangle)
        img.setColor(fillColor);
        img.fillRect(x + arc/2, y, w - arc, h);      // horizontal central rectangle
        img.fillRect(x, y + arc/2, w, h - arc);      // vertical central rectangle

        // TODO: Add corner ovals and border drawing for full rounded rectangle appearance
    }
}
