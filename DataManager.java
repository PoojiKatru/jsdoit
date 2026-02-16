import java.io.*;
import java.util.ArrayList;

/**
 * Utility class for saving and loading user data to/from disk.
 * Uses Java serialization to store ArrayList<User> in a file.
 */
public class DataManager {

    /** File path where user data is stored */
    private static final String FILE_PATH = "users.dat";

    /**
     * Saves the given list of users to disk.
     *
     * @param users The ArrayList of User objects to save
     */
    public static void saveUsers(ArrayList<User> users) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            out.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the list of users from disk.
     * If the file does not exist or an error occurs, returns an empty list.
     *
     * @return ArrayList of User objects
     */
    public static ArrayList<User> loadUsers() {
        File f = new File(FILE_PATH);
        if (!f.exists()) return new ArrayList<>(); // return empty if no file

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (ArrayList<User>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}


