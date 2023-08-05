import org.junit.Before;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;
public class UserTest {
    private User user;

    @Before
    public void setUp() throws NoSuchAlgorithmException {
        user = new User("username", "password");
    }

    @Test
    public void testPasswordHashing() throws NoSuchAlgorithmException {
        assertTrue(user.isPasswordCorrect("password"));
        assertFalse(user.isPasswordCorrect("wrongpassword"));
    }

    @Test
    public void testUsername() {
        assertEquals("username", user.getUsername());
        user.setUsername("newusername");
        assertEquals("newusername", user.getUsername());
    }
}
