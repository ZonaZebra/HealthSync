package com.healthsync.util;

import org.mindrot.jbcrypt.BCrypt;

public class UserUtils {

    public static String generateUserId(String firstName, String lastName) {
        return firstName.substring(0, Math.min(firstName.length(), 2)) +
                lastName.substring(0, Math.min(lastName.length(), 2)) +
                String.format("%05d", (int) (Math.random() * 100000));
    }

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
