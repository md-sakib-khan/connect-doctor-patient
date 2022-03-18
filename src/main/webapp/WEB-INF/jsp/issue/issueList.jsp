<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>
        <fmt:message key="title.issue.list"/>
    </title>
</head>
<body>
<c:url var="editUrl" value="/issue"/>
<c:set var="loggedUser" value="${sessionScope['loggedUser']}"/>

<div class="container m-auto">
    <div class="row bg-dark text-light mx-4 mt-4 shadow-lg ps-5 pe-5 py-2 border rounded-2">
        <div class="col-2 d-flex align-items-center fw-bold">
            <fmt:message key="label.category"/>
        </div>

        <div class="col-6 d-flex align-items-center fw-bold">
            <fmt:message key="label.problem.statement"/>
        </div>

        <div class="col-2 d-flex align-items-center fw-bold">
            <fmt:message key="btn.status"/>
        </div>

        <div class="col-2 d-flex justify-content-center align-items-center fw-bold">
            <fmt:message key="btn.action"/>
        </div>
    </div>

    <div class="row ms-3 me-5 py-2">
        <div class="col-2 d-flex align-items-center fw-bold">
            <form class="my-auto w-100" method="get">
                <select class="form-control form-control-sm"
                        name="category"
                        onchange="this.form.submit()">

                    <option class="fw-bold" value="0" selected="true" hidden="true" disabled="true">
                        <fmt:message key="filter"/>
                    </option>


                    <c:forEach var="item" items="${categories}">
                        <option class="fw-bold" value="${item}">
                            <c:out value="${item.displayName} "/>
                        </option>
                    </c:forEach>
                </select>
            </form>
        </div>

        <div class="col-6 d-flex align-items-center fw-bold">
            <form class="my-auto w-100" method="get">
                <input class="form-control-sm"
                       name="searchText"
                       placeholder="Search">
            </form>
        </div>

        <div class="col-2 d-flex align-items-center fw-bold">
            <form class="my-auto w-100" method="get">
                <select class="form-control form-control-sm"
                        name="status"
                        onchange="this.form.submit()">

                    <option class="fw-bold" value="0" selected="true" hidden="true" disabled="true">
                        <fmt:message key="filter"/>
                    </option>


                    <c:forEach var="item" items="${statuses}">
                        <option class="fw-bold" value="${item}">
                            <c:out value="${item.displayName} "/>
                        </option>
                    </c:forEach>
                </select>
            </form>
        </div>

        <div class="col-2 d-flex justify-content-center align-items-center fw-bold">

        </div>
    </div>

    <c:if test="${not empty issues}">
        <c:forEach var="item" items="${issues}">
            <div class="row mx-4 mb-3 shadow-lg p-5 border rounded-2 item-row-color">
                <div class="col-sm-2 d-flex align-items-center fw-bold">
                    <c:out value="${item.category}"/>
                </div>

                <div class="col-sm-6 d-flex align-items-center">
                    <c:out value="${item.problemStatement}"/>
                </div>

                <div class="col-sm-2 d-flex align-items-center">
                    <c:if test="${'RESPONDED' eq item.status}">
                        <div class="bg-success text-light fw-bold border rounded px-2 py-1">
                            <c:out value="${item.status.displayName}"/>
                        </div>
                    </c:if>

                    <c:if test="${'PENDING' eq item.status}">
                        <div class="bg-warning fw-bold border rounded px-2 py-1">
                            <c:out value="${item.status.displayName}"/>
                        </div>
                    </c:if>
                </div>

                <div class="col-sm-2 d-flex justify-content-center align-items-center">
                    <form class="m-auto">
                        <button formaction="${editUrl}"
                                name="issueId"
                                value="${item.id}"
                                class="btn btn-outline-secondary fw-bold">
                            <c:choose>
                                <c:when test="${'PATIENT' eq loggedUser.role}">
                                    <fmt:message key="btn.edit"/>
                                </c:when>
                                <c:when test="${'DOCTOR' eq loggedUser.role}">
                                    <fmt:message key="btn.respond"/>
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
