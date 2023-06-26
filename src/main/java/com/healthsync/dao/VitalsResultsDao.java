package com.healthsync.dao;

import com.healthsync.entities.Vitals_Results;
import com.healthsync.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VitalsResultsDao {

    public boolean createVitalsResults(Vitals_Results vitalsResults) {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.err.println("Failed to establish database connection.");
                return false;
            }

            String sql = "INSERT INTO vitals_results (vitals_results_id, height, weight, systolic_bp, diastolic_bp, resting_pulse, temperature) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vitalsResults.getVitals_results_id());
            stmt.setDouble(2, vitalsResults.getHeight());
            stmt.setDouble(3, vitalsResults.getWeight());
            stmt.setInt(4, vitalsResults.getSystolic_bp());
            stmt.setInt(5, vitalsResults.getDiastolic_bp());
            stmt.setDouble(6, vitalsResults.getResting_pulse());
            stmt.setDouble(7, vitalsResults.getTemperature());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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
                        rs.getDouble("height"),
                        rs.getDouble("weight"),
                        rs.getInt("systolic_bp"),
                        rs.getInt("diastolic_bp"),
                        rs.getDouble("resting_pulse"),
                        rs.getDouble("temperature")
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

    public List<Vitals_Results> getAllVitalsResults() {
        List<Vitals_Results> vitalsResultsList = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.err.println("Failed to establish database connection.");
                return vitalsResultsList; // Return empty list
            }

            String sql = "SELECT * FROM vitals_results";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                vitalsResultsList.add(new Vitals_Results(
                        rs.getInt("vitals_results_id"),
                        rs.getDouble("height"),
                        rs.getDouble("weight"),
                        rs.getInt("systolic_bp"),
                        rs.getInt("diastolic_bp"),
                        rs.getDouble("resting_pulse"),
                        rs.getDouble("temperature")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vitalsResultsList;
    }
}
