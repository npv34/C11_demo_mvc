package org.app.demo.models;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseModel {
    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/demo2";
    private static final String USER = "root";
    private static final String PASS = "123456@Abc";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
