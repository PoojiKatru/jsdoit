import greenfoot.*;
import java.util.ArrayList;

/**
 * CalendarDay represents a single day in a calendar UI with optional events.
 * It displays the day number and highlights today. Clicking the day opens an EventPopup
 * to view or manage events for that day.
 */
public class CalendarDay extends Actor {

    private int day;                      // Numeric day of the month
    private User currentUser;             // The user owning this calendar
    private ArrayList<String> events = new ArrayList<>(); // List of events for this day
    private boolean isToday;              // Whether this day is the current day
    private int w = 68, h = 65;           // Width and height of the CalendarDay box
    private EventPopup popup;             // Popup displayed when clicked

    /**
     * Constructor for CalendarDay.
     *
     * @param day         The numeric day of the month
     * @param currentUser The user owning this calendar
     * @param isToday     Whether this day is today (highlighted)
     */
    public CalendarDay(int day, User currentUser, boolean isToday) {
        this.day = day;
        this.currentUser = currentUser;
        this.isToday = isToday;
        updateImage();
    }

    /**
     * Act method handles mouse clicks on this CalendarDay.
     * Clicking opens an EventPopup in the center of the world and removes any previous popup.
     */
    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            World world = getWorld();
            if (world != null) {
                // Remove existing popups
                for (EventPopup p : world.getObjects(EventPopup.class)) {
                    world.removeObject(p);
                }

                // Create new popup for this day
                popup = new EventPopup(day, events, this);
                world.addObject(popup, world.getWidth() / 2, world.getHeight() / 2);
                playSound("click.wav");
            }
        }
    }

    /**
     * Adds a new event to this day and updates the display.
     *
     * @param event The event name to add
     */
    public void addEvent(String event) {
        events.add(event);
        updateImage();
    }

    /**
     * Removes an event from this day and updates the display.
     *
     * @param event The event name to remove
     */
    public void removeEvent(String event) {
        events.remove(event);
        updateImage();
    }

    /**
     * Updates the visual representation of this CalendarDay.
     * Highlights today, displays the day number, shows the first event,
     * and indicates the number of additional events.
     */
    private void updateImage() {
        GreenfootImage img = new GreenfootImage(w, h);

        // Background color: light blue if today, white otherwise
        img.setColor(isToday ? new greenfoot.Color(225, 235, 255) : new greenfoot.Color(255, 255, 255));
        img.fill();

        // Border for hover effect
        img.setColor(new greenfoot.Color(230, 230, 230));
        img.drawRect(0, 0, w - 1, h - 1);

        // Day number
        img.setFont(new Font("Arial", false, false, 14));
        if (isToday) {
            img.setColor(new greenfoot.Color(0, 0, 128));
            img.fillOval(w / 2 - 12, 5, 24, 24); // highlight circle
            img.setColor(new greenfoot.Color(255, 255, 255));
        } else {
            img.setColor(new greenfoot.Color(60, 60, 60));
        }
        String dayStr = "" + day;
        int textWidth = img.getFont().getSize() * dayStr.length() / 2;
        img.drawString(dayStr, w / 2 - textWidth, 20);

        // Display first event if available
        if (!events.isEmpty()) {
            img.setColor(new greenfoot.Color(0, 0, 128));
            img.setFont(new Font("Arial", false, false, 9));

            String firstEvent = events.get(0);
            if (firstEvent.length() > 9) {
                firstEvent = firstEvent.substring(0, 9) + "..";
            }
            img.drawString(firstEvent, 3, 35);

            // Show count of additional events
            if (events.size() > 1) {
                img.setColor(new greenfoot.Color(100, 100, 100));
                img.drawString("+" + (events.size() - 1) + " more", 3, 45);
            }
        }

        setImage(img);
    }

    /**
     * Plays a sound effect when the day is clicked.
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

    /**
     * Returns the list of events for this day.
     *
     * @return ArrayList of event names
     */
    public ArrayList<String> getEvents() {
        return events;
    }
}

