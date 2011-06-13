package reevent.service;

import java.util.UUID;

import reevent.domain.User;

public interface UserService {
    /**
     * Creates a new user with the given password.
     * @param newUser The user profile to create.
     * @param password The plaintext password.
     * @return The persistent user profile.
     */
    User register(User newUser, String password);

    /**
     * Cryptographically hash a salt and a password and return the hash code
     * as a hexadecimal string.
     *
     * @param salt A (random) salt string.
     * @param password The plaintext password.
     * @return The hash code as a hex string.
     */
    
    User update(User newUser, String passwort);
        
    
    String hashPassword(String salt, String password);

    /**
     * Check user credentials and return the user profile if they're valid.
     *
     * @param username The username to authenticate.
     * @param password The password to use for authentication.
     *
     * @return The user profile if authentication succeeds. If authentication
     * fails, returns <code>null</code>
     */
    User authenticate(String username, String password);

    /**
     * Check if the given username is available.
     * @param username The username to check
     * @return
     */
    boolean isAvailable(String username);

	void delete(UUID id);
}
