
package controller;

import dal.AccountDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String pass = req.getParameter("pass");
        String repass = req.getParameter("repass");                
        req.setAttribute("name", name);
        req.setAttribute("email", email);
        req.setAttribute("phone", phone);
        req.setAttribute("pass", pass);
        req.setAttribute("repass", repass);
        AccountDAO adao = new AccountDAO();
        if (adao.checkEmailExist(email)) {
            req.setAttribute("error2", "Email is exist!");
            req.setAttribute("active", "active");
            req.getRequestDispatcher("login").forward(req, resp);
        }
        if (adao.checkPhoneExist(phone)) {
            req.setAttribute("error2", "Phone is exist!");
            req.setAttribute("active", "active");
            req.getRequestDispatcher("login").forward(req, resp);
        }
        if (pass.equals(repass) == false) {
            req.setAttribute("error2", "Password not equal repassword");
            req.setAttribute("active", "active");            
            req.getRequestDispatcher("login").forward(req, resp);
        }
        
        adao.register(name, email, phone, pass);        
        req.setAttribute("mess", "Signup successful! <a href='login'>Login Now</a>");
        req.getRequestDispatcher("showMessage.jsp").forward(req, resp);
    }   
}
