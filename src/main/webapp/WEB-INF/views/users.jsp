<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:attribute name="title">
      Users
    </jsp:attribute>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <ul>
            <c:if test="${not empty users}">
                <c:forEach var="user" items="${users}">
                    <li>
                        <a href="/api/users/${fn:escapeXml(user.getEncodedUsername())}">${fn:escapeXml(user.getUsername())}</a>: ${fn:escapeXml(user.getName())}
                        <a href="/api/users/${fn:escapeXml(user.getEncodedUsername())}/form">Edit user</a>
                    </li>
                </c:forEach>
            </c:if>
        </ul>
        <a href="/api/users/form">New user</a>
    </jsp:body>
</t:layout>
