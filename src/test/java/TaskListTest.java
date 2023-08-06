import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.scheduling.TaskScheduler;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    TaskList taskList;
    User testUser;
    Task sampleTask;

    @BeforeEach
    public void setUp() {
        testUser = new User("TestUser", "password");
        taskList = new TaskList(testUser);
        sampleTask = new Task("SampleTask", "Sample Description", Task.PriorityLevel.Medium, LocalDateTime.now().plusDays(1));
    }
     @Test
    public void addTaskTest() {
        taskList.addTask(sampleTask);
        assertEquals(1, taskList.getTasks().size());
        assertEquals(sampleTask, taskList.getTasks().get(0));
     }

     @Test
    public void removeTaskTest() {
        taskList.addTask(sampleTask);
        assertTrue(taskList.removeTask("SampleTask"));
        assertEquals(0, taskList.getTasks().size());
     }

     @Test
    public void markTaskAsCompletedTest() {
        taskList.addTask(sampleTask);
        taskList.markTaskAsCompleted("SampleTask");
        assertTrue(taskList.getTasks().get(0).isCompleted());
     }

     @Test
    public void getTasksSortedByDeadlineTest() {
        taskList.addTask(new Task("Task1", "Description", Task.PriorityLevel.Low, LocalDateTime.now().plusDays(2)));
        taskList.addTask(new Task("Task2", "Description", Task.PriorityLevel.High, LocalDateTime.now().plusDays(1)));
         List<Task> sortedTasks = taskList.getTasksSortedByDeadline();
         assertEquals("Task2", sortedTasks.get(0).getName());
         assertEquals("Task1", sortedTasks.get(1).getName());
     }

     @Test
    public void getUncompletedTaskTest() {
        taskList.addTask(sampleTask);
        assertEquals(1, taskList.getUncompletedTasks().size());
        taskList.markTaskAsCompleted("SampleTask");
        assertEquals(0, taskList.getUncompletedTasks().size());
     }

     @Test
    public void getTasksDueSoonTest() {
        taskList.addTask(sampleTask);
        assertEquals(1, taskList.getUncompletedTasks().size());
        taskList.markTaskAsCompleted("SampleTask");
        assertEquals(0, taskList.getUncompletedTasks().size());
     }

     @Test
    public void getTaskTest() {
        taskList.addTask(sampleTask);
        Task fetchedTask = taskList.getTask("SampleTask");
        assertEquals(sampleTask, fetchedTask);
     }

     @Test
    public void deleteTaskTest() {
        taskList.addTask(sampleTask);
        taskList.deleteTask("SampleTask");
        assertEquals(0, taskList.getTasks().size());
     }

    // Additional test, such as testing for exception and more, to be added
}
