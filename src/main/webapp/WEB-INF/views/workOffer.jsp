<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:attribute name="title">
      WorkOffer
    </jsp:attribute>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
    <h3>WorkOffer:</h3>
        <p>${fn:escapeXml(workOffer.getMinNumWeeks())}</p>
        <p>${fn:escapeXml(workOffer.getMaxNumWeeks())}</p>
        <p>${fn:escapeXml(workOffer.getCountry())}</p>
    <a href="/api/workOffers">Return to list</a>
    </jsp:body>
</t:layout>
