package com.service;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.entity.Employee;

public class EmpService {
    private static String url = "jdbc:postgresql://localhost:5432/emp";
    private static String user = "postgres";
    private static String pwd = "123";
    private static Connection conn;

    static {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void save(Employee emp) {
        try {
            String query = "INSERT INTO employees (id, name, age, sal) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, emp.getEid());
            pstmt.setString(2, emp.getName());
            pstmt.setInt(3, emp.getAge());
            pstmt.setDouble(4, emp.getSal());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void update(Employee emp) {
        try {
            String query = "UPDATE employees SET name = ?, age = ?, sal = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, emp.getName());
            pstmt.setInt(2, emp.getAge());
            pstmt.setDouble(3, emp.getSal());
            pstmt.setInt(4, emp.getEid());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(int eid) {
        try {
            String query = "DELETE FROM employees WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, eid);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Employee fetch(int eid) {
        try {
            String query = "SELECT * FROM employees WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, eid);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Employee(rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getDouble("sal"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Employee> fetchAll() {
        List<Employee> employees = new ArrayList<>();
        try {
            String query = "SELECT * FROM employees";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                employees.add(new Employee(rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getDouble("sal")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
    
  

}
