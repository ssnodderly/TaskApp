import java.util.HashMap;
import java.util.Map;

// This class is to represent the data storage system.
public class Database {
    private Map<String, User> users;
    private Map<String, TaskList> taskLists;

    public Database() {
        users = new HashMap<>();
        taskLists = new HashMap<>();
    }
    // User operations
    public User getUser(String username) {
        return users.get(username);
    }

    public void addUser(String username, User user) {
        users.put(username, user);
    }

    public boolean containsUser(String username) {
        return users.containsKey(username);
    }

    // TaskList operations
    public TaskList getTaskList(String username) {
        return taskLists.get(username);
    }

    public void addTaskList(String username, TaskList taskList) {
        taskLists.put(username, taskList);
    }
}
