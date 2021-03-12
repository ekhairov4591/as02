<%@page language="java" contentType="text/html; charset-ISO-8859-1"
        pageEncoding="ISO-8859-1" %>

<html>
<head>
    <link rel="stylesheet" href="CSS/register.css">
    <meta http-equiv="Content-Type" content="text/html; charset-ISO-8859-1">
    <title>Login WMII</title>
</head>

<body>

<div id="container">

    <div id="title">
        <h1>Sign In</h1>
    </div>

    <p>Please fill in this form to login into your student account.</p>
    <hr>

    <form action="/as02/log" method="POST">
        <label for="email"><b>Email</b></label>
        <input
                type="text" placeholder="Enter Email" name="email" id="email" required>

        <label for="password"><b>Password</b></label>
        <input
                type="password"
                placeholder="Enter Password"
                name="password"
                id="password" required>

        <input type="submit" class="registerbtn" value="Sign in">

        <div class="container signin">
            <p>Don't have an account? <a href="register.jsp">Register</a>.</p>
        </div>
        </form>
</div>

</body>


</html>