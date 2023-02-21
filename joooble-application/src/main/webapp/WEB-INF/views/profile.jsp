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

<body>

<jsp:include page="../partials/_header.jsp"/>
<c:if test="${not empty messageView}">
    <jsp:include page="../partials/_messageBox.jsp" />
</c:if>

<main class="container-fluid">
    <c:choose>
        <c:when test="${userType == 'employer'}">
            <jsp:include page="../partials/_employer_profile.jsp">
                <jsp:param name="employer" value="${employerView}"/>
            </jsp:include>
        </c:when>
        <c:otherwise>
            <jsp:include page="../partials/_employee_profile.jsp">
                <jsp:param name="employee" value="${employeeView}"/>
            </jsp:include>
        </c:otherwise>
    </c:choose>
</main>

<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"
        type="application/javascript"></script>
</body>
</html>