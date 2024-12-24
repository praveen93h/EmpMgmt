package com.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.EmpService;

import java.io.IOException;

@WebServlet("/DeleteEmployee")
public class DeleteEmployee extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String eidStr = request.getParameter("eid");

        if (eidStr != null && !eidStr.isEmpty()) {
            try {
                int eid = Integer.parseInt(eidStr);
                EmpService.delete(eid);
                response.sendRedirect("DisplayEmployees");
            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.getWriter().println("Invalid Employee ID.");
            }
        } else {
            response.getWriter().println("Employee ID is missing.");
        }
    }
}