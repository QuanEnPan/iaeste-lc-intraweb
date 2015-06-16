<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:attribute name="title">
      Companies
    </jsp:attribute>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <ul>
            <c:if test="${not empty companies}">
                <c:forEach var="company" items="${companies}">
                    <li>
                        <a href="/api/companies/${company.getId()}">${company.getId()}</a>: ${fn:escapeXml(company.getName())} <a href="/api/companies/${company.getId()}/form">Edit company</a>
                    </li>
                </c:forEach>
            </c:if>
        </ul>
        <a href="/api/companies/form">New company</a>
    </jsp:body>
</t:layout>
