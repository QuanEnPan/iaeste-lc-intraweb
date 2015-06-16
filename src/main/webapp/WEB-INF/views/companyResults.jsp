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
        <c:choose>
            <c:when test="${not empty results}">
                <ul>
                    <c:forEach var="company" items="${results}">
                        <li>
                            <form id="company" action="/api/companies" method="POST">
                                <table>
                                    <tr>
                                        <td><label for="name">Name</label></td>
                                        <td><input id="name" name="name" type="text"
                                                   value="${fn:escapeXml(company.getName())}" readonly/>
                                            <i><form:errors path="name"></form:errors></td>
                                    </tr>
                                    <tr>
                                        <td><label for="headquarters">Headquarters</label></td>
                                        <td><input id="headquarters" name="headquarters" type="text"
                                                   value="${fn:escapeXml(company.getHeadquarters())}" readonly/>
                                            <i><form:errors path="headquarters"></form:errors></td>
                                    </tr>
                                    <tr>
                                        <td><label for="workPlace">Work Place</label></td>
                                        <td><input id="workPlace" name="workPlace" type="text"
                                                   value="${fn:escapeXml(company.getWorkPlace())}" readonly/>
                                            <i><form:errors path="workPlace"></form:errors></td>
                                    </tr>
                                    <tr>
                                        <td><label for="foundationDate">Foundation Date</label></td>
                                        <td><input id="foundationDate" name="foundationDate" type="text"
                                                   value="${fn:escapeXml(company.getFoundationDate())}" readonly/>
                                            <i><form:errors path="foundationDate"></form:errors></td>
                                    </tr>
                                    <tr>
                                        <td><label for="phoneNumber">Phone Number</label></td>
                                        <td><input id="phoneNumber" name="phoneNumber" type="text"
                                                   value="${fn:escapeXml(company.getPhoneNumber())}" readonly/>
                                            <i><form:errors path="phoneNumber"></form:errors></td>
                                    </tr>
                                    <tr>
                                        <td><input type="submit" value="Submit"/></td>
                                    </tr>
                                </table>
                            </form>
                        </li>
                    </c:forEach>
                </ul>
            </c:when>
            <c:otherwise>
                <h1>This company isn't registered in Spain</h1>
            </c:otherwise>
        </c:choose>
        <a href="/api/companies">Return to list</a>
    </jsp:body>
</t:layout>
