<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>
        <fmt:message key="title.index"/>
    </title>
    <link rel="stylesheet" href="<c:url value="/webjars/bootstrap/5.1.1/css/bootstrap.min.css"/>">
</head>
<body>
<div class="container">
    <img class="image-content card-img-overlay" src="/assets/images/index.png" alt="Background">

    <div class="card login-section">
        <div class="row">
            <div class="h1 text-center mb-5">
                <fmt:message key="text.login"/>
            </div>
        </div>

        <form:form method="post" modelAttribute="loginDTO">
            <div class="row w-50 m-auto d-flex justify-content-center">
                <form:input path="email"
                            placeholder="Enter Email"
                            cssClass="form-control form-control-lg bg-transparent border-dark"/>
                <div class="error-section">
                    <form:errors path="email" cssClass="error-message"/>
                </div>
            </div>

            <div class="row w-50 m-auto d-flex justify-content-center">
                <form:input path="password"
                            placeholder="Enter Password"
                            type="password"
                            cssClass="form-control form-control-lg bg-transparent border-dark"/>
                <div class="error-section">
                    <form:errors path="password" cssClass="error-message"/>
                </div>
            </div>

            <div class="row w-25 m-auto d-flex justify-content-center">
                <form:button class="btn btn-lg btn-outline-success">
                    <fmt:message key="btn.login"/>
                </form:button>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>