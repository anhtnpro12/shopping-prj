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
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.Account;

/**
 *
 * @author TNA
 */
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account acc = (Account)session.getAttribute("account");
        
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String pass = req.getParameter("pass");
        String phone = req.getParameter("phone");        
        String gender = req.getParameter("gender");
        String dob = req.getParameter("dob");
        String address = req.getParameter("address");
        String aid = req.getParameter("aid");
        String uid = req.getParameter("uid");
        
        AccountDAO adao = new AccountDAO();        
        adao.updateAccount(name, email, pass, phone, acc.getRole()+"", gender, dob, address, aid, uid);
        
        session.setAttribute("account", adao.getAccountByID(aid));
        
        resp.sendRedirect("profile");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account acc = (Account)session.getAttribute("account");
        if (acc == null) {
            req.setAttribute("mess", "Please <a href='login'>login</a> to access!!");
            req.getRequestDispatcher("showMessage.jsp").forward(req, resp);
        }
                
        req.setAttribute("a", acc);
        
        req.getRequestDispatcher("profile.jsp").forward(req, resp);
    }
    
}
