import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.security.NoSuchAlgorithmException;

public class AuthenticationTest {
    Authentication auth;

    @BeforeEach
    public void setUp() throws NoSuchAlgorithmException {
        auth = new Authentication();
        auth.register("TestUser", "Password");
    }

    @Test
    public void loginSuccessfulTest() throws NoSuchAlgorithmException {
        String sessionId = auth.login("TestUser", "password");
        assertNotNull(sessionId);
        Session session = auth.getSession(sessionId);
        assertNotNull(session);
        assertEquals("TestUser", session.getUser().getUsername());
    }

    @Test
    public void loginFailTest() throws NoSuchAlgorithmException {
        String sessionId = auth.login("TestUser", "wrongpassword");
        assertNull(sessionId);
    }

    @Test
    public void accountLockTest() throws NoSuchAlgorithmException {
        for (int i = 0; i < 3; i++) {
            auth.login("TestUser", "wrongpassword");
        }
        assertThrows(IllegalArgumentException.class, () -> auth.login("TestUser", "wrongpassword"));
    }

    @Test
    public void resetPasswordTest() throws NoSuchAlgorithmException {
        auth.resetPassword("TestUser", "newpassword");
        String sessionId = auth.login("TestUser", "newpassword");
        assertNotNull(sessionId);
    }
}
