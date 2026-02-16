import greenfoot.*;
import java.util.ArrayList;

/**
 * SchoolPanel is a visual panel displaying a user's school tasks in Greenfoot.
 * It shows pending and completed assignments, allows adding new tasks, and
 * organizes tasks using SchoolTaskCard objects. It also manages cleanup of
 * its elements when needed.
 */
public class SchoolPanel extends Actor {
    private User user;                           // The current user whose tasks are displayed
    private int width, height;                   // Panel dimensions
    private ArrayList<SchoolTaskCard> cards = new ArrayList<>(); // Current task cards
    private ArrayList<Actor> panelElements = new ArrayList<>();  // All panel UI elements
    private ButtonActor addButton;               // Button to add new assignments

    /**
     * Constructor to create a SchoolPanel for a specific user.
     *
     * @param user   The User whose school tasks will be displayed.
     * @param width  Width of the panel.
     * @param height Height of the panel.
     */
    public SchoolPanel(User user, int width, int height) {
        this.user = user;
        this.width = width;
        this.height = height;
        drawPanel();
    }

    /**
     * Called when the panel is added to the world.
     * Adds the title and "Add Task" button to the panel.
     */
    protected void addedToWorld(World world) {
        // Add title
        TextLabel title = new TextLabel("School Tasks", 32, new greenfoot.Color(0, 0, 128), 250, 40, new greenfoot.Color(0, 0, 0, 0));
        world.addObject(title, getX(), getY() - (height / 2) + 30);
        panelElements.add(title);

        // Add "Add Task" button
        addButton = new ButtonActor("+ New Assignment", 239, 232, 219, 0, 0, 128, 150, 40) {
            public void onClick() {
                String task = Greenfoot.ask("Enter assignment name:");
                if (task != null && !task.trim().isEmpty()) {
                    String teacher = Greenfoot.ask("Enter teacher name:");
                    if (teacher != null && !teacher.trim().isEmpty()) {
                        user.getSchoolTasks().add(new SchoolTask(task.trim(), teacher.trim()));
                        refreshTasks();
                    }
                }
            }
        };
        world.addObject(addButton, getX(), getY() - (height / 2) + 80);
        panelElements.add(addButton);

        refreshTasks();
    }

    /**
     * Draws the panel background with a beige gradient and navy border.
     */
    private void drawPanel() {
        GreenfootImage img = new GreenfootImage(width, height);

        // Gradient background
        for (int i = 0; i < height; i++) {
            int r = 239 - (i * 10 / height);
            int g = 232 - (i * 10 / height);
            int b = 219 - (i * 10 / height);
            img.setColor(new greenfoot.Color(r, g, b));
            img.drawLine(0, i, width, i);
        }

        // Navy border
        img.setColor(new greenfoot.Color(0, 0, 128));
        img.drawRect(0, 0, width - 1, height - 1);
        img.drawRect(1, 1, width - 3, height - 3);

        setImage(img);
    }

    /**
     * Refreshes the displayed tasks by clearing old cards and labels,
     * and re-displaying pending and completed tasks in order.
     */
    public void refreshTasks() {
        World w = getWorld();
        if (w == null) return;

        // Remove old cards
        for (SchoolTaskCard c : cards) {
            if (c.getWorld() != null) {
                w.removeObject(c);
                panelElements.remove(c);
            }
        }
        cards.clear();

        // Remove old labels (except title)
        for (Actor a : new ArrayList<>(panelElements)) {
            if (a instanceof TextLabel && a != panelElements.get(0)) {
                if (a.getWorld() != null) {
                    w.removeObject(a);
                }
                panelElements.remove(a);
            }
        }

        int y = getY() - (height / 2) + 130;
        int spacing = 55;

        ArrayList<SchoolTask> allTasks = user.getSchoolTasks();
        ArrayList<SchoolTask> pending = new ArrayList<>();
        ArrayList<SchoolTask> completed = new ArrayList<>();

        for (SchoolTask task : allTasks) {
            if (task.isCompleted()) completed.add(task);
            else pending.add(task);
        }

        // Display pending tasks
        if (!pending.isEmpty()) {
            TextLabel pendingLabel = new TextLabel("To Do", 20, new greenfoot.Color(0, 0, 128), 150, 30, new greenfoot.Color(0, 0, 0, 0));
            w.addObject(pendingLabel, getX() - 150, y);
            panelElements.add(pendingLabel);
            y += 40;
            for (SchoolTask task : pending) {
                SchoolTaskCard card = new SchoolTaskCard(task, user, this);
                w.addObject(card, getX(), y);
                cards.add(card);
                panelElements.add(card);
                y += spacing;
            }
        }

        // Display completed tasks
        if (!completed.isEmpty()) {
            y += 10;
            TextLabel completedLabel = new TextLabel("Completed", 20, new greenfoot.Color(0, 150, 0), 150, 30, new greenfoot.Color(0, 0, 0, 0));
            w.addObject(completedLabel, getX() - 150, y);
            panelElements.add(completedLabel);
            y += 40;
            for (SchoolTask task : completed) {
                SchoolTaskCard card = new SchoolTaskCard(task, user, this);
                w.addObject(card, getX(), y);
                cards.add(card);
                panelElements.add(card);
                y += spacing;
            }
        }

        // Show empty message if no tasks
        if (pending.isEmpty() && completed.isEmpty()) {
            TextLabel emptyMsg = new TextLabel("No assignments yet. Click '+ New Assignment'!", 18, new greenfoot.Color(100, 100, 100), 400, 30, new greenfoot.Color(0, 0, 0, 0));
            w.addObject(emptyMsg, getX(), getY());
            panelElements.add(emptyMsg);
        }
    }

    /**
     * Removes all elements from the panel including task cards, labels, and buttons.
     * Useful for cleanup when switching worlds or panels.
     */
    public void removeAllElements() {
        World world = getWorld();
        if (world == null) return;

        for (Actor element : panelElements) {
            if (element.getWorld() != null) {
                world.removeObject(element);
            }
        }
        panelElements.clear();
        cards.clear();
    }
}
