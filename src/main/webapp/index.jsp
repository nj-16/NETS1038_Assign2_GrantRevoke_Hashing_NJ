<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration Page</title>

<style>
    body {
        font-family: serif;
        margin: 40px;
    }

    h1 {
        font-size: 34px;
        margin-bottom: 20px;
    }

    table {
        border-collapse: collapse;
    }

    td {
        padding: 6px;
        vertical-align: top;
    }

    .label {
        text-align: right;
        padding-right: 10px;
        width: 150px;
    }

    input[type="text"],
    input[type="password"],
    input[type="email"],
    input[type="tel"] {
        height: 25px;
        width: 250px;
        box-sizing: border-box;
    }

    .captcha-image {
        width: 250px;
        height: 75px;
        object-fit: contain;
        border: 1px solid #999;
    }

    .submit-row {
        text-align: center;
        padding-top: 15px;
    }

    .error {
        color: red;
        font-weight: bold;
        margin-bottom: 15px;
    }
</style>

</head>

<body>

<h1>200582109 Assignment 2</h1>
<h1>Registration Page</h1>

<%
    String errorMessage = (String) request.getAttribute("errorMessage");

    if (errorMessage != null) {
%>
    <p class="error"><%= errorMessage %></p>
<%
    }
%>

<form action="StudentServlet" method="post">

    <table>
        <tr>
            <td class="label">Username:</td>
            <td>
                <input type="text"
                       id="username"
                       name="username"
                       required
                       title="Username cannot be blank">
            </td>
        </tr>

        <tr>
            <td class="label">Password:</td>
            <td>
                <input type="password"
                       id="password"
                       name="password"
                       required
                       title="Password cannot be blank">
            </td>
        </tr>

        <tr>
            <td class="label">Retype Password:</td>
            <td>
                <input type="password"
                       id="retypePassword"
                       name="retypePassword"
                       required
                       title="Please re-enter the password">
            </td>
        </tr>

        <tr>
            <td class="label">Mobile Number:</td>
            <td>
                <input type="tel"
                       id="mobileNumber"
                       name="mobileNumber"
                       required
                       pattern="[0-9]{10}"
                       minlength="10"
                       maxlength="10"
                       title="Mobile number must contain exactly 10 numbers">
            </td>
        </tr>

        <tr>
            <td class="label">Email ID:</td>
            <td>
                <input type="email"
                       id="email"
                       name="email"
                       required
                       title="Please enter a valid email address">
            </td>
        </tr>

        <tr>
            <td class="label">Captcha Image:</td>
            <td>
                <img src="capcha.png"
                     alt="Captcha image"
                     class="captcha-image">
            </td>
        </tr>

        <tr>
            <td class="label">Enter Captcha:</td>
            <td>
                <input type="text"
                       id="captcha"
                       name="captcha"
                       required
                       title="Captcha cannot be blank">
            </td>
        </tr>

        <tr>
            <td></td>
            <td class="submit-row">
                <input type="submit" value="Submit">
            </td>
        </tr>
    </table>

</form>

<br>

<a href="ViewStudentsServlet">View Registered Users</a>

</body>
</html>
