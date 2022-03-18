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
<c:url var="appointmentUrl" value="/appointment"/>
<c:url var="medicalHistoryListUrl" value="/medicalHistory/list"/>
<c:set var="loggedUser" value="${sessionScope['loggedUser']}"/>
<c:set var="action" value="${issue.isNew() ? 'SAVE' : 'UPDATE'}"/>
<c:set var="responseFieldCondition" value="${('DOCTOR' eq loggedUser.role && not issue.isNew())
                                            or ('PATIENT' eq loggedUser.role && not empty issue.doctorResponse)}"/>

<div class="container">
    <div class="card medical-history-form-section">
        <div class="row">
            <div class="h1 text-center mb-5">
                <fmt:message key="text.issue"/>
            </div>
        </div>

        <form:form method="post" modelAttribute="issue">
            <c:if test="${'PATIENT' eq loggedUser.role}">
                <div class="row w-75 m-auto d-flex justify-content-center">
                    <form:label cssClass="custom-form-label" path="category">
                        <fmt:message key="label.category"/>
                        <strong class="text-danger">*</strong>
                    </form:label>
                    <div class="border border-dark rounded p-2">
                        <form:radiobuttons path="category"
                                           items="${categories}"
                                           itemLabel="displayName"
                                           cssClass="ms-4 me-2"/>
                    </div>
                    <div class="error-section">
                        <form:errors path="category" cssClass="error-message"/>
                    </div>
                </div>
            </c:if>

            <div class="row w-75 m-auto d-flex justify-content-center text-start">
                <form:label cssClass="custom-form-label" path="problemStatement">
                    <fmt:message key="label.problem.statement"/>
                    <strong class="text-danger">*</strong>
                </form:label>
                <form:textarea path="problemStatement"
                               readonly="${'DOCTOR' eq loggedUser.role}"
                               placeholder="Type your problem in Brief"
                               rows="8"
                               cssClass="form-control form-control-lg bg-transparent border-dark"/>
                <div class="error-section">
                    <form:errors path="problemStatement" cssClass="error-message"/>
                </div>
            </div>

            <div class="row w-75 m-auto d-flex justify-content-center">
                <form:label cssClass="custom-form-label" path="startDate">
                    <fmt:message key="label.date.start"/>
                    <strong class="text-danger">*</strong>
                </form:label>
                <form:input path="startDate"
                            type="text"
                            readonly="${'DOCTOR' eq loggedUser.role}"
                            onfocus="(this.type='date')"
                            placeholder="Start Date"
                            max="${LocalDate.now()}"
                            cssClass="form-control form-control-lg bg-transparent border-dark"/>
                <div class="error-section">
                    <form:errors path="startDate" cssClass="error-message"/>
                </div>
            </div>

            <c:if test="${responseFieldCondition}">
                <div class="row w-75 m-auto d-flex justify-content-center text-start">
                    <form:label cssClass="custom-form-label" path="doctorResponse">
                        <fmt:message key="label.doctor.response"/>
                    </form:label>
                    <form:textarea path="doctorResponse"
                                   readonly="${'PATIENT' eq loggedUser.role}"
                                   placeholder="Enter Response"
                                   rows="8"
                                   cssClass="form-control form-control-lg bg-transparent border-dark"/>
                    <div class="error-section">
                        <form:errors path="doctorResponse" cssClass="error-message"/>
                    </div>
                </div>
            </c:if>

            <div class="row w-25 m-auto d-flex justify-content-center">
                <form:button class="btn btn-lg btn-outline-success"
                             name="action"
                             value="${action}">
                    <fmt:message key="btn.action.${action}"/>
                </form:button>

                <c:if test="${not issue.isNew() and 'PATIENT' eq loggedUser.role}">
                    <form:button class="btn btn-lg btn-danger fw-bold mt-3"
                                 name="action"
                                 value="DELETE">
                        <fmt:message key="btn.action.delete"/>
                    </form:button>
                </c:if>
            </div>
        </form:form>

        <c:if test="${not issue.isNew() and 'DOCTOR' eq loggedUser.role}">
            <div class="w-25 m-auto d-flex justify-content-center">
                <form class="w-100" method="get">
                    <button class="btn btn-lg btn-secondary fw-bold mt-3 w-100"
                            formAction="${medicalHistoryListUrl}"
                            name="patientId"
                            value="${issue.patient.id}">
                        <fmt:message key="btn.medicalHistoryList"/>
                    </button>
                </form>
            </div>

            <div class="w-25 m-auto d-flex justify-content-center">
                <form class="w-100" method="get">
                    <button class="btn btn-lg btn-success fw-bold mt-3 w-100"
                            formAction="${appointmentUrl}"
                            name="issueId"
                            value="${issue.id}">
                        <fmt:message key="btn.appointment"/>
                    </button>
                </form>
            </div>
        </c:if>
    </div>
</div>
</body>
</html>