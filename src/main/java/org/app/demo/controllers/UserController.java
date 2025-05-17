package org.app.demo.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.app.demo.entities.Department;
import org.app.demo.entities.User;
import org.app.demo.services.DepartmentService;
import org.app.demo.services.UserService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserController", value = {"/users/*"})
public class UserController extends BaseController {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        if (path == null) {
            path = "";
        }

        switch (path) {
            case "":
            case "/":
                List<User> users = UserService.getUsers(req, resp);
                req.setAttribute("users", users);
                renderView("/views/users/list.jsp", req, resp);
                break;
            case "/delete":
                UserService.deleteUser(req, resp);
                break;
            case "/create":
                List<Department> departments = DepartmentService.getDepartments();
                req.setAttribute("departments", departments);
                renderView("/views/users/create.jsp", req, resp);
                break;
            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        if (path == null) {
            path = "";
        }

        switch (path) {
            case "/create":
               UserService.createUser(req, resp);
                break;
            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }
}
