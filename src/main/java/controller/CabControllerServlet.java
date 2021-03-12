package controller;

import sun.rmi.runtime.Log;
import utils.DBUtils;
import utils.EnrolledCourses;
import utils.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class CabControllerServlet extends HttpServlet {

    private static String AUTHORIZE_FIELD = "is_authorize";
    private static String CURRENT_USER = "current_email";

    public static String getCurrentUser() {
        return CURRENT_USER;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        System.out.println("Received by the Cab servlet...");

        // if session is not null
        if (session.getAttribute(CabControllerServlet.AUTHORIZE_FIELD) != null) {
            // if logging out
            if (req.getParameter("action") != null && req.getParameter("action").equals("logout")) {
                // LOGOUT --> dropping values to default and redirecting to login page
                session.removeAttribute(CabControllerServlet.AUTHORIZE_FIELD);
                session.removeAttribute(CabControllerServlet.CURRENT_USER);
                resp.sendRedirect("login.jsp");

                // if no logout direct to authorized page
            } else {
                if ((Boolean) session.getAttribute(CabControllerServlet.AUTHORIZE_FIELD)) {
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
        String email = req.getParameter("email");
        //int course_id = Integer.parseInt(req.getParameter("course_id"));
        //String course_name = req.getParameter("course_name");


        EnrolledCourses enrolledCourses = new EnrolledCourses(email);
        DBUtils dbUtils = new DBUtils();


        resp.sendRedirect("/as02/log");

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
