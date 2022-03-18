<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>
        <fmt:message key="title.success"/>
    </title>
</head>
<body>
<div class="container">
    <div class="row h-75 d-flex justify-content-center align-items-center">
        <c:if test="${not empty registerMessage}">
            <div>
                <div class="d-flex justify-content-center">
                    <img class="w-25" src="../../../assets/images/check_icon.png">
                </div>

                <div class="mt-3 d-flex justify-content-center fs-5 fw-bold">
                    <c:out value="${registerMessage}"/>
                </div>

                <div class="mt-3 d-flex justify-content-center fs-5 fw-bold">
                    <a class="text-info text-decoration-none fw-bold" href="/login">
                        <fmt:message key="btn.login"/>
                    </a>
                </div>
            </div>
        </c:if>

        <c:if test="${not empty message}">
            <div>
                <div class="d-flex justify-content-center">
                    <img class="w-25" src="../../../assets/images/check_icon.png">
                </div>

                <div class="mt-3 d-flex justify-content-center fs-5 fw-bold">
                    <c:out value="${message}"/>
                </div>
            </div>
        </c:if>
    </div>
</div>
</body>
</html>