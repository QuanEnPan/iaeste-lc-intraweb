<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:attribute name="title">
      User From
    </jsp:attribute>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <c:choose>
            <c:when test="${user.getUsername()!=null}">
                <h3>Update User</h3>
                <c:set var="method" value="PUT"/>
                <c:set var="action" value="/api/users/${user.getUsername()}"/>
            </c:when>
            <c:otherwise>
                <h3>Create User</h3>
                <c:set var="method" value="POST"/>
                <c:set var="action" value="/api/users"/>
            </c:otherwise>
        </c:choose>
        <form:form method="${method}" action="${action}" modelAttribute="user">
            <table>
                <tr>
                    <td><form:hidden path="username" /></td>
                </tr>
                <tr>
                    <td><form:label path="birthdate">Birthday</form:label></td>
                    <td><form:input path="birthdate"/> <i><form:errors path="birthdate"></form:errors></i></td>
                </tr>
                <tr>
                    <td><form:label path="email">Email</form:label></td>
                    <td><form:input path="email"/> <i><form:errors path="email"></form:errors></i></td>
                </tr>
                <tr>
                    <td><form:label path="surname">Surname</form:label></td>
                    <td><form:input path="surname"/> <i><form:errors path="surname"></form:errors></i></td>
                </tr>
                <tr>
                    <td><form:label path="join_date">join_date</form:label></td>
                    <td><form:input path="join_date"/> <i><form:errors path="join_date"></form:errors></i></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Submit"/></td>
                </tr>
            </table>
        </form:form>
    </jsp:body>
</t:layout>
