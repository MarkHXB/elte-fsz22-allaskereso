<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<link rel="stylesheet" href="${contextPath}/css/messageBox.css" type="text/css"/>

<div id="myModal" class="modal">
    <div class="modal-content">
        <c:choose>
            <c:when test="${messageView.type eq 'error'}">
                <div class="modal-header modal-error">
                    <h2>${messageView.title}</h2>
                    <span class="close">&times;</span>

                </div>
            </c:when>
            <c:when test="${messageView.type eq 'message'}">
                <div class="modal-header modal-message">
                    <h2>${messageView.title}</h2>
                    <span class="close">&times;</span>

                </div>
            </c:when>
            <c:otherwise>
                <div class="modal-header modal-text">
                    <h2>${messageView.title}</h2>
                    <span class="close">&times;</span>

                </div>
            </c:otherwise>
        </c:choose>

        <div class="modal-body">
            <strong>${messageView.message}</strong>
        </div>
    </div>
</div>

<script src="${contextPath}/js/messageBox.js"></script>