import java.util.ArrayList;
import greenfoot.*;

/**
 * EventPopup represents a popup window for a specific day in the calendar.
 * It allows the user to view, add, and delete events for that day.
 * Includes a close button and automatically closes when clicking outside the popup.
 */
public class EventPopup extends Actor {

    private int day;                   // The day of the month this popup corresponds to
    private ArrayList<String> events;  // List of events for this day
    private CalendarDay parentDay;     // The CalendarDay this popup is associated with
    private int width = 350;           // Popup width
    private int height = 400;          // Popup height

    /**
     * Constructor for EventPopup.
     *
     * @param day       The day number
     * @param events    List of events for this day
     * @param parentDay The CalendarDay this popup belongs to
     */
    public EventPopup(int day, ArrayList<String> events, CalendarDay parentDay) {
        this.day = day;
        this.events = events;
        this.parentDay = parentDay;
        createPopup();
    }

    /**
     * Creates the main popup visual including background, header, and close button.
     */
    private void createPopup() {
        GreenfootImage img = new GreenfootImage(width, height);

        // Semi-transparent shadow
        img.setColor(new greenfoot.Color(0, 0, 0, 150));
        img.fillRect(2, 2, width - 4, height - 4);

        // Main background
        img.setColor(new greenfoot.Color(250, 245, 235));
        img.fillRect(0, 0, width - 4, height - 4);

        // Navy border
        img.setColor(new greenfoot.Color(0, 0, 128));
        img.drawRect(0, 0, width - 5, height - 5);
        img.drawRect(1, 1, width - 7, height - 7);

        // Header
        img.setColor(new greenfoot.Color(0, 0, 128));
        img.fillRect(0, 0, width - 4, 50);

        // Day number in header
        img.setColor(new greenfoot.Color(255, 255, 255));
        img.setFont(new Font("Arial", true, false, 24));
        img.drawString("Day " + day, 15, 35);

        // Close button (X)
        img.setColor(new greenfoot.Color(255, 100, 100));
        img.fillOval(width - 40, 10, 30, 30);
        img.setColor(new greenfoot.Color(255, 255, 255));
        img.setFont(new Font("Arial", true, false, 20));
        img.drawString("×", width - 31, 32);

        setImage(img);
    }

    /**
     * Called when added to the world.
     * Adds the "+ Add Event" button and displays existing events.
     *
     * @param world The Greenfoot world
     */
    protected void addedToWorld(World world) {
        int startY = getY() - (height / 2) + 80;

        // Add Event button
        ButtonActor addBtn = new ButtonActor("+ Add Event", 239, 232, 219, 0, 0, 128, 120, 35) {
            public void onClick() {
                String newEvent = Greenfoot.ask("Enter event name:");
                if (newEvent != null && !newEvent.trim().isEmpty()) {
                    parentDay.addEvent(newEvent.trim());
                    refreshPopup();
                }
            }
        };
        world.addObject(addBtn, getX(), startY);

        // Display current events
        displayEvents(world);
    }

    /**
     * Displays all events for the day inside the popup.
     *
     * @param world The Greenfoot world
     */
    private void displayEvents(World world) {
        int y = getY() - (height / 2) + 130;

        if (events.isEmpty()) {
            TextLabel emptyMsg = new TextLabel(
                "No events yet",
                16,
                new greenfoot.Color(150, 150, 150),
                250, 30,
                new greenfoot.Color(0, 0, 0, 0)
            );
            world.addObject(emptyMsg, getX(), y);
        } else {
            for (String event : events) {
                EventItem item = new EventItem(event, parentDay, this);
                world.addObject(item, getX(), y);
                y += 45;

                if (y > getY() + (height / 2) - 30) break; // Avoid overflow
            }
        }
    }

    /**
     * Refreshes the popup content after adding or removing events.
     */
    public void refreshPopup() {
        World world = getWorld();
        if (world == null) return;

        // Remove all existing event items
        for (EventItem item : world.getObjects(EventItem.class)) {
            world.removeObject(item);
        }

        // Remove empty message if present
        for (TextLabel label : world.getObjects(TextLabel.class)) {
            if (label.getY() > getY() - 100 && label.getY() < getY() + 100) {
                world.removeObject(label);
            }
        }

        displayEvents(world);
    }

    /**
     * Handles mouse interactions for closing the popup.
     * Also detects clicks outside the popup to close it.
     */
    public void act() {
        // Check if clicked on close button
        if (Greenfoot.mouseClicked(this)) {
            MouseInfo mouse = Greenfoot.getMouseInfo();
            if (mouse != null) {
                int relX = mouse.getX() - (getX() - width / 2);
                int relY = mouse.getY() - (getY() - height / 2);

                if (relX > width - 40 && relX < width - 10 && relY > 10 && relY < 40) {
                    closePopup();
                }
            }
        }

        // Close popup if clicked outside
        if (Greenfoot.mouseClicked(null) && !Greenfoot.mouseClicked(this)) {
            boolean clickedOnChild = false;
            World world = getWorld();

            if (world != null) {
                for (ButtonActor btn : world.getObjects(ButtonActor.class)) {
                    if (Greenfoot.mouseClicked(btn)) clickedOnChild = true;
                }
                for (EventItem item : world.getObjects(EventItem.class)) {
                    if (Greenfoot.mouseClicked(item)) clickedOnChild = true;
                }
            }

            if (!clickedOnChild) closePopup();
        }
    }

    /**
     * Closes the popup and removes all associated elements (events, buttons, labels).
     */
    private void closePopup() {
        World world = getWorld();
        if (world != null) {
            // Remove event items
            for (EventItem item : world.getObjects(EventItem.class)) {
                world.removeObject(item);
            }

            // Remove buttons and labels within popup bounds
            for (ButtonActor btn : world.getObjects(ButtonActor.class)) {
                if (Math.abs(btn.getX() - getX()) < width / 2 &&
                    Math.abs(btn.getY() - getY()) < height / 2) {
                    world.removeObject(btn);
                }
            }

            for (TextLabel label : world.getObjects(TextLabel.class)) {
                if (Math.abs(label.getX() - getX()) < width / 2 &&
                    Math.abs(label.getY() - getY()) < height / 2) {
                    world.removeObject(label);
                }
            }

            // Remove popup itself
            world.removeObject(this);
        }
    }
}
