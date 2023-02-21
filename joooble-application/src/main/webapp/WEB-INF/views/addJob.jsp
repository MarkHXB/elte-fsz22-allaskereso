<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Joooble - Welcome page</title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
            crossorigin="anonymous">
</head>
<body>
<jsp:include page="../partials/_header.jsp" />

<main class="container text-center border border-1 rounded p-5">
    <h1>Add new Job</h1>

    <hr class="w-75 mx-auto"/>

    <form:form modelAttribute="jobForm" action="/jobs/addJob">
        <div>
            <form:label path="title" cssClass="form-label">Title:</form:label><br>
            <form:errors cssClass="message" path="title"/>
        </div>
        <div>
            <form:input path="title" cssClass="form-control"/>
        </div>
        <div>
            <form:label path="location">Location:</form:label><br>
            <form:errors cssClass="message" path="location"/>
        </div>
        <div>
            <form:input path="location" cssClass="form-control"/>
        </div>
        <div>
            <form:label path="description">Description:</form:label><br>
            <form:errors cssClass="message" path="description"/>
        </div>
        <div>
            <form:textarea path="description" cssClass="form-control"/>
        </div>
       <div>
            <form:label path="jobCategory">Job Category:</form:label>
        </div>
        <div>
            <form:select path="jobCategory" cssClass="form-select">
                <form:option value="" label="-- Select Job Category"/>
                <form:options items="${jobcategories}" />
            </form:select>
        </div>

        <div>
            <form:label path="salary">Salary:</form:label><br>
            <form:errors cssClass="message" path="salary"/>
        </div>
        <div>
            <form:input path="salary" type="number" max="10000000" cssClass="form-control"/>
        </div>
        <div>
            <form:label path="workingHours">Working Hours:</form:label><br>
            <form:errors cssClass="message" path="workingHours"/>
        </div>
        <div>
            <form:input path="workingHours" type="number" min="3" max="40" cssClass="form-control"/>
        </div>
        <div>
            <form:label path="positionName">Position Name:</form:label><br>
            <form:errors cssClass="message" path="positionName"/>
        </div>
        <div>
            <form:input path="positionName" type="text" cssClass="form-control"/>
        </div>
        <br><br>
        <div class="d-flex flex-column">
            <input type="submit" value="Add job" class="btn btn-primary mt-2"/>
            <input type="button" value="Cancel" class="btn btn-secondary mt-2" onclick="window.location.href='/jobs'"/>
        </div>

    </form:form>
</main>

<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>