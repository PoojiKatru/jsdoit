import greenfoot.*;

/**
 * EventItem represents a single event displayed in an EventPopup.
 * Shows the event name with a colored dot and a clickable delete button.
 * When the delete button is clicked, the event is removed from the parent CalendarDay
 * and the popup is refreshed.
 */
public class EventItem extends Actor {

    private String eventName;       // Name of the event
    private CalendarDay parentDay;  // The CalendarDay this event belongs to
    private EventPopup parentPopup; // The EventPopup containing this item

    /**
     * Constructor for EventItem.
     *
     * @param eventName   The name of the event
     * @param parentDay   The CalendarDay this event is associated with
     * @param parentPopup The EventPopup displaying this event
     */
    public EventItem(String eventName, CalendarDay parentDay, EventPopup parentPopup) {
        this.eventName = eventName;
        this.parentDay = parentDay;
        this.parentPopup = parentPopup;
        updateImage();
    }

    /**
     * Updates the visual representation of the EventItem.
     * Draws the background, border, event dot, event text, and delete button.
     */
    private void updateImage() {
        GreenfootImage img = new GreenfootImage(300, 40);

        // Background
        img.setColor(new greenfoot.Color(255, 255, 255));
        img.fill();

        // Border
        img.setColor(new greenfoot.Color(200, 200, 200));
        img.drawRect(0, 0, 299, 39);

        // Event dot
        img.setColor(new greenfoot.Color(0, 0, 128));
        img.fillOval(10, 15, 10, 10);

        // Event text
        img.setColor(new greenfoot.Color(0, 0, 0));
        img.setFont(new Font("Arial", false, false, 14));

        String displayText = eventName;
        if (displayText.length() > 28) {
            displayText = displayText.substring(0, 28) + "...";
        }
        img.drawString(displayText, 30, 25);

        // Delete button
        img.setColor(new greenfoot.Color(200, 0, 0));
        img.fillOval(270, 10, 20, 20);
        img.setColor(new greenfoot.Color(255, 255, 255));
        img.setFont(new Font("Arial", true, false, 14));
        img.drawString("×", 276, 25);

        setImage(img);
    }

    /**
     * Act method checks for mouse clicks on this EventItem.
     * If the delete button area is clicked, removes the event from the parent CalendarDay,
     * refreshes the popup, and plays a delete sound.
     */
    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            MouseInfo mouse = Greenfoot.getMouseInfo();
            if (mouse != null) {
                int relX = mouse.getX() - (getX() - 150);

                // Check if clicked on delete button (x = 270 to 290)
                if (relX > 270 && relX < 290) {
                    parentDay.removeEvent(eventName);
                    parentPopup.refreshPopup();
                    playSound("delete.wav");
                }
            }
        }
    }

    /**
     * Plays a sound effect.
     * Silently ignores errors if the sound file is missing.
     *
     * @param soundFile The sound file to play
     */
    private void playSound(String soundFile) {
        try {
            Greenfoot.playSound(soundFile);
        } catch (Exception e) {
            // Ignore missing sound
        }
    }
}

