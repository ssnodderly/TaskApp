import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Objects;

public class User {

    private String username;
    private String passwordHash;
    private byte[] salt;

    public User(String username, String password) {
        this.username = username;
        try {
            this.salt = generateSalt();
            setPassword(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            // Here you might want to add some default behavior, like setting a default password hash,
            // or you might rethrow this as an unchecked exception, depending on your application's needs.
        }
    }

    // Getters and Setters
    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Instead of setting the password directly, we hash it and store the hash
    public void setPassword(String password) throws NoSuchAlgorithmException {
        setPasswordHash(hashPassword(password, this.salt));
    }

    // Check if a provided password is correct by hashing it and comparing the hashes
    public boolean isPasswordCorrect(String password) throws NoSuchAlgorithmException {
        return Objects.equals(passwordHash, hashPassword(password, this.salt));
    }

    // Hash a password using SHA-256
    private String hashPassword(String password, byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(salt);
        byte[] hashedPassword = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hashedPassword);
    }

    // Generate a random salt
    private byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    // Equals and HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}