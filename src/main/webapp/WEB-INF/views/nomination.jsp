<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:attribute name="title">
      Nomination
    </jsp:attribute>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <h3>Nomination:</h3>

        <p>${fn:escapeXml(nomination.getNominated())}</p>

        <p>${fn:escapeXml(nomination.getWorkOffer())}</p>

        <p>${fn:escapeXml(nomination.getOfficerDelegate())}</p>
        <a href="/api/nominations">Return to list</a>
    </jsp:body>
</t:layout>
