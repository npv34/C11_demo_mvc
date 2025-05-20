package org.app.demo.models;

import org.app.demo.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel {
    private static Connection conn = DatabaseModel.getConnection();

    public static ResultSet getUserByEmailPassword(String email, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);
        return preparedStatement.executeQuery();
    }

    public static ResultSet getAllUser() throws SQLException {
        String sql = "SELECT users.*, departments.name as 'department_name'\n" +
                "FROM users\n" +
                "left join departments\n" +
                "ON users.department_id = departments.id";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        return preparedStatement.executeQuery();
    }

    public static void destroy(int id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }

    public static void create(User user) throws SQLException {
        String sql = "INSERT INTO users (name, email, password, phone, address, department_id) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getEmail());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.setString(4, user.getPhone());
        preparedStatement.setString(5, user.getAddress());
        if (user.getDepartment() != null) {
            preparedStatement.setInt(6, user.getDepartment().getId());
        } else {
            preparedStatement.setNull(6, java.sql.Types.INTEGER);
        }
        preparedStatement.executeUpdate();
    }

    public static ResultSet findById(int id) throws SQLException {
        String sql = "SELECT * FROM users WHERE id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        return preparedStatement.executeQuery();
    }

    public static void update(User user) throws SQLException {
        String sql = "UPDATE users SET name = ?, email = ?, phone = ?, address = ?, department_id = ? WHERE id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getEmail());
        preparedStatement.setString(3, user.getPhone());
        preparedStatement.setString(4, user.getAddress());
        if (user.getDepartment() != null) {
            preparedStatement.setInt(5, user.getDepartment().getId());
        } else {
            preparedStatement.setNull(5, java.sql.Types.INTEGER);
        }
        preparedStatement.setInt(6, user.getId());
        preparedStatement.executeUpdate();
    }

}
