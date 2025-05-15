package org.app.demo.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.app.demo.models.UserModel;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthService {
    public static boolean checkAccount(HttpServletRequest request, HttpServletResponse response) {
        // Implement login logic here
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            // For example, check the username and password against a database
            ResultSet resultSet = UserModel.getUserByEmailPassword(email, password);
            return resultSet.next();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
