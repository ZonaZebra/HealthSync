package com.healthsync.dao;

import com.healthsync.entities.Vitals_Results;
import com.healthsync.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VitalsResultsDao {

    public int createVitalsResults(Vitals_Results vitalsResults) {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.err.println("Failed to establish database connection.");
                return -1;
            }

            String sql = "INSERT INTO vitals_results (height, weight, systolic_bp, diastolic_bp, resting_pulse, temperature, patient_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setDouble(1, vitalsResults.getHeight());
            stmt.setDouble(2, vitalsResults.getWeight());
            stmt.setInt(3, vitalsResults.getSystolic_bp());
            stmt.setInt(4, vitalsResults.getDiastolic_bp());
            stmt.setDouble(5, vitalsResults.getResting_pulse());
            stmt.setDouble(6, vitalsResults.getTemperature());
            stmt.setString(7, vitalsResults.getPatient_id());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating vitals results failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating vitals results failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public Vitals_Results getVitalsResultsById(int vitalsResultsId) {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.err.println("Failed to establish database connection.");
                return null;
            }

            String sql = "SELECT * FROM vitals_results WHERE vitals_results_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vitalsResultsId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Vitals_Results(
                        rs.getInt("vitals_results_id"),
                        rs.getInt("height"),
                        rs.getInt("weight"),
                        rs.getInt("systolic_bp"),
                        rs.getInt("diastolic_bp"),
                        rs.getInt("resting_pulse"),
                        rs.getInt("temperature"),
                        rs.getString("patient_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateVitalsResults(Vitals_Results vitalsResults) {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.err.println("Failed to establish database connection.");
                return false;
            }

            String sql = "UPDATE vitals_results SET height = ?, weight = ?, systolic_bp = ?, diastolic_bp = ?, resting_pulse = ?, temperature = ? WHERE vitals_results_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, vitalsResults.getHeight());
            stmt.setDouble(2, vitalsResults.getWeight());
            stmt.setInt(3, vitalsResults.getSystolic_bp());
            stmt.setInt(4, vitalsResults.getDiastolic_bp());
            stmt.setDouble(5, vitalsResults.getResting_pulse());
            stmt.setDouble(6, vitalsResults.getTemperature());
            stmt.setInt(7, vitalsResults.getVitals_results_id());
            stmt.setString(8, vitalsResults.getPatient_id());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteVitalsResults(int vitalsResultsId) {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.err.println("Failed to establish database connection.");
                return false;
            }

            String sql = "DELETE FROM vitals_results WHERE vitals_results_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vitalsResultsId);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Should return all vitals for a given patient ID
    public List<Vitals_Results> getAllVitalsResults(String patientID) {
        List<Vitals_Results> vitalsResultsList = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.err.println("Failed to establish database connection.");
                return vitalsResultsList; // Return empty list
            }

            String sql = "SELECT * FROM vitals_results WHERE patient_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, patientID);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                vitalsResultsList.add(new Vitals_Results(
                        rs.getInt("vitals_results_id"),
                        rs.getInt("height"),
                        rs.getInt("weight"),
                        rs.getInt("systolic_bp"),
                        rs.getInt("diastolic_bp"),
                        rs.getInt("resting_pulse"),
                        rs.getInt("temperature"),
                        rs.getString("patient_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vitalsResultsList;
    }
}
