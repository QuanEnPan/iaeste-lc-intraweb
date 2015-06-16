<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:attribute name="title">
      User
    </jsp:attribute>
    <jsp:attribute name="header">
        <h1>Users</h1>
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <c:if test="${not empty user}">
            <h2>User ${user.getUsername()}</h2>
            <c:if test="${not empty user.getEmail()}">
                <p>E-mail: ${user.getEmail()}</p>
            </c:if>
            <p>${fn:escapeXml(user.getBirthdate())}</p>
        </c:if>
        <a href="/api/users">Return to list</a>
    </jsp:body>
</t:layout>
