<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:attribute name="title">
      Company
    </jsp:attribute>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <h3>Company:</h3>
        <p>${fn:escapeXml(company.getName())}</p>
        <p>${fn:escapeXml(company.getHeadquarters())}</p>
        <p>${fn:escapeXml(company.getWorkPlace())}</p>
        <p>${fn:escapeXml(company.getFoundationDate())}</p>
        <p>${fn:escapeXml(company.getPhoneNumber())}</p>
        <a href="/api/companies">Return to list</a>
    </jsp:body>
</t:layout>
