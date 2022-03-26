<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head>
    <title>Login Webapp</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</head>
<body style="background-color:darkblue">
<div class="container">
    <nav class="navbar navbar-light bg-info">
        <div class="container-fluid">
            <a class="navbar-brand">SSC - Webapp</a>
            <a class="btn btn-light btn-sm pull-right" type="button" href="/logout">
                <i class="fa fa-sign-out"></i> &nbsp; Logout
            </a>
        </div>
    </nav>
    <h3 class="my-4" style="color: azure">Welcome, ${username}</h3>
    <c:if test="${not empty message}">
        <c:choose>
            <c:when test="${hasError}">
                <div class="alert alert-danger" role="alert">
                    ${message}
                </div>
            </c:when>
            <c:otherwise>
                <div class="alert alert-success" role="alert">
                    ${message}
                </div>
            </c:otherwise>
        </c:choose>
    </c:if>
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
                <td class="align-middle">
                    <button class="btn btn-warning btn-sm" type="button"><i class="fa fa-pencil-square"></i>Edit</button>
                    <c:if test="${currentUser.username != user.username}">
                        <!-- Button trigger modal -->
                        <button class="btn btn-danger btn-sm"
                                type="button"
                                href="/user/delete?username=${user.username}"
                                data-bs-toggle="modal"
                                data-bs-target="#delete-modal-${user.id}"><i class="fa fa-trash"></i>Delete
                        </button>

                        <!-- Modal -->
                        <div class="modal fade" id="delete-modal-${user.id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel" style="color: black">Confirm deleting user</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <a style="color: black">Do you wish to delete
                                            <b>${user.displayName}</b> (<b>${user.username}</b>)?</a>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                        <a class="btn btn-danger" href="/user/delete?username=${user.username}">
                                            <i class="fa fa-trash"></i>&nbsp; Delete
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>