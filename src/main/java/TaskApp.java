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
                    break;
                case "2":
                    // Implement view tasks functionality here
                    break;
                case "3":
                    // Implement edit task functionality here
                    break;
                case "4":
                    // Implement delete task functionality here
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
