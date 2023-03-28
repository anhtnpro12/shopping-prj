/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dal.CategoryDAO;
import dal.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Category;
import model.Product;

/**
 *
 * @author TNA
 */
public class AdminProductsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDAO pdao = new ProductDAO();
        ArrayList<Product> list = pdao.getListProduct();
        
        int page, numPerPage = 10, size = list.size();
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
        req.setAttribute("plist", pdao.getListByPage(list, begin, end));
        
        CategoryDAO cdao = new CategoryDAO();
        ArrayList<Category> clist = cdao.getCategoryList();
        req.setAttribute("cdao", cdao);
        req.setAttribute("clist", clist);        
        
        req.getRequestDispatcher("adminProducts.jsp").forward(req, resp);
    }
    
}
