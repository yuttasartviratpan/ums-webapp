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
<body style="background-color:darkgray">
<div class="container">
    <nav class="navbar navbar-light bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="/" style="color: aliceblue">SSC - Webapp</a>
            <a class="btn btn-light btn-sm pull-right" type="button" href="/logout">
                <i class="fa fa-sign-out"></i> &nbsp; Logout
            </a>
        </div>
    </nav>
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
    <div class="row justify-content-md-center">
        <div class="col-sm-12 col-md-6 col-lg-4 mt-5">
                <h3 class="text-center mb-4">Change Password (${username})</h3>
                <p>${error}</p>
                <form action="/user/password?username=${username}" method="post" autocomplete="off">
                    <div class="input-group mb-3">
                        <span class="input-group-text" id="password" style="width: 50px">
                            <i class="fa fa-key"></i>
                        </span>
                        <input type="password" class="form-control py-2" name="password" placeholder="Password"
                               aria-label="Password" aria-describedby="password" autocomplete="off" value="${password}">
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text" id="cpassword" style="width: 50px">
                            <i class="fa fa-key"></i>
                        </span>
                        <input type="password" class="form-control py-2" name="cpassword" placeholder="Confirm Password"
                               aria-label="Password" aria-describedby="cpassword" autocomplete="off" value="${cpassword}">
                    </div>
                    <div class="d-grid gap-2">
                        <button class="btn btn-success" type="submit"><i class="fa fa-pencil-square"></i>&nbsp; Save</button>
                    </div>
                </form>
        </div>
    </div>
</div>
</body>
</html>