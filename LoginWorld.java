import greenfoot.*;

/**
 * The LoginWorld class represents the initial login screen for the application.
 * It allows users to log in with their email and password or navigate to the sign-up screen.
 * This world is the entry point of the application.
 */
public class LoginWorld extends World {

    private UserManager userManager;

    /**
     * Default constructor for LoginWorld.
     * Used when the app starts with no pre-existing users.
     * It creates a new UserManager instance.
     */
    public LoginWorld() {
        this(new UserManager());
    }

    /**
     * Main constructor for LoginWorld.
     * Initializes the world, sets the background color, and adds UI elements.
     *
     * @param manager The UserManager instance that handles user accounts and authentication.
     */
    public LoginWorld(UserManager manager) {
        super(800, 600, 1); // World size: 800x600 pixels, 1x1 cell
        this.userManager = manager;

        setBackgroundColor();
        addUI();
    }

    /**
     * Sets the background color of the login screen.
     * Uses a light beige color (RGB 239, 232, 219).
     */
    private void setBackgroundColor() {
        GreenfootImage bg = new GreenfootImage(getWidth(), getHeight());
        bg.setColor(new greenfoot.Color(239, 232, 219)); 
        bg.fill();
        setBackground(bg);
    }

    /**
     * Adds the main UI elements to the login screen:
     * - Login button: prompts for email and password, authenticates the user, 
     *   and navigates to the DashboardWorld if successful.
     * - Sign Up button: navigates to the SignUpWorld to create a new account.
     */
    private void addUI() {
        // Login button
        addObject(new ButtonActor(
            "Login",
            173, 216, 230,   // Text color (light blue)
            0, 0, 128,       // Background color (dark blue)
            120, 40          // Width x Height
        ) {
            public void onClick() {
                String email = Greenfoot.ask("Enter email:");
                String password = Greenfoot.ask("Enter password:");

                User loggedInUser = userManager.login(email, password);

                if (loggedInUser != null) {
                    // Successful login: go to dashboard
                    Greenfoot.setWorld(new DashboardWorld(loggedInUser, userManager));
                    Greenfoot.playSound("loginsuccess.wav");
                } else {
                    // Failed login: display error message
                    showText("Invalid login!", getWidth() / 2, getHeight() - 50);
                    Greenfoot.playSound("loginfail.wav");
                }
            }
        }, 400, 300);

        // Sign Up button
        addObject(new ButtonActor(
            "Sign Up",
            173, 216, 230,   // Text color (light blue)
            0, 0, 128,       // Background color (dark blue)
            120, 40          // Width x Height
        ) {
            public void onClick() {
                // Navigate to the sign-up screen
                Greenfoot.setWorld(new SignUpWorld(userManager));
            }
        }, 400, 400);
    }
}

