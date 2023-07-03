package com.healthsync.dao;

import com.healthsync.entities.Patient;
import com.healthsync.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class PatientDao {

    public boolean createPatient(Patient patient) {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.err.println("Failed to establish database connection.");
                return false;
            }

            // Creating user
            String userSql = "INSERT INTO users (user_id, first_name, last_name, password, role) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement userStmt = conn.prepareStatement(userSql);
            userStmt.setString(1, patient.getUserId());
            userStmt.setString(2, patient.getFirstName());
            userStmt.setString(3, patient.getLastName());
            userStmt.setString(4, patient.getPassword());
            userStmt.setString(5, patient.getRole());

            // Creating patient
            String patientSql = "INSERT INTO patients (user_id, birthday, phone_number, email, insurance_provider, insurance_policy_number, pharmacy_name, pharmacy_location) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement patientStmt = conn.prepareStatement(patientSql);
            patientStmt.setString(1, patient.getUserId());
            patientStmt.setDate(2, new java.sql.Date(patient.getBirthday().getTime()));
            // Assuming contactInformation is a string in format: "phoneNumber,email"
            String[] contactInfo = patient.getContactInformation().split(",");
            patientStmt.setString(3, contactInfo[0]);
            patientStmt.setString(4, contactInfo[1]);
            // Assuming insuranceInformation is a string in format: "provider,policyNumber"
            String[] insuranceInfo = patient.getInsuranceInformation().split(",");
            patientStmt.setString(5, insuranceInfo[0]);
            patientStmt.setString(6, insuranceInfo.length > 1 ? insuranceInfo[1] : "N/A");

            // Assuming pharmacyInformation is a string in format: "pharmacyName,pharmacyLocation"
            String[] pharmacyInfo = patient.getPharmacyInformation().split(",");
            patientStmt.setString(7, pharmacyInfo[0]);
            patientStmt.setString(8, pharmacyInfo.length > 1 ? pharmacyInfo[1] : "N/A");


            int userRowsInserted = userStmt.executeUpdate();
            int patientRowsInserted = patientStmt.executeUpdate();

            return userRowsInserted > 0 && patientRowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Patient getPatientById(String userId) {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.err.println("Failed to establish database connection.");
                return null;
            }
            String sql = "SELECT * FROM users JOIN patients ON users.user_id = patients.user_id WHERE users.user_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, userId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Date birthday = new Date(rs.getDate("birthday").getTime());
                String contactInformation = rs.getString("phone_number") + "," + rs.getString("email");
                String insuranceInformation = rs.getString("insurance_provider") + "," + rs.getString("insurance_policy_number");
                String pharmacyInformation = rs.getString("pharmacy_name") + "," + rs.getString("pharmacy_location");

                return new Patient(
                        rs.getString("user_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("password"),
                        birthday,
                        contactInformation,
                        insuranceInformation,
                        pharmacyInformation
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
