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
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import model.Category;
import model.Product;

/**
 *
 * @author TNA
 */
public class IndexServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {                
        
        ProductDAO pdao = new ProductDAO();
        CategoryDAO cdao = new CategoryDAO();
        ArrayList<Category> clist = cdao.getCategoryList();
        
        
        // handle image link of new list
        ArrayList<Product> viewList = pdao.getListMostViewProduct();
        for (Product pr : viewList) {
            String[] images = pr.getImages();
            for (int i = 0; i < images.length; i++) {
                images[i] = ".\\img\\UploadImgs\\" + pr.getUserID() + "\\" + images[i]; 
//                System.out.println(images[i]);
            }                
            pr.setImages(String.join(";", images)); 
        }
        
        // handle image link of new list
        ArrayList<Product> newList = pdao.getListNewProduct();
        for (Product pr : newList) {
            String[] images = pr.getImages();
            for (int i = 0; i < images.length; i++) {
                images[i] = ".\\img\\UploadImgs\\" + pr.getUserID() + "\\" + images[i]; 
//                System.out.println(images[i]);
            }                
            pr.setImages(String.join(";", images)); 
        }
        
        // handle image link of new list
        ArrayList<Product> featuredList = pdao.getListFeaturedProduct();
        for (Product pr : featuredList) {
            String[] images = pr.getImages();
            for (int i = 0; i < images.length; i++) {
                images[i] = ".\\img\\UploadImgs\\" + pr.getUserID() + "\\" + images[i]; 
//                System.out.println(images[i]);
            }                
            pr.setImages(String.join(";", images)); 
        }
        
        req.setAttribute("newList", pdao.getListByPage(newList, 0, 8));
        req.setAttribute("viewList", pdao.getListByPage(viewList, 0, 5));
        req.setAttribute("featuredList", pdao.getListByPage(featuredList, 0, 8));        
        req.setAttribute("cdao", cdao);
        req.setAttribute("clist", clist);
        
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
    
}
