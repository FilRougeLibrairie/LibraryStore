<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%-- header--%>
<%@include file="includes/header.jsp" %>

<%-- navigator --%>
<%@include file="includes/navigator.jsp" %>

<%-- body --%>

<c:url value="ControllerMain?section=carousel-event" var="url01" />
<c:import url="${url01}" />


<%-- footer --%>
<%@include file="includes/footer.jsp" %>





