import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TaskManager {

    // Store users by username
    private Map<String, User> users;

    // Store TaskLists by User
    private Map<User, TaskList> taskLists;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public TaskManager() {
        this.users = new HashMap<>();
        this.taskLists = new HashMap<>();
    }

    // Create a new user and an associated task list
    public User createUser(String username, String password) {
        if (users.containsKey(username)) {
            throw new IllegalArgumentException("User with this username already exists.");
        }

        User newUser = new User(username, password);
        users.put(username, newUser);

        // Create an empty task list for the new user
        TaskList newTaskList = new TaskList(newUser);
        taskLists.put(newUser, newTaskList);

        return newUser;
    }

    // Authenticate a user
    public boolean authenticateUser(String username, String password) {
        User user = users.get(username);

        if (user == null) {
            throw new IllegalArgumentException("User does not exist.");
        }

        try {
            return user.isPasswordCorrect(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get a user's task list
    public TaskList getUserTaskList(String username) {
        User user = users.get(username);

        if (user == null) {
            throw new IllegalArgumentException("User does not exist.");
        }

        return taskLists.get(user);
    }

    public void createTask(String username, String taskName, String taskPriority, String taskDeadline, String taskDescription) {
        User user = users.get(username);
        TaskList taskList = taskLists.get(user);

        LocalDateTime deadline = LocalDateTime.parse(taskDeadline, formatter);
        Task newTask = new Task(taskName, taskDescription, Task.PriorityLevel.valueOf(taskPriority.toUpperCase()), deadline);
        taskList.addTask(newTask);
    }

    public Iterable<Object> viewTasks(String username) {
        User user = users.get(username);
        TaskList taskList = taskLists.get(user);
        return Collections.singleton(taskList.getTasks());
    }

    public void editTask(String username, String taskNameToEdit, String newTaskPriority, String newTaskDeadline, String newTaskDescription) {
        User user = users.get(username);
        TaskList taskList = taskLists.get(user);
        Task task = taskList.getTask(taskNameToEdit);

        task.setPriorityLevel(Task.PriorityLevel.valueOf(newTaskPriority.toUpperCase()));
        task.setDeadline(LocalDateTime.parse(newTaskDeadline, formatter));
        task.setDescription(newTaskDescription);
    }

    public void deleteTask(String username, String taskNameToDelete) {
        User user = users.get(username);
        TaskList taskList = taskLists.get(user);
        taskList.removeTask(taskNameToDelete);
    }
}