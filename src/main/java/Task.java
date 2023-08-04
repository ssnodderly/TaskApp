import java.time.LocalDateTime;

// This class is to represent a task.
public class Task {
    private String name;
    private String description;
    private PriorityLevel priorityLevel;
    private LocalDateTime deadline;
    private boolean completed;

    public enum PriorityLevel {
        High,
        Medium,
        Low
    }

    public Task(String name, String description, PriorityLevel priorityLevel, LocalDateTime deadline) {
        this.name = name;
        this.description = description;
        this.priorityLevel = priorityLevel;
        this.deadline = deadline;
        this.completed = false;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PriorityLevel getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(PriorityLevel priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markAsCompleted() {
        this.completed = true;
    }
}
