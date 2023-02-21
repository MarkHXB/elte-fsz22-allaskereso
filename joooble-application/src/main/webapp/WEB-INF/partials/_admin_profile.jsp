<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="row">
    <div class="col col-sm-12 col-md-12 col-lg-6 col-xl-6 p-5">
        <h1>My Profile</h1>
        <hr class="w-75 mx-auto"/>
        <strong><u>Admin information</u></strong>
        <br>
        <div class="d-flex align-items-center mt-2">
            <div class="border rounded bg-primary text-light p-1">
                <strong>Email address </strong>
            </div>
            <div style="margin-left:15px">
                ${adminView.name}
            </div>
        </div>
        <div class="d-flex align-items-center mt-2">
            <div class="border rounded bg-primary text-light p-1">
                <strong>Email address</strong>
            </div>
            <div style="margin-left:15px">
                ${adminView.email}
            </div>
        </div>

    </div>
    <div class="col col-sm-12 col-md-12 col-lg-6 col-xl-6 p-5">
        <!-- employee list -->
        <div class="border border-dark border-1 rounded d-flex flex-column mt-3">
            <div style="background:rgba(0,0,0,.1)">
                <h4 class="m-1 p-2">Employees</h4>
            </div>
            <div>
                <c:forEach items="${adminView.activeJobList}" var="job">
                    <div class="d-flex justify-content-between m-2">
                        <h6 class="m-1 p-2">${job.getTitle()}</h6>
                        <div class="d-flex justify-content-between">
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

        <!-- employer list -->
        <div class="border border-dark border-1 rounded d-flex flex-column mt-3">
            <div style="background:rgba(0,0,0,.1)">
                <h4 class="m-1 p-2">Employers</h4>
            </div>
            <div>
                <c:forEach items="${adminView.activeJobList}" var="job">
                    <div class="d-flex justify-content-between m-2">
                        <h6 class="m-1 p-2">${job.getTitle()}</h6>
                        <div class="d-flex justify-content-between">
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

        <!-- job list -->
        <div class="border border-dark border-1 rounded d-flex flex-column mt-3">
            <div style="background:rgba(0,0,0,.1)">
                <h4 class="m-1 p-2">Jobs</h4>
            </div>
            <div>
                <c:forEach items="${adminView.inactiveJobList}" var="job">
                    <div class="d-flex justify-content-between m-2">
                        <h6 class="m-1 p-2">${job.getTitle()}</h6>
                        <div class="d-flex justify-content-between">
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