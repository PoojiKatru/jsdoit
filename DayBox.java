import greenfoot.*;

/**
 * DayBox represents a single day in a calendar or note UI.
 * Displays the day number, allows the user to add a note via a click,
 * and visually indicates the presence of a note with a small dot.
 */
public class DayBox extends Actor {

    private String note = ""; // Note associated with this day
    private int day;          // Numeric day of the month

    /**
     * Constructor for DayBox.
     *
     * @param d The numeric day of the month
     */
    public DayBox(int d) {
        this.day = d;
        updateImage();
    }

    /**
     * Updates the visual representation of the DayBox.
     * Draws a rounded cream background, day number, and a small dot if a note exists.
     */
    private void updateImage() {
        int w = 80, h = 70, arc = 16;
        GreenfootImage img = new GreenfootImage(w, h);

        // Rounded cream background with soft border
        drawRoundedRect(img, new greenfoot.Color(252, 248, 243), new greenfoot.Color(206, 193, 181),
                        0, 0, w, h, arc);

        // Day number (charcoal)
        img.setColor(new greenfoot.Color(50, 50, 50));
        img.drawString("" + day, 30, 30);

        // Small dot to indicate a note exists
        if (!note.isEmpty()) {
            img.setColor(new greenfoot.Color(173, 188, 173)); // sage accent
            img.fillOval(w - 14, h - 14, 10, 10);
        }

        setImage(img);
    }

    /**
     * Act method checks for mouse clicks on this DayBox.
     * Prompts the user to enter or update a note via Greenfoot.ask.
     */
    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            String result = Greenfoot.ask("Notes for day " + day + ":");
            if (result != null) {
                note = result.trim();
                updateImage();
            }
        }
    }

    /**
     * Helper method to draw a rounded rectangle with fill and border colors.
     *
     * @param img        The GreenfootImage to draw on
     * @param fillColor  The color to fill the rectangle
     * @param borderColor The color of the border (currently not drawn separately)
     * @param x          X-coordinate of the rectangle
     * @param y          Y-coordinate of the rectangle
     * @param w          Width of the rectangle
     * @param h          Height of the rectangle
     * @param arc        Diameter of the corner arcs
     */
    private void drawRoundedRect(GreenfootImage img, greenfoot.Color fillColor, greenfoot.Color borderColor,
                                 int x, int y, int w, int h, int arc) {
        // Fill main area (center rectangles)
        img.setColor(fillColor);
        img.fillRect(x + arc / 2, y, w - arc, h);
        img.fillRect(x, y + arc / 2, w, h - arc);

        // TODO: Add corner ovals and border if needed
    }
}
