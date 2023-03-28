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
import java.util.ArrayList;
import model.Account;
import model.Category;
import model.Product;

/**
 *
 * @author TNA
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, //50MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class AdminEditProServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account acc = (Account)session.getAttribute("account");        
        if (acc == null) {
            req.setAttribute("mess", "Please <a href='login.jsp'>login</a> to access!!");
            req.getRequestDispatcher("showMessage.jsp").forward(req, resp);
        }
        
        // upload file to server
        String files = "";
        for (Part part : req.getParts()) {
            if (part.getName().equals("file")) {
                String fileName = extractFileName(part);
                fileName = new File(fileName).getName(); 
                if (fileName == null || fileName.isEmpty()) {
                    continue;
                }
                files += (files.length()>0?";":"") + fileName;                
                System.out.println(this.getFolderUpload(acc.getUser().getUserID()).getAbsolutePath() + File.separator + fileName);
                System.out.println(this.getFolderUpload2(acc.getUser().getUserID()).getAbsolutePath() + File.separator + fileName);
                part.write(this.getFolderUpload(acc.getUser().getUserID()).getAbsolutePath() + File.separator + fileName);                
                part.write(this.getFolderUpload2(acc.getUser().getUserID()).getAbsolutePath() + File.separator + fileName);                
            }
        } 
        
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String cate = req.getParameter("cate");
        String qpu = req.getParameter("qpu");
        String unitpr = req.getParameter("unitpr");
        String uis = req.getParameter("uis");
        String conti = req.getParameter("conti");
        String desc = req.getParameter("desc");
        String type = req.getParameter("type");
        if (files.isEmpty()) {
            files = req.getParameter("imgs");
        }
        String sellquan = req.getParameter("sellquan");   
        Product pro = new Product(Integer.parseInt(id), name, Integer.parseInt(cate)
                , qpu, Double.parseDouble(unitpr), Integer.parseInt(uis)
                , conti.equals("0"), acc.getUser().getUserID(), desc, type, files, Integer.parseInt(sellquan));
        
        ProductDAO pdao = new ProductDAO();
        pdao.updateProduct(pro);
        
        resp.sendRedirect("admin-editpro?id="+id);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        ProductDAO pdao = new ProductDAO();
        CategoryDAO cdao = new CategoryDAO();
        Product pro = pdao.getProductByID(id);
        ArrayList<Category> clist = cdao.getCategoryList();
        
        req.setAttribute("p", pro);        
        req.setAttribute("clist", clist); 
        req.setAttribute("thisc", cdao.getCategoryByID(clist, pro.getCategoryID()).getCategoryID());
        
        req.getRequestDispatcher("adminEditPro.jsp").forward(req, resp);
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
    
}
