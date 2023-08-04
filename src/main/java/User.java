import org.mindrot.jbcrypt.BCrypt;

public class User {
    private String Username;
    private String hashedPassworkd;

    public User(String username, String password) {
        this.username = username;
        // Hash the password and store the hashed value
        this.hashedPassworkd = BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
