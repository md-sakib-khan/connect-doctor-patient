<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>
        <fmt:message key="title.menu"/>
    </title>
</head>
<body>
<c:url var="expertiseUrl" value="/expertise"/>
<c:url var="expertiseListUrl" value="/expertise/list"/>
<c:url var="issueListUrl" value="/issue/list"/>
<c:url var="appointmentListUrl" value="/appointment/list"/>
<c:set var="currentUri" value="${pageContext.request.requestURI}"/>

<div class="container-fluid">
    <div class="row">
        <a class="col ${expertiseUrl eq currentUri? 'active' : ''}" href="${expertiseUrl}">
            <fmt:message key="btn.add.expertise"/>
        </a>

        <a class="col ${expertiseListUrl eq currentUri? 'active' : ''}" href="${expertiseListUrl}">
            <fmt:message key="btn.show.expertise"/>
        </a>

        <a class="col ${issueListUrl eq currentUri? 'active' : ''}" href="${issueListUrl}">
            <fmt:message key="btn.show.issue"/>
        </a>

        <a class="col ${appointmentListUrl eq currentUri? 'active' : ''}" href="${appointmentListUrl}">
            <fmt:message key="btn.appointments"/>
        </a>
    </div>
</div>
</body>
</html>