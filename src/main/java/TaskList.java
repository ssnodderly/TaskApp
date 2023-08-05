import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class TaskList {
    private User owner;
    private List<Task> tasks;

    public TaskList(User owner) {
        this.owner = owner;
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        if (tasks.stream().anyMatch(t -> t.getName().equals(task.getName()))) {
            throw new IllegalArgumentException("Task with this name already exists.");
        }
        tasks.add(task);
    }

    public boolean removeTask(String taskName) {
        Optional<Task> taskToRemove = tasks.stream()
                .filter(task -> task.getName().equals(taskName))
                .findFirst();
        if (taskToRemove.isPresent()) {
            tasks.remove(taskToRemove.get());
            return true;
        } else {
            return false;
        }
    }

    public void markTaskAsCompleted(String taskName) {
        tasks.stream()
                .filter(task -> task.getName().equals(taskName))
                .findFirst()
                .ifPresent(task -> task.markAsCompleted());
    }

    public List<Task> getTasksSortedByDeadline() {
        return tasks.stream()
                .sorted(Comparator.comparing(Task::getDeadline))
                .collect(Collectors.toList());
    }

    public List<Task> getUncompletedTasks() {
        return tasks.stream()
                .filter(task -> !task.isCompleted())
                .collect(Collectors.toList());
    }

    public List<Task> getTasksDueSoon() {
        LocalDateTime now = LocalDateTime.now();
        return tasks.stream()
                .filter(task -> !task.isCompleted() && ChronoUnit.HOURS.between(now, task.getDeadline()) <= 24)
                .collect(Collectors.toList());
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public User getOwner() {
        return owner;
    }

    public Task getTask(String taskNameToEdit) {
        return tasks.stream()
                .filter(task -> task.getName().equals(taskNameToEdit))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Task not found."));
    }

    public void deleteTask(String taskNameToDelete) {
        Optional<Task> taskToDelete = tasks.stream()
                .filter(task -> task.getName().equals(taskNameToDelete))
                .findFirst();

        if (!taskToDelete.isPresent()) {
            throw new IllegalArgumentException("Task not found.");
        }

        tasks.remove(taskToDelete.get());
    }
}
