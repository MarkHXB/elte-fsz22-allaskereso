<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col col-sm-12 col-md-2 col-lg-2 col-xl-2 mt-3 mb-3">
    <div class="container">
        <h5 class="text-center mt-2 mb-4">Job categories</h5>
        <hr/>
        <c:forEach items="${categorystatistics}" var="view">
            <c:set var="id" value="${view.categoryId}" scope="page"/>
            <c:set var="name" value="${view.categoryName}" scope="page"/>
            <c:set var="count" value="${view.count}" scope="page"/>
            <a class="btn btn-outline-primary mt-1 mb-1"
               href="/jobs/${id}">${name} - ${count}</a>
            <br/>
        </c:forEach>
    </div>
</div>