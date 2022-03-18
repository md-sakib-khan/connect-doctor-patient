<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>
        <fmt:message key="title.expertise"/>
    </title>
</head>
<body>
<c:set var="action" value="${expertise.isNew() ? 'SAVE' : 'UPDATE'}"/>

<div class="container">
    <div class="card medical-history-form-section">
        <div class="row">
            <div class="h1 text-center mb-5">
                <fmt:message key="text.expertise"/>
            </div>
        </div>

        <form:form method="post" modelAttribute="expertise">
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

            <div class="row w-75 m-auto d-flex justify-content-center text-start">
                <form:label cssClass="custom-form-label" path="details">
                    <fmt:message key="label.details"/>
                    <strong class="text-danger">*</strong>
                </form:label>
                <form:textarea path="details"
                               placeholder="Tell Something About your Expertise"
                               rows="8"
                               cssClass="form-control form-control-lg bg-transparent border-dark"/>
                <div class="error-section">
                    <form:errors path="details" cssClass="error-message"/>
                </div>
            </div>

            <div class="row w-75 m-auto d-flex justify-content-center">
                <form:label cssClass="custom-form-label" path="yearsOfExpertise">
                    <fmt:message key="label.expertise.years"/>
                    <strong class="text-danger">*</strong>
                </form:label>
                <form:input path="yearsOfExpertise"
                            cssClass="form-control form-control-lg bg-transparent border-dark"/>
                <div class="error-section">
                    <form:errors path="yearsOfExpertise" cssClass="error-message"/>
                </div>
            </div>

            <div class="row w-25 m-auto d-flex justify-content-center">
                <form:button class="btn btn-lg btn-outline-success"
                             name="action"
                             value="${action}">
                    <fmt:message key="btn.action.${action}"/>
                </form:button>

                <c:if test="${not expertise.isNew()}">
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