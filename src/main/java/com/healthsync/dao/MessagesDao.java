package com.healthsync.dao;

import com.healthsync.entities.Messages;
import com.healthsync.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessagesDao {

    public boolean createMessage(Messages message) {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.err.println("Failed to establish database connection.");
                return false;
            }
            String sql = "INSERT INTO messages (message_id, sender_id, receiver_id, date_sent, subject, message) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, message.getMessageID());
            stmt.setString(2, message.getSenderID());
            stmt.setString(3, message.getReceiverID());
            stmt.setTimestamp(4, new java.sql.Timestamp(message.getDateSent().getTime()));
            stmt.setString(5, message.getSubject());
            stmt.setString(6, message.getMessage());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Messages getMessageById(String messageId) {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.err.println("Failed to establish database connection.");
                return null;
            }
            String sql = "SELECT * FROM messages WHERE message_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, messageId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Messages(
                        rs.getInt("message_id"),
                        rs.getString("sender_id"),
                        rs.getString("receiver_id"),
                        rs.getDate("date_sent"),
                        rs.getString("subject"),
                        rs.getString("message")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
