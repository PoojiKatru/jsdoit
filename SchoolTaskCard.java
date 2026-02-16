import greenfoot.*;

/**
 * SchoolTaskCard represents a visual card for a single school assignment
 * within a {@link SchoolPanel}. It displays the task name, teacher,
 * and completion status. Clicking the card toggles its completion.
 */
public class SchoolTaskCard extends Actor {

    /** The school task associated with this card */
    private SchoolTask task;

    /** The user who owns this task */
    private User user;

    /** The parent panel displaying this card */
    private SchoolPanel parentPanel;

    /**
     * Constructs a SchoolTaskCard for the given task.
     *
     * @param task The SchoolTask to display
     * @param user The owner of the task
     * @param parentPanel The panel this card belongs to
     */
    public SchoolTaskCard(SchoolTask task, User user, SchoolPanel parentPanel) {
        this.task = task;
        this.user = user;
        this.parentPanel = parentPanel;
        updateImage();
    }

    /**
     * Draws the visual representation of the task card, including:
     * - Background and shadow
     * - Navy border
     * - Checkbox (with checkmark if completed)
     * - Task name and teacher
     * Completed tasks appear greyed out.
     */
    private void updateImage() {
        GreenfootImage img = new GreenfootImage(450, 50);

        // Background color based on completion
        if (task.isCompleted()) {
            img.setColor(new greenfoot.Color(220, 215, 200));
        } else {
            img.setColor(new greenfoot.Color(250, 245, 235));
        }
        img.fill();

        // Shadow effect
        img.setColor(new greenfoot.Color(200, 190, 170));
        img.fillRect(2, 2, 446, 46);

        // Main card
        if (task.isCompleted()) {
            img.setColor(new greenfoot.Color(230, 225, 210));
        } else {
            img.setColor(new greenfoot.Color(255, 250, 240));
        }
        img.fillRect(0, 0, 446, 46);

        // Navy border
        img.setColor(new greenfoot.Color(0, 0, 128));
        img.drawRect(0, 0, 445, 45);

        // Checkbox
        img.setColor(new greenfoot.Color(0, 0, 128));
        img.drawRect(10, 15, 18, 18);
        if (task.isCompleted()) {
            img.drawString("✓", 13, 30);
        }

        // Task name
        img.setFont(new Font("Arial", false, false, 16));
        img.setColor(task.isCompleted() ? new greenfoot.Color(100, 100, 100)
                                        : new greenfoot.Color(0, 0, 0));
        img.drawString(task.getTaskName(), 40, 24);

        // Teacher name (smaller, italics)
        img.setFont(new Font("Arial", false, true, 13));
        img.setColor(new greenfoot.Color(100, 100, 150));
        img.drawString("Teacher: " + task.getTeacher(), 40, 40);

        setImage(img);
    }

    /**
     * Called by Greenfoot on each act cycle.
     * Detects clicks on the card and toggles task completion.
     * Updates the parent panel and plays sounds accordingly.
     */
    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            task.setCompleted(!task.isCompleted());

            if (task.isCompleted()) {
                playSound("success.wav");
            } else {
                playSound("click.wav");
            }

            if (parentPanel != null) {
                parentPanel.refreshTasks();
            }
        }
    }

    /**
     * Plays a sound file safely; exceptions are ignored if the file is missing.
     *
     * @param soundFile The filename of the sound to play
     */
    private void playSound(String soundFile) {
        try {
            Greenfoot.playSound(soundFile);
        } catch (Exception e) {
            // Sound file not found, continue silently
        }
    }
}
