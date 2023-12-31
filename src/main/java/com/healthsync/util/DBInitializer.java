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
                    "issues VARCHAR NOT NULL, " +
                    "date DATE NOT NULL, " +
                    "sex CHAR(1), " +
                    "administered_by VARCHAR REFERENCES users(user_id), " +
                    "patient_id VARCHAR REFERENCES users(user_id)" +
                    ")";
            stmt.execute(createQuestionnaireResultsTable);

            // Vitals_Results table
            String createVitalsResultsTable = "CREATE TABLE IF NOT EXISTS vitals_results (" +
                    "vitals_results_id SERIAL PRIMARY KEY CHECK (vitals_results_id >= 10000), " +
                    "height INT NOT NULL, " +
                    "weight INT NOT NULL, " +
                    "systolic_bp INT NOT NULL, " +
                    "diastolic_bp INT NOT NULL, " +
                    "resting_pulse INT NOT NULL, " +
                    "temperature INT NOT NULL, " +
                    "patient_id VARCHAR REFERENCES users(user_id)" +
                    ")";
            stmt.execute(createVitalsResultsTable);

            // Moved 001 -> 005

            // Added user_id column to the Vitals_Results table - 002
            String addUserColumnToVitalsResultsTable = "ALTER TABLE vitals_results " + "ADD COLUMN IF NOT EXISTS user_id VARCHAR REFERENCES users(user_id)";
            stmt.execute(addUserColumnToVitalsResultsTable);

            // Drop insurance_info table - 003
            String dropInsuranceInfoTable = "DROP TABLE IF EXISTS insurance_info";
            stmt.execute(dropInsuranceInfoTable);

            // Truncate serialized tables - 004
            stmt.execute("TRUNCATE TABLE messages RESTART IDENTITY CASCADE");
            stmt.execute("TRUNCATE TABLE appointments RESTART IDENTITY CASCADE");
            stmt.execute("TRUNCATE TABLE physical_test_findings RESTART IDENTITY CASCADE");
            stmt.execute("TRUNCATE TABLE prescriptions RESTART IDENTITY CASCADE");
            stmt.execute("TRUNCATE TABLE questionnaire_results RESTART IDENTITY CASCADE");
            stmt.execute("TRUNCATE TABLE vitals_results RESTART IDENTITY CASCADE");
            stmt.execute("TRUNCATE TABLE users RESTART IDENTITY CASCADE");
            stmt.execute("TRUNCATE TABLE staff RESTART IDENTITY CASCADE");
            stmt.execute("TRUNCATE TABLE patients RESTART IDENTITY CASCADE");


            // Adding min values to PK sequences - 005

            stmt.execute("ALTER SEQUENCE messages_message_id_seq RESTART WITH 10000");
            stmt.execute("ALTER SEQUENCE appointments_appointment_id_seq RESTART WITH 10000");
            stmt.execute("ALTER SEQUENCE physical_test_findings_physical_test_id_seq RESTART WITH 10000");
            stmt.execute("ALTER SEQUENCE prescriptions_prescription_id_seq RESTART WITH 10000");
            stmt.execute("ALTER SEQUENCE questionnaire_results_questionnaire_id_seq RESTART WITH 10000");
            stmt.execute("ALTER SEQUENCE vitals_results_vitals_results_id_seq RESTART WITH 10000");

            // Add column to questionnaire_results table - 006
            String addPatientIdToQuestionnaireResults = "ALTER TABLE questionnaire_results " +
                    "ADD COLUMN IF NOT EXISTS patient_id VARCHAR REFERENCES patients(user_id)";
            stmt.execute(addPatientIdToQuestionnaireResults);

            // Adding test data, Password on all of these is set to "pass"
            // Insert a doctor
            String insertDoctor = "INSERT INTO users (user_id, first_name, last_name, password, role) VALUES " +
                    "('TestDoctor11111', 'Benedict', 'Cumberbatch', '$2a$10$yk/Djmus7yREI6vO1VUtRO5YzqmzbdLpAQqrwJB4cFrwUB3n382lm', 'Doctor')";
            stmt.execute(insertDoctor);
            String insertDoctorDetails = "INSERT INTO staff (user_id, email) VALUES " +
                    "('TestDoctor11111', 'doctor@email.com')";
            stmt.execute(insertDoctorDetails);
            // Insert a patient into the 'users' table
            String insertPatient = "INSERT INTO users (user_id, first_name, last_name, password, role) VALUES " +
                    "('TestPatient11111', 'Robert', 'Downie', '$2a$10$yk/Djmus7yREI6vO1VUtRO5YzqmzbdLpAQqrwJB4cFrwUB3n382lm', 'Patient')";
            stmt.execute(insertPatient);
            // Insert the same patient into the 'patients' table
            String insertPatientDetails = "INSERT INTO patients (user_id, birthday, phone_number, email, insurance_provider, insurance_policy_number, pharmacy_name, pharmacy_location) VALUES " +
                    "('TestPatient11111', '1970-01-01', '5555555555', 'robert.downie@example.com', 'Test Insurance', '123456', 'Walgreens', 'Camelback Rd & N 32nd St')";
            stmt.execute(insertPatientDetails);
            // Insert a nurse
            String insertNurse = "INSERT INTO users (user_id, first_name, last_name, password, role) VALUES " +
                    "('TestNurse11111', 'Chris', 'Evans', '$2a$10$yk/Djmus7yREI6vO1VUtRO5YzqmzbdLpAQqrwJB4cFrwUB3n382lm', 'Nurse')";
            stmt.execute(insertNurse);
            // Insert an admin
            String insertAdmin = "INSERT INTO users (user_id, first_name, last_name, password, role) VALUES " +
                    "('TestAdmin11111', 'Chris', 'Hemsworth', '$2a$10$yk/Djmus7yREI6vO1VUtRO5YzqmzbdLpAQqrwJB4cFrwUB3n382lm', 'Admin')";
            stmt.execute(insertAdmin);

            // Add new db updates here with a comment...


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
