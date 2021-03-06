<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:attribute name="title">
      Works
    </jsp:attribute>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <ul>
            <c:if test="${not empty works}">
                <c:forEach var="work" items="${works}">
                    <li>
                        <a href="/api/works/${work.getId()}">${work.getId()}</a>: ${fn:escapeXml(work.getName())} <a
                            href="/api/works/${work.getId()}/form">Edit work</a>
                    </li>
                </c:forEach>
            </c:if>
        </ul>
        <a href="/api/works/form">New work</a>
    </jsp:body>
</t:layout>
