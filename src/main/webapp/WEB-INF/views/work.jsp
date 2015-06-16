<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:attribute name="title">
      Work
    </jsp:attribute>
    <jsp:attribute name="header">
      <h3>Work:</h3>
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <p>${fn:escapeXml(work.getPoints())}</p>

        <p>${fn:escapeXml(work.getWorker())}</p>

        <p>${fn:escapeXml(work.isFinished())}</p>
        <a href="/api/works">Return to list</a>
    </jsp:body>
</t:layout>
