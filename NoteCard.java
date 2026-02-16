import greenfoot.*;

/**
 * NoteCard represents a single sticky note in the NotesPanel.
 * It displays the note's text, background color, and provides
 * a small "X" button to delete the note.
 */
public class NoteCard extends Actor {

    private String text;                 // The content of the note
    private greenfoot.Color bgColor;     // Background color of the note
    private User user;                   // The user owning this note
    private NotesPanel parentPanel;      // Reference to the parent panel
    private int w = 130, h = 140;        // Width and height of the note card

    /**
     * Constructor for a NoteCard.
     *
     * @param text        The text to display on the note.
     * @param bgColor     Background color of the note.
     * @param user        The User who owns this note.
     * @param parentPanel The NotesPanel that contains this card.
     */
    public NoteCard(String text, greenfoot.Color bgColor, User user, NotesPanel parentPanel) {
        this.text = text;
        this.bgColor = bgColor;
        this.user = user;
        this.parentPanel = parentPanel;
        updateImage();
    }

    /**
     * Updates the visual representation of the note.
     * Draws shadow, colored background, tape effect, border, text,
     * and a small "X" delete button.
     */
    private void updateImage() {
        GreenfootImage img = new GreenfootImage(w, h);

        // Shadow
        img.setColor(new greenfoot.Color(0, 0, 0, 80));
        img.fillRect(3, 3, w-6, h-6);

        // Main sticky note
        img.setColor(bgColor);
        img.fillRect(0, 0, w-3, h-3);

        // Top "tape" effect
        img.setColor(new greenfoot.Color(255, 255, 255, 150));
        img.fillRect(w/2 - 15, 0, 30, 8);

        // Border (slightly darker)
        int r = Math.max(0, bgColor.getRed() - 30);
        int g = Math.max(0, bgColor.getGreen() - 30);
        int b = Math.max(0, bgColor.getBlue() - 30);
        img.setColor(new greenfoot.Color(r, g, b));
        img.drawRect(0, 0, w-4, h-4);

        // Text rendering with simple wrapping
        img.setColor(new greenfoot.Color(50, 50, 50));
        img.setFont(new Font("Comic Sans MS", false, false, 14));

        String[] words = text.split(" ");
        String line = "";
        int y = 25;
        int maxWidth = w - 15;

        for (String word : words) {
            String testLine = line + word + " ";
            GreenfootImage testImg = new GreenfootImage(testLine, 14,
                    new greenfoot.Color(0,0,0), new greenfoot.Color(0,0,0,0));

            if (testImg.getWidth() > maxWidth && !line.isEmpty()) {
                img.drawString(line, 8, y);
                line = word + " ";
                y += 16;
                if (y > h - 25) break; // Stop if too long
            } else {
                line = testLine;
            }
        }
        if (!line.isEmpty() && y <= h - 25) {
            img.drawString(line, 8, y);
        }

        // Small "X" button for deleting note
        img.setColor(new greenfoot.Color(200, 0, 0));
        img.fillOval(w - 20, 5, 15, 15);
        img.setColor(new greenfoot.Color(255, 255, 255));
        img.setFont(new Font("Arial", true, false, 12));
        img.drawString("×", w - 15, 16);

        setImage(img);
    }

    /**
     * Handles mouse clicks on the note.
     * If clicked on the "X" button, deletes the note from the user's list
     * and refreshes the parent panel display.
     */
    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            MouseInfo mouse = Greenfoot.getMouseInfo();
            if (mouse != null) {
                int relX = mouse.getX() - (getX() - w/2);
                int relY = mouse.getY() - (getY() - h/2);

                // If clicked on "X" button
                if (relX > w - 20 && relX < w - 5 && relY > 5 && relY < 20) {
                    playSound("au.wav"); // Delete sound effect
                    user.getNotes().remove(text);
                    if (parentPanel != null) {
                        parentPanel.displayNotes();
                    }
                }
            }
        }
    }

    /**
     * Plays a sound file safely, ignoring errors if file is missing.
     *
     * @param soundFile Name of the sound file to play.
     */
    private void playSound(String soundFile) {
        try {
            Greenfoot.playSound(soundFile);
        } catch (Exception e) {
            // Sound file not found, ignore
        }
    }
}
