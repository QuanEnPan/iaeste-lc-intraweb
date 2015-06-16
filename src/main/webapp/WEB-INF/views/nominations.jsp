<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:attribute name="title">
      Nominations
    </jsp:attribute>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <c:if test="${not empty nominations}">
            <c:forEach var="nomination" items="${nominations}">
                <li>
                    <a href="/api/nominations/${nomination.getId()}">${nomination.getId()}</a>: ${fn:escapeXml(nomination.getName())}
                    <a href="/api/nominations/${nomination.getId()}/form">Edit nomination</a>
                </li>
            </c:forEach>
        </c:if>
        </ul>
        <a href="/api/nominations/form">New nomination</a>
    </jsp:body>
</t:layout>
