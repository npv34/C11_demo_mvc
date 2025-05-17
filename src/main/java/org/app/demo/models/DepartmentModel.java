package org.app.demo.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentModel {
    private static Connection conn = DatabaseModel.getConnection();

    public static ResultSet getAllDepartments() throws SQLException, SQLException {
        String sql = "SELECT * FROM departments";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        return preparedStatement.executeQuery();
    }

    public static ResultSet getDepartmentById(int id) throws SQLException {
        String sql = "SELECT * FROM departments WHERE id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        return preparedStatement.executeQuery();
    }

}
