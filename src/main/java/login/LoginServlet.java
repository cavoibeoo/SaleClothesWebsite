package login;

import entity.CustomerEntity;
import entity.CustomeraccountEntity;
import util.CookieUtil;

import javax.mail.MessagingException;
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


        //      <--- Login --->
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

        //      <--- Email Sending --->
        else if (action.equals("sendmessage")){
            String useremail = request.getParameter("email");
            String messages = request.getParameter("msg");
            // send email to user
            String to = useremail;
            String from = "email_list@murach.com";
            String subject = "Coza Shop had received your sending!";
            String body = "Dear " + useremail + ",\n\n"
                    + "Welcome to the world of Coza,\n"
                    + "I'm assistant Sine,\n\n"
                    + "Thank you for always trusting and supporting Cozashop during the past time. Through this email Sine has confirmed receipt of your request with reference code CS-221805.\n" +
                    "While waiting for a response, don't forget to experience shopping at Coza with many incentives\n"
                    + "Coza hopes you will continue to have wonderful and easy experiences with the shop!\n\n"
                    + "No matter where you are, far away in the horizon or right in front of your eyes, Sine is always by your side, so if you have any questions, don't hesitate to send Sine's assistant an email to chat@coza. .vn or call now 0355305190 (excluding international subscribers).\n" +
                    "Assistant Sine thanks you for always accompanying Coza!\n\n"
                    + "Admin: Duc Quang\n"
                    + "Admin 2: Tran Phuoc Binh\n"
                    + "Here are your question:\n\n"
                    + "------------------------------------------\n"
                    + messages;
            boolean isBodyHTML = false;
            try {
                util.MailUtilGmail.sendMail(to, from, subject, body,
                        isBodyHTML);
            } catch (MessagingException e) {
                String errorMessage
                        = "ERROR: Unable to send email. "
                        + "Check Tomcat logs for details.<br>"
                        + "NOTE: You may need to configure your system "
                        + "as described in chapter 14.<br>"
                        + "ERROR MESSAGE: " + e.getMessage();
                request.setAttribute("errorMessage", errorMessage);
                this.log(
                        "Unable to send email. \n"
                                + "Here is the email you tried to send: \n"
                                + "=====================================\n"
                                + "TO: " + useremail + "\n"
                                + "FROM: " + from + "\n"
                                + "SUBJECT: " + subject + "\n\n"
                                + body + "\n\n");
            }
            url = "/Home.jsp";
        }

        //      <--- Regist --->
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
                // send email to user
                String to = registusername;
                String from = "email_list@murach.com";
                String subject = "So happy that you're become Coza Member!";
                String body = "Dear " + registusername + ",\n\n"
                        + "Thanks for joining Coza familly. "
                        + "We'll make sure to send "
                        + "you announcements about new products "
                        + "and promotions.\n"
                        + "Have a great day and thanks again!\n\n"
                        + "Admin: Duc Quang\n"
                        + "Admin 2: Tran Phuoc Binh";
                boolean isBodyHTML = false;
                try {
                    util.MailUtilGmail.sendMail(to, from, subject, body,
                            isBodyHTML);
                } catch (MessagingException e) {
                    String errorMessage
                            = "ERROR: Unable to send email. "
                            + "Check Tomcat logs for details.<br>"
                            + "NOTE: You may need to configure your system "
                            + "as described in chapter 14.<br>"
                            + "ERROR MESSAGE: " + e.getMessage();
                    request.setAttribute("errorMessage", errorMessage);
                    this.log(
                            "Unable to send email. \n"
                                    + "Here is the email you tried to send: \n"
                                    + "=====================================\n"
                                    + "TO: " + registusername + "\n"
                                    + "FROM: " + from + "\n"
                                    + "SUBJECT: " + subject + "\n\n"
                                    + body + "\n\n");
                }
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

        //      <--- Check login --->
        else if (action.equals("CheckUser")) {
            url = CheckUser(request, response);
        }

        //      <--- Check Cookie --->
        else if (action.equals("CheckCookie")) {
            url = CheckCookie(request, response);
        }

        //      <--- Logout --->
        else if (action.equals("logout")) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            url = "/Home.jsp";
        }

        //Edit Customer information
        else if (action.equals("edit")) {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String address = request.getParameter("address");
            String phonenumber = request.getParameter("phonenumber");

            HttpSession session = request.getSession();
            CustomerEntity user = (CustomerEntity) session.getAttribute("user");
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPhoneNumber(phonenumber);
            user.setAddress(address);
            request.setAttribute("user", user);

            String message;
            if (firstName == null || lastName == null || address == null || phonenumber == null ||
                    firstName.isEmpty() || lastName.isEmpty() || address.isEmpty() || phonenumber.isEmpty()) {
                message = "Please fill out all three text boxes.";
            } else {
                message = null;
                url = "/CustomerAccount.jsp";
            }
            request.setAttribute("message", message);
        }
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    private String CheckUser (HttpServletRequest request,
                             HttpServletResponse response) {
        HttpSession session = request.getSession();
        CustomeraccountEntity user = (CustomeraccountEntity) session.getAttribute("user");
        CustomerEntity userinfo = (CustomerEntity) session.getAttribute("userinfo");

        String emailUser = user.getMail();
        String firstname = userinfo.getFirstName();
        String lastname = userinfo.getLastName();
        String address = userinfo.getAddress();
        String phonenum = userinfo.getPhoneNumber();
        session.setAttribute("emailuser", emailUser);
        session.setAttribute("firstname", firstname);
        session.setAttribute("lastname", lastname);
        session.setAttribute("address", address);
        session.setAttribute("phonenum", phonenum);

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
                String emailuser = user.getMail();
                session.setAttribute("user", emailAddress);
                session.setAttribute("emailuser", emailAddress);
                url = "order?";
            }
        }
        // if User object exists, go to Downloads page
        else {
            url = "/order?";
        }
        request.setAttribute("emailuser", emailUser);
        request.setAttribute("firstname", firstname);
        request.setAttribute("lastname", lastname);
        request.setAttribute("address", address);
        request.setAttribute("phonenum", phonenum);
        return url;
    }

    private String CheckCookie (HttpServletRequest request,
                              HttpServletResponse response) {
        HttpSession session = request.getSession();
        CustomeraccountEntity user = (CustomeraccountEntity) session.getAttribute("user");
        String url;
        if (user == null) {
            Cookie[] cookies = request.getCookies();
            String emailAddress = CookieUtil.getCookieValue(cookies, "emailCookie");
            if (emailAddress == null || emailAddress.equals("")) {
                url = "/login.jsp";
            }
            else {
                user = (CustomeraccountEntity) session.getAttribute("user");
                session.setAttribute("user", user);
                url = "/cart?";
            }
        }
        else {
            url = "/cart?";
        }
        return url;
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        CustomeraccountEntity user = (CustomeraccountEntity) session.getAttribute("user");

        if (user != null ) {
            request.setAttribute("isLoggedIn", true);
        }
        doPost(request, response);
    }
}

