package com.healthsync.dao;

import com.healthsync.entities.Questionnaire_Results;
import com.healthsync.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuestionnaireResultsDao {

    public int createQuestionnaireResult(Questionnaire_Results result) {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.err.println("Failed to establish database connection.");
                return -1;
            }

            String sql = "INSERT INTO questionnaire_results (name, issues, date, sex, administered_by, patient_id) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, result.getName());
            stmt.setString(2, result.getIssues());
            stmt.setDate(3, new java.sql.Date(result.getDate().getTime()));
            stmt.setString(4, String.valueOf(result.getSex()));
            stmt.setString(5, result.getAdministered_by());
            stmt.setString(6, result.getPatient_id());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating questionnaire result failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating questionnaire result failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    // Grabs all questionnaires associated with patient ID
    public List<Questionnaire_Results> getQuestionnaireResultsByPatientId(String patientId) {
        List<Questionnaire_Results> results = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.err.println("Failed to establish database connection.");
                return null;
            }

            String sql = "SELECT * FROM questionnaire_results WHERE patient_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, patientId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Date date = new Date(rs.getDate("date").getTime());
                results.add(new Questionnaire_Results(
                        rs.getInt("questionnaire_id"),
                        rs.getString("name"),
                        rs.getString("issues"),
                        date,
                        rs.getString("sex").charAt(0),
                        rs.getString("administered_by"),
                        rs.getString("patient_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }


    public boolean updateQuestionnaireResult(Questionnaire_Results result) {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.err.println("Failed to establish database connection.");
                return false;
            }

            String sql = "UPDATE questionnaire_results SET name = ?, date = ?, sex = ?, administered_by = ? WHERE questionnaire_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, result.getName());
            stmt.setDate(2, new java.sql.Date(result.getDate().getTime()));
            stmt.setString(3, String.valueOf(result.getSex()));
            stmt.setString(4, result.getAdministered_by());
            stmt.setInt(5, result.getQuestionnaire_id());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteQuestionnaireResult(int questionnaireId) {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.err.println("Failed to establish database connection.");
                return false;
            }

            String sql = "DELETE FROM questionnaire_results WHERE questionnaire_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, questionnaireId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
