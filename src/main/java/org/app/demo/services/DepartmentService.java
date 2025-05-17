package org.app.demo.services;

import org.app.demo.entities.Department;
import org.app.demo.models.DepartmentModel;
import org.app.demo.models.UserModel;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DepartmentService {
    public static List<Department> getDepartments() {
        try {
            ResultSet departments = DepartmentModel.getAllDepartments();
            List<Department> departmentList = new ArrayList<>();
            while (departments.next()) {
                int id = departments.getInt("id");
                String name = departments.getString("name");
                Department department = new Department(id, name);
                departmentList.add(department);
            }
            return departmentList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
