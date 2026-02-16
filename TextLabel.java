import greenfoot.*;

/**
 * TextLabel is a simple label actor for Greenfoot that displays text
 * with a background and optional click behavior.
 */
public class TextLabel extends Actor {
    /**
     * Runnable to store the action to perform when this label is clicked.
     */
    public Runnable onClick;

    /**
     * Constructs a TextLabel with specified text, font size, colors, and dimensions.
     *
     * @param text      The text to display
     * @param fontSize  Font size for the text
     * @param textColor Color of the text
     * @param width     Width of the label
     * @param height    Height of the label
     * @param bgColor   Background color of the label
     */
    public TextLabel(String text, int fontSize, greenfoot.Color textColor, int width, int height, greenfoot.Color bgColor) {
        GreenfootImage img = new GreenfootImage(width, height);

        // Draw background
        img.setColor(bgColor);
        img.fillRect(0, 0, width, height);

        // Draw centered text
        GreenfootImage textImg = new GreenfootImage(text, fontSize, textColor, new greenfoot.Color(0,0,0,0));
        int x = (width - textImg.getWidth()) / 2;
        int y = (height - textImg.getHeight()) / 2;
        img.drawImage(textImg, x, y);

        setImage(img);
    }

    /**
     * Sets a click action for the label.
     *
     * @param action A Runnable representing the action to perform when clicked
     */
    public void setClickable(Runnable action) {
        this.onClick = action;
    }

    /**
     * Act method to detect clicks and trigger the assigned action if present.
     */
    public void act() {
        if (onClick != null && Greenfoot.mouseClicked(this)) {
            onClick.run();
        }
    }
}
