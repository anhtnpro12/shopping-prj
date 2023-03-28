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
import java.util.Comparator;
import model.Category;
import model.Product;

/**
 *
 * @author TNA
 */
public class SortNameServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDAO pdao = new ProductDAO();
        CategoryDAO cdao = new CategoryDAO();
        ArrayList<Product> list;
        ArrayList<Category> clist = cdao.getCategoryList();

        // filter list after searching
        String search = req.getParameter("search");

        if (search != null && !search.isEmpty()) {
            ArrayList<Product> temp = pdao.getListProduct();
            list = new ArrayList<>();
            for (Product p : temp) {
                if (p.getProductName().contains(search)) {
                    list.add(p);
                }
            }
        } else {
            list = pdao.getListProduct();
        }

        // image address handler
        for (Product pro : list) {
            String[] images = pro.getImages();
            for (int i = 0; i < images.length; i++) {
                images[i] = ".\\img\\UploadImgs\\" + pro.getUserID() + "\\" + images[i];
//                System.out.println(images[i]);
            }
            pro.setImages(String.join(";", images));
        }

        // Pagination
        int page, numPerPage = 16, size = list.size();
        String xpage = req.getParameter("page");
        if (xpage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(xpage);
        }
        int begin = numPerPage * (page - 1);
        int end = Math.min(numPerPage * page, size);

        ArrayList<Product> tempList = pdao.getListByPage(list, begin, end);
        tempList.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                String ty = req.getParameter("ty");
                if (ty == null || ty.isEmpty()) {
                    ty = "0";
                }
                if (ty.equals("0")) {
                    return o1.getProductName().compareTo(o2.getProductName());
                }

                return o2.getProductName().compareTo(o1.getProductName());
            }
        });

        String ty = req.getParameter("ty");
        if (ty == null || ty.isEmpty()) {
            ty = "0";
        }
        

        req.setAttribute("list", tempList);
        req.setAttribute("clist", clist);
        req.setAttribute("cdao", cdao);
        req.setAttribute("size", size % numPerPage == 0 ? size / numPerPage : (size / numPerPage) + 1);
        req.setAttribute("page", page);
        req.setAttribute("search", search);
        req.setAttribute("ty", ((Integer.parseInt(ty) + 1)%2) + "");

        req.getRequestDispatcher("shop.jsp").forward(req, resp);
    }

}
