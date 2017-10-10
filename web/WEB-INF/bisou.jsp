<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%-- header--%>
<%@include file="includes/header.jsp" %>

<%-- navigator --%>
<%@include file="includes/navigator.jsp" %>





<%-- body --%>  


    <c:url value="ControllerMain?section=menuCatalog" var="url105" />
    <c:import url="${url105}"/>
    <c:forEach items="${keys}" var ="k">
        <li>
        <h1> ${k} </h1>
        </li>
    </c:forEach>
    




<%-- footer --%>
<%@include file="includes/footer.jsp" %>