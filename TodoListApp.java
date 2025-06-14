// TodoListApp.java
// This is the main application class that manages the To-Do list.
// It uses an ArrayList to store Task objects and provides methods for
// adding, deleting, displaying, and marking tasks as complete.
import java.util.ArrayList; // Used for storing tasks
import java.util.Scanner;    // Used for reading user input

public class TodoListApp {

    // ArrayList to hold Task objects. This is our chosen data structure.
    private static ArrayList<Task> tasks = new ArrayList<>();
    // Scanner object to read input from the console.
    private static Scanner scanner = new Scanner(System.in);

    /**
     * The main method is the entry point of the application.
     * It runs the main application loop, displays the menu, and handles user choices.
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        boolean running = true; // Flag to keep the application running
        System.out.println("\nWelcome to your To-Do List Application!");

        // Main application loop
        while (running) {
            displayMenu();        // Show the user the available options
            int choice = getUserChoice(); // Get the user's selected option

            // Process the user's choice using a switch statement
            switch (choice) {
                case 1:
                    addTask();           // Call method to add a new task
                    break;
                case 2:
                    deleteTask();        // Call method to delete an existing task
                    break;
                case 3:
                    displayTasks();      // Call method to display all tasks
                    break;
                case 4:
                    markTaskComplete();  // Call method to mark a task as complete
                    break;
                case 5:
                    running = false;     // Set running to false to exit the loop
                    System.out.println("Exiting To-Do List Application. Goodbye!");
                    break;
                default:
                    // Handle invalid input
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
        scanner.close(); // Close the scanner to release system resources
    }

    /**
     * Displays the main menu options to the user.
     */
    public static void displayMenu() {
        System.out.println("\n--- To-Do List Menu ---");
        System.out.println("1. Add Task");
        System.out.println("2. Delete Task");
        System.out.println("3. Display Tasks");
        System.out.println("4. Mark Task Complete");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    /**
     * Reads and validates the user's numerical input for menu choice.
     * Ensures that the user enters an integer and prompts again if not.
     * @return The valid integer choice entered by the user.
     */
    public static int getUserChoice() {
        // Loop until a valid integer is entered
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); // Consume the invalid input (e.g., if user typed "abc")
            System.out.print("Enter your choice: "); // Prompt again
        }
        int choice = scanner.nextInt(); // Read the integer choice
        scanner.nextLine(); // Consume the remaining newline character after reading int
                            // This is crucial to prevent issues with subsequent nextLine() calls
        return choice;
    }

    /**
     * Prompts the user for a task description and adds a new Task object to the list.
     */
    public static void addTask() {
        System.out.print("Enter the task description: ");
        String description = scanner.nextLine(); // Read the full line of text for the description
        if (description.trim().isEmpty()) { // Basic validation for empty description
            System.out.println("Task description cannot be empty. Task not added.");
            return;
        }
        tasks.add(new Task(description)); // Create a new Task object and add it to the ArrayList
        System.out.println("Task added successfully!");
    }

    /**
     * Allows the user to delete a task by its number.
     * First displays tasks with numbers, then prompts for the task to delete.
     */
    public static void deleteTask() {
        if (tasks.isEmpty()) { // Check if there are any tasks to delete
            System.out.println("No tasks to delete. Your to-do list is empty.");
            return;
        }

        displayTasksWithNumbers(); // Show tasks with their corresponding numbers
        System.out.print("Enter the number of the task to delete: ");
        int taskNumber = getUserChoice(); // Get the task number from the user

        // Validate the task number entered by the user
        // ArrayLists are 0-indexed, so taskNumber - 1 gives the correct index.
        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            Task removedTask = tasks.remove(taskNumber - 1); // Remove the task from the list
            System.out.println("Task '" + removedTask.getDescription() + "' deleted successfully!");
        } else {
            System.out.println("Invalid task number. Please enter a number within the displayed range.");
        }
    }

    /**
     * Displays all tasks currently in the list, including their completion status.
     */
    public static void displayTasks() {
        if (tasks.isEmpty()) { // Check if the list is empty
            System.out.println("Your to-do list is empty!");
            return;
        }
        System.out.println("\n--- Your Tasks ---");
        // Iterate through the ArrayList and print each task.
        // The Task's toString() method handles the formatting ([ ] or [X]).
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * A helper method to display tasks with numbers, useful for actions like delete or mark complete.
     * This is similar to displayTasks but explicitly meant for scenarios where user needs to pick by number.
     */
    private static void displayTasksWithNumbers() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to display.");
            return;
        }
        System.out.println("\n--- Current Tasks (Select by Number) ---");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
    }

    /**
     * Allows the user to mark a task as complete by its number.
     * Displays tasks with numbers, then prompts for the task to mark.
     */
    public static void markTaskComplete() {
        if (tasks.isEmpty()) { // Check if there are tasks to mark complete
            System.out.println("No tasks to mark complete. Your to-do list is empty.");
            return;
        }

        displayTasksWithNumbers(); // Show tasks with their numbers
        System.out.print("Enter the number of the task to mark complete: ");
        int taskNumber = getUserChoice(); // Get the task number from the user

        // Validate the task number and mark it complete if valid
        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            Task taskToComplete = tasks.get(taskNumber - 1); // Get the Task object
            if (taskToComplete.isComplete()) {
                System.out.println("Task '" + taskToComplete.getDescription() + "' is already complete.");
            } else {
                taskToComplete.markComplete(); // Call the markComplete method on the Task object
                System.out.println("Task '" + taskToComplete.getDescription() + "' marked as complete!");
            }
        } else {
            System.out.println("Invalid task number. Please enter a number within the displayed range.");
        }
    }
}