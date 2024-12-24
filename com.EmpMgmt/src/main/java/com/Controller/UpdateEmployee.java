package com.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Employee;
import com.service.EmpService;

import java.io.IOException;

@WebServlet("/UpdateEmployee")
public class UpdateEmployee extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
	        String eidStr = request.getParameter("eid");
	        if (eidStr == null || eidStr.isEmpty()) {
	            throw new IllegalArgumentException("Employee ID is missing or invalid");
	        }

	        int eid = Integer.parseInt(eidStr);
	        String name = request.getParameter("name");
	        int age = Integer.parseInt(request.getParameter("age"));
	        double sal = Double.parseDouble(request.getParameter("sal"));

	        Employee emp = new Employee(eid, name, age, sal);
	        EmpService.update(emp);

	        response.sendRedirect("DisplayEmployees");
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.getWriter().println("Error: " + e.getMessage());
	    }
	}
}