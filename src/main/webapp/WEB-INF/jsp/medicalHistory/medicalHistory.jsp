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
<c:set var="action" value="${medicalHistory.isNew() ? 'SAVE' : 'UPDATE'}"/>

<div class="container">
    <div class="card medical-history-form-section">
        <div class="row">
            <div class="h1 text-center mb-5">
                <fmt:message key="text.medical.history"/>
            </div>
        </div>

        <form:form method="post" modelAttribute="medicalHistory">
            <div class="row w-75 m-auto d-flex justify-content-center">
                <form:label cssClass="custom-form-label" path="category">
                    <fmt:message key="label.category"/>
                    <strong class="text-danger">*</strong>
                </form:label>
                <div class="border border-dark rounded p-2">
                    <form:radiobuttons path="category"
                                       items="${categories}"
                                       cssClass="ms-4 me-2"/>
                </div>
                <div class="error-section">
                    <form:errors path="category" cssClass="error-message"/>
                </div>
            </div>

            <div class="row w-75 m-auto d-flex justify-content-center text-start">
                <form:label cssClass="custom-form-label" path="problemStatement">
                    <fmt:message key="label.problem.statement"/>
                    <strong class="text-danger">*</strong>
                </form:label>
                <form:textarea path="problemStatement"
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
                            onfocus="(this.type='date')"
                            placeholder="Start Date"
                            max="${LocalDate.now()}"
                            cssClass="form-control form-control-lg bg-transparent border-dark"/>
                <div class="error-section">
                    <form:errors path="startDate" cssClass="error-message"/>
                </div>
            </div>

            <div class="row w-75 m-auto d-flex justify-content-center">
                <form:label cssClass="custom-form-label" path="endDate">
                    <fmt:message key="label.date.end"/>
                    <strong class="text-danger">*</strong>
                </form:label>
                <form:input path="endDate"
                            type="text"
                            onfocus="(this.type='date')"
                            placeholder="End Date"
                            max="${LocalDate.now()}"
                            cssClass="form-control form-control-lg bg-transparent border-dark"/>
                <div class="error-section">
                    <form:errors path="endDate" cssClass="error-message"/>
                </div>
            </div>

            <div class="row w-75 m-auto d-flex justify-content-center text-start">
                <form:label cssClass="custom-form-label" path="doctorName">
                    <fmt:message key="label.doctor.name"/>
                </form:label>
                <form:input path="doctorName"
                            placeholder="Enter Doctor's Name"
                            cssClass="form-control form-control-lg bg-transparent border-dark"/>
                <div class="error-section">
                    <form:errors path="doctorName" cssClass="error-message"/>
                </div>
            </div>

            <div class="row w-75 m-auto d-flex justify-content-center text-start">
                <form:label cssClass="custom-form-label" path="doctorPrescription">
                    <fmt:message key="label.doctor.prescription"/>
                </form:label>
                <form:textarea path="doctorPrescription"
                               placeholder="Enter Doctor's Prescription"
                               rows="8"
                               cssClass="form-control form-control-lg bg-transparent border-dark"/>
                <div class="error-section">
                    <form:errors path="doctorPrescription" cssClass="error-message"/>
                </div>
            </div>

            <div class="row w-75 m-auto d-flex justify-content-center text-start">
                <form:label cssClass="custom-form-label" path="doctorComment">
                    <fmt:message key="label.doctor.comment"/>
                </form:label>
                <form:textarea path="doctorComment"
                               placeholder="Enter Doctor's Comment"
                               rows="8"
                               cssClass="form-control form-control-lg bg-transparent border-dark"/>
                <div class="error-section">
                    <form:errors path="doctorComment" cssClass="error-message"/>
                </div>
            </div>

            <c:if test="${'PATIENT' eq loggedUser.role}">
                <div class="row w-25 m-auto d-flex justify-content-center">
                    <form:button class="btn btn-lg btn-outline-success"
                                 name="action"
                                 value="${action}">
                        <fmt:message key="btn.action.${action}"/>
                    </form:button>

                    <c:if test="${not medicalHistory.isNew()}">
                        <form:button class="btn btn-lg btn-danger fw-bold mt-3"
                                     name="action"
                                     value="DELETE">
                            <fmt:message key="btn.action.delete"/>
                        </form:button>
                    </c:if>
                </div>
            </c:if>
        </form:form>
    </div>
</div>
</body>
</html>