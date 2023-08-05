import java.util.Scanner;

// This class serves as the main entry point to the application.
public class TaskApp {

    private TaskManager taskManager;
    private Scanner scanner;

    public TaskApp() {
        this.taskManager = new TaskManager();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    register();
                    break;
                case "2":
                    login();
                    break;
                case "3":
                    System.exit(0);
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }

    private void register() {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        try {
            taskManager.createUser(username, password);
            System.out.println("User registered successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Username already in use.");
        }
    }

    private void login() {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();

        if (taskManager.authenticateUser(username, password)) {
            System.out.println("Logged in successfully!");
            manageTasks(username);
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    private void manageTasks(String username) {
        while (true) {
            System.out.println("1. Create task");
            System.out.println("2. View tasks");
            System.out.println("3. Edit task");
            System.out.println("4. Delete task");
            System.out.println("5. Log out");

            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    // Implement create task functionality here
                    System.out.println("Enter task name");
                    String taskName = scanner.nextLine();
                    System.out.println("Enter task priority (LOW, MEDIUM, HIGH):");
                    String taskPriority = scanner.nextLine();
                    System.out.println("Enter task deadline (mm-dd-yyyy):");
                    String taskDeadline = scanner.nextLine();
                    System.out.println("Enter task description:");
                    String taskDescription = scanner.nextLine();
                    taskManager.createTask(username, taskName, taskPriority, taskDeadline, taskDescription);
                    System.out.println("Task created successfully!");
                    break;
                case "2":
                    // Implement view tasks functionality here
                    System.out.println("Here are your tasks:");
                    taskManager.viewTasks(username).forEach(task -> System.out.println(task.toString()));
                    break;
                case "3":
                    // Implement edit task functionality here
                    System.out.println("Enter task name to edit:");
                    String taskNameToEdit = scanner.nextLine();
                    System.out.println("Enter new task priority (LOW, MEDIUM, HIGH):");
                    String newTaskPriority = scanner.nextLine();
                    System.out.println("Enter new task deadline (mm-dd-yyyy):");
                    String newTaskDeadline = scanner.nextLine();
                    System.out.println("Enter new task description:");
                    String newTaskDescription = scanner.nextLine();
                    taskManager.editTask(username, taskNameToEdit, newTaskPriority, newTaskDeadline, newTaskDescription);
                    System.out.println("Task edited successfully!");
                    break;
                case "4":
                    // Implement delete task functionality here
                    System.out.println("Enter task name to delete:");
                    String taskNameToDelete = scanner.nextLine();
                    taskManager.deleteTask(username, taskNameToDelete);
                    System.out.println("Task deleted successfully!");
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        new TaskApp().start();
    }
}
