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

@WebServlet("/GetEmployeeDetails")
public class GetEmployeeDetails extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String eidStr = request.getParameter("eid");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Employee Details</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f9; margin: 0; padding: 20px; }");
        out.println(".container { max-width: 600px; margin: 50px auto; background: #fff; padding: 20px; border-radius: 8px; box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); text-align: center; }");
        out.println("h2 { color: #333; font-size: 2rem; margin-bottom: 20px; }");
        out.println("table { width: 100%; border-collapse: collapse; margin: 20px 0; background: #fff; }");
        out.println("table, th, td { border: 1px solid #ddd; }");
        out.println("th, td { text-align: left; padding: 12px; }");
        out.println("th { background-color: #007bff; color: white; }");
        out.println("tr:nth-child(even) { background-color: #f2f2f2; }");
        out.println("tr:hover { background-color: #e2e2ff; }");
        out.println("a { display: inline-block; margin-top: 15px; text-decoration: none; color: #007bff; font-weight: bold; }");
        out.println("a:hover { color: #0056b3; text-decoration: underline; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");

        if (eidStr != null && !eidStr.isEmpty()) {
            try {
                int eid = Integer.parseInt(eidStr);
                Employee emp = EmpService.fetch(eid);

                if (emp != null) {
                    out.println("<h2>Employee Details</h2>");
                    out.println("<table>");
                    out.println("<tr><th>ID</th><th>Name</th><th>Age</th><th>Salary</th></tr>");
                    out.println("<tr><td>" + emp.getEid() + "</td><td>" + emp.getName() + "</td><td>" + emp.getAge() + "</td><td>" + emp.getSal() + "</td></tr>");
                    out.println("</table>");
                } else {
                    out.println("<h2>No Employee Found</h2>");
                    out.println("<p>No employee exists with ID: " + eid + "</p>");
                }
            } catch (NumberFormatException e) {
                out.println("<h2>Error</h2>");
                out.println("<p>Invalid Employee ID. Please enter a valid number.</p>");
            }
        } else {
            out.println("<h2>Error</h2>");
            out.println("<p>Employee ID is missing. Please provide an ID.</p>");
        }

        out.println("<a href='getsingle.html'>Try Again</a>");
        out.println("<br>");
        out.println("<a href='homepage.html'>Back to Home</a>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");

        out.close();
    }
}