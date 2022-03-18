<%@ page contentType="text/html; ISO-8859-1" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>
        <fmt:message key="title.patient"/>
    </title>
</head>
<body>
<c:set var="action" value="${patient.isNew() ? 'SAVE' : 'UPDATE'}"/>

<div class="container">
    <div class="card user-form-section">
        <div class="row">
            <div class="h1 text-center mb-5">
                <fmt:message key="text.patient"/>
            </div>
        </div>

        <form:form method="post" modelAttribute="patient">
            <div class="row w-75 m-auto d-flex justify-content-center">
                <form:label cssClass="custom-form-label" path="firstName">
                    <fmt:message key="label.name.first"/>
                    <strong class="text-danger">*</strong>
                </form:label>
                <form:input path="firstName"
                            placeholder="Enter First Name"
                            cssClass="form-control form-control-lg bg-transparent border-dark"/>
                <div class="error-section">
                    <form:errors path="firstName" cssClass="error-message"/>
                </div>
            </div>

            <div class="row w-75 m-auto d-flex justify-content-center">
                <form:label cssClass="custom-form-label" path="firstName">
                    <fmt:message key="label.name.last"/>
                    <strong class="text-danger">*</strong>
                </form:label>
                <form:input path="lastName"
                            placeholder="Enter Last Name"
                            cssClass="form-control form-control-lg bg-transparent border-dark"/>
                <div class="error-section">
                    <form:errors path="lastName" cssClass="error-message"/>
                </div>
            </div>

            <div class="row w-75 m-auto d-flex justify-content-center text-start">
                <form:label cssClass="custom-form-label" path="gender">
                    <fmt:message key="label.gender"/>
                    <strong class="text-danger">*</strong>
                </form:label>
                <div class="border border-dark rounded p-2">
                    <form:radiobuttons path="gender"
                                       items="${genders}"
                                       itemLabel="displayName"
                                       cssClass="ms-4 me-2"/>
                </div>
                <div class="error-section">
                    <form:errors path="gender" cssClass="error-message"/>
                </div>
            </div>

            <div class="row w-75 m-auto d-flex justify-content-center">
                <form:label cssClass="custom-form-label" path="phoneNo">
                    <fmt:message key="label.phone"/>
                    <strong class="text-danger">*</strong>
                </form:label>
                <form:input path="phoneNo"
                            placeholder="Enter Phone Number"
                            cssClass="form-control form-control-lg bg-transparent border-dark"/>
                <div class="error-section">
                    <form:errors path="phoneNo" cssClass="error-message"/>
                </div>
            </div>

            <div class="row w-75 m-auto d-flex justify-content-center">
                <form:label cssClass="custom-form-label" path="dateOfBirth">
                    <fmt:message key="label.dateOfBirth"/>
                    <strong class="text-danger">*</strong>
                </form:label>
                <form:input path="dateOfBirth"
                            type="text"
                            onfocus="(this.type='date')"
                            placeholder="Date Of Birth"
                            max="${LocalDate.now()}"
                            cssClass="form-control form-control-lg bg-transparent border-dark"/>
                <div class="error-section">
                    <form:errors path="dateOfBirth" cssClass="error-message"/>
                </div>
            </div>

            <div class="row w-75 m-auto d-flex justify-content-center">
                <form:label cssClass="custom-form-label" path="email">
                    <fmt:message key="label.email"/>
                    <strong class="text-danger">*</strong>
                </form:label>
                <form:input path="email"
                            placeholder="Enter Email"
                            cssClass="form-control form-control-lg bg-transparent border-dark"/>
                <div class="error-section">
                    <form:errors path="email" cssClass="error-message"/>
                </div>
            </div>

            <div class="row w-75 m-auto d-flex justify-content-center">
                <form:label cssClass="custom-form-label" path="password">
                    <fmt:message key="label.password"/>
                    <strong class="text-danger">*</strong>
                </form:label>
                <form:input path="password"
                            placeholder="Enter Password"
                            type="password"
                            cssClass="form-control form-control-lg bg-transparent border-dark"/>
                <div class="error-section">
                    <form:errors path="password" cssClass="error-message"/>
                </div>
            </div>

            <div class="row w-25 m-auto d-flex justify-content-center">
                <form:button class="btn btn-lg btn-outline-success"
                             name="action"
                             value="${action}">
                    <fmt:message key="btn.action.${action}"/>
                </form:button>

                <c:if test="${not patient.isNew()}">
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