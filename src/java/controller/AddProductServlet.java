/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dal.CategoryDAO;
import dal.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import model.Account;
import model.Product;

/**
 *
 * @author TNA
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, //50MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class AddProductServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account acc = (Account)session.getAttribute("account");
        if (acc == null) {
            request.setAttribute("mess", "Please <a href='login'>login</a> to access!!");
            request.getRequestDispatcher("showMessage.jsp").forward(request, response);
        }
        
        // upload file to server
        String files = "";
        for (Part part : request.getParts()) {
            if (part.getName().equals("file")) {
                String fileName = extractFileName(part);
                fileName = new File(fileName).getName();                                            
                files += (files.length()>0?";":"") + fileName;
                System.out.println(this.getFolderUpload(acc.getUser().getUserID()).getAbsolutePath() + File.separator + fileName);
                System.out.println(this.getFolderUpload2(acc.getUser().getUserID()).getAbsolutePath() + File.separator + fileName);
                part.write(this.getFolderUpload(acc.getUser().getUserID()).getAbsolutePath() + File.separator + fileName);                
                part.write(this.getFolderUpload2(acc.getUser().getUserID()).getAbsolutePath() + File.separator + fileName);                
            }
        }                
        
        String name = request.getParameter("name");
        String cost = request.getParameter("cost");
        String[] types = request.getParameterValues("type");
        String type = "";
        for (String type1 : types) {
            if (!type1.isEmpty()) {
                type += (type.length()>0?";":"") + type1;
            }
        }
        String category = request.getParameter("category");
        String quanPerUnit = request.getParameter("quanPerUnit");
        String unitInStock = request.getParameter("unitInStock");
        String description = request.getParameter("description");
        Product pro = new Product(0, name, Integer.parseInt(category), quanPerUnit, Double.parseDouble(cost), 
                Integer.parseInt(unitInStock), false, acc.getUser().getUserID(), description, 
                type, files, 0);
        ProductDAO pdao = new ProductDAO();
        pdao.insertProduct(pro);
        
        response.sendRedirect("shop");
    }
    
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

    private File getFolderUpload(int userID) {        
        String basePath = getServletContext().getRealPath(File.separator) + "\\..\\..\\web\\img\\UploadImgs\\" + userID;
//        System.out.println(basePath);
        File folderUpload = new File(basePath);
        if (!folderUpload.exists()) {
            folderUpload.mkdirs();
        }
        return folderUpload;
    }
    
    private File getFolderUpload2(int userID) {        
        String basePath = getServletContext().getRealPath(File.separator) + "\\img\\UploadImgs\\" + userID;
//        System.out.println(basePath);
        File folderUpload = new File(basePath);
        if (!folderUpload.exists()) {
            folderUpload.mkdirs();
        }
        return folderUpload;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        // check author
        HttpSession session = req.getSession();
        if (session.getAttribute("account") == null) {
            req.setAttribute("mess", "Please <a href='login.jsp'>login</a> to access!!");
            req.getRequestDispatcher("showMessage.jsp").forward(req, resp);
        }
        
        CategoryDAO cdao = new CategoryDAO();
        req.setAttribute("cateList", cdao.getCategoryList());
        
        req.getRequestDispatcher("addProduct.jsp").forward(req, resp);
    }
    
}
