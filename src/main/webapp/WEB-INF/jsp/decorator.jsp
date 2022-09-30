<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>

<html>
<head>
    <title>
        <decorator:title/>
    </title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="<c:url value="/webjars/bootstrap/5.1.1/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="/css/index-1.0.1.css">
    <link rel="stylesheet" href="/css/form-1.0.1.css">
    <link rel="stylesheet" href="/css/common-1.0.1.css">
    <link rel="stylesheet" href="/css/dashboard-1.0.1.css">
    <link rel="stylesheet" href="/css/menu-1.0.1.css">
    <link rel="stylesheet" href="/css/error-1.0.1.css">
</head>
<body>
<c:url var="dashboardUrl" value="/dashboard"/>
<c:url var="indexUrl" value="/"/>
<c:set var="loggedUser" value="${sessionScope['loggedUser']}"/>

<div class="sticky-top ">
    <nav class="navbar navbar-dark bg-dark">
        <div class="container-fluid m-2">
            <a class="navbar-brand me-auto" href="${empty loggedUser? indexUrl : dashboardUrl}">
                <div class="display-6">
                    <fmt:message key="title.navbar"/>
                </div>
            </a>

            <span class="border-2 d-flex justify-content-end">
            <c:if test="${not empty loggedUser}">
                <i class="fa fa-user"></i>
                <div class="fw-bold my-auto text-light my-auto me-3 border rounded p-1">
                    <i class="fa fa-user me-1"></i>
                    <c:out value="${loggedUser.firstName} ${loggedUser.lastName} (${loggedUser.role.displayName})"/>
                </div>
                <form class="m-lg-auto" method="post">
                    <button class="btn btn-danger btn-sm"
                            type="submit"
                            formaction="<c:url value="/logout"/>">
                        <fmt:message key="btn.logout"/>
                    </button>
                </form>
            </c:if>
        </span>
        </div>
    </nav>

    <c:choose>
        <c:when test="${'PATIENT' eq loggedUser.role}">
            <div class="container-fluid p-0">
                <div class="menu">
                    <jsp:include page="menu/paitent.jsp"/>
                </div>
            </div>
        </c:when>

        <c:when test="${'DOCTOR' eq loggedUser.role}">
            <div class="container-fluid p-0">
                <div class="menu">
                    <jsp:include page="menu/doctor.jsp"/>
                </div>
            </div>
        </c:when>
    </c:choose>
</div>
<decorator:body/>
</body>
</html>