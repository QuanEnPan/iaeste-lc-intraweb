<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
        <c:choose>
            <c:when test="${company.getId()>=0}">
                <h3>Update Company</h3>
                <c:set var="method" value="PUT"/>
                <c:set var="action" value="/api/companies/${company.getId()}"/>
                <c:set var="exist" value="${true}"/>
            </c:when>
            <c:otherwise>
                <h3>Create Company</h3>
                <c:set var="method" value="POST"/>
                <c:set var="action" value="/api/companies"/>
                <c:set var="exist" value="${false}"/>
            </c:otherwise>
        </c:choose>
        <form:form method="${method}" action="${action}" modelAttribute="company">
            <table>
                <tr>
                    <td><form:label path="name">Name</form:label></td>
                    <td><form:input path="name"/> <i><form:errors path="name"></form:errors></i></td>
                </tr>
                <tr>
                    <td><form:label path="headquarters">Headquarters</form:label></td>
                    <td><form:input path="headquarters"/> <i><form:errors path="headquarters"></form:errors></i></td>
                </tr>
                <tr>
                    <td><form:label path="workPlace">Work Place</form:label></td>
                    <td><form:input path="workPlace"/> <i><form:errors path="workPlace"></form:errors></i></td>
                </tr>
                <tr>
                    <td><form:label path="foundationDate">Foundation Date</form:label></td>
                    <td><form:input path="foundationDate"/> <i><form:errors path="foundationDate"></form:errors></i>
                    </td>
                </tr>
                <tr>
                    <td><form:label path="phoneNumber">Phone Number</form:label></td>
                    <td><form:input path="phoneNumber"/> <i><form:errors path="phoneNumber"></form:errors></i></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Submit"/></td>
                </tr>
            </table>
        </form:form>

        <c:if test="${!exist}">
            <form:form method="GET" action="/api/companies/results" modelAttribute="company">
                <table>
                    <tr>
                        <td><form:label path="name">Search company by name</form:label></td>
                        <td><form:input path="name"/> <i><form:errors path="name"></form:errors></i></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Submit"/></td>
                    </tr>
                </table>
            </form:form>
        </c:if>
    </jsp:body>
</t:layout>