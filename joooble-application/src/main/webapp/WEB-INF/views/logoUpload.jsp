<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>File Uploading Form</title>
    <style>
        .message{color:green;}
        .error{color:red;}
    </style>
</head>

<body>
    <c:forEach items="${files}" var="file">
        <p>${file}</p>
    </c:forEach>

    <c:if test="${not empty message}">
        <p class="message">${message}</p>
    </c:if>

    <c:if test="${not empty error}">
        <p class="error">${error}</p>
    </c:if>

    <h3>File Upload:</h3>
    Select a file to upload: <br/>
    <form:form modelAttribute="logoFormView" action="/files/logos/upload?${_csrf.parameterName}=${_csrf.token}" method="post" enctype="multipart/form-data">
        <div>
            <form:label path="filename">Filename:</form:label><br>
            <form:errors cssClass="error" path="filename"/>
        </div>
        <div>
            <form:input path="filename"/>
        </div>
        <div>
            <form:label path="file">File:</form:label><br>
            <form:errors cssClass="error" path="file"/>
        </div>
        <div>
            <form:input path="file" type="file"/>
        </div>
        <div>
            <input type="submit" value="Save"/>
        </div>
    </form:form>
</body>

</html>