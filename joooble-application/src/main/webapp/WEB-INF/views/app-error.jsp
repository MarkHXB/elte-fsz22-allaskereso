<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/hotel.css">
</head>
<body>

<h1>Error page</h1>
<div class="message">
    <c:if test="${not empty errorCode}">
        ${errorCode} : ${errorMessage}
    </c:if>
    <c:if test="${empty errorCode}">
        System error.
    </c:if>
</div>



</body>
</html>
