import java.util.ArrayList;

/**
 * UserManager handles the storage, login, and management
 * of all users in the system.
 */
public class UserManager {
    private ArrayList<User> users;  // List of all registered users

    /**
     * Constructor that initializes the user list from persistent storage.
     */
    public UserManager() {
        users = DataManager.loadUsers();  // Load existing users
        if (users == null) {
            users = new ArrayList<>(); // Ensure list is initialized
        }
    }

    /**
     * Registers a new user and saves the updated list.
     *
     * @param email    Email of the new user
     * @param password Password of the new user
     * @param username Username of the new user
     */
    public void addUser(String email, String password, String username) {
        users.add(new User(email, password, username));
        save();
    }

    /**
     * Attempts to log in a user by matching email and password.
     *
     * @param email    Email of the user
     * @param password Password of the user
     * @return The User object if credentials are correct, otherwise null
     */
    public User login(String email, String password) {
        for (User u : users) {
            if (u.getEmail().equals(email) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }

    /**
     * Returns the list of all users.
     *
     * @return ArrayList of users
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * Saves the current list of users to persistent storage.
     */
    public void save() {
        DataManager.saveUsers(users);
    }
}


