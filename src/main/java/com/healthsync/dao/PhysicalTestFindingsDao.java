package com.healthsync.dao;

import com.healthsync.entities.Physical_Test_Findings;
import com.healthsync.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PhysicalTestFindingsDao {

    public boolean createPhysicalTestFinding(Physical_Test_Findings finding) {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.err.println("Failed to establish database connection.");
                return false;
            }

            String sql = "INSERT INTO physical_test_findings (physical_test_id, issues, notes, administered_by) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, finding.getPhysical_test_id());
            stmt.setString(2, finding.getIssues());
            stmt.setString(3, finding.getNotes());
            stmt.setString(4, finding.getAdministered_by());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
