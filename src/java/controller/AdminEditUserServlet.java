/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dal.AccountDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author TNA
 */
public class AdminEditUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String pass = req.getParameter("pass");
        String phone = req.getParameter("phone");
        String role = req.getParameter("role");
        String gender = req.getParameter("gender");
        String dob = req.getParameter("dob");
        String address = req.getParameter("address");
        String aid = req.getParameter("aid");
        String uid = req.getParameter("uid");
        
        AccountDAO adao = new AccountDAO();
        adao.updateAccount(name, email, pass, phone, role, gender, dob, address, aid, uid);
        
        resp.sendRedirect("admin-edituser?id=" + aid);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String id = req.getParameter("id");
        AccountDAO adao = new AccountDAO();
        req.setAttribute("a", adao.getAccountByID(id));
        
        req.getRequestDispatcher("adminEditUser.jsp").forward(req, resp);
    }
    
}
