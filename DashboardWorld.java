import greenfoot.*;
import java.util.ArrayList;

/**
 * 
 * The DashboardWorld class represents the main dashboard interface for a user.
 * It includes a sidebar for navigation between Home, Calendar, Tasks, School, Notes, 
 * and Logout options. It dynamically displays panels based on user interaction.
 */
public class DashboardWorld extends World {

    private User currentUser;
    private UserManager userManager;
    private ArrayList<Actor> contentArea = new ArrayList<>();

    /**
     * Constructor for DashboardWorld.
     * Initializes the world, sets the background color, adds the sidebar, and shows the Home page.
     *
     * @param user    The current logged-in user.
     * @param manager The user manager handling users.
     */
    public DashboardWorld(User user, UserManager manager) {
        super(800, 600, 1);
        this.currentUser = user;
        this.userManager = manager;
        setBackgroundColor();
        addSidebar();
        showHome();
    }

    /**
     * Sets the background color of the dashboard to a light beige color.
     */
    private void setBackgroundColor() {
        GreenfootImage bg = new GreenfootImage(getWidth(), getHeight());
        bg.setColor(new greenfoot.Color(239, 232, 219)); // beige
        bg.fill();
        setBackground(bg);
    }

    /**
     * Adds the sidebar buttons for navigation: Home, Calendar, Tasks, School, Notes, and Log Out.
     * Each button is linked to its respective method.
     */
    private void addSidebar() {
        int sidebarX = 75;
        int startY = 150;
        int spacing = 70;

        // Home Button
        addObject(new ButtonActor("Home", 255, 255, 255, 0, 0, 128, 120, 50) {
            public void onClick() { showHome(); }
        }, sidebarX, startY);

        // Calendar Button
        addObject(new ButtonActor("Calendar", 255, 255, 255, 0, 0, 128, 120, 50) {
            public void onClick() { showCalendar(); }
        }, sidebarX, startY + spacing);

        // Tasks Button
        addObject(new ButtonActor("Tasks", 255, 255, 255, 0, 0, 128, 120, 50) {
            public void onClick() { showTasks(); }
        }, sidebarX, startY + spacing*2);

        // School Button
        addObject(new ButtonActor("School", 255, 255, 255, 0, 0, 128, 120, 50) {
            public void onClick() { showSchool(); }
        }, sidebarX, startY + spacing*3);

        // Notes Button
        addObject(new ButtonActor("Notes", 255, 255, 255, 0, 0, 128, 120, 50) {
            public void onClick() { showNotes(); }
        }, sidebarX, startY + spacing*4);

        // Logout Button
        addObject(new ButtonActor("Log Out", 255, 255, 255, 128, 0, 0, 120, 50) {
            public void onClick() { Greenfoot.setWorld(new LoginWorld(userManager)); }
        }, sidebarX, startY + spacing*5);
    }

    /**
     * Shows the School page by adding a SchoolPanel to the dashboard.
     */
    private void showSchool() {
        clearContent();
        SchoolPanel schoolPanel = new SchoolPanel(currentUser, 500, 400);
        addObject(schoolPanel, 500, 300);
        contentArea.add(schoolPanel);
    }

    /**
     * Clears the main content area by removing all existing panels and temporary objects.
     * Ensures that the dashboard does not overlap content when switching pages.
     */
    private void clearContent() {
        // Remove existing panels and their elements
        for (CalendarPanel panel : getObjects(CalendarPanel.class)) panel.removeAllElements();
        for (TaskPanel panel : getObjects(TaskPanel.class)) panel.removeAllElements();
        for (SchoolPanel panel : getObjects(SchoolPanel.class)) panel.removeAllElements();
        for (NotesPanel panel : getObjects(NotesPanel.class)) panel.removeAllElements();
        for (MusicSelector selector : getObjects(MusicSelector.class)) selector.removeAllElements();

        // Remove menus and temporary objects
        removeObjects(getObjects(UrgencyMenu.class));
        removeObjects(getObjects(EventPopup.class));
        removeObjects(getObjects(EventItem.class));

        // Remove content actors
        for (Actor a : contentArea) {
            if (a.getWorld() != null) removeObject(a);
        }

        contentArea.clear();
    }

    // ----------------- Pages -----------------

    /**
     * Shows the Home page with welcome message, summary cards, and music selector.
     */
    private void showHome() {
        clearContent();

        // Welcome header
        TextLabel welcome = new TextLabel(
            "Welcome back, " + currentUser.getUsername() + "! ✨",
            40,
            new greenfoot.Color(0, 0, 128),
            700, 70,
            new greenfoot.Color(0, 0, 0, 0)
        );
        addObject(welcome, 520, 80);
        contentArea.add(welcome);

        // Subtitle
        TextLabel subtitle = new TextLabel(
            "Here's your productivity overview",
            18,
            new greenfoot.Color(100, 100, 100),
            500, 30,
            new greenfoot.Color(0, 0, 0, 0)
        );
        addObject(subtitle, 520, 120);
        contentArea.add(subtitle);

        // Left side - Summary cards
        int cardX = 340;
        int cardStartY = 210;
        int cardSpacing = 95;

        // Tasks Card
        int pendingTasks = currentUser.getTasks().getToDo().size();
        int completedTasks = currentUser.getTasks().getCompleted().size();
        SummaryCard tasksCard = new SummaryCard(
            "📋 Tasks",
            pendingTasks + " pending  •  " + completedTasks + " done",
            "",
            new greenfoot.Color(220, 230, 255)
        );
        addObject(tasksCard, cardX, cardStartY);
        contentArea.add(tasksCard);

        // School Card
        int schoolPending = 0;
        int schoolCompleted = 0;
        for (SchoolTask task : currentUser.getSchoolTasks()) {
            if (task.isCompleted()) schoolCompleted++;
            else schoolPending++;
        }
        SummaryCard schoolCard = new SummaryCard(
            "🎓 School",
            schoolPending + " pending  •  " + schoolCompleted + " done",
            "",
            new greenfoot.Color(255, 235, 220)
        );
        addObject(schoolCard, cardX, cardStartY + cardSpacing);
        contentArea.add(schoolCard);

        // Notes Card
        SummaryCard notesCard = new SummaryCard(
            "📝 Notes",
            currentUser.getNotes().size() + " total notes",
            "",
            new greenfoot.Color(255, 253, 220)
        );
        addObject(notesCard, cardX, cardStartY + cardSpacing * 2);
        contentArea.add(notesCard);

        // Music Selector on the right
        MusicSelector musicSelector = new MusicSelector();
        addObject(musicSelector, 620, 330);
        contentArea.add(musicSelector);
    }

    /**
     * Shows the Calendar page with a CalendarPanel.
     */
    private void showCalendar() {
        clearContent();
        CalendarPanel calendar = new CalendarPanel(currentUser, 500, 350);
        addObject(calendar, 500, 300);
        contentArea.add(calendar);
    }

    /**
     * Shows the Tasks page with a TaskPanel.
     */
    private void showTasks() {
        clearContent();
        TaskPanel tasksPanel = new TaskPanel(currentUser, 500, 350);
        addObject(tasksPanel, 500, 300);
        contentArea.add(tasksPanel);
    }

    /**
     * Shows the Notes page with a NotesPanel.
     */
    private void showNotes() {
        clearContent();
        NotesPanel notesPanel = new NotesPanel(currentUser, 500, 350);
        addObject(notesPanel, 500, 300);
        contentArea.add(notesPanel);
    }
}


