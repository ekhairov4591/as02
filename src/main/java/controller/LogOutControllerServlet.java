package controller;
import utils.DBUtils;
import utils.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LogOutControllerServlet extends HttpServlet {

    private static String AUTHORIZE_FIELD = "is_authorize";
    private static String CURRENT_USER = "current_email";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        System.out.println("Received by the LogOut servlet...");


        // if session is not null
        if (session.getAttribute(LogOutControllerServlet.AUTHORIZE_FIELD) != null) {
            // if logging out
            if (req.getParameter("action") != null && req.getParameter("action").equals("/as02/out")) {

                // LOGOUT --> dropping values to default and redirecting to login page
                session.removeAttribute(LogOutControllerServlet.AUTHORIZE_FIELD);
                session.removeAttribute(LogOutControllerServlet.CURRENT_USER);
                resp.sendRedirect("login.jsp");

                // if no logout direct to authorized page
            } else {
                if ((Boolean) session.getAttribute(LogOutControllerServlet.AUTHORIZE_FIELD)) {
                    System.out.println("This user is authorized");
                    //TODO  Redirect to authorized page with logout button & other options
                    resp.sendRedirect("cabinet.jsp");

                } else {
                    resp.sendRedirect("login.jsp");
                }
            }
        } else {
            resp.sendRedirect("register.jsp");
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute(LogOutControllerServlet.AUTHORIZE_FIELD);
        session.removeAttribute(LogOutControllerServlet.CURRENT_USER);
        System.out.println("User Logged Out...Forwarding to Log In Page...");
        resp.sendRedirect("/as02/reg");
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }
}
