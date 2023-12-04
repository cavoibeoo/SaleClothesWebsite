package Controller.Customer;

import Service.CustomerService;
import Service.impl.CustomerServiceImpl;
import model.Cart;
import model.CustomerEntity;
import util.CookieUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@MultipartConfig
@WebServlet(urlPatterns = "/login", name = "LoginServlet")
public class LoginController extends HttpServlet {
    CustomerService customerService = new CustomerServiceImpl();
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("SQL");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

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
            url = go(request,response);
        }

        //      <--- Email Sending --->
        else if (action.equals("sendmessage")){
            sendMessage(request, response, url);
        }
        //      <--- Regist --->
        else if (action.equals("regist")){
            url = regist(request, response);
        }

        //      <--- Check login --->
        else if (action.equals("CheckUser")) {
            url = checkuser(request, response);
        }

        //      <--- Check Cookie --->
        else if (action.equals("CheckCookie")) {
            url = checkcookies(request, response);
        }
        //      <--- Logout --->
        else if (action.equals("logout")) {
            url = logout(request, response);
        }
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    protected String go (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String message = null;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String url = "/login.jsp";


        CustomerEntity cus1 = new CustomerEntity();
        cus1.setCustomerMail(username);
        cus1.setCustomerPwd(password);


        if(customerService.checklogin(username, password)){
            cus1 = customerService.findByEmail(username);
            // store the User object as a session attribute
            HttpSession session = request.getSession(true);
            session.setAttribute("user", cus1);
    
            // add a cookie that stores the user's email to browser
            Cookie c = new Cookie("emailCookie", username);
            c.setPath("/");
            c.setMaxAge(60 * 60 * 24 * 365 * 3); // set age to 2 years
            response.addCookie(c);
            url = "/Home.jsp";
        }
        else
            message = "Invalid Login Information!";
        
        request.setAttribute("message", message);
        return url;
    }
    
    protected void sendMessage (HttpServletRequest request, HttpServletResponse response, String url)
            throws ServletException, IOException{
        String useremail = request.getParameter("email");
        String messages = request.getParameter("msg");
        url = "/Home.jsp";
    }

    protected String regist (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String registfirstname = request.getParameter("fname");
        String registlastname = request.getParameter("lname");
        String registusername = request.getParameter("email");
        String registpassword = request.getParameter("password");
        String url;
        try{
            transaction.begin();
            CustomerEntity cus = new CustomerEntity();
            cus.setCustomerFName(registfirstname);
            cus.setCustomerLName(registlastname);
            cus.setCustomerMail(registusername);
            cus.setCustomerPwd(registpassword);
            entityManager.persist(cus);

            Cart cart = new Cart();
            cart.setCustomer(cus);
            entityManager.persist(cart);

            // store the User object as a session attribute
            HttpSession session = request.getSession();
            session.setAttribute("user", cus);
            // add a cookie that stores the user's email to browser
            Cookie c = new Cookie("emailCookie", registusername);
            c.setMaxAge(60 * 60 * 24 * 365 * 3); // set age to 2 years
            response.addCookie(c);
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
                url = "/register.jsp";
            }
            else
            {
                url = "/Home.jsp";
            }
            entityManager.close();
            entityManagerFactory.close();
        }
        return url;
    }
    
    private String checkuser (HttpServletRequest request,
                              HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        CustomerEntity account = (CustomerEntity) session.getAttribute("user");
        String url;
        // if User object doesn't exist, check email cookie
        if (account == null) {
            Cookie[] cookies = request.getCookies();
            String emailAddress = CookieUtil.getCookieValue(cookies, "emailCookie");
            // if cookie doesn't exist, go to Registration page
            if (emailAddress == null || emailAddress.equals("")) {
                url = "/login.jsp";
            }
            // if cookie exists, create User object and go to Downloads page
            else {
                account = customerService.findByEmail(emailAddress);
                session.setAttribute("user", account);
                url = "/customer-account.jsp";
            }
        }
        
        else {
            url = "/customer-account.jsp";
        }
        return url;
    }
    
    private String checkcookies (HttpServletRequest request,
                                HttpServletResponse response) {
        HttpSession session = request.getSession();
        CustomerEntity user = (CustomerEntity) session.getAttribute("user");
        String url;
        if (user == null) {
            Cookie[] cookies = request.getCookies();
            String emailAddress = CookieUtil.getCookieValue(cookies, "emailCookie");
            if (emailAddress == null || emailAddress.equals("")) {
                url = "/login.jsp";
            }
            else {
                user = (CustomerEntity) session.getAttribute("user");
                session.setAttribute("user", user);
                url = "/cart?";
            }
        }
        else {
            url = "/cart?";
        }
        return url;
    }

    private String logout (HttpServletRequest request,
                                 HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            cookie.setMaxAge(0); //delete the cookie
            cookie.setPath("/"); //allow the download application to access it
            response.addCookie(cookie);
        }
        HttpSession session = request.getSession(false);
        if (session != null)
            session.invalidate();
        String url = "/Home.jsp";
        return url;
    }


    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        CustomerEntity user = (CustomerEntity) session.getAttribute("user");

        if (user != null || session != null) {
            request.setAttribute("isLoggedIn", true);
        } else {
            request.setAttribute("isLoggedIn", false);
        }
        doPost(request, response);
    }
}


