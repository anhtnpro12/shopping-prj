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
import java.util.ArrayList;
import model.Account;

/**
 *
 * @author TNA
 */
public class AdminUsersServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {        
        AccountDAO adao = new AccountDAO();
        ArrayList<Account> list = adao.getAccountList();        
        
        int page, numPerPage = 2, size = list.size();
        String xpage = req.getParameter("page");
        if (xpage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(xpage);
        }
        int begin = numPerPage * (page - 1);
        int end = Math.min(numPerPage * page, size); 
        
        req.setAttribute("size", size%numPerPage==0?size/numPerPage:(size/numPerPage)+1);
        req.setAttribute("page", page);
        req.setAttribute("alist", adao.getListByPage(list, begin, end));
        
        req.getRequestDispatcher("adminUsers.jsp").forward(req, resp);
    }
    
}
