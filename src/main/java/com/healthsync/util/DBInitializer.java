package com.healthsync.util;

import java.sql.Connection;
import java.sql.Statement;

public class DBInitializer {

    public static void initializeDatabase() {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                throw new Exception("Failed to establish database connection.");
            }
            Statement stmt = conn.createStatement();

            // Creating the user table
            String createUserTable = "CREATE TABLE IF NOT EXISTS users (" +
                    "user_id VARCHAR PRIMARY KEY, " +
                    "first_name VARCHAR NOT NULL, " +
                    "last_name VARCHAR NOT NULL, " +
                    "password VARCHAR NOT NULL, " +
                    "role VARCHAR NOT NULL" +
                    ")";
            stmt.execute(createUserTable);

            // Creating the patient table
            String createPatientTable = "CREATE TABLE IF NOT EXISTS patients (" +
                    "user_id VARCHAR PRIMARY KEY REFERENCES users(user_id), " +
                    "birthday DATE NOT NULL, " +
                    "phone_number VARCHAR NOT NULL, " +
                    "email VARCHAR NOT NULL, " +
                    "insurance_provider VARCHAR, " +
                    "insurance_policy_number VARCHAR, " +
                    "pharmacy_name VARCHAR, " +
                    "pharmacy_location VARCHAR" +
                    ")";
            stmt.execute(createPatientTable);

            // Creating the staff table
            String createStaffTable = "CREATE TABLE IF NOT EXISTS staff (" +
                    "user_id VARCHAR PRIMARY KEY REFERENCES users(user_id), " +
                    "email VARCHAR NOT NULL" +
                    ")";
            stmt.execute(createStaffTable);

            // Creating the messages table
            String createMessagesTable = "CREATE TABLE IF NOT EXISTS messages (" +
                    "message_id VARCHAR PRIMARY KEY, " +
                    "sender_id VARCHAR REFERENCES users(user_id), " +
                    "receiver_id VARCHAR REFERENCES users(user_id), " +
                    "date_sent TIMESTAMP NOT NULL, " +
                    "subject VARCHAR NOT NULL, " +
                    "message TEXT NOT NULL" +
                    ")";
            stmt.execute(createMessagesTable);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
