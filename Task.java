import greenfoot.*;

/**
 * The Task class represents a single task item in the Greenfoot world.
 * Each task displays a text label on a pastel background and can be
 * removed from the world by clicking it.
 */
public class Task extends Actor {
    private String text; // The text content of the task

    /**
     * Constructs a Task with the given text.
     *
     * @param t The text describing the task.
     */
    public Task(String t) {
        this.text = t;
        updateImage();
    }

    /**
     * Updates the visual representation of the task.
     * Draws a pastel background and renders the task text.
     */
    public void updateImage() {
        GreenfootImage img = new GreenfootImage(300, 50);

        // Pastel background
        img.setColor(new greenfoot.Color(255, 228, 225));
        img.fill();

        // Text
        img.setColor(new greenfoot.Color(0, 0, 0));
        img.setFont(new Font("Arial", 18));
        img.drawString(text, 10, 30);

        setImage(img);
    }

    /**
     * Called by Greenfoot in each act cycle.
     * Checks for mouse clicks on this task; if clicked, removes it from the world.
     */
    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            getWorld().removeObject(this);
        }
    }
}

