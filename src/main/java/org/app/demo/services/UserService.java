package org.app.demo.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.app.demo.entities.Department;
import org.app.demo.entities.User;
import org.app.demo.models.DepartmentModel;
import org.app.demo.models.UserModel;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    public static List<User> getUsers(HttpServletRequest request, HttpServletResponse response) {

        try {
            ResultSet resultSet = UserModel.getAllUser();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                int uID = resultSet.getInt("id");
                String uName = resultSet.getString("name");
                String uEmail = resultSet.getString("email");
                String uPhone = resultSet.getString("phone");
                String uAddress = resultSet.getString("address");
                User u = new User(uName, uEmail, uPhone, uAddress);
                u.setId(uID);

                int dID = resultSet.getInt("department_id");
                System.out.println(dID);
                if (dID != 0) {
                    String dName = resultSet.getString("department_name");
                    Department department = new Department(dID, dName);
                    u.setDepartment(department);
                }

                users.add(u);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void deleteUser(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            UserModel.destroy(id);
            response.sendRedirect("/users");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void createUser(HttpServletRequest request, HttpServletResponse response) {
        try {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = "123456";
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            int departmentId = Integer.parseInt(request.getParameter("department_id"));

            User user = new User(name, email, password, phone, address);
            ResultSet resultSet = DepartmentModel.getDepartmentById(departmentId);

            if (resultSet.next()) {
                String dName = resultSet.getString("name");
                Department department = new Department(departmentId, dName);
                user.setDepartment(department);
            } else {
                user.setDepartment(null);
            }

            UserModel.create(user);
            response.sendRedirect("/users");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
