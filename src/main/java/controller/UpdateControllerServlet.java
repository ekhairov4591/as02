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

public class UpdateControllerServlet extends HttpServlet {

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
        if (session.getAttribute(UpdateControllerServlet.AUTHORIZE_FIELD) != null) {
            // if logging out
            if (req.getParameter("action") != null && req.getParameter("action").equals("logout")) {
                // LOGOUT --> dropping values to default and redirecting to login page
                session.removeAttribute(UpdateControllerServlet.AUTHORIZE_FIELD);
                session.removeAttribute(UpdateControllerServlet.CURRENT_USER);
                resp.sendRedirect("login.jsp");

                // if no logout direct to authorized page
            } else {
                if ((Boolean) session.getAttribute(UpdateControllerServlet.AUTHORIZE_FIELD)) {
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
        // Same as registering but I just update existing record
        String email = (String) session.getAttribute("email");
        String password = (String) session.getAttribute("password");

        int age = Integer.parseInt(req.getParameter("update_age"));
        String gender = req.getParameter("update_gender");
        String country = req.getParameter("update_country");
        String city = req.getParameter("update_city");
        String name = req.getParameter("update_name");
        String surname = req.getParameter("update_surname");
        System.out.println("Fetched updated personal info from cabinet in Update Servlet");

        User user = new User(email, password, country,gender, city, name, surname, age);
        DBUtils dbUtils = new DBUtils();
        if(dbUtils.update(user)){
        System.out.println("Updated tables in Update Servlet");


        session.setAttribute("name", user.getName());
        session.setAttribute("surname", user.getSurname());
        session.setAttribute("age", user.getAge());
        session.setAttribute("country", user.getCountry());
        session.setAttribute("city", user.getCity());
        session.setAttribute("gender", user.getGender());
        System.out.println("Updated session page & redirected");
        resp.sendRedirect("/as02/cab");
        } else {
            System.out.println("Failed to update in Update Servlet");
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
