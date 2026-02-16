import greenfoot.*;
import java.util.ArrayList;

/**
 * NotesPanel displays a collection of sticky notes (NoteCards) for a given user.
 * Users can add new notes, and each note can be deleted via the NoteCard interface.
 * Notes are displayed in a grid layout with varying pastel colors.
 */
public class NotesPanel extends Actor {

    private User currentUser;                // The user whose notes are displayed
    private int width, height;               // Width and height of the panel
    private ArrayList<NoteCard> noteCards = new ArrayList<>();   // Currently displayed note cards
    private ArrayList<Actor> panelElements = new ArrayList<>(); // All added actors (title, buttons, messages)
    private ButtonActor addButton;           // Button to add new notes

    /**
     * Constructor for NotesPanel.
     *
     * @param user The User whose notes will be displayed.
     * @param w    Width of the panel.
     * @param h    Height of the panel.
     */
    public NotesPanel(User user, int w, int h) {
        currentUser = user;
        width = w;
        height = h;
        createPanel();
    }

    /**
     * Initializes panel visual appearance with background and border.
     */
    private void createPanel() {
        GreenfootImage img = new GreenfootImage(width, height);

        // Beige gradient background
        for (int i = 0; i < height; i++) {
            int r = 239 - (i * 10 / height);
            int g = 232 - (i * 10 / height);
            int b = 219 - (i * 10 / height);
            img.setColor(new greenfoot.Color(r, g, b));
            img.drawLine(0, i, width, i);
        }

        // Navy border
        img.setColor(new greenfoot.Color(0, 0, 128));
        img.drawRect(0, 0, width-1, height-1);
        img.drawRect(1, 1, width-3, height-3);

        setImage(img);
    }

    /**
     * Called when the panel is added to the world.
     * Adds title and "Add Note" button, and displays all current notes.
     */
    protected void addedToWorld(World world) {
        // Add panel title
        TextLabel title = new TextLabel("My Notes", 32, new greenfoot.Color(0, 0, 128),
                                        200, 40, new greenfoot.Color(0, 0, 0, 0));
        world.addObject(title, getX(), getY() - (height/2) + 30);
        panelElements.add(title);

        // Add "Add Note" button
        addButton = new ButtonActor("+ New Note", 239, 232, 219, 0, 0, 128, 130, 40) {
            public void onClick() {
                String note = Greenfoot.ask("Enter new note:");
                if (note != null && !note.trim().isEmpty()) {
                    currentUser.getNotes().add(note.trim());
                    displayNotes();
                }
            }
        };
        world.addObject(addButton, getX(), getY() - (height/2) + 80);
        panelElements.add(addButton);

        // Display existing notes
        displayNotes();
    }

    /**
     * Displays all notes of the current user in a grid layout.
     * Pastel colors are cycled for visual variety. Clears old notes before adding new ones.
     */
    public void displayNotes() {
        World world = getWorld();
        if (world != null) {
            // Remove previous note cards
            for (NoteCard card : noteCards) {
                if (card.getWorld() != null) {
                    world.removeObject(card);
                    panelElements.remove(card);
                }
            }
        }
        noteCards.clear();

        // Remove previous empty message
        for (Actor a : new ArrayList<>(panelElements)) {
            if (a instanceof TextLabel && a != panelElements.get(0)) { // Skip title
                if (a.getWorld() != null) {
                    world.removeObject(a);
                }
                panelElements.remove(a);
            }
        }

        ArrayList<String> notes = currentUser.getNotes();

        // Display empty message if no notes
        if (notes.isEmpty()) {
            TextLabel emptyMsg = new TextLabel(
                "No notes yet. Click '+ New Note' to add one!", 18,
                new greenfoot.Color(80, 60, 40), 400, 30,
                new greenfoot.Color(0, 0, 0, 0)
            );
            if (world != null) {
                world.addObject(emptyMsg, getX(), getY());
                panelElements.add(emptyMsg);
            }
            return;
        }

        // Grid layout
        int startX = getX() - 200;
        int startY = getY() - (height/2) + 140;
        int col = 0;
        int row = 0;
        int spacing = 150;

        // Pastel colors for note cards
        greenfoot.Color[] colors = {
            new greenfoot.Color(255, 253, 150), // Yellow
            new greenfoot.Color(255, 200, 200), // Pink
            new greenfoot.Color(200, 230, 255), // Blue
            new greenfoot.Color(200, 255, 200), // Green
            new greenfoot.Color(255, 220, 180), // Peach
            new greenfoot.Color(230, 200, 255)  // Purple
        };

        int colorIndex = 0;
        for (String note : notes) {
            greenfoot.Color cardColor = colors[colorIndex % colors.length];
            NoteCard card = new NoteCard(note, cardColor, currentUser, this);

            int x = startX + (col * spacing);
            int y = startY + (row * 140);

            if (world != null) {
                world.addObject(card, x, y);
                noteCards.add(card);
                panelElements.add(card);
            }

            col++;
            if (col >= 3) { // 3 notes per row
                col = 0;
                row++;
            }
            colorIndex++;
        }
    }

    /**
     * Removes all elements (notes, title, buttons) from the panel.
     * Useful when switching worlds or cleaning up.
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
        noteCards.clear();
    }
}



