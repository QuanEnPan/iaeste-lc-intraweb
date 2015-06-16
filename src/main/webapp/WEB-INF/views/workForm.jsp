<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:attribute name="title">
      Work Form
    </jsp:attribute>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <c:choose>
            <c:when test="${work.getId()>=0}">
                <h3>Update Work</h3>
                <c:set var="method" value="PUT"/>
                <c:set var="action" value="/api/works/${work.getId()}"/>
            </c:when>
            <c:otherwise>
                <h3>Create Work</h3>
                <c:set var="method" value="POST"/>
                <c:set var="action" value="/api/works"/>
            </c:otherwise>
        </c:choose>
        <form:form method="${method}" action="${action}" modelAttribute="work">
            <table>
                <tr>
                    <td><form:label path="points">Points </form:label></td>
                    <td><form:input path="points"/> <i><form:errors path="points"></form:errors></i></td>
                </tr>
                <tr>
                    <td><form:label path="worker">Worker</form:label></td>
                    <td><form:input path="worker"/> <i><form:errors path="worker"></form:errors></i></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Submit"/></td>
                </tr>
            </table>
        </form:form>
    </jsp:body>
</t:layout>
