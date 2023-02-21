<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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

<jsp:include page="../partials/_header.jsp"/>
<c:if test="${not empty messageView}">
    <jsp:include page="../partials/_messageBox.jsp" />
</c:if>

<main class="container">
    <div class="row d-flex justify-content-center align-items-center text-center">
        <div class="col col-sm-12 col-md-4 col-lg-4 col-xl-4">
            <img src="${view.employerLogoPath}" alt="company's logo" class="rounded"
                 style="max-width: 150px"/>
        </div>
        <div class="col col-sm-12 col-md-4 col-lg-4 col-xl-4">
            <h3>${view.employerCompanyName}</h3>
        </div>
        <div class="col col-sm-12 col-md-4 col-lg-4 col-xl-4">
            <strong class="text-muted"> ${view.jobAddedDate} </strong>
        </div>
        <div class="col col-12">
            <strong>${view.jobPositionName}</strong>
        </div>
        <div class="col col-12">
            <h6>${view.jobLocation}</h6>
        </div>
        <div class="col col-12">
            <p>${view.jobDescription}</p>
        </div>
        <div class="col col-12 ">
            <p>Contact information</p>
            <div class="border border-1 rounded-2">
                <span>${view.employerEmail}</span>
                <span>${view.employerLocation}</span>
            </div>
        </div>
    </div>
    <div class="text-center">
        <c:if test="${view.userType == 'employee'}">

            <c:if test="${view.jobStatus eq 'DEFAULT'}">
                <form method="post" action="/jobs/applyJob?${_csrf.parameterName}=${_csrf.token}">
                    <input type="hidden" name="jobId" value="${view.jobId}"/>
                    <input value="Apply" type="submit" class="btn btn-outline-primary"/>
                </form>
                <br/>
            </c:if>

            <c:choose>
                <c:when test="${view.jobStatus eq 'APPLIED'}">
                    <div class="border rounded bg-secondary text-light p-2 m-3">
                        You've already applied for this job.
                    </div>
                </c:when>
                <c:when test="${view.jobStatus eq 'HIRED'}">
                    <div class="border rounded bg-success text-light p-2 m-3">
                        You've been hired.
                    </div>
                </c:when>
                <c:when test="${view.jobStatus eq 'REJECTED'}">
                    <div class="border rounded bg-warning text-dark p-2 m-3">
                        You were rejected.
                    </div>
                </c:when>
            </c:choose>
        </c:if>
    </div>
    <div class="text-center">
        <a href="/jobs" class="btn btn-outline-primary">Back to jobs</a>
    </div>
</main>

<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>