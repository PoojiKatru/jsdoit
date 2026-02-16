import greenfoot.*;

/**
 * CalendarBox represents a single day in a calendar UI.
 * It displays the day number and an optional label.
 * Responds to mouse clicks by printing the day to the console.
 */
public class CalendarBox extends Actor {

    private String label;
    private int day;

    /**
     * Constructor for CalendarBox.
     *
     * @param label Optional label for the day (e.g., "Holiday" or event name)
     * @param day   The numeric day of the month to display
     */
    public CalendarBox(String label, int day) {
        this.label = label;
        this.day = day;
        updateImage();
    }

    /**
     * Updates the visual representation of the CalendarBox.
     * Draws a white rectangle with a light border and the day number centered.
     */
    private void updateImage() {
        GreenfootImage img = new GreenfootImage(80, 60);

        // Fill background
        img.setColor(new greenfoot.Color(255, 255, 255));
        img.fill();

        // Draw border
        img.setColor(new greenfoot.Color(200, 190, 180));
        img.drawRect(0, 0, img.getWidth() - 1, img.getHeight() - 1);

        // Draw day number
        img.setColor(new greenfoot.Color(60, 60, 60));
        img.drawString("" + day, 30, 35); // rough center position

        setImage(img);
    }

    /**
     * Act method checks for mouse clicks on this CalendarBox.
     * Prints the clicked day number to the console.
     */
    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            System.out.println("Clicked day: " + day);
        }
    }
}
