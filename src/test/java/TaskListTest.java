import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TaskListTest {
    private TaskList taskList;

    @Before
    public void setUp() {
        taskList = new TaskList();
        taskList.addTask("Task 1", "Description", Task.PriorityLevel.High, "2023-08-04");
    }

    @Test
    public void testAddTask() {
        assertEquals(1, taskList.getTasks().size());
    }
}
