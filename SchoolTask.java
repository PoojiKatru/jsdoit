/**
 * Represents a school assignment with a name, associated teacher, and completion status.
 */
public class SchoolTask {
    /** Name of the assignment */
    private String taskName;

    /** Name of the teacher who assigned the task */
    private String teacher;

    /** Completion status of the assignment */
    private boolean completed;

    /**
     * Constructs a new SchoolTask with a given name and teacher.
     * The task is initially marked as incomplete.
     *
     * @param taskName Name or title of the assignment
     * @param teacher Name of the teacher who assigned the task
     */
    public SchoolTask(String taskName, String teacher) {
        this.taskName = taskName;
        this.teacher = teacher;
        this.completed = false;
    }

    /** Returns the name of the task */
    public String getTaskName() {
        return taskName;
    }

    /** Returns the name of the teacher */
    public String getTeacher() {
        return teacher;
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
