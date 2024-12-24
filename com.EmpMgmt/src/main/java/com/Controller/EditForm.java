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

@WebServlet("/EditForm")
public class EditForm extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String eidStr = request.getParameter("eid");
        Employee emp = null;

        if (eidStr != null && !eidStr.isEmpty()) {
            int eid = Integer.parseInt(eidStr);
            emp = EmpService.fetch(eid);
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Edit Employee</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f9; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }");
        out.println("form { background: #fff; padding: 20px; border-radius: 8px; box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); text-align: left; width: 300px; }");
        out.println("h2 { color: #333; font-size: 1.8rem; margin-bottom: 20px; text-align: center; }");
        out.println("label { display: block; font-weight: bold; margin-bottom: 5px; color: #555; }");
        out.println("input[type='text'], input[type='number'] { width: 100%; padding: 10px; margin: 10px 0 20px 0; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; }");
        out.println("button { background-color: #007bff; color: white; border: none; padding: 10px 20px; border-radius: 4px; cursor: pointer; font-size: 16px; width: 100%; }");
        out.println("button:hover { background-color: #0056b3; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Edit Employee</h2>");
        out.println("<form action='UpdateEmployee' method='post'>");

        // Hidden field for eid (if editing)
        if (emp != null) {
            out.println("<input type='hidden' id='eid' name='eid' value='" + emp.getEid() + "'>");
        } else {
            out.println("<input type='hidden' id='eid' name='eid' value=''>");
        }

        // Name field
        out.println("<label for='name'>Name:</label>");
        out.println("<input type='text' id='name' name='name' value='" + (emp != null ? emp.getName() : "") + "' required>");

        // Age field
        out.println("<label for='age'>Age:</label>");
        out.println("<input type='number' id='age' name='age' value='" + (emp != null ? emp.getAge() : "") + "' required>");

        // Salary field
        out.println("<label for='sal'>Salary:</label>");
        out.println("<input type='number' id='sal' name='sal' step='0.01' value='" + (emp != null ? emp.getSal() : "") + "' required>");

        out.println("<button type='submit'>Submit</button>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");

        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}