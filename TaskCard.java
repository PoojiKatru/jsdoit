import greenfoot.*;
import java.util.Arrays;
import java.util.List;

/**
 * TaskCard represents a visual task item in the TaskPanel.
 * It displays the task's name, completion status, and urgency level.
 * Users can toggle completion and update urgency through the card UI.
 */
public class TaskCard extends Actor {
    private TaskData task;             // The underlying task data
    private boolean completed;          // Whether the task is marked complete
    private User user;                  // User who owns the task
    private TaskPanel parentPanel;      // Parent panel for refreshing display

    private List<String> urgencyLevels = Arrays.asList("Low", "Normal", "High", "Critical");

    /**
     * Constructs a TaskCard for a given task.
     *
     * @param task         The TaskData object.
     * @param completed    Whether the task is initially completed.
     * @param user         The owning User.
     * @param parentPanel  The TaskPanel containing this card.
     */
    public TaskCard(TaskData task, boolean completed, User user, TaskPanel parentPanel) {
        this.task = task;
        this.completed = completed;
        this.user = user;
        this.parentPanel = parentPanel;
        updateImage();
    }

    /**
     * Updates the visual representation of the task card.
     * Shows background, shadow, border, checkbox, task name, and urgency badge.
     */
    private void updateImage() {
        GreenfootImage img = new GreenfootImage(450, 60);

        // Background
        img.setColor(completed ? new greenfoot.Color(220, 215, 200) : new greenfoot.Color(250, 245, 235));
        img.fill();

        // Shadow
        img.setColor(new greenfoot.Color(200, 190, 170));
        img.fillRect(2, 2, 446, 56);

        // Main card
        img.setColor(completed ? new greenfoot.Color(230, 225, 210) : new greenfoot.Color(255, 250, 240));
        img.fillRect(0, 0, 446, 56);

        // Border
        img.setColor(new greenfoot.Color(0, 0, 128));
        img.drawRect(0, 0, 445, 55);

        // Checkbox
        img.setColor(new greenfoot.Color(0, 0, 128));
        img.drawRect(10, 12, 18, 18);
        if (completed) img.drawString("✓", 13, 27);

        // Task name
        img.setFont(new Font("Arial", false, false, 16));
        img.setColor(completed ? new greenfoot.Color(100, 100, 100) : new greenfoot.Color(0, 0, 0));
        img.drawString(task.getTaskName(), 40, 25);

        drawUrgencyBadge(img);

        setImage(img);
    }

    /**
     * Draws the urgency badge on the card.
     *
     * @param img The GreenfootImage to draw on.
     */
    private void drawUrgencyBadge(GreenfootImage img) {
        String urgency = urgencyLevels.get(task.getUrgencyLevel());
        int x = 40, y = 32, w = 110, h = 22;

        // Determine badge color based on urgency
        greenfoot.Color badgeColor;
        switch(task.getUrgencyLevel()) {
            case 0: badgeColor = new greenfoot.Color(200, 230, 200); break;  // Low
            case 1: badgeColor = new greenfoot.Color(200, 200, 255); break;  // Normal
            case 2: badgeColor = new greenfoot.Color(255, 200, 100); break;  // High
            case 3: badgeColor = new greenfoot.Color(255, 150, 150); break;  // Critical
            default: badgeColor = new greenfoot.Color(200, 200, 200);
        }

        img.setColor(badgeColor);
        img.fillRect(x, y, w, h);

        // Border
        img.setColor(new greenfoot.Color(80, 80, 80));
        img.drawRect(x, y, w, h);

        // Text
        img.setFont(new Font("Arial", false, false, 13));
        img.setColor(new greenfoot.Color(40, 40, 40));
        img.drawString("⚡ " + urgency, x + 5, y + 16);

        // Dropdown indicator
        img.drawString("▼", x + w - 15, y + 16);
    }

    /**
     * Handles user interaction with the card.
     * Click on the checkbox toggles completion, click on the urgency badge opens the urgency menu.
     */
    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            MouseInfo mouse = Greenfoot.getMouseInfo();
            if (mouse != null) {
                int cardLeft = getX() - 225;
                int cardTop = getY() - 30;
                int relX = mouse.getX() - cardLeft;
                int relY = mouse.getY() - cardTop;

                // Click on urgency badge
                if (relX >= 40 && relX <= 150 && relY >= 32 && relY <= 54) {
                    showUrgencyOptions();
                    playSound("click.wav");
                }
                // Click on checkbox
                else if (relX >= 10 && relX <= 28 && relY >= 12 && relY <= 30) {
                    toggleCompletion();
                }
            }
        }
    }

    /**
     * Opens the urgency menu for this task card.
     */
    private void showUrgencyOptions() {
        World w = getWorld();
        if (w == null) return;

        for (UrgencyMenu m : w.getObjects(UrgencyMenu.class)) {
            w.removeObject(m);
        }

        UrgencyMenu menu = new UrgencyMenu(this);
        w.addObject(menu, getX() + 35, getY() + 45);
    }

    /**
     * Toggles the task's completion status and refreshes the parent panel.
     */
    private void toggleCompletion() {
        if (completed) user.getTasks().markIncomplete(task);
        else user.getTasks().markCompleted(task);

        completed = !completed;  
        playSound("click.wav");

        if (parentPanel != null) parentPanel.refreshTasks();
    }

    /**
     * Sets the task's urgency level and updates the visual card.
     *
     * @param index Index corresponding to the urgency level.
     */
    public void setUrgency(int index) {
        task.setUrgencyLevel(index);
        updateImage();
    }

    /**
     * Returns the string representation of the current urgency level.
     *
     * @return Current urgency as a string.
     */
    public String getUrgency() {
        return urgencyLevels.get(task.getUrgencyLevel());
    }

    /**
     * Safely plays a sound file.
     *
     * @param s The filename of the sound.
     */
    private void playSound(String s) {
        try { Greenfoot.playSound(s); } catch (Exception e) {}
    }
}


