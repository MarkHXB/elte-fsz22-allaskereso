<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <title>Joooble Application</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/login.css">
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
            crossorigin="anonymous">
</head>

<h1>Welcome to Joooble Application!</h1>
<body>
<c:if test="${param.logout != null}">
    <div id="message-box">
        <div>
            You have been logged out.
        </div>
    </div>
</c:if>

<main class="container-fluid border border-1 rounded row text-center" style="height: 400px">
    <div class="col col-6" id="hero-image">
        <!-- image loaded by css -->
    </div>
    <div class="col col-6">
        <c:if test="${param.error != null}">
        <div>
            Invalid username and password.
        </div>
        </c:if>
        <div>
            <form:form action="login" method='POST'>
                <!-- USERNAME -->
                <div class="form-group">
            <span>
                <label path="username">Username:</label><br>
            </span>
                    <span>
            <input type="text" name="username" class="form-control"/> </label>
            </span>
                </div>

                <!-- PASSWORD -->
                <div class="form-group">
            <span>
                <label path="password">Password:</label><br>
            </span>
                    <span>
            <input id="password" type="password" name="password" class="form-control"/>
            </span>
                </div>
                <br>
                <!-- SUBMIT -->
                <button type="submit" class="btn btn-primary">Login</button>
            </form:form>
        </div>
</main>

<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"
        type="application/javascript"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js" type="application/javascript"></script>
</body>
</html>