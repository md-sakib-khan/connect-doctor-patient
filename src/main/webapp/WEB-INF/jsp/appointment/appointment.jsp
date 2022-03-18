<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>
        <fmt:message key="title.medical.history"/>
    </title>
</head>
<body>
<c:set var="loggedUser" value="${sessionScope['loggedUser']}"/>
<c:set var="action" value="${appointment.isNew() ? 'SAVE' : 'UPDATE'}"/>

<div class="container">
    <div class="card medical-history-form-section">
        <div class="row">
            <div class="h1 text-center mb-5">
                <fmt:message key="text.appointment"/>
            </div>
        </div>

        <form:form method="post" modelAttribute="appointment">

            <div class="row w-75 m-auto d-flex justify-content-center">
                <form:label cssClass="custom-form-label" path="date">
                    <fmt:message key="label.date"/>
                    <strong class="text-danger">*</strong>
                </form:label>
                <form:input path="date"
                            type="text"
                            readonly="${'PATIENT' eq loggedUser.role}"
                            onfocus="(this.type='date')"
                            placeholder="Date"
                            cssClass="form-control form-control-lg bg-transparent border-dark"/>
                <div class="error-section">
                    <form:errors path="date" cssClass="error-message"/>
                </div>
            </div>

            <div class="row w-75 m-auto d-flex justify-content-center text-start">
                <form:label cssClass="custom-form-label" path="details">
                    <fmt:message key="label.details"/>
                    <strong class="text-danger">*</strong>
                </form:label>
                <form:textarea path="details"
                               readonly="${'PATIENT' eq loggedUser.role}"
                               placeholder="Enter Details"
                               rows="8"
                               cssClass="form-control form-control-lg bg-transparent border-dark"/>
                <div class="error-section">
                    <form:errors path="details" cssClass="error-message"/>
                </div>
            </div>

            <div class="row w-25 m-auto d-flex justify-content-center">
                <c:if test="${'DOCTOR' eq loggedUser.role}">
                    <form:button class="btn btn-lg btn-outline-success"
                                 name="action"
                                 value="${action}">
                        <fmt:message key="btn.action.${action}"/>
                    </form:button>
                </c:if>

                <c:if test="${not appointment.isNew()}">
                    <form:button class="btn btn-lg btn-danger fw-bold mt-3"
                                 name="action"
                                 value="DELETE">
                        <fmt:message key="btn.action.delete"/>
                    </form:button>
                </c:if>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>