/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dal.AccountDAO;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * @author TNA
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String pass = req.getParameter("pass");
        String rem = req.getParameter("rem");
        
        AccountDAO adao = new AccountDAO();
        if (adao.checkLogin(email, pass)) {
            
            HttpSession session = req.getSession();
            session.setAttribute("account", adao.getAccountByEmail(email));
            
            Cookie cu = new Cookie("email", email);
            Cookie cp = new Cookie("pass", pass);
            Cookie cr = new Cookie("rem", rem);
            
            if (rem == null) {
                cu.setMaxAge(0);
                cp.setMaxAge(0);
                cr.setMaxAge(0);
            } else {
                cu.setMaxAge(24 * 60 * 60);
                cp.setMaxAge(24 * 60 * 60);
                cr.setMaxAge(24 * 60 * 60);
            }
            resp.addCookie(cu);
            resp.addCookie(cp);
            resp.addCookie(cr);
            
            resp.sendRedirect("index");
        } else {
            req.setAttribute("error", "Email or password wrong! Please try again");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }    
}
