<%@page language="java" contentType="text/html; charset-ISO-8859-1"
        pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="controller.LogControllerServlet" %>
<%@ page import="utils.DBUtils" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>

<html>
<head>
    <link rel="stylesheet" href="CSS/register.css">
    <link rel="stylesheet" href="CSS/table.css">
    <meta http-equiv="Content-Type" content="text/html; charset-ISO-8859-1">
    <title>Login WMII</title>
</head>

<body>

<div id="container">

    <div id="title">
        <h2>Cabinet of ${sessionScope['name']} ${sessionScope['surname']} </h2>
    </div>

    <h3><b>Explore your cabinet...</b></h3>

    <div class="tableTitle"><h2>Personal Information</h2></div>
    <hr>
    <div>
        <table>
            <tr>
                <th>Email</th>
                <th>Name</th>
                <th>Surname</th>
                <th>Age</th>
                <th>Gender</th>
                <th>Country</th>
                <th>City</th>
            </tr>
            <tr>
                <td><b>${sessionScope['email']}</b></td>
                <td><b>${sessionScope['name']}</b></td>
                <td><b>${sessionScope['surname']}</b></td>
                <td><b>${sessionScope['age']}</b></td>
                <td><b>${sessionScope['gender']}</b></td>
                <td><b>${sessionScope['country']}</b></td>
                <td><b>${sessionScope['city']}</b></td>
            </tr>
        </table>
    </div>
    <hr>
    <hr>


    <div class="tableTitle">
        <h2>Available Courses</h2>
    </div>
    <form action="/as02/cab" method="post">
        <div>
            <table>
                <tr>
                    <th>Course Name</th>
                    <th>Course ID</th>
                    <th>Register</th>
                </tr>
                <%
                    try {
                        DBUtils dbUtils = new DBUtils();
                        Connection connection = dbUtils.connExternal();
                        String sql = "select * from courses";
                        try {
                            PreparedStatement preparedStatement = connection.prepareStatement(sql);
                            ResultSet resultSet = preparedStatement.executeQuery();

                            while (resultSet.next()) { %>
                <tr>
                    <td><b><%=resultSet.getString("course_name")%>
                    </b></td>
                    <td><b><%=resultSet.getString("course_id")%>
                    </b></td>
                    <td><input type="submit" class="takeCourse" value="Register"></td>
                </tr>
                <%
                    }
                %>
            </table>
            <%
                        resultSet.close();
                        preparedStatement.close();
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            %>

        </div>
    </form>



    <div class="tableTitle">
        <h2>Active Courses</h2>
    </div>
    <div>
        <table>
            <tr>
                <th>Email</th>
                <th>Course ID</th>
                <th>Course Name</th>
            </tr>
            <tr>
                <td><b>${sessionScope['email']}</b></td>
                <td><b>${sessionScope['course_id']}</b></td>
                <td><b>${sessionScope['course_name']}</b></td>
            </tr>
        </table>
    </div>

</div>
<hr>
<hr>
<form action="/as02/out" method="post">
<input type="submit" class="registerbtn" value="Sign Out">
</form>

</body>

</html>