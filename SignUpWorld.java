import greenfoot.*;

/**
 * The SignUpWorld class represents the sign-up screen for new users.
 * It allows users to create an account by entering an email, password, and username.
 * After successful account creation, it navigates back to the LoginWorld.
 */
public class SignUpWorld extends World {

    private UserManager userManager;
    private TextBox emailBox, passwordBox, usernameBox;

    /**
     * Constructor for SignUpWorld.
     * Initializes the world, sets the background, and adds the UI elements.
     *
     * @param manager The UserManager instance handling user accounts.
     */
    public SignUpWorld(UserManager manager) {
        super(800, 600, 1); // World size: 800x600 pixels, 1x1 cell
        userManager = manager;
        setBackgroundColor();
        addUI();
    }

    /**
     * Sets the background color of the sign-up screen.
     * Uses a light beige color (RGB 239, 232, 219) to match the app theme.
     */
    private void setBackgroundColor() {
        GreenfootImage bg = new GreenfootImage(getWidth(), getHeight());
        bg.setColor(new greenfoot.Color(239, 232, 219));
        bg.fill();
        setBackground(bg);
    }

    /**
     * Adds the UI elements for the sign-up screen:
     * - Labels for Email, Password, and Username
     * - TextBoxes for user input
     * - "Create Account" button to submit the form
     *
     * The Create Account button validates that all fields are filled.
     * If valid, it adds a new user to the UserManager and returns to LoginWorld.
     * If any field is empty, it displays an error message.
     */
    private void addUI() {
        // Labels
        addObject(new Label("Email:", 24, 0, 0, 0), 250, 200);
        addObject(new Label("Password:", 24, 0, 0, 0), 250, 250);
        addObject(new Label("Username:", 24, 0, 0, 0), 250, 300);

        // Text boxes for user input
        emailBox = new TextBox(200, 30);
        addObject(emailBox, 400, 200);

        passwordBox = new TextBox(200, 30);
        addObject(passwordBox, 400, 250);

        usernameBox = new TextBox(200, 30);
        addObject(usernameBox, 400, 300);

        // Create Account button
        addObject(new ButtonActor("Create Account", 173, 216, 230, 0, 0, 128, 150, 40) {
            private boolean clicked = false; // Prevent multiple account creation clicks

            @Override
            public void onClick() {
                if (clicked) return; // Prevent multiple triggers
                clicked = true;

                String email = emailBox.getText();
                String password = passwordBox.getText();
                String username = usernameBox.getText();

                // Validate all fields are filled
                if (email != null && password != null && username != null &&
                    !email.isEmpty() && !password.isEmpty() && !username.isEmpty()) {

                    // Add new user to UserManager
                    userManager.addUser(email, password, username);

                    // Return to login screen
                    Greenfoot.setWorld(new LoginWorld(userManager));
                } else {
                    // Show error message and allow retry
                    showText("Fill all fields!", getWidth() / 2, getHeight() - 50);
                    clicked = false;
                }
            }
        }, 400, 400);
    }
}
