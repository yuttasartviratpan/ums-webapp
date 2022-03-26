<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head>
    <title>Login Webapp</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
</head>
<body style="background-color:darkblue">
<div class="container mt-4">
    <h3 class="my-4" style="color: azure">Welcome, ${username}</h3>
    <table class="table table-dark table-striped table-bordered">
        <thead>
        <tr>
            <th class="py-3" scope="col">ID</th>
            <th class="py-3" scope="col">Username</th>
            <th class="py-3" scope="col">Display Name</th>
            <th class="py-3" scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td class="py-2">${user.id}</td>
                <td class="py-2">${user.username}</td>
                <td class="py-2">${user.displayName}</td>
                <td>
                    <button class="btn btn-warning btn-sm" type="button">Edit</button>
                    <button class="btn btn-danger btn-sm" type="button">Delete</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
