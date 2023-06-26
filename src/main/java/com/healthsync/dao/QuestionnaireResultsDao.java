package com.healthsync.dao;

import com.healthsync.entities.Questionnaire_Results;
import com.healthsync.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class QuestionnaireResultsDao {

    public boolean createQuestionnaireResult(Questionnaire_Results result) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO questionnaire_results (questionnaire_id, name, date, sex, administered_by) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, result.getQuestionnaire_id());
            stmt.setString(2, result.getName());
            stmt.setDate(3, new java.sql.Date(result.getDate().getTime()));
            stmt.setString(4, String.valueOf(result.getSex()));
            stmt.setString(5, result.getAdministered_by());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Questionnaire_Results getQuestionnaireResultById(int questionnaireId) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM questionnaire_results WHERE questionnaire_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, questionnaireId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Date date = new Date(rs.getDate("date").getTime());
                return new Questionnaire_Results(
                        rs.getInt("questionnaire_id"),
                        rs.getString("name"),
                        date,
                        rs.getString("sex").charAt(0),
                        rs.getString("administered_by")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateQuestionnaireResult(Questionnaire_Results result) {
        try (Connection conn = DBConnection.getConnection()) {
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
