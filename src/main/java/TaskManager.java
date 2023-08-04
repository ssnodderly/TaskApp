import java.util.List;

public interface TaskManager {
    void addTask(Task task);
    List<Task> getAllTasks();
    List<Task> getTasksByPriority(int priority);
    void markTaskAsCompleted(String taskName);
    List<Task> getCompletedTasks();
}