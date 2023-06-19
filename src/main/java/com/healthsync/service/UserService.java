package com.healthsync.service;

import com.healthsync.dao.UserDao;
import com.healthsync.entities.User;

public class UserService {

    private final UserDao userDao = new UserDao();

    public User createUser(String firstName, String lastName, String password, String role) {
        // Generate a unique User ID
        String userId = generateUserId(firstName, lastName);

        User user = new User(userId, firstName, lastName, password, role);

        // Use UserDao to interact with the database
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
        User user = new User(userId, firstName, lastName, password, role);

        return userDao.updateUser(user);
    }

    public boolean deleteUser(String userId) {
        return userDao.deleteUser(userId);
    }

    private String generateUserId(String firstName, String lastName) {
        return firstName + lastName + String.format("%05d", (int)(Math.random() * 100000));
    }

    public boolean authenticate(String userId, String password) {
        User user = userDao.getUserById(userId);
        return user != null && user.getPassword().equals(password);
    }
}
