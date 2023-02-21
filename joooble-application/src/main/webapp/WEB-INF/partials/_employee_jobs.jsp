<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col col-10">
    <div class="container mt-3 mb-2">
        <c:if test="${recommendedJobs.size() gt 0}">
            <div>
                <h1>Recommended for You</h1>
            </div>
            <div class="row p-2 m-4">
                <c:forEach items="${recommendedJobs}" var="job">
                    <div class="col col-3 card" style="width: 18rem;">
                        <img class="card-img-top" src="${job.employerLogoPath}"
                             alt="Card image cap">
                        <div class="card-body">
                            <h4 class="card-title">${job.employerName}</h4>
                            <h5 class="card-title">${job.title}</h5>
                            <p class="card-text">${job.description}</p>
                            <a href="/job/details/${job.id}" class="btn btn-primary">Details</a>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <hr class="w-75 mx-auto"/>
        </c:if>
    </div>


    <!-- Jobs in Selected Categories [ START ] -->
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
                            <a href="/job/details/${job.id}" class="btn btn-primary">Details</a>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <hr class="w-75 mx-auto"/>
        </div>
    </c:if>

    <div class="container mt-3 mb-2">
        <div>
            <h1>Other jobs</h1>
        </div>
        <div class="row p-2 m-4 mt-3 mb-2">
            <c:forEach items="${otherjobs}" var="job">
                <div class="col col-3 card" style="width: 18rem;">
                    <img class="card-img-top" src="${job.employerLogoPath}"
                         alt="Card image cap">
                    <div class="card-body">
                        <h4 class="card-title">${job.employerName}</h4>
                        <h5 class="card-title">${job.title}</h5>
                        <p class="card-text">${job.description}</p>
                        <a href="/job/details/${job.id}" class="btn btn-primary">Details</a>
                    </div>
                </div>
            </c:forEach>
        </div>

        <!-- Jobs in Selected Categories [ END ] -->
    </div>
</div>