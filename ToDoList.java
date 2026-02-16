import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a user's personal to-do list.
 * Maintains separate lists for pending (to-do) and completed tasks.
 */
public class ToDoList implements Serializable {
    private static final long serialVersionUID = 1L;

    private ArrayList<TaskData> todo = new ArrayList<>();  // Pending tasks
    private ArrayList<TaskData> done = new ArrayList<>();  // Completed tasks

    /**
     * Returns the list of tasks that are pending (to-do).
     * @return ArrayList of TaskData objects
     */
    public ArrayList<TaskData> getToDo() {
        return todo;
    }

    /**
     * Returns the list of tasks that have been completed.
     * @return ArrayList of TaskData objects
     */
    public ArrayList<TaskData> getCompleted() {
        return done;
    }

    /**
     * Adds a new task to the to-do list.
     * @param name Task name
     * @param urgency Urgency level (0=Low, 1=Normal, 2=High, 3=Critical)
     */
    public void addTask(String name, int urgency) {
        todo.add(new TaskData(name, urgency));
    }

    /**
     * Marks a task as completed: removes it from to-do and adds to completed list.
     * @param task TaskData object to mark complete
     */
    public void markCompleted(TaskData task) {
        if (todo.remove(task)) {
            task.setCompleted(true);
            done.add(task);
        }
    }

    /**
     * Marks a task as incomplete: removes it from completed and adds back to to-do.
     * @param task TaskData object to mark incomplete
     */
    public void markIncomplete(TaskData task) {
        if (done.remove(task)) {
            task.setCompleted(false);
            todo.add(task);
        }
    }
}
