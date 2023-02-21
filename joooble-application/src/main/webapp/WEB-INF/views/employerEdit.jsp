<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <title>Joooble Application</title>
    <meta charset="UTF-8">
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
            crossorigin="anonymous">
    <style>
        .message{
            color:red;
        }
    </style>
</head>

<body>
<jsp:include page="../partials/_header.jsp"/>

<c:if test="${not empty messageView}">
    <jsp:include page="../partials/_messageBox.jsp" />
</c:if>

<main class="container mx-auto">
    <form:form modelAttribute="employerEditForm" action="/profile/employer-update" method="POST" enctype="multipart/form-data" class="m-2 p-1">
        <input type="hidden" name="employerId" value="${employerView.employerId}"/>
        <hr class="mx-auto w-50"/>
        <div class="text-center">
            <h3>Personal information</h3>
        </div>
        <div class="mt-2 mb-2">
            <form:label path="email" cssClass="form-label">New email address:</form:label><br>
            <form:errors cssClass="message" path="email"/>
            <div>
                <input type="email" name="email" class="form-control"/>
            </div>
        </div>
        <div class="mt-2 mb-2">
            <form:label path="password" cssClass="form-label">New password:</form:label><br>
            <form:errors cssClass="message" path="password"/>
            <div>
                <input type="password" name="password" class="form-control"/>
            </div>
        </div>
        <div class="mt-2 mb-2">
            <form:label path="companyName" cssClass="form-label">New company name:</form:label><br>
            <form:errors cssClass="message" path="companyName"/>
            <div>
                <input type="text" name="companyName" value="${employerView.companyName}" class="form-control"/>
            </div>
        </div>
        <div class="mt-2 mb-2">
            <form:label path="companyAddress" cssClass="form-label">New company address:</form:label><br>
            <form:errors cssClass="message" path="companyAddress"/>
            <div>
                <input type="text" name="companyAddress" value="${employerView.companyAddress}" class="form-control"/>
            </div>
        </div>
        <hr class="mx-auto w-50"/>
        <div class="text-center">
            <h3>Logo</h3>
        </div>
        <div class="mt-2 mb-2">
            <form:label path="filename" cssClass="form-label">Logo title:</form:label><br>
            <form:errors cssClass="message" path="filename"/>
            <div>
                <input type="text" name="filename" class="form-control" value="${employerView.logoName}"/>
            </div>
        </div>
        <div class="mt-2 mb-2">
            <form:label path="file" cssClass="form-label">Logo file ( jpg/jpeg or png allowed only! ):</form:label><br>
            <form:errors cssClass="message" path="file"/>
            <div>
                <input type="file" name="file" class="form-control" />
            </div>
        </div>

        <div class="mt-4 mb-2">
            <input type="submit" value="Save" class="btn btn-primary" style="margin-right: 20px"/>
            <a href="/profile" class="btn btn-outline-primary">Back to profile</a>
        </div>
    </form:form>
</main>

<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"
        type="application/javascript"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js" type="application/javascript"></script>
</body>
</html>