package controller;

import sun.java2d.loops.GeneralRenderer;
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

public class LogControllerServlet extends HttpServlet {

    private static String AUTHORIZE_FIELD = "is_authorize";
    private static String CURRENT_USER = "current_email";

    public static String getCurrentUser() {
        return CURRENT_USER;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        System.out.println("Received by the Log servlet...");

        // if session is not null
        if (session.getAttribute(LogControllerServlet.AUTHORIZE_FIELD) != null) {
            // if logging out
            if (req.getParameter("action") != null && req.getParameter("action").equals("logout")) {
                // LOGOUT --> dropping values to default and redirecting to login page
                session.removeAttribute(LogControllerServlet.AUTHORIZE_FIELD);
                session.removeAttribute(LogControllerServlet.CURRENT_USER);
                resp.sendRedirect("login.jsp");

                // if no logout direct to authorized page
            } else {
                if ((Boolean) session.getAttribute(LogControllerServlet.AUTHORIZE_FIELD)) {
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
        String password = req.getParameter("password");
        User user = new User(email, password);
        User fetchedUser;

        DBUtils dbUtils = new DBUtils();

        if(dbUtils.validate(user)){
            HttpSession session = req.getSession();
            session.setAttribute("email", email);
            session.setAttribute("password", password);
            EnrolledCourses fetchedCourses;
            // retrieving
            fetchedUser = dbUtils.retrieveUser(user);
            fetchedCourses = dbUtils.retrieveActiveCourses(user);

            if(fetchedUser != null){
                session.setAttribute("name", fetchedUser.getName());
                session.setAttribute("surname", fetchedUser.getSurname());
                session.setAttribute("age", fetchedUser.getAge());
                session.setAttribute("country", fetchedUser.getCountry());
                session.setAttribute("city", fetchedUser.getCity());
                session.setAttribute("gender", fetchedUser.getGender());
                session.setAttribute(LogControllerServlet.AUTHORIZE_FIELD, true);
                session.setAttribute(LogControllerServlet.CURRENT_USER, email);
            } else {
                System.out.println("Failed to fetch...");
            }
            if(fetchedCourses != null){
                session.setAttribute("course_id", fetchedCourses.getCourse_id());
                session.setAttribute("course_name", fetchedCourses.getCourse_name());
            } else {
                System.out.println("Failed to fetch courses");
            }


            System.out.println("User found & passed authentication. Session set...Forwarding to Cabinet...");
           resp.sendRedirect("/as02/cab");
        } else {
            System.out.println("User not found...Forwarding to Log In Page...");
            resp.sendRedirect("/as02/log");
        }
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
