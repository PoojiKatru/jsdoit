import java.io.Serializable;

/**
 * Represents a single task with a name, urgency level, and completion status.
 * Implements Serializable so it can be saved and loaded.
 */
public class TaskData implements Serializable {
    private static final long serialVersionUID = 1L;

    /** Name or description of the task */
    private String taskName;

    /** Urgency level of the task:
     * 0 = Low, 1 = Normal, 2 = High, 3 = Critical
     */
    private int urgencyLevel;

    /** Completion status of the task */
    private boolean completed;

    /**
     * Constructs a TaskData object with a specific name and urgency level.
     * The task is initially not completed.
     *
     * @param taskName Name or description of the task
     * @param urgencyLevel Urgency level (0=Low, 1=Normal, 2=High, 3=Critical)
     */
    public TaskData(String taskName, int urgencyLevel) {
        this.taskName = taskName;
        this.urgencyLevel = urgencyLevel;
        this.completed = false;
    }

    /**
     * Constructs a TaskData object with a name and default normal urgency.
     *
     * @param taskName Name or description of the task
     */
    public TaskData(String taskName) {
        this(taskName, 1); // Default to Normal urgency
    }

    /** Returns the task name */
    public String getTaskName() {
        return taskName;
    }

    /** Returns the urgency level */
    public int getUrgencyLevel() {
        return urgencyLevel;
    }

    /** Sets the urgency level */
    public void setUrgencyLevel(int urgencyLevel) {
        this.urgencyLevel = urgencyLevel;
    }

    /** Returns true if the task is completed */
    public boolean isCompleted() {
        return completed;
    }

    /** Sets the completion status of the task */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}

