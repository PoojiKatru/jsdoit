import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a user's task list, managing both pending and completed tasks.
 * Provides methods to add tasks and update their completion status.
 */
public class TaskList implements Serializable {
    private static final long serialVersionUID = 1L;

    /** List of tasks that are not yet completed */
    private ArrayList<TaskData> todo = new ArrayList<>();

    /** List of tasks that have been completed */
    private ArrayList<TaskData> done = new ArrayList<>();

    /**
     * Returns the list of pending tasks.
     * @return ArrayList of TaskData objects representing tasks to do
     */
    public ArrayList<TaskData> getToDo() {
        return todo;
    }

    /**
     * Returns the list of completed tasks.
     * @return ArrayList of TaskData objects representing completed tasks
     */
    public ArrayList<TaskData> getCompleted() {
        return done;
    }

    /**
     * Adds a new task to the to-do list.
     * @param name Name of the task
     * @param urgency Urgency level (0=Low, 1=Normal, 2=High, 3=Critical)
     */
    public void addTask(String name, int urgency) {
        todo.add(new TaskData(name, urgency));
    }

    /**
     * Marks a task as completed by moving it from the to-do list to the completed list.
     * @param task TaskData object to mark as completed
     */
    public void markCompleted(TaskData task) {
        if (todo.remove(task)) {
            task.setCompleted(true);
            done.add(task);
        }
    }

    /**
     * Marks a task as incomplete by moving it from the completed list back to the to-do list.
     * @param task TaskData object to mark as incomplete
     */
    public void markIncomplete(TaskData task) {
        if (done.remove(task)) {
            task.setCompleted(false);
            todo.add(task);
        }
    }
}
