package com.healthsync.dao;

import com.healthsync.entities.Messages;
import com.healthsync.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessagesDao {

    public int createMessage(Messages message) {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.err.println("Failed to establish database connection.");
                return -1;
            }
            String sql = "INSERT INTO messages (sender_id, receiver_id, date_sent, subject, message) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, message.getSenderID());
            stmt.setString(2, message.getReceiverID());
            stmt.setTimestamp(3, new java.sql.Timestamp(message.getDateSent().getTime()));
            stmt.setString(4, message.getSubject());
            stmt.setString(5, message.getMessage());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating message failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating message failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<Messages> getMessagesBySenderId(String senderId) {
        List<Messages> messages = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.err.println("Failed to establish database connection.");
                return messages;
            }
            String sql = "SELECT * FROM messages WHERE sender_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, senderId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                messages.add(new Messages(
                        rs.getInt("message_id"),
                        rs.getString("sender_id"),
                        rs.getString("receiver_id"),
                        rs.getDate("date_sent"),
                        rs.getString("subject"),
                        rs.getString("message")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }

    public List<Messages> getMessagesByReceiverId(String receiverId) {
        List<Messages> messages = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.err.println("Failed to establish database connection.");
                return messages;
            }
            String sql = "SELECT * FROM messages WHERE receiver_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, receiverId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                messages.add(new Messages(
                        rs.getInt("message_id"),
                        rs.getString("sender_id"),
                        rs.getString("receiver_id"),
                        rs.getDate("date_sent"),
                        rs.getString("subject"),
                        rs.getString("message")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }


    public List<Messages> getAllMessages() {
        List<Messages> messages = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.err.println("Failed to establish database connection.");
                return null;
            }
            String sql = "SELECT * FROM messages";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                messages.add(new Messages(
                        rs.getInt("message_id"),
                        rs.getString("sender_id"),
                        rs.getString("receiver_id"),
                        rs.getDate("date_sent"),
                        rs.getString("subject"),
                        rs.getString("message")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }

    public boolean updateMessage(Messages message) {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.err.println("Failed to establish database connection.");
                return false;
            }
            String sql = "UPDATE messages SET sender_id = ?, receiver_id = ?, date_sent = ?, subject = ?, message = ? WHERE message_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, message.getSenderID());
            stmt.setString(2, message.getReceiverID());
            stmt.setObject(3, message.getDateSent());
            stmt.setString(4, message.getSubject());
            stmt.setString(5, message.getMessage());
            stmt.setInt(6, message.getMessageID());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteMessage(String messageId) {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.err.println("Failed to establish database connection.");
                return false;
            }
            String sql = "DELETE FROM messages WHERE message_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, messageId);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
