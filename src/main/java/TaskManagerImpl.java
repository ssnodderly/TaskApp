import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskManagerImpl implements TaskManager {
    private List<Task> tasks;

    public TaskManagerImpl() {
        this.tasks = new ArrayList<>();
    }

    @Override
    public void addTask(Task task) {
        tasks.add(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return tasks;
    }

    @Override
    public List<Task> getTasksByPriority(int priority) {
        return tasks.stream()
                .filter(task -> task.getPriority() == priority)
                .collect(Collectors.toList());
    }

    @Override
    public void markTaskAsCompleted(String taskName) {
        for (Task task : tasks) {
            if (task.getName().equals(taskName)) {
                task.setCompleted(true);
                break;
            }
        }
    }

    @Override
    public List<Task> getCompletedTasks() {
        return tasks.stream()
                .filter(Task::isCompleted)
                .collect(Collectors.toList());
    }
}