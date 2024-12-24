package com.Controller;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Employee;
import com.service.EmpService;

import java.io.IOException;

@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int eid = Integer.parseInt(request.getParameter("eid"));
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        double sal = Double.parseDouble(request.getParameter("sal"));

        Employee emp = new Employee(eid, name, age, sal);
        EmpService.save(emp);

        response.sendRedirect("homepage.html");
    }
}
