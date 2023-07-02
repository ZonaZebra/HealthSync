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
                    "message_id SERIAL PRIMARY KEY CHECK (message_id >= 10000), " +
                    "sender_id VARCHAR REFERENCES users(user_id), " +
                    "receiver_id VARCHAR REFERENCES users(user_id), " +
                    "date_sent TIMESTAMP NOT NULL, " +
                    "subject VARCHAR NOT NULL, " +
                    "message TEXT NOT NULL" +
                    ")";
            stmt.execute(createMessagesTable);

            // Appointments table
            String createAppointmentsTable = "CREATE TABLE IF NOT EXISTS appointments (" +
                    "appointment_id SERIAL PRIMARY KEY CHECK (appointment_id >= 10000), " +
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
                    "policy_id SERIAL PRIMARY KEY CHECK (policy_id >= 10000), " +
                    "insurance_company VARCHAR NOT NULL, " +
                    "insurance_policy_number VARCHAR, " +
                    "insurance_group_number VARCHAR" +
                    ")";
            stmt.execute(createInsuranceInfoTable);

            // Physical_Test_Findings table
            String createPhysicalTestFindingsTable = "CREATE TABLE IF NOT EXISTS physical_test_findings (" +
                    "physical_test_id SERIAL PRIMARY KEY CHECK (physical_test_id >= 10000), " +
                    "issues VARCHAR, " +
                    "notes VARCHAR, " +
                    "patient_id VARCHAR REFERENCES users(user_id), " +
                    "administered_by VARCHAR REFERENCES users(user_id)" +
                    ")";
            stmt.execute(createPhysicalTestFindingsTable);

            // Prescriptions table
            String createPrescriptionsTable = "CREATE TABLE IF NOT EXISTS prescriptions (" +
                    "prescription_id SERIAL PRIMARY KEY CHECK (prescription_id >= 10000), " +
                    "product VARCHAR NOT NULL, " +
                    "dosage_in_mg INT NOT NULL, " +
                    "frequency INT NOT NULL, " +
                    "instructions VARCHAR, " +
                    "pharmacy_id INT, " +
                    "patient_id VARCHAR REFERENCES users(user_id), " +
                    "prescribed_by VARCHAR REFERENCES staff(user_id)" +
                    ")";
            stmt.execute(createPrescriptionsTable);

            // Questionnaire_Results table
            String createQuestionnaireResultsTable = "CREATE TABLE IF NOT EXISTS questionnaire_results (" +
                    "questionnaire_id SERIAL PRIMARY KEY CHECK (questionnaire_id >= 10000), " +
                    "name VARCHAR NOT NULL, " +
                    "date DATE NOT NULL, " +
                    "sex CHAR(1), " +
                    "administered_by VARCHAR REFERENCES users(user_id)" +
                    ")";
            stmt.execute(createQuestionnaireResultsTable);

            // Vitals_Results table
            String createVitalsResultsTable = "CREATE TABLE IF NOT EXISTS vitals_results (" +
                    "vitals_results_id SERIAL PRIMARY KEY CHECK (vitals_results_id >= 10000), " +
                    "height FLOAT NOT NULL, " +
                    "weight FLOAT NOT NULL, " +
                    "systolic_bp INT NOT NULL, " +
                    "diastolic_bp INT NOT NULL, " +
                    "resting_pulse FLOAT NOT NULL, " +
                    "temperature FLOAT NOT NULL" +
                    ")";
            stmt.execute(createVitalsResultsTable);

            // Adding min values to PK sequences - 001

            stmt.execute("ALTER SEQUENCE messages_message_id_seq RESTART WITH 10000");
            stmt.execute("ALTER SEQUENCE appointments_appointment_id_seq RESTART WITH 10000");
            stmt.execute("ALTER SEQUENCE insurance_info_policy_id_seq RESTART WITH 10000");
            stmt.execute("ALTER SEQUENCE physical_test_findings_physical_test_id_seq RESTART WITH 10000");
            stmt.execute("ALTER SEQUENCE prescriptions_prescription_id_seq RESTART WITH 10000");
            stmt.execute("ALTER SEQUENCE questionnaire_results_questionnaire_id_seq RESTART WITH 10000");
            stmt.execute("ALTER SEQUENCE vitals_results_vitals_results_id_seq RESTART WITH 10000");

            // Add new db updates here with a comment...

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
