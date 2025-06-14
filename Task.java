// Task.java
// This class represents a single To-Do item.
// It stores the description of the task and its completion status.
class Task {
    private String description; // The actual text of the task
    private boolean isComplete; // True if the task is completed, false otherwise

    /**
     * Constructor for the Task class.
     * Initializes a new task with the given description and sets its status to incomplete by default.
     * @param description The textual description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isComplete = false; // New tasks are always incomplete initially
    }

    /**
     * Gets the description of the task.
     * @return The description string.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Checks if the task is complete.
     * @return True if the task is complete, false otherwise.
     */
    public boolean isComplete() {
        return isComplete;
    }

    /**
     * Marks the task as complete.
     * Once called, the task's status changes to true.
     */
    public void markComplete() {
        this.isComplete = true;
    }

    /**
     * Overrides the default toString method to provide a formatted string representation of the task.
     * This makes displaying tasks in the list more user-friendly.
     * @return A string showing the completion status ([ ] for incomplete, [X] for complete)
     * followed by the task description.
     */
    @Override
    public String toString() {
        // Displays "[X] Task Description" if complete, or "[ ] Task Description" if incomplete
        return (isComplete ? "[X] " : "[ ] ") + description;
    }
}
