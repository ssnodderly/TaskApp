public class Task {
    private String name;
    private int priority;
    private String deadline;
    private boolean completed;

    public Task(String name, int priority, String deadline) {
        this.name = name;
        this.priority = priority;
        this.deadline = deadline;
        this.completed = false; // initially, the task is not completedd
    }

    //Getters

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    public String getDeadline() {
        return deadline;
    }

    public boolean isCompleted() {
        return completed;
    }

    // Setters


    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    //toString method to print task details
    @Override
    public String toString() {
        return "Task{" +
                "name" + name + '\'' +
                ", priority=" + priority +
                ", deadline='" + deadline +'\'' +
                ", completed=" + completed +
                '}';
    }
}
