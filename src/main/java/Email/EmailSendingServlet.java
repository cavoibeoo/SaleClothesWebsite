package Email;

import javax.mail.MessagingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/email", name = "EmailSendingServlet")
public class EmailSendingServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        getServletContext()
                .getRequestDispatcher("Home.jsp")
                .forward(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
