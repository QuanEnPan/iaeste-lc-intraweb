<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:attribute name="title">
      IAESTE LC Intraweb Login
    </jsp:attribute>
    <jsp:attribute name="header">
        <h1>GreetingsApp Login</h1>
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>

        <sec:authorize access="isAnonymous()">
            <!-- Social Sign In Buttons -->
            <form action="<c:url value="/api/connect/twitter"/>" method="POST">
                <p><input type="submit" value="Connect with Twitter"/></p>
            </form>
            <form action="<c:url value="/api/connect/facebook"/>" method="POST">
                <p><input type="submit" value="Connect with Facebook"/></p>
            </form>
        </sec:authorize>

        <sec:authorize access="isAuthenticated()">
            <p>User already authenticated</p>
        </sec:authorize>
    </jsp:body>
</t:layout>