import greenfoot.*;
import java.util.ArrayList;

/**
 * TaskPanel is a visual panel for displaying a user's tasks in Greenfoot.
 * It separates tasks into "To Do" and "Completed" sections, allows adding
 * new tasks, and manages TaskCard objects for display.
 */
public class TaskPanel extends Actor {
    private User user;                           // The user whose tasks are displayed
    private int width, height;                   // Panel dimensions
    private ArrayList<TaskCard> cards = new ArrayList<>();      // Current TaskCard objects
    private ArrayList<Actor> panelElements = new ArrayList<>(); // All UI elements including labels and buttons
    private ButtonActor addButton;               // Button to add new tasks

    /**
     * Constructor for creating a TaskPanel.
     *
     * @param user   The User whose tasks will be displayed.
     * @param width  Width of the panel.
     * @param height Height of the panel.
     */
    public TaskPanel(User user, int width, int height) {
        this.user = user;
        this.width = width;
        this.height = height;
        drawPanel();
    }

    /**
     * Called when the panel is added to the world.
     * Adds the title label and "Add Task" button.
     */
    protected void addedToWorld(World world) {
        // Title
        TextLabel title = new TextLabel("My Tasks", 32, new greenfoot.Color(0, 0, 128), 200, 40, new greenfoot.Color(0,0,0,0));
        world.addObject(title, getX(), getY() - height / 2 + 30);
        panelElements.add(title);

        // Add Task button
        addButton = new ButtonActor("+ New Task", 239, 232, 219, 0,0,128, 130, 40) {
            public void onClick() {
                String taskName = Greenfoot.ask("Enter new task:");
                if (taskName != null && !taskName.trim().isEmpty()) {
                    user.getTasks().addTask(taskName.trim(), 1); // default urgency = 1 (Normal)
                    refreshTasks();
                }
            }
        };
        world.addObject(addButton, getX(), getY() - height / 2 + 80);
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
            img.setColor(new greenfoot.Color(r,g,b));
            img.drawLine(0, i, width, i);
        }

        // Navy border
        img.setColor(new greenfoot.Color(0,0,128));
        img.drawRect(0,0,width-1,height-1);
        img.drawRect(1,1,width-3,height-3);

        setImage(img);
    }

    /**
     * Refreshes the displayed tasks.
     * Clears old TaskCard objects and labels, then re-displays To Do and Completed tasks.
     */
    public void refreshTasks() {
        World w = getWorld();
        if (w == null) return;

        // Remove old cards
        for (TaskCard c : cards) {
            if (c.getWorld() != null) w.removeObject(c);
        }
        cards.clear();

        // Remove old labels (except title)
        for (Actor a : new ArrayList<>(panelElements)) {
            if (a instanceof TextLabel && a != panelElements.get(0)) {
                if (a.getWorld() != null) w.removeObject(a);
                panelElements.remove(a);
            }
        }

        int y = getY() - height / 2 + 130;
        int spacing = 65;

        ArrayList<TaskData> todo = user.getTasks().getToDo();
        ArrayList<TaskData> done = user.getTasks().getCompleted();

        // Display To Do tasks
        if (!todo.isEmpty()) {
            TextLabel todoLabel = new TextLabel("To Do", 20, new greenfoot.Color(0,0,128), 150, 30, new greenfoot.Color(0,0,0,0));
            w.addObject(todoLabel, getX() - 150, y);
            panelElements.add(todoLabel);
            y += 40;

            for (TaskData t : todo) {
                TaskCard card = new TaskCard(t, false, user, this);
                w.addObject(card, getX(), y);
                cards.add(card);
                panelElements.add(card);
                y += spacing;
            }
        }

        // Display Completed tasks
        if (!done.isEmpty()) {
            y += 10;
            TextLabel doneLabel = new TextLabel("Completed", 20, new greenfoot.Color(0,150,0), 150, 30, new greenfoot.Color(0,0,0,0));
            w.addObject(doneLabel, getX() - 150, y);
            panelElements.add(doneLabel);
            y += 40;

            for (TaskData t : done) {
                TaskCard card = new TaskCard(t, true, user, this);
                w.addObject(card, getX(), y);
                cards.add(card);
                panelElements.add(card);
                y += spacing;
            }
        }

        // Show empty message if no tasks exist
        if (todo.isEmpty() && done.isEmpty()) {
            TextLabel emptyMsg = new TextLabel("No tasks yet. Click '+ New Task' to add one!", 18, new greenfoot.Color(100,100,100), 400, 30, new greenfoot.Color(0,0,0,0));
            w.addObject(emptyMsg, getX(), getY());
            panelElements.add(emptyMsg);
        }
    }

    /**
     * Removes all elements (TaskCards, labels, buttons) from the panel.
     * Useful for cleanup when switching worlds or panels.
     */
    public void removeAllElements() {
        World w = getWorld();
        if (w == null) return;

        for (Actor a : panelElements) {
            if (a.getWorld() != null) w.removeObject(a);
        }

        panelElements.clear();
        cards.clear();
    }
}


