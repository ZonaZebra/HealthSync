package com.healthsync.dao;

import com.healthsync.entities.User;
import com.healthsync.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public boolean createUser(User user) {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.err.println("Failed to establish database connection.");
                return false;
            }
            String sql = "INSERT INTO users (user_id, first_name, last_name, password, role) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getUserId());
            stmt.setString(2, user.getFirstName());
            stmt.setString(3, user.getLastName());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getRole());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User getUserById(String userId) {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.err.println("Failed to establish database connection.");
                return null;
            }
            String sql = "SELECT * FROM users WHERE user_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, userId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getString("user_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("password"),
                        rs.getString("role")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.err.println("Failed to establish database connection.");
                return null;
            }
            String sql = "SELECT * FROM users";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                users.add(new User(
                        rs.getString("user_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("password"),
                        rs.getString("role")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public boolean updateUser(User user) {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.err.println("Failed to establish database connection.");
                return false;
            }
            String sql = "UPDATE users SET first_name = ?, last_name = ?, password = ?, role = ? WHERE user_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getRole());
            stmt.setString(5, user.getUserId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteUser(String userId) {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.err.println("Failed to establish database connection.");
                return false;
            }
            String sql = "DELETE FROM users WHERE user_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, userId);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
