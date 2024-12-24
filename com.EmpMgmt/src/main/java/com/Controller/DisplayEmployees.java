package com.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Employee;
import com.service.EmpService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/DisplayEmployees")
public class DisplayEmployees extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        List<Employee> employees = EmpService.fetchAll();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Employee List</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f9; margin: 0; padding: 20px; }");
        out.println("h2 { text-align: center; color: #333; font-size: 2rem; margin-bottom: 20px; }");
        out.println("table { width: 100%; border-collapse: collapse; margin: 20px 0; background: #fff; }");
        out.println("table, th, td { border: 1px solid #ddd; }");
        out.println("th, td { text-align: left; padding: 12px; }");
        out.println("th { background-color: #007bff; color: white; }");
        out.println("tr:nth-child(even) { background-color: #f2f2f2; }");
        out.println("tr:hover { background-color: #e2e2ff; }");
        out.println("a { text-decoration: none; color: #007bff; font-weight: bold; }");
        out.println("a:hover { color: #0056b3; text-decoration: underline; }");
        out.println(".back-link { display: block; margin: 20px 0; text-align: center; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Employee List</h2>");
        out.println("<table>");
        out.println("<tr><th>ID</th><th>Name</th><th>Age</th><th>Salary</th><th>Actions</th></tr>");

        for (Employee emp : employees) {
            out.println("<tr>");
            out.println("<td>" + emp.getEid() + "</td>");
            out.println("<td>" + emp.getName() + "</td>");
            out.println("<td>" + emp.getAge() + "</td>");
            out.println("<td>" + emp.getSal() + "</td>");
            out.println("<td><a href='EditForm?eid=" + emp.getEid() + "'>Edit</a> | <a href='DeleteEmployee?eid=" + emp.getEid() + "'>Delete</a></td>");
            out.println("</tr>");
        }

        out.println("</table>");
        out.println("<div class='back-link'><a href='homepage.html'>Back to Home</a></div>");
        out.println("</body>");
        out.println("</html>");

        out.close();
    }
}