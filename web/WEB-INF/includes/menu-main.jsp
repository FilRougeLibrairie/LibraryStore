<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<p>
    <c:url value="ControllerMain" var="url01" />
    <c:url value="ControllerMain?section=all-book" var="url02" />
    <a href="${url01}">accueil</a> |
    <a href="${url02}">Livre</a>
</p>
<hr />