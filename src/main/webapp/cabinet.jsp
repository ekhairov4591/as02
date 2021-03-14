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

    <div class="tableTitle"><h2>Edit Personal Information</h2></div>

    <form action="/as02/up" method="POST">
        <!-- NAME -->
        <label for="name"><b>Name</b></label>
        <input
                id="name" type="text" placeholder="Enter Name" name="update_name" required>
        <!-- SURNAME -->
        <label for="surname"><b>Surname</b></label>
        <input
                id="surname" type="text" placeholder="Enter Surname" name="update_surname" required>

        <!-- AGE -->
        <label for="age"><b>Age</b></label>
        <input
                type="number"
                placeholder="Student Age"
                name="update_age"
                id="age" required><br>

        <!-- COUNTRY -->
        <label for="country"><b>Country</b></label>
        <input
                type="text"
                placeholder="Student Country"
                name="update_country"
                id="country" required>

        <!-- CITY -->
        <label for="city"><b>City</b></label>

        <input
                type="text"
                placeholder="Student City"
                name="update_city"
                id="city" required>

        <!-- GENDER -->
        <label for="gender"><b>Gender</b></label>
        <select name="update_gender" id="gender" required>
            <option selected="selected" selected disabled hidden>Select</option>

            <option value="Male">Male</option>
            <option value="Female">Female</option>
        </select>

        <input
                type="submit" class="registerbtn" value="Update">

    </form>



    <div class="tableTitle">
        <h2>Available Courses</h2>
    </div>
    <div>
        <table>
            <tr>
                <th>Course Name</th>
                <th>Course ID</th>

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

    <hr>
    <hr>
    <hr>
    <hr>
    <hr>

    <div class="tableTitle">
        <h2>Register To</h2>
    </div>
    <div>
        <form action="/as02/cab" method="POST">
            <!-- Courses Available to Reg Form -->
            <label for="availableCourses"></label>
            <select name="availableCourses" id="availableCourses" required>
                <option value="0" selected disabled hidden>Course ID</option>

                <option value="1">ID:1 Course:Music</option>
                <option value="2">ID:2 Course:Arts</option>
                <option value="3">ID:3 Course:Psychology</option>
                <option value="4">ID:4 Course:Sound Design</option>
                <option value="5">ID:5 Course:Mixing&Mastering</option>
            </select>
            <input type="submit" class="registerbtn" value="Web Register">
        </form>
    </div>
    <hr>

    <div class="tableTitle">
        <h2>Active Courses</h2>
    </div>
    <div>
        <table>
            <tr>
                <th>Email</th>
                <th>Course ID</th>

            </tr>
            <%
                try {
                    String email = (String) session.getAttribute("email");
                    DBUtils dbUtils = new DBUtils();
                    Connection connection = dbUtils.connExternal();
                    String sql = "select * from active_courses where email=?";
                    try {
                        PreparedStatement preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setString(1, email);
                        ResultSet resultSet = preparedStatement.executeQuery();

                        while (resultSet.next()) { %>
            <tr>
                <td><b><%=resultSet.getString("email")%>
                </b></td>
                <td><b><%=resultSet.getString("course_id")%>
                </b></td>

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

</div>
<hr>
<hr>
<form action="/as02/out" method="POST">
    <input type="submit" class="registerbtn" value="Sign Out">
</form>
<hr>

</body>

</html>