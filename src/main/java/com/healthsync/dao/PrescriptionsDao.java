package com.healthsync.dao;

import com.healthsync.entities.Prescriptions;
import com.healthsync.util.DBConnection;

import java.sql.*;


public class PrescriptionsDao {

    public int createPrescription(Prescriptions prescription) {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.err.println("Failed to establish database connection.");
                return -1;
            }

            String sql = "INSERT INTO prescriptions (product, dosage_in_mg, frequency, instructions, pharmacy_id, patient_id, prescribed_by) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, prescription.getProduct());
            stmt.setInt(2, prescription.getDosage_in_mg());
            stmt.setInt(3, prescription.getFrequency());
            stmt.setString(4, prescription.getInstructions());
            stmt.setInt(5, prescription.getPharmacy_id());
            stmt.setString(6, prescription.getPatient_id());
            stmt.setString(7, prescription.getPrescribed_by());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating prescription failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating prescription failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
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

    public Prescriptions getPrescription(String patientID) {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.err.println("Failed to establish database connection.");
                return null;
            }

            String sql = "SELECT * FROM prescriptions WHERE patient_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, patientID);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int testID = rs.getInt("prescription_id");
                String product = rs.getString("product");
                int dosage = rs.getInt("dosage_in_mg");
                int frequency = rs.getInt("frequency");
                String instruction = rs.getString("instructions");
                int pharmacyID = rs.getInt("pharmacy_id");
                String patient_ID = rs.getString("patient_id");
                String prescriber = rs.getString("prescribed_by");

                return new Prescriptions(testID, product, dosage, frequency, instruction, pharmacyID, patient_ID, prescriber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
