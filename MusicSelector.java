import greenfoot.*;
import java.util.ArrayList;

/**
 * MusicSelector provides a UI panel for selecting and controlling music playback.
 * Users can start predefined tracks or stop music entirely.
 * The panel includes a title and buttons for each song.
 */
public class MusicSelector extends Actor {

    private int width = 300;  // Panel width
    private int height = 280; // Panel height
    private ArrayList<Actor> selectorElements = new ArrayList<>(); // Stores added UI elements

    /**
     * Constructor: Initializes the music selector panel.
     */
    public MusicSelector() {
        createPanel();
    }

    /**
     * Creates the panel background with shadow, beige fill, and navy border.
     */
    private void createPanel() {
        GreenfootImage img = new GreenfootImage(width, height);

        // Shadow
        img.setColor(new greenfoot.Color(0, 0, 0, 40));
        img.fillRect(4, 4, width - 4, height - 4);

        // Solid beige background
        img.setColor(new greenfoot.Color(245, 240, 230));
        img.fillRect(0, 0, width - 4, height - 4);

        // Navy border (double line)
        img.setColor(new greenfoot.Color(0, 0, 128));
        img.drawRect(0, 0, width - 5, height - 5);
        img.drawRect(1, 1, width - 7, height - 7);

        setImage(img);
    }

    /**
     * Called when the actor is added to the world.
     * Adds the title and music control buttons to the world.
     *
     * @param world The Greenfoot world
     */
    protected void addedToWorld(World world) {
        // Title label
        TextLabel title = new TextLabel(
            "🎵 Music Player", 22,
            new greenfoot.Color(0, 0, 128), 250, 35,
            new greenfoot.Color(0, 0, 0, 0)
        );
        world.addObject(title, getX(), getY() - (height / 2) + 30);
        selectorElements.add(title);

        int startY = getY() - (height / 2) + 75;
        int spacing = 47; // Vertical spacing between buttons

        // Song buttons
        ButtonActor song1Btn = new ButtonActor("♫Lofi", 255, 255, 255, 0, 0, 128, 220, 38) {
            public void onClick() {
                MusicManager.startMusic("lofi.mp3");
            }
        };
        world.addObject(song1Btn, getX(), startY);
        selectorElements.add(song1Btn);

        ButtonActor song2Btn = new ButtonActor("♫ Jazz", 255, 255, 255, 0, 0, 128, 220, 38) {
            public void onClick() {
                MusicManager.startMusic("jazz.mp3");
            }
        };
        world.addObject(song2Btn, getX(), startY + spacing);
        selectorElements.add(song2Btn);

        ButtonActor song3Btn = new ButtonActor("♫ Lock In", 255, 255, 255, 0, 0, 128, 220, 38) {
            public void onClick() {
                MusicManager.startMusic("lockin.mp3");
            }
        };
        world.addObject(song3Btn, getX(), startY + spacing * 2);
        selectorElements.add(song3Btn);

        ButtonActor song4Btn = new ButtonActor("♫ IVY BGM", 255, 255, 255, 0, 0, 128, 220, 38) {
            public void onClick() {
                MusicManager.startMusic("ivy.mp3");
            }
        };
        world.addObject(song4Btn, getX(), startY + spacing * 3);
        selectorElements.add(song4Btn);

        // Stop music button
        ButtonActor stopBtn = new ButtonActor("■ Stop Music", 255, 255, 255, 128, 0, 0, 220, 38) {
            public void onClick() {
                MusicManager.stopMusic();
            }
        };
        world.addObject(stopBtn, getX(), startY + spacing * 4);
        selectorElements.add(stopBtn);
    }

    /**
     * Removes all UI elements added by this MusicSelector from the world.
     * Useful when switching pages or clearing the content area.
     */
    public void removeAllElements() {
        World world = getWorld();
        if (world == null) return;

        for (Actor element : selectorElements) {
            if (element.getWorld() != null) {
                world.removeObject(element);
            }
        }
        selectorElements.clear();
    }
}
