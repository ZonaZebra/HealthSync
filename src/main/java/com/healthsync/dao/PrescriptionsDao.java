package com.healthsync.dao;

import com.healthsync.entities.Prescriptions;
import com.healthsync.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class PrescriptionsDao {

    public boolean createPrescription(Prescriptions prescription) {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.err.println("Failed to establish database connection.");
                return false;
            }

            String sql = "INSERT INTO prescriptions (prescription_id, product, dosage_in_mg, frequency, instructions, pharmacy_id, prescribed_by) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, prescription.getPrescription_id());
            stmt.setString(2, prescription.getProduct());
            stmt.setInt(3, prescription.getDosage_in_mg());
            stmt.setInt(4, prescription.getFrequency());
            stmt.setString(5, prescription.getInstructions());
            stmt.setInt(6, prescription.getPharmacy_id());
            stmt.setString(7, prescription.getPrescribed_by());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updatePrescription(Prescriptions prescription) {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.err.println("Failed to establish database connection.");
                return false;
            }

            String sql = "UPDATE prescriptions SET product = ?, dosage_in_mg = ?, frequency = ?, instructions = ? WHERE prescription_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, prescription.getProduct());
            stmt.setInt(2, prescription.getDosage_in_mg());
            stmt.setInt(3, prescription.getFrequency());
            stmt.setString(4, prescription.getInstructions());
            stmt.setString(5, prescription.getPrescribed_by());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}