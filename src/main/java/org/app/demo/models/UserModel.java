package org.app.demo.models;

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
        String sql = "SELECT * FROM users";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        return preparedStatement.executeQuery();
    }

}
