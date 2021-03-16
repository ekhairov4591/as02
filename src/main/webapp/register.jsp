<%@page language="java" contentType="text/html; charset-ISO-8859-1"
        pageEncoding="ISO-8859-1" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" href="CSS/register.css">
    <meta http-equiv="Content-Type" content="text/html; charset-ISO-8859-1">
    <title>Register WMII</title>
</head>

<body>

<div id="container">
    <div id="title">

        <h1><b>Register</b></h1>
    </div>
    <p>Please fill in this form to create an account.</p>
    <hr>

    <form action="/as02/reg" method="POST">
        <!-- EMAIL -->
        <label for="email"><b>Email</b></label>
        <input
                id="email" type="text" placeholder="Enter Email" name="email" required>
        <!-- NAME -->
        <label for="name"><b>Name</b></label>
        <input
                id="name" type="text" placeholder="Enter Name" name="name" required>
        <!-- SURNAME -->
        <label for="surname"><b>Surname</b></label>
        <input
                id="surname" type="text" placeholder="Enter Surname" name="surname" required>

        <!-- AGE -->
        <label for="age"><b>Age</b></label>
        <input
                type="number"
                placeholder="Student Age"
                name="age"
                id="age" required><br>

        <!-- COUNTRY -->
        <label for="country"><b>Country</b></label>
        <input
                type="text"
                placeholder="Student Country"
                name="country"
                id="country" required>

        <!-- CITY -->
        <label for="city"><b>City</b></label>

        <input
                type="text"
                placeholder="Student City"
                name="city"
                id="city" required>

        <!-- GENDER -->
        <label for="gender"><b>Gender</b></label>
        <select name="gender" id="gender" required>
            <option selected="selected" selected disabled hidden>Select</option>

            <option value="Male">Male</option>
            <option value="Female">Female</option>
        </select>

        <!-- PASSWORD -->
        <label for="password"><b>Password</b></label>
        <input
                type="password"
                placeholder="Enter Password"
                name="password"
                id="password" required>
        <hr>
        <p>By creating an account
            you agree to our <a href="terms.html">Terms & Privacy</a>.</p>

        <input
                type="submit" class="registerbtn" value="Register">

        <div class="signin">
            <p>Already have an account? <a href="login.jsp">Sign in</a></p>
        </div>
    </form>

</div>
</body>


</html>