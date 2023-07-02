package com.healthsync.dao;

import com.healthsync.entities.Physical_Test_Findings;
import com.healthsync.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PhysicalTestFindingsDao {

    public int createPhysicalTestFinding(Physical_Test_Findings finding) {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.err.println("Failed to establish database connection.");
                return -1;
            }

            String sql = "INSERT INTO physical_test_findings (physical_test_id, issues, notes, patient_id, administered_by) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, finding.getPhysical_test_id());
            stmt.setString(2, finding.getIssues());
            stmt.setString(3, finding.getNotes());
            stmt.setString(4, finding.getPatientID());
            stmt.setString(5, finding.getAdministered_by());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating physical test finding failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating physical test finding failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<Physical_Test_Findings> getPhysicalTestFindings(String patientID) {
        List<Physical_Test_Findings> findings = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.err.println("Failed to establish database connection.");
                return findings;
            }

            String sql = "SELECT * FROM physical_test_findings WHERE patient_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, patientID);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int testID = rs.getInt("physical_test_id");
                String issues = rs.getString("issues");
                String notes = rs.getString("notes");
                String patientId = rs.getString("patient_id");
                String adminBy = rs.getString("administered_by");

                findings.add(new Physical_Test_Findings(testID, issues, notes, patientId, adminBy));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return findings;
    }


}
