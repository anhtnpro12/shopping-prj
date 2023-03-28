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
import model.Category;

/**
 *
 * @author TNA
 */
public class AdminEditCateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String desc = req.getParameter("desc");
        String id = req.getParameter("id");
        
        CategoryDAO cdao = new CategoryDAO();
        cdao.updateCategory(id, name, desc);
        
        resp.sendRedirect("admin-editcate?id="+id);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        
        CategoryDAO cdao = new CategoryDAO();        
        
        req.setAttribute("cate", cdao.getCategoryByID(cdao.getCategoryList(), Integer.parseInt(id)));
        
        req.getRequestDispatcher("adminEditCate.jsp").forward(req, resp);
    }
    
}
