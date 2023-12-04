package Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@MultipartConfig
@WebServlet(urlPatterns = {"/admin-login"})
public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (Objects.equals(username, "admin") && Objects.equals(password, "1234")) {
            HttpSession session = request.getSession();
            session.setAttribute("admin",username);
            request.getRequestDispatcher("/views/admin/index.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Error: Username or Password is incorrect!");
            request.getRequestDispatcher("/views/admin/login.jsp").forward(request, response);
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            request.getRequestDispatcher("/views/admin/login.jsp").forward(request,response);
    }
}
