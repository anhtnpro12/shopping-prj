/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dal.CategoryDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Category;

/**
 *
 * @author TNA
 */
public class AdminCateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String desc = req.getParameter("desc");
        
        CategoryDAO cdao = new CategoryDAO();
        cdao.insertCategory(name, desc);
        
        resp.sendRedirect("admin-cates");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        CategoryDAO cdao = new CategoryDAO();
        ArrayList<Category> list = cdao.getCategoryList();
        
        int page, numPerPage = 5, size = list.size();
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
        req.setAttribute("list", cdao.getListByPage(list, begin, end));
        
        req.getRequestDispatcher("adminCates.jsp").forward(req, resp);
    }
    
}
