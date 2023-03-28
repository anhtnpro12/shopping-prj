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
public class SproductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String proID = req.getParameter("proID");
        
        ProductDAO pdao = new ProductDAO();
        CategoryDAO cdao = new CategoryDAO();
        ArrayList<Category> clist = cdao.getCategoryList(); 
        
        // image link handler
        Product pro = pdao.getProductByID(proID);
        String[] images = pro.getImages();
        for (int i = 0; i < images.length; i++) {
            images[i] = ".\\img\\UploadImgs\\" + pro.getUserID() + "\\" + images[i]; 
        }
        pro.setImages(String.join(";", images));
        
        // image link handler
        ArrayList<Product> newList = pdao.getListNewProduct();
        for (Product pr : newList) {
            images = pr.getImages();
            for (int i = 0; i < images.length; i++) {
                images[i] = ".\\img\\UploadImgs\\" + pr.getUserID() + "\\" + images[i]; 
//                System.out.println(images[i]);
            }                
            pr.setImages(String.join(";", images)); 
        }
        
        int count = pro.getViews() + 1;
        
        pdao.updateProduct(pro.getProductID(), count);
        
        req.setAttribute("count", count + "");
        req.setAttribute("product", pro);   
        req.setAttribute("cate", cdao.getCategoryByID(clist, pro.getCategoryID()).getCategoryName());        
        req.setAttribute("list", pdao.getListByPage(newList, 0, 4));
        
        req.getRequestDispatcher("sproduct.jsp").forward(req, resp);
    }
    
}
