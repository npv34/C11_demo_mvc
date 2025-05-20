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

@WebServlet(name = "UserController", value = {"/admin/users/*"})
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
                showListUser(req, resp);
                break;
            case "/delete":
                UserService.deleteUser(req, resp);
                break;
            case "/edit":
                showFormEditUser(req, resp);
                break;
            case "/create":
                showFormCreate(req, resp);
                break;
            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    private void showFormCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Department> listDepartments = DepartmentService.getDepartments();
        req.setAttribute("departments", listDepartments);
        renderView("/views/users/create.jsp", req, resp);
    }

    private void showFormEditUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Department> departments = DepartmentService.getDepartments();
        User currentUser = UserService.getUserById(req, resp);
        req.setAttribute("departments", departments);
        req.setAttribute("user", currentUser);
        renderView("/views/users/edit.jsp", req, resp);
    }

    private void showListUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = UserService.getUsers(req, resp);
        req.setAttribute("users", users);
        renderView("/views/users/list.jsp", req, resp);
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
            case "/edit":
                UserService.updateUser(req, resp);
                break;
            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }
}
