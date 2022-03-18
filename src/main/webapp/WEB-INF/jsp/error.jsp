<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>
        <fmt:message key="title.error"/>
    </title>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value="/assets/css/error-1.0.1.css"/>">
</head>
<body>
<div class="container">
    <div class="row error-message-row">
        <div class="error-page-message">
            <i class="fa fa-warning"></i>
            <fmt:message key="error.message.default"/>
        </div>
    </div>
</div>
</body>
</html>