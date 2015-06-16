<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="title" fragment="true" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<html>
<head>
    <meta name="google-site-verification" content="xxxxxxxxx" />
    <title><jsp:invoke fragment="title"/></title>
    <meta name="description" content="IAESTE LC Intraweb">
    <c:url var="cssURL" value="/static/css/bootstrap.min.css"/>
    <link href="${cssURL}" rel="stylesheet" type="text/css"/>
    <link rel="icon" href="<c:url value='/static/img/favicon.png'/>" type="image/x-icon" />
    <meta charset="utf-8">
</head>
<body>
<div id="pageheader">
    <jsp:invoke fragment="header"/>
</div>
<div id="body">
    <jsp:doBody/>
</div>
<div id="pagefooter">
    <jsp:invoke fragment="footer"/>
</div>
</body>
</html>