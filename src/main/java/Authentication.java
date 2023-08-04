import java.security.NoSuchAlgorithmException;
import java.util.*;

// This class is responsible for the login process.
public class Authentication {

    private Map<String, User> users = new HashMap<>();
    private Map<String, Session> sessions = new HashMap<>();
    private Map<String, Integer> loginAttempts = new HashMap<>();
    private static final int MAX_LOGIN_ATTEMPTS = 3;

    public void register(String username, String password) throws NoSuchAlgorithmException {
        if (users.containsKey(username)) {
            throw new IllegalArgumentException("Username is already taken");
        }
        User newUser = new User(username, password);
        users.put(username, newUser);
    }

    public String login(String username, String password) throws NoSuchAlgorithmException {
        if (!loginAttempts.containsKey(username)) {
            loginAttempts.put(username, 0);
        }
        if (loginAttempts.get(username) >= MAX_LOGIN_ATTEMPTS) {
            throw new IllegalStateException("Account is locked due to too many failed login attempts");
        }
        User user = users.get(username);
        if (user != null && user.isPasswordCorrect(password)) {
            String sessionId = UUID.randomUUID().toString();
            sessions.put(sessionId, new Session(user));
            return sessionId;
        } else {
            loginAttempts.put(username, loginAttempts.get(username) + 1);
            return null;
        }
    }

    public void logout(String sessionId) {
        sessions.remove(sessionId);
    }

    public void resetPassword(String username, String newPassword) throws NoSuchAlgorithmException {
        User user = users.get(username);
        if (user != null) {
            user.setPassword(newPassword);
            loginAttempts.put(username, 0); // reset login attempts after password reset
        }
    }

    public Session getSession(String sessionId) {
        return sessions.get(sessionId);
    }
}
