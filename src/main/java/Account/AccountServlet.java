package Account;

import entity.CustomerEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/accountservlet", name = "AccountServlet")
public class AccountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get current action
        String action = request.getParameter("action");
        if (action == null) {
            action = "join";
        }
        String url = "/editcustomerin4.jsp";
        if (action.equals("join")) {
            url = "/editcustomerin4.jsp";

        } else if (action.equals("edit")) {
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
            }
            else {
                message = null;
                url = "/CustomerAccount.jsp";
            }
            request.setAttribute("message", message);
        }
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
}
