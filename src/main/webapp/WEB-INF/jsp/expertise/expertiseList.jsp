<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>
        <fmt:message key="title.expertise.list"/>
    </title>
</head>
<body>
<c:url var="editUrl" value="/expertise"/>

<div class="container m-auto">
    <div class="row bg-dark text-light m-4 shadow-lg ps-5 pe-5 py-2 border rounded-2">
        <div class="col-2 d-flex align-items-center fw-bold">
            <fmt:message key="label.category"/>
        </div>

        <div class="col-8 d-flex align-items-center fw-bold">
            <fmt:message key="label.details"/>
        </div>

        <div class="col-2 d-flex justify-content-center align-items-center fw-bold">
            <fmt:message key="btn.action"/>
        </div>
    </div>

    <c:if test="${not empty expertiseList}">
        <c:forEach var="item" items="${expertiseList}">
            <div class="row m-4 shadow-lg p-5 border rounded-2 item-row-color">
                <div class="col-2 d-flex align-items-center fw-bold">
                    <c:out value="${item.category}"/>
                </div>

                <div class="col-8 d-flex align-items-center">
                    <c:out value="${item.details}"/>
                </div>

                <div class="col-2 d-flex justify-content-center align-items-center">
                    <form class="m-auto">
                        <button formaction="${editUrl}"
                                name="expertiseId"
                                value="${item.id}"
                                class="btn btn-outline-secondary fw-bold">
                            <fmt:message key="btn.edit"/>
                        </button>
                    </form>
                </div>
            </div>
        </c:forEach>
    </c:if>
</div>
</body>
</html>
