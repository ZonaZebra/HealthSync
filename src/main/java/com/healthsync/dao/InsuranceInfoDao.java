package com.healthsync.dao;

import com.healthsync.entities.Insurance_Info;
import com.healthsync.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsuranceInfoDao {

    public boolean createInsuranceInfo(Insurance_Info insurance_info) {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.err.println("Failed to establish database connection.");
                return false;
            }

            String sql = "INSERT INTO insurance_info (policy_id, insurance_company, insurance_policy_number, insurance_group_number) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, insurance_info.getPolicy_id());
            stmt.setString(2, insurance_info.getInsurance_company());
            stmt.setString(3, insurance_info.getInsurance_policy_number());
            stmt.setString(4, insurance_info.getInsurance_group_number());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Insurance_Info getInsuranceInfoById(int policy_id) {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.err.println("Failed to establish database connection.");
                return null;
            }
            String sql = "SELECT * FROM insurance_info WHERE policy_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, policy_id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Insurance_Info(
                        rs.getInt("policy_id"),
                        rs.getString("insurance_company"),
                        rs.getString("insurance_policy_number"),
                        rs.getString("insurance_group_number")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateInsuranceInfo(Insurance_Info insurance_info) {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.err.println("Failed to establish database connection.");
                return false;
            }

            String sql = "UPDATE insurance_info SET insurance_company = ?, insurance_policy_number = ?, insurance_group_number = ? WHERE policy_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, insurance_info.getInsurance_company());
            stmt.setString(2, insurance_info.getInsurance_policy_number());
            stmt.setString(3, insurance_info.getInsurance_group_number());
            stmt.setInt(4, insurance_info.getPolicy_id());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteInsuranceInfo(int policy_id) {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.err.println("Failed to establish database connection.");
                return false;
            }

            String sql = "DELETE FROM insurance_info WHERE policy_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, policy_id);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
