<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="com.va.week6.model.Student" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registered Users</title>

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
        width: 100%;
    }

    th,
    td {
        border: 1px solid black;
        padding: 8px;
        text-align: left;
    }

    th {
        font-weight: bold;
    }

    .error {
        color: red;
        font-weight: bold;
        margin-bottom: 15px;
    }

    .message {
        font-weight: bold;
        margin-bottom: 15px;
    }
</style>

</head>

<body>

<h1>Registered Users</h1>

<%
    String errorMessage = (String) request.getAttribute("errorMessage");
    List<Student> students = (List<Student>) request.getAttribute("students");

    if (errorMessage != null) {
%>
    <p class="error"><%= errorMessage %></p>
<%
    }

    if (students == null || students.isEmpty()) {
%>
    <p class="message">No registered users were found.</p>
<%
    } else {
%>

<table>
    <tr>
        <th>Username</th>
        <th>Hashed Password</th>
        <th>Mobile Number</th>
        <th>Email</th>
    </tr>

    <% for (Student student : students) { %>
        <tr>
            <td><%= student.getUsername() %></td>
            <td><%= student.getPassword() %></td>
            <td><%= student.getMobileNumber() %></td>
            <td><%= student.getEmail() %></td>
        </tr>
    <% } %>
</table>

<%
    }
%>

<br>

<a href="index.jsp">Registration Page</a>

</body>
</html>