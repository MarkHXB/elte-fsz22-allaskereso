<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
    <div class="col col-sm-12 col-md-12 col-lg-6 col-xl-6 p-5">
        <h1>My Profile</h1>
        <hr class="w-75 mx-auto"/>
        <div class="d-flex align-items-center mt-2">
            <div class="border rounded bg-success text-light p-1">
                <strong>Email address </strong>
            </div>
            <div style="margin-left:15px">
                ${employeeView.email}
            </div>
        </div>
        <div class="d-flex align-items-center mt-2">
            <div class="border rounded bg-success text-light p-1">
                <strong>Current resume's title</strong>
            </div>
            <div style="margin-left:15px">
                ${employeeView.resumeName}
            </div>
        </div>
        <div class="d-flex align-items-center mt-2">
            <div class="border rounded bg-success text-light p-1">
                <strong>Your resume file:</strong>
            </div>
        </div>
        <div class="d-flex align-items-center mt-2 p-2">
            <a href="/files/${employeeView.resumeFileName}"><img src="assets/images/download-icon.png"
                                                                 style="max-width:50px;margin-right:20px"/> Click here to download</a>
        </div>

        <!-- User personal information modification -->
        <div class="border border-dark border-1 rounded d-flex flex-column mt-3">
            <div style="background:rgba(0,0,0,.1)">
                <h4 class="m-1 p-2">Edit personal information</h4>
            </div>
            <a class="btn btn-outline-primary" href="/profile/employee-update">Click here to change personal
                information</a>
        </div>

    </div>
    <div class="col col-sm-12 col-md-12 col-lg-6 col-xl-6 p-5">
        <!-- Hired job list -->
        <div class="border border-dark border-1 rounded d-flex flex-column mt-3">
            <div style="background:rgba(0,0,0,.1)">
                <h4 class="m-1 p-2">Hired jobs list</h4>
            </div>
            <div>
                <c:forEach items="${employeeView.hiredJobList}" var="job">
                    <div class="d-flex justify-content-between m-2">
                        <h6 class="m-1 p-2">${job.getTitle()}</h6>
                        <div class="d-flex justify-content-between">
                            <a href="/job/details/${job.getId()}" class="btn btn-outline-success"
                               style="margin-right:10px">Job details</a>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <!-- Applied job list -->
        <div class="border border-dark border-1 rounded d-flex flex-column mt-3">
            <div style="background:rgba(0,0,0,.1)">
                <h4 class="m-1 p-2">Applied job list</h4>
            </div>
            <div>
                <c:forEach items="${employeeView.appliedJobList}" var="job">
                    <div class="d-flex justify-content-between m-2">
                        <h6 class="m-1 p-2">${job.getTitle()}</h6>
                        <div class="d-flex justify-content-between">
                            <a href="/job/details/${job.getId()}" class="btn btn-outline-primary"
                               style="margin-right:10px">Job details</a>
                            <form action="/job/withdraw-applicant?${_csrf.parameterName}=${_csrf.token}" method="post">
                                <input type="hidden" name="jobId" value="${job.getId()}">
                                <input type="submit" class="btn btn-warning" value="Withdraw application" style="margin-right:10px">
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <!-- Rejected job list -->
        <div class="border border-dark border-1 rounded d-flex flex-column mt-3">
            <div style="background:rgba(0,0,0,.1)">
                <h4 class="m-1 p-2">Rejected job list</h4>
            </div>
            <div>
                <c:forEach items="${employeeView.rejectedJobList}" var="job">
                    <div class="d-flex justify-content-between m-2">
                        <h6 class="m-1 p-2">${job.getTitle()}</h6>
                        <div class="d-flex justify-content-between">
                            <a href="/job/details/${job.getId()}" class="btn btn-outline-warning"
                               style="margin-right:10px">Job details</a>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>