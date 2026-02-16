import greenfoot.*;

/**
 * SummaryCard represents a compact dashboard card for displaying
 * a title and optional statistic information. It features a colored 
 * background, subtle shadow, and styled borders for visual appeal.
 */
public class SummaryCard extends Actor {
    private String title;
    private String line1;
    private String line2;
    private greenfoot.Color bgColor;
    private int width = 300;  // Card width in pixels
    private int height = 70;  // Card height in pixels

    /**
     * Constructs a SummaryCard with a title, info lines, and background color.
     *
     * @param title   The main label of the card, can include emojis.
     * @param line1   The first line of additional information (e.g., stats).
     * @param line2   The second line of information (currently unused in display).
     * @param bgColor The background color of the card.
     */
    public SummaryCard(String title, String line1, String line2, greenfoot.Color bgColor) {
        this.title = title;
        this.line1 = line1;
        this.line2 = line2;
        this.bgColor = bgColor;
        createCard();
    }

    /**
     * Creates the visual representation of the card including:
     * shadow, main colored background, border, and text.
     */
    private void createCard() {
        GreenfootImage img = new GreenfootImage(width, height);

        // Shadow effect behind the card
        img.setColor(new greenfoot.Color(0, 0, 0, 40));
        img.fillRect(4, 4, width - 4, height - 4);

        // Main card background
        img.setColor(bgColor);
        img.fillRect(0, 0, width - 4, height - 4);

        // Navy border for emphasis
        img.setColor(new greenfoot.Color(0, 0, 128));
        img.drawRect(0, 0, width - 5, height - 5);
        img.drawRect(1, 1, width - 7, height - 7);

        // Title (bold)
        img.setFont(new Font("Arial", true, false, 22));
        img.setColor(new greenfoot.Color(0, 0, 128));
        img.drawString(title, 15, 30);

        // Line1 - additional info (smaller font)
        if (!line1.isEmpty()) {
            img.setFont(new Font("Arial", false, false, 15));
            img.setColor(new greenfoot.Color(60, 60, 60));
            img.drawString(line1, 15, 52);
        }

        setImage(img);
    }

    /**
     * Updates the card's line1 text and redraws the card.
     * Useful for dynamic dashboards where stats change.
     *
     * @param newLine1 The updated line1 text to display.
     */
    public void updateLine1(String newLine1) {
        this.line1 = newLine1;
        createCard();
    }

    /**
     * Updates the card's title and redraws the card.
     *
     * @param newTitle The new title to display on the card.
     */
    public void updateTitle(String newTitle) {
        this.title = newTitle;
        createCard();
    }

    /**
     * Updates the card's background color and redraws the card.
     *
     * @param newColor The new background color for the card.
     */
    public void updateBackgroundColor(greenfoot.Color newColor) {
        this.bgColor = newColor;
        createCard();
    }
}

