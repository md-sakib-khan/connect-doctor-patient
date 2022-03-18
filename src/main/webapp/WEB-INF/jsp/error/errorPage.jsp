<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>
        <fmt:message key="title.exception"/>
    </title>
</head>
<body>
<div class="container">
    <div class="row error-message-row">
        <div class="error-page-message">
            <i class="fa fa-warning"></i>
            <c:if test="${not empty error}">
                <c:out value="${error}"/>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>