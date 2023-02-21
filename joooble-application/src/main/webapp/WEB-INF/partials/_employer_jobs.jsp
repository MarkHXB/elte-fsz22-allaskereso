<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col col-sm-12 col-md-10 col-lg-10 col-xl-10">
    <div class="text-center p-2">
        <a href="/jobs/addJob" class="btn btn-lg btn-primary mt-5 mb-3">Add new job</a>
        <hr class="w-25 mx-auto"/>
    </div>

    <div class="container">
        <!-- Jobs by category [ START ] -->
        <c:if test="${jobsbycategory.size() gt 0}">
            <div class="container mt-3 mb-2">
                <div>
                    <h1>Jobs in selected categories</h1>
                </div>
                <div class="text-center">
                    <h5 class="mt-4 mb-4">We Found ${jobsbycategory.size()}
                        jobs in ${selectedCategory}</h5>
                </div>
                <div class="row p-2 m-4">
                    <c:forEach items="${jobsbycategory}" var="job">
                        <div class="col col-3 card" style="width: 18rem;">
                            <img class="card-img-top" src="${job.employerLogoPath}"
                                 alt="Card image cap">
                            <div class="card-body">
                                <h4 class="card-title">${job.employerName}</h4>
                                <h5 class="card-title">${job.title}</h5>
                                <p class="card-text">${job.description}</p>
                                <a href="../job/details/${job.id}" class="btn btn-primary">Details</a>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <hr class="w-75 mx-auto"/>
            </div>
        </c:if>
        <!-- Jobs by category [ END ] -->
        <!-- Any other jobs ( open jobs ) [ START ] -->
        <c:if test="${openjobs.size() gt 0}">
            <div class="container mt-3 mb-2">
                <div class="mt-2 mb-2 text-center">
                    <h1>Active job advertisments</h1>
                </div>
                <div class="mt-2 mb-2 text-center">
                    <span class="bg-warning rounded text-dark p-2">
                        <strong>We Found ${openjobs.size()} active jobs.</strong>
                    </span>
                </div>
                <div class="row p-2 m-4 d-flex justify-content-center">
                    <c:forEach items="${openjobs}" var="job">
                        <div class="col col-3 card m-4" style="width: 18rem;">
                            <img class="card-img-top" src="${job.employerLogoPath}"
                                 alt="Card image cap">
                            <div class="card-body">
                                <h4 class="card-title">${job.employerName}</h4>
                                <h5 class="card-title">${job.title}</h5>
                                <p class="card-text">${job.description}</p>
                                <div class="text-center">
                                    <a href="/job/details/${job.id}" class="btn btn-primary">Details</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <hr class="w-75 mx-auto"/>
            </div>
        </c:if>
        <!-- Any other jobs ( open jobs ) [ END ] -->
    </div>
</div>