import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;


// This class is to manage multiple task list.
public class TaskManager {

    // Store users by username
    private Map<String, User> users;

    // Store TaskLists by User
    private Map<User, TaskList> taskLists;

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
}