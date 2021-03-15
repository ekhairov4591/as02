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

public class RegControllerServlet extends HttpServlet {

    private static String AUTHORIZE_FIELD = "is_authorize";
    private static String CURRENT_USER = "current_email";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        System.out.println("Received by the Reg servlet...");


        // if session is not null
        if (session.getAttribute(RegControllerServlet.AUTHORIZE_FIELD) != null) {
            // if logging out
            if (req.getParameter("action") != null && req.getParameter("action").equals("logout")) {
                // LOGOUT --> dropping values to default and redirecting to login page
                session.removeAttribute(RegControllerServlet.AUTHORIZE_FIELD);
                session.removeAttribute(RegControllerServlet.CURRENT_USER);
                resp.sendRedirect("login.jsp");

                // if no logout direct to authorized page
            } else {
                if ((Boolean) session.getAttribute(RegControllerServlet.AUTHORIZE_FIELD)) {
                    System.out.println("This user is authorized");
                    //TODO  Redirect to authorized page with logout button & other options
                    resp.sendRedirect("cabinet.jsp");

                } else {
                    resp.sendRedirect("login.jsp");
                }
            }
        } else {
            resp.sendRedirect("login.jsp");
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        int age = Integer.parseInt(req.getParameter("age"));
        String country = req.getParameter("country");
        String city = req.getParameter("city");
        String gender = req.getParameter("gender");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");

        System.out.println("Fetched New User Info in Reg Controller...");

        User user = new User(email, password, country, city, gender, name, surname, age);
        DBUtils dbUtils = new DBUtils();
        String result = dbUtils.insert(user);
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
