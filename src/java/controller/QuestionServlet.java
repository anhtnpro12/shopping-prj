/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dal.QuestionDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Question;

/**
 *
 * @author TNA
 */
public class QuestionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ans = req.getParameter("ans");
        String listAns = req.getParameter("listAns");
        if (listAns == null) {
            listAns = "";
        }
        if (ans == null) {
            ans = "";
        }        
        listAns += ans;
        
        QuestionDAO qdao = new QuestionDAO();
        ArrayList<Question> list = qdao.getListQuestion();
        
        // Pagination
        int page = 0, numPerPage = 1, size = list.size();
        
        
        String xpage = req.getParameter("page");          
        
        if (xpage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(xpage);
        }        
        if (page > (size%numPerPage==0?size/numPerPage:(size/numPerPage)+1)) {
            int score = qdao.getScore(listAns);
            req.setAttribute("score", score);
            req.getRequestDispatcher("score.jsp").forward(req, resp);
        }
        int begin = numPerPage * (page - 1);
        int end = Math.min(numPerPage * page, size);
        
        req.setAttribute("size", size%numPerPage==0?size/numPerPage:(size/numPerPage)+1);
        req.setAttribute("page", page);
        req.setAttribute("list", qdao.getListByPage(list, begin, end));
        req.setAttribute("listAns", listAns);
        
        req.getRequestDispatcher("Question.jsp").forward(req, resp);
    }
    
}
