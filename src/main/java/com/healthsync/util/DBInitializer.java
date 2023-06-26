package com.healthsync.util;

import java.sql.Connection;
import java.sql.Statement;

public class DBInitializer {

    // If you need to add/remove/update a table, just add on to this method don't remove stuff, EVER...

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

            // Update messages table
            String updateMessagesTable = "DROP TABLE IF EXISTS messages; " +
                    "CREATE TABLE messages (" +
                    "message_id INT PRIMARY KEY, " +
                    "sender_id VARCHAR REFERENCES users(user_id), " +
                    "receiver_id VARCHAR REFERENCES users(user_id), " +
                    "date_sent TIMESTAMP NOT NULL, " +
                    "subject VARCHAR NOT NULL, " +
                    "message TEXT NOT NULL" +
                    ")";
            stmt.execute(updateMessagesTable);

            // Appointments table
            String createAppointmentsTable = "CREATE TABLE IF NOT EXISTS appointments (" +
                    "appointment_id INT PRIMARY KEY, " +
                    "patient_id VARCHAR REFERENCES users(user_id), " +
                    "doctor_id VARCHAR REFERENCES users(user_id), " +
                    "appointment_date TIMESTAMP NOT NULL, " +
                    "questionnaire_id INT, " +
                    "vitals_results_id INT, " +
                    "physical_test_id INT" +
                    ")";
            stmt.execute(createAppointmentsTable);

            // Insurance_Info table
            String createInsuranceInfoTable = "CREATE TABLE IF NOT EXISTS insurance_info (" +
                    "policy_id INT PRIMARY KEY, " +
                    "insurance_company VARCHAR NOT NULL, " +
                    "insurance_policy_number VARCHAR, " +
                    "insurance_group_number VARCHAR" +
                    ")";
            stmt.execute(createInsuranceInfoTable);

            // Physical_Test_Findings table
            String createPhysicalTestFindingsTable = "CREATE TABLE IF NOT EXISTS physical_test_findings (" +
                    "physical_test_id INT PRIMARY KEY, " +
                    "issues VARCHAR, " +
                    "notes VARCHAR, " +
                    "administered_by VARCHAR REFERENCES users(user_id)" +
                    ")";
            stmt.execute(createPhysicalTestFindingsTable);

            // Prescriptions table
            String createPrescriptionsTable = "CREATE TABLE IF NOT EXISTS prescriptions (" +
                    "prescription_id INT PRIMARY KEY, " +
                    "product VARCHAR NOT NULL, " +
                    "dosage_in_mg INT NOT NULL, " +
                    "frequency INT NOT NULL, " +
                    "instructions VARCHAR, " +
                    "pharmacy_id INT, " +
                    "prescribed_by VARCHAR REFERENCES staff(user_id)" +
                    ")";
            stmt.execute(createPrescriptionsTable);

            // Questionnaire_Results table
            String createQuestionnaireResultsTable = "CREATE TABLE IF NOT EXISTS questionnaire_results (" +
                    "questionnaire_id INT PRIMARY KEY, " +
                    "name VARCHAR NOT NULL, " +
                    "date DATE NOT NULL, " +
                    "sex CHAR(1), " +
                    "administered_by VARCHAR REFERENCES users(user_id)" +
                    ")";
            stmt.execute(createQuestionnaireResultsTable);

            // Vitals_Results table
            String createVitalsResultsTable = "CREATE TABLE IF NOT EXISTS vitals_results (" +
                    "vitals_results_id INT PRIMARY KEY, " +
                    "height FLOAT NOT NULL, " +
                    "weight FLOAT NOT NULL, " +
                    "systolic_bp INT NOT NULL, " +
                    "diastolic_bp INT NOT NULL, " +
                    "resting_pulse FLOAT NOT NULL, " +
                    "temperature FLOAT NOT NULL" +
                    ")";
            stmt.execute(createVitalsResultsTable);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
