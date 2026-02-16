import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a user in the system.
 * Stores authentication info, tasks, school assignments, and notes.
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String email;                   // User's email for login
    private String password;                // User's password
    private String username;                // Display name
    private ToDoList tasks;                 // User's personal task list
    private ArrayList<String> notes;        // User's personal notes
    private ArrayList<SchoolTask> schoolTasks; // User's school assignments

    /**
     * Creates a new user with email, password, and username.
     * Initializes task and note lists.
     *
     * @param email    User's email
     * @param password User's password
     * @param username User's display name
     */
    public User(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.tasks = new ToDoList();
        this.notes = new ArrayList<>();
        this.schoolTasks = new ArrayList<>();
    }

    // Getters
    public ToDoList getTasks() { return tasks; }
    public ArrayList<String> getNotes() { return notes; }
    public ArrayList<SchoolTask> getSchoolTasks() { return schoolTasks; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getUsername() { return username; }

    // Optional: could add setters if needed for updating email, username, or password
}

