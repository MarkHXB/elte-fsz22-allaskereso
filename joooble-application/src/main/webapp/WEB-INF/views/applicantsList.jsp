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
    <div class="row mt-4 mb-3">
        <div class="col col-sm-12 col-md-6 col-lg-4 col-xl-4 text-center">
            <h4>Applied people</h4>
            <hr class="w-75 mx-auto" />
            <c:forEach items="${appliedEmployees}" var="emp">
                <div class="border border-1 rounded mt-2 mb-2 p-1 d-flex justify-content-between align-items-center">
                    <div>
                        <span>${emp.getCredential().getEmail()}</span>
                    </div>
                    <div class="d-flex justify-content-between align-content-items">
                        <form method="post" action="/jobs/hireEmployee?${_csrf.parameterName}=${_csrf.token}" style="margin-right: 15px">
                            <input type="hidden" value="${emp.getId()}" name="employeeId" />
                            <input type="hidden" value="${jobId}" name="jobId" />
                            <input type="submit" value="Hire" class="btn btn-primary"/>
                        </form>
                        <form method="post" action="/jobs/rejectEmployee?${_csrf.parameterName}=${_csrf.token}" style="margin-right: 15px">
                            <input type="hidden" value="${emp.getId()}" name="employeeId" />
                            <input type="hidden" value="${jobId}" name="jobId" />
                            <input type="submit" value="Reject" class="btn btn-danger"/>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="col col-sm-12 col-md-6 col-lg-4 col-xl-4 text-center">
            <h4>Hired people</h4>
            <hr class="w-75 mx-auto" />
            <c:forEach items="${hiredEmployees}" var="emp">
                <div class="border border-1 rounded mt-2 mb-2 p-1 d-flex justify-content-between align-items-center">
                    <div>
                        <span>${emp.getCredential().getEmail()}</span>
                    </div>
                    <div class="d-flex justify-content-between align-content-items">
                        <form method="post" action="/jobs/rejectEmployee?${_csrf.parameterName}=${_csrf.token}" style="margin-right: 15px">
                            <input type="hidden" value="${emp.getId()}" name="employeeId" />
                            <input type="hidden" value="${jobId}" name="jobId" />
                            <input type="submit" value="Reject" class="btn btn-danger"/>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="col col-sm-12 col-md-6 col-lg-4 col-xl-4 text-center">
            <h4>Rejected people</h4>
            <hr class="w-75 mx-auto" />
            <c:forEach items="${rejectedEmployees}" var="emp">
                <div class="border border-1 rounded mt-2 mb-2 p-1 d-flex justify-content-between align-items-center">
                    <div>
                        <span>${emp.getCredential().getEmail()}</span>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</main>

<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>