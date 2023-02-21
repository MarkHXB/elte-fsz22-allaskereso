<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="row">
    <div class="col col-sm-12 col-md-12 col-lg-6 col-xl-6 p-5">
        <h1>My Profile</h1>
        <hr class="w-75 mx-auto"/>
        <strong><u>Personal information</u></strong>
        <br>
        <div class="d-flex align-items-center mt-2">
            <div class="border rounded bg-primary text-light p-1">
                <strong>Email address </strong>
            </div>
            <div style="margin-left:15px">
                ${employerView.email}
            </div>
        </div>
        <div class="d-flex align-items-center mt-2">
            <div class="border rounded bg-primary text-light p-1">
                <strong>Company name </strong>
            </div>
            <div style="margin-left:15px">
                ${employerView.companyName}
            </div>
        </div>
        <div class="d-flex align-items-center mt-2">
            <div class="border rounded bg-primary text-light p-1">
                <strong>Company address </strong>
            </div>
            <div style="margin-left:15px">
                ${employerView.companyAddress}
            </div>
        </div>
        <div class="mt-3 mb-3">
            <c:choose>
                <c:when test="${not empty employerView.logoPath}">
                    <img src="${employerView.logoPath}" style="max-width: 150px" alt="logo">
                </c:when>
                <c:otherwise>
                    <img src="resources/images/default-cv.png" style="max-width: 150px" alt="logo">
                    <br/>
                </c:otherwise>
            </c:choose>
            <a class="btn btn-outline-primary" href="/profile/employer-update">Change logo</a>
        </div>

        <!-- User personal information modification -->
        <div class="border border-dark border-1 rounded d-flex flex-column mt-3">
            <div style="background:rgba(0,0,0,.1)">
                <h4 class="m-1 p-2">Edit personal information</h4>
            </div>
            <a class="btn btn-outline-primary" href="/profile/employer-update">Click here to change personal information</a>
        </div>

    </div>
    <div class="col col-sm-12 col-md-12 col-lg-6 col-xl-6 p-5">
        <!-- Active job list -->
        <div class="border border-dark border-1 rounded d-flex flex-column mt-3">
            <div style="background:rgba(0,0,0,.1)">
                <h4 class="m-1 p-2">Active jobs list</h4>
            </div>
            <div>
                <c:forEach items="${employerView.activeJobList}" var="job">
                    <div class="d-flex justify-content-between m-2">
                        <h6 class="m-1 p-2">${job.getTitle()}</h6>
                        <div class="d-flex justify-content-between">
                            <a href="/job/applicants/${job.getId()}" class="btn btn-primary"
                               style="margin-right:10px">Applicants</a>
                            <a href="/job/details/${job.getId()}" class="btn btn-outline-success"
                               style="margin-right:10px">Job details</a>
                            <form method="post" action="/job/inactivate?${_csrf.parameterName}=${_csrf.token}">
                                <input type="hidden" value="${job.getId()}" name="jobId">
                                <input type="submit" value="Inactivate" class="btn btn-warning" style="margin-right:10px">
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <!-- Inactive job list -->
        <div class="border border-dark border-1 rounded d-flex flex-column mt-3">
            <div style="background:rgba(0,0,0,.1)">
                <h4 class="m-1 p-2">Inactive jobs list</h4>
            </div>
            <div>
                <c:forEach items="${employerView.inactiveJobList}" var="job">
                    <div class="d-flex justify-content-between m-2">
                        <h6 class="m-1 p-2">${job.getTitle()}</h6>
                        <div class="d-flex justify-content-between">
                            <a href="/job/applicants/${job.getId()}" class="btn btn-primary"
                               style="margin-right:10px">Applicants</a>
                            <a href="/job/details/${job.getId()}" class="btn btn-outline-primary"
                               style="margin-right:10px">Job details</a>
                            <form method="post" action="/job/activate?${_csrf.parameterName}=${_csrf.token}">
                                <input type="hidden" value="${job.getId()}" name="jobId">
                                <input type="submit" value="Activate" class="btn btn-success" style="margin-right:10px">
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>