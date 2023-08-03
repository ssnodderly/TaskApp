import java.util.Scanner;

public class TaskApp {
    private TaskManager taskManager;

    public TaskApp() {
        taskManager = new TaskManagerImpl();
    }

    public static void main(String[] args) {
        TaskApp taskApp = new TaskApp();
        taskApp.run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please select an option:");
            System.out.println("1. Add a task");
            System.out.println("2. List all tasks");
            System.out.println("3. List tasks by priority");
            System.out.println("4. Mark tasks as completed");
            System.out.println("5. List completed tasks");
            System.out.println("6. Exit");

            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    System.out.println("Enter task name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter task Priority:");
                    int priority = Integer.parseInt(scanner.next());
                    System.out.println("Enter task deadline:");
                    String deadline = scanner.nextLine();
                    taskManager.addTask(new Task(name, priority, deadline));
                    break;
                case "2":
                    for (Task task : taskManager.getAllTasks()) {
                        System.out.println(task);
                    }
                    break;
                case "3":
                    System.out.println("Enter Priority:");
                    int p = Integer.parseInt(scanner.nextLine());
                    for (Task task : taskManager.getTasksByPriority(p)) {
                        System.out.println(task);
                    }
                    break;
                case "4":
                    System.out.println("Enter task name to mark as completed:");
                    String taskName = scanner.nextLine();
                    taskManager.markTaskAsCompleted(taskName);
                    break;
                case "5":
                    for (Task task : taskManager.getCompletedTasks()) {
                        System.out.println(task);
                    }
                    break;
                case "6":
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
