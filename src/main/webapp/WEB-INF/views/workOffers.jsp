<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:attribute name="title">
      WorkOffers
    </jsp:attribute>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <ul>
            <c:if test="${not empty workOffers}">
                <c:forEach var="workOffer" items="${workOffers}">
                    <li>
                        <a href="/api/workOffers/${workOffer.getId()}">${workOffer.getId()}</a>: ${fn:escapeXml(workOffer.getCountry())}
                        <a href="/api/workOffers/${workOffer.getId()}/form">Edit workOffer</a>
                    </li>
                </c:forEach>
            </c:if>
        </ul>
        <a href="/api/workOffers/form">New workOffer</a>
    </jsp:body>
</t:layout>
