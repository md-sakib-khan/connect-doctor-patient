<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>
        <fmt:message key="title.medical.history.list"/>
    </title>
</head>
<body>
<c:set var="loggedUser" value="${sessionScope['loggedUser']}"/>
<c:url var="editUrl" value="/medicalHistory"/>

<div class="container m-auto">
    <div class="row bg-dark text-light m-4 shadow-lg ps-5 pe-5 py-2 border rounded-2">
        <div class="col-2 d-flex align-items-center fw-bold">
            <fmt:message key="label.category"/>
        </div>

        <div class="col-8 d-flex align-items-center fw-bold">
            <fmt:message key="label.problem.statement"/>
        </div>

        <div class="col-2 d-flex justify-content-center align-items-center fw-bold">
            <fmt:message key="btn.action"/>
        </div>
    </div>

    <c:if test="${not empty medicalHistories}">
        <c:forEach var="item" items="${medicalHistories}">
            <div class="row m-4 shadow-lg p-5 border rounded-2 item-row-color">
                <div class="col-sm-2 d-flex align-items-center">
                    <c:out value="${item.category}"/>
                </div>

                <div class="col-sm-8 d-flex align-items-center">
                    <c:out value="${item.problemStatement}"/>
                </div>

                <div class="col-sm-2 d-flex justify-content-center align-items-center">
                    <form class="m-auto">
                        <button formaction="${editUrl}"
                                name="medicalHistoryId"
                                value="${item.id}"
                                class="btn btn-outline-secondary fw-bold">
                            <c:choose>
                                <c:when test="${'DOCTOR' eq loggedUser.role}">
                                    <fmt:message key="btn.show"/>
                                </c:when>
                                <c:when test="${'PATIENT' eq loggedUser.role}">
                                    <fmt:message key="btn.edit"/>
                                </c:when>
                            </c:choose>
                        </button>
                    </form>
                </div>
            </div>
        </c:forEach>
    </c:if>
</div>
</body>
</html>
