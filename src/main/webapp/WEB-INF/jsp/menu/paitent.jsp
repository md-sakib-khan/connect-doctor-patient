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
<c:url var="medicalHistoryUrl" value="/medicalHistory"/>
<c:url var="medicalHistoryListUrl" value="/medicalHistory/list"/>
<c:url var="issueUrl" value="/issue"/>
<c:url var="issueListUrl" value="/issue/list"/>
<c:url var="appointmentListUrl" value="/appointment/list"/>
<c:set var="currentUri" value="${pageContext.request.requestURI}"/>

<div class="container-fluid">
    <div class="row">
        <a class="col ${medicalHistoryUrl eq currentUri? 'active' : ''}" href="${medicalHistoryUrl}">
            <fmt:message key="btn.add.medicalHistory"/>
        </a>

        <a class="col ${medicalHistoryListUrl eq currentUri? 'active' : ''}" href="${medicalHistoryListUrl}">
            <fmt:message key="btn.medicalHistoryList"/>
        </a>

        <a class="col ${issueUrl eq currentUri? 'active' : ''}" href="${issueUrl}">
            <fmt:message key="btn.add.issue"/>
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