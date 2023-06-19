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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
