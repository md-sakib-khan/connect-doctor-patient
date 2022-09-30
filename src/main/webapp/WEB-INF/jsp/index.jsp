<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>
        <fmt:message key="title.index"/>
    </title>
</head>
<body>
<c:url var="patientUrl" value="/patient"/>
<c:url var="doctorUrl" value="/doctor"/>
<c:url var="loginUrl" value="/login"/>

<div class="container">
    <img class="image-content card-img-overlay" src="../../assets/images/index.png" alt="Background">

    <div class="card register-button-section">
        <div class="row">
            <div class="h1 text-center mb-5">
                <fmt:message key="text.register.as"/>
            </div>
        </div>

        <div class="row d-flex justify-content-center">
            <a href="${doctorUrl}"
               class="btn btn-lg btn-outline-success w-50 m-1 fw-bold">
                <fmt:message key="btn.doctor"/>
            </a>
        </div>

        <div class="row d-flex justify-content-center">
            <a href="${patientUrl}"
               class="btn btn-lg btn-outline-dark w-50 m-1 fw-bold">
                <fmt:message key="btn.patient"/>
            </a>
        </div>

        <div class="row mt-3">
            <h6 class="text-center">
                <fmt:message key="text.registered"/>
                <a href="${loginUrl}">
                    <fmt:message key="text.login"/>
                </a>
            </h6>
        </div>
    </div>
</div>
</body>
</html>