package login;

import entity.CustomeraccountEntity;
import util.CookieUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

import java.math.BigDecimal;
import java.sql.*;

@WebServlet(urlPatterns = "/login", name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {


        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        String url = "/login.jsp";

        String action = request.getParameter("action");
        if (action == null) {
            action = "stay";  // default action
        }
        if (action.equals("stay")){
            url = "/login.jsp";
        }
        else if (action.equals("go")){
            String message = null;
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            url = "/login.jsp";
            try{
                transaction.begin();
                CustomeraccountEntity cus1 = new CustomeraccountEntity();
                cus1.setMail(username);
                cus1.setPwd(password);
                if(cus1.CheckLogin())
                    url = "/Home.jsp";
                else
                    message = "Invalid Login Information!";

                // store the User object as a session attribute
                HttpSession session = request.getSession(true);
                session.setAttribute("user", cus1);

                // add a cookie that stores the user's email to browser
                Cookie c = new Cookie("userEmail", username);
                c.setMaxAge(60 * 60 * 24 * 365 * 3); // set age to 2 years
                response.addCookie(c);

               transaction.commit();
                } finally {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
                entityManager.close();
                entityManagerFactory.close();
            }
            request.setAttribute("message", message);

        }
        else if (action.equals("regist")){
            String registusername = request.getParameter("email");
            String registpassword = request.getParameter("password");
            url = "/Home.jsp";
            try{
                transaction.begin();
                CustomeraccountEntity cus1 = new CustomeraccountEntity();
                BigDecimal bd = new BigDecimal(0.00);
                cus1.setMail(registusername);
                cus1.setPwd(registpassword);
                cus1.setTotalPayment(bd);
                entityManager.persist(cus1);

                // store the User object as a session attribute
                HttpSession session = request.getSession();
                session.setAttribute("user", cus1);

                // add a cookie that stores the user's email to browser
                Cookie c = new Cookie("userEmail", registusername);
                c.setMaxAge(60 * 60 * 24 * 365 * 3); // set age to 2 years
                response.addCookie(c);

                transaction.commit();
            } finally {
                if (transaction.isActive()) {
                    transaction.rollback();
                    url = "/register.jsp";
                }
                entityManager.close();
                entityManagerFactory.close();
            }
        }
        else if (action.equals("CheckUser")) {
            url = CheckUser(request, response);
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    private String CheckUser (HttpServletRequest request,
                             HttpServletResponse response) {
        HttpSession session = request.getSession();
        CustomeraccountEntity user = (CustomeraccountEntity) session.getAttribute("user");
        String url;

        // if User object doesn't exist, check email cookie
        if (user == null) {
            Cookie[] cookies = request.getCookies();
            String emailAddress = CookieUtil.getCookieValue(cookies, "emailCookie");

            // if cookie doesn't exist, go to Registration page
            if (emailAddress == null || emailAddress.equals("")) {
                url = "/login.jsp";
            }
            // if cookie exists, create User object and go to Downloads page
            else {
                user = (CustomeraccountEntity) session.getAttribute("user");
                session.setAttribute("user", user);
                url = "/CustomerAccount.jsp";
            }
        }
        // if User object exists, go to Downloads page
        else {
            url = "/CustomerAccount.jsp";
        }
        return url;
    }
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}

