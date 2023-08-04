import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TaskManagerImplTest {
    private TaskManager taskManager;

    @Before
    public void setUp() {
        taskManager = new TaskManagerImpl();
    }

    @Test
    public void testAddTask() {
        Task task = new Task("Do homework", 1, "22023-09-01");
        taskManager.addTask(task);

        List<Task> tasks = taskManager.getAllTasks();
        Assert.assertEquals(1, tasks.size());
        Assert.assertEquals(task, tasks.get(0));
    }

    @Test
    public void testGetTasksBYPriority() {
        Task task1 = new Task("Do homework", 1, "2023-09-01");
        Task task2 = new Task("Go to the gym", 2, "2023-09-02");
        taskManager.addTask(task1);
        taskManager.addTask(task2);

        List<Task> tasks = taskManager.getTasksByPriority(1);
        Assert.assertEquals(1, tasks.size());
        Assert.assertEquals(task1, tasks.get(0));
    }

    @Test
    public void testMarkTaskAsCompleted() {
        Task task = new Task("Do homework", 1, "2023-09-01");
        taskManager.addTask(task);
        taskManager.markTaskAsCompleted("Do homework");

        Assert.assertTrue(task.isCompleted());
    }
}
