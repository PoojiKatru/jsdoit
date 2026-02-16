import greenfoot.*;

/**
 * UrgencyMenu provides a small dropdown menu for a TaskCard
 * to select the urgency level: Low, Normal, High, or Critical.
 * Clicking an option updates the parent TaskCard and closes the menu.
 */
public class UrgencyMenu extends Actor {
    private TaskCard parentCard;  // The card that owns this menu
    private int width = 120;      // Menu width
    private int height = 100;     // Menu height

    /**
     * Constructor linking this menu to a specific TaskCard.
     *
     * @param parentCard The TaskCard that will be updated when an option is selected
     */
    public UrgencyMenu(TaskCard parentCard) {
        this.parentCard = parentCard;
        createMenu();
    }

    /**
     * Draws the menu, including shadow, background, border, and urgency options.
     */
    private void createMenu() {
        GreenfootImage img = new GreenfootImage(width, height);

        // Shadow behind menu
        img.setColor(new greenfoot.Color(0, 0, 0, 100));
        img.fillRect(2, 2, width - 2, height - 2);

        // White background
        img.setColor(new greenfoot.Color(255, 255, 255));
        img.fillRect(0, 0, width - 2, height - 2);

        // Navy border
        img.setColor(new greenfoot.Color(0, 0, 128));
        img.drawRect(0, 0, width - 3, height - 3);

        // Draw each urgency option
        drawOption(img, 5, 5, "⚡ Low", new greenfoot.Color(200, 230, 200));
        drawOption(img, 5, 28, "⚡ Normal", new greenfoot.Color(200, 200, 255));
        drawOption(img, 5, 51, "⚡ High", new greenfoot.Color(255, 200, 100));
        drawOption(img, 5, 74, "⚡ Critical", new greenfoot.Color(255, 150, 150));

        setImage(img);
    }

    /**
     * Helper method to draw a single urgency option.
     */
    private void drawOption(GreenfootImage img, int x, int y, String text, greenfoot.Color color) {
        img.setColor(color);
        img.fillRect(x, y, 110, 20);
        img.setColor(new greenfoot.Color(0, 0, 0));
        img.setFont(new Font("Arial", false, false, 14));
        img.drawString(text, x + 5, y + 15);
    }

    /**
     * Act method handles mouse clicks for selecting an option
     * and closes the menu if clicked outside.
     */
    public void act() {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse != null) {
            int mouseX = mouse.getX();
            int mouseY = mouse.getY();
            int menuTop = getY() - height / 2;
            int menuLeft = getX() - width / 2;

            // Check if click is inside the menu
            if (Greenfoot.mouseClicked(this)) {
                int relY = mouseY - menuTop;

                if (relY >= 5 && relY <= 25) parentCard.setUrgency(0);     // Low
                else if (relY >= 28 && relY <= 48) parentCard.setUrgency(1); // Normal
                else if (relY >= 51 && relY <= 71) parentCard.setUrgency(2); // High
                else if (relY >= 74 && relY <= 94) parentCard.setUrgency(3); // Critical

                playSound("click.wav");
                getWorld().removeObject(this);
            }

            // Close menu if clicked outside
            if (Greenfoot.mousePressed(null) && !Greenfoot.mousePressed(this)) {
                World world = getWorld();
                if (world != null) world.removeObject(this);
            }
        }
    }

    /**
     * Safely plays a sound, ignoring missing file exceptions.
     *
     * @param soundFile Name of the sound file
     */
    private void playSound(String soundFile) {
        try { 
            Greenfoot.playSound(soundFile); 
        } catch (Exception e) {}
    }
}

