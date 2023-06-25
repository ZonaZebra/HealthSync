package com.healthsync.service;

import com.healthsync.dao.UserDao;
import com.healthsync.entities.User;
import org.mindrot.jbcrypt.BCrypt;

public class UserService {

    private final UserDao userDao = new UserDao();

    // These will be used when executing Staff fxns probably, otherwise I'll remove them later
    public User createUser(String firstName, String lastName, String password, String role) {
        String userId = generateUserId(firstName, lastName);
        String hashedPassword = hashPassword(password);

        User user = new User(userId, firstName, lastName, hashedPassword, role);

        boolean isCreated = userDao.createUser(user);
        if (isCreated) {
            return user;
        } else {
            return null;
        }
    }

    public User getUserById(String userId) {
        return userDao.getUserById(userId);
    }

    public boolean updateUser(String userId, String firstName, String lastName, String password, String role) {
        // Hash the new password
        String hashedPassword = hashPassword(password);

        User user = new User(userId, firstName, lastName, hashedPassword, role);

        return userDao.updateUser(user);
    }

    public boolean deleteUser(String userId) {
        return userDao.deleteUser(userId);
    }

    String generateUserId(String firstName, String lastName) {
        return firstName.substring(0, Math.min(firstName.length(), 5)) +
                lastName.substring(0, Math.min(lastName.length(), 5)) +
                String.format("%05d", (int) (Math.random() * 100000));
    }

    String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean authenticate(String userId, String password) {
        User user = userDao.getUserById(userId);
        return user != null && checkPassword(password, user.getPassword());
    }

    private boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
