package com.healthsync.dao;

import com.healthsync.entities.Appointments;
import com.healthsync.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentsDao {

    public int createAppointment(Appointments appointment) {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.err.println("Failed to establish database connection.");
                return -1;
            }

            String sql = "INSERT INTO appointments (patient_id, doctor_id, appointment_date, questionnaire_id, vitals_results_id, physical_test_id) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, appointment.getPatient_id());
            stmt.setString(2, appointment.getDoctor_id());
            stmt.setTimestamp(3, new java.sql.Timestamp(appointment.getAppointment_date().getTime()));
            stmt.setInt(4, appointment.getQuestionnaire_id());
            stmt.setInt(5, appointment.getVitals_results_id());
            stmt.setInt(6, appointment.getPhysical_test_id());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating appointment failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating appointment failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }


    public Appointments getAppointmentById(int id) {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.err.println("Failed to establish database connection.");
                return null;
            }
            String sql = "SELECT * FROM appointments WHERE appointment_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Appointments(
                        rs.getInt("appointment_id"),
                        rs.getString("patient_id"),
                        rs.getString("doctor_id"),
                        rs.getTimestamp("appointment_date"),
                        rs.getInt("questionnaire_id"),
                        rs.getInt("vitals_results_id"),
                        rs.getInt("physical_test_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateAppointment(Appointments appointment) {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.err.println("Failed to establish database connection.");
                return false;
            }

            String sql = "UPDATE appointments SET patient_id = ?, doctor_id = ?, appointment_date = ?, questionnaire_id = ?, vitals_results_id = ?, physical_test_id = ? WHERE appointment_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, appointment.getPatient_id());
            stmt.setString(2, appointment.getDoctor_id());
            stmt.setTimestamp(3, new java.sql.Timestamp(appointment.getAppointment_date().getTime()));
            stmt.setInt(4, appointment.getQuestionnaire_id());
            stmt.setInt(5, appointment.getVitals_results_id());
            stmt.setInt(6, appointment.getPhysical_test_id());
            stmt.setInt(7, appointment.getAppointment_id());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteAppointment(int id) {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.err.println("Failed to establish database connection.");
                return false;
            }

            String sql = "DELETE FROM appointments WHERE appointment_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Appointments> getAllAppointments() {
        List<Appointments> appointments = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.err.println("Failed to establish database connection.");
                return appointments;
            }

            String sql = "SELECT * FROM appointments";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                appointments.add(
                        new Appointments(
                                rs.getInt("appointment_id"),
                                rs.getString("patient_id"),
                                rs.getString("doctor_id"),
                                rs.getTimestamp("appointment_date"),
                                rs.getInt("questionnaire_id"),
                                rs.getInt("vitals_results_id"),
                                rs.getInt("physical_test_id")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }
}

