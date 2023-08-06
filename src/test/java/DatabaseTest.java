import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest {
    Database database;
    User testUser;
    TaskList testTaskList;

    @BeforeEach
    public void setUp() {
        database = new Database();
        testUser = new User("TestUser", "password");
        testTaskList = new TaskList(testUser);
        database.addUser("TestUser", testUser);
        database.addTaskList("TestUser", testTaskList);
    }

    @Test
    public void getUserTest() {
        User fetchedUser = database.getUser("TestUser");
        assertNotNull(fetchedUser);
        assertEquals("TestUser", fetchedUser.getUsername());
    }

    @Test
    public void getNonexistentUserTest() {
        User fetchedUser = database.getUser("NonexistentUser");
        assertNull(fetchedUser);
    }

    @Test
    public void containsUserTest() {
        assertTrue(database.containsUser("TestUser"));
        assertFalse(database.containsUser("NonexistentUser"));
    }

    @Test
    public void getTaskListTest() {
        TaskList fetchedTaskList = database.getTaskList("TestUser");
        assertNotNull(fetchedTaskList);
        assertEquals(testUser, fetchedTaskList.getOwner());
    }

    @Test
    public void getNonexistentTaskListTest() {
        TaskList fetchedTaskList = database.getTaskList("NonexistentUser");
        assertNull(fetchedTaskList);
    }
}
